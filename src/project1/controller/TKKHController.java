package project1.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import project1.model.KhachHangDAO;
import project1.model.KhachHangDB;
import project1.view.MainUI;
import project1.view.TKKHInformation;
import project1.view.TKKHView;

public class TKKHController {
	private MainUI mainUI;
	private KhachHangDAO khachHangDB;
	
	private TKKHView tkkhView;
	private TKKHInformation tkkhInformation;
	private JButton btnTKKH;
	private JComboBox<String> cbTKKH;
	
	public TKKHController(MainUI mainUI) {
		this.mainUI = mainUI;
		khachHangDB = new KhachHangDB();
		
		btnTKKH = mainUI.getQlKH().getBtnKH().getBtnThongKe();
		cbTKKH  = mainUI.getQlKH().getBtnKH().getThongKeCB();
		
		btnTKKHEvent();	
	}
	
	private void btnTKKHEvent() {
		btnTKKH.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (cbTKKH.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(mainUI, "Hãy chọn 1 kiểu thống kê");
					return;
				}
				else {
					tkkhView = new TKKHView(mainUI);
					tkkhInformation = tkkhView.getTkkhInformation();
					tkkhView.setVisible(true);
					
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
					LocalDate localDate   = LocalDate.now();
					String ngayHT     = Integer.toString(localDate.getDayOfMonth());
					String thangHT    = Integer.toString(localDate.getMonthValue());
					String namHT      = Integer.toString(localDate.getYear());
					String now = ngayHT + "-" + thangHT + "-" + namHT;
					tkkhInformation.getLbCurrentDate().setText(now);
					
					int indexOfCombo = cbTKKH.getSelectedIndex();
					String title = cbTKKH.getSelectedItem().toString();
					tkkhInformation.getLbTitle().setText("THỐNG KÊ KHÁCH HÀNG THEO " + title.toUpperCase());
					
					
					//System.out.println("Index cb: " + indexOfCombo);
					String data[][] = getDataTKKHFromDB(indexOfCombo);

					tkkhInformation.getTableTKKHView().updateTable(data, indexOfCombo);
					//loadDataForTKTable(indexOfCombo);
					
					setActionForThongKeKHView(data);
				}
			}
		});
	}
	
	private String[][] getDataTKKHFromDB(int indexOfCb) {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		if (indexOfCb == 1) {
			result = khachHangDB.thongKeKH("ngaySinh");
		}
		else if (indexOfCb == 2) {
			result = khachHangDB.thongKeKH("diaChi");
			
		}
		else if (indexOfCb == 3) {
			result = khachHangDB.thongKeKH("gioiTinh");
		}
		
		String[][] data = convertToString(result);
		return data;
	}
	
	/* Convert ArrayList<ArrayList<String>> to String[][] */
	private String[][] convertToString (ArrayList<ArrayList<String>> arr) {
		String[][] convertResult = new String[arr.size()][3];
		for (int i = 0; i < arr.size(); i ++) {
			convertResult[i][0] = Integer.toString(i+1);
			convertResult[i][1] = arr.get(i).get(0);
			convertResult[i][2] = arr.get(i).get(1);
		}
		
		return convertResult;
	}
	
	/* Set Action for thongKeSachView - Print & Cancel */
	private void setActionForThongKeKHView(String[][] data) {
		JButton btnPrint  = tkkhView.getBtnPrint();
		JButton btnCancel = tkkhView.getBtnCancel();
		
		btnPrint.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				inThongKe(data);
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				huy();
			}
		});		
	}
	
	/* In Thong ke */
	private void inThongKe(String[][] data) {
		JFileChooser fileChooser = new JFileChooser();
		int select = fileChooser.showSaveDialog(this.tkkhView);
		String saveFilePath = "";
		
		if (select == JFileChooser.APPROVE_OPTION) {
			String path = fileChooser.getCurrentDirectory().toString() 
			       	   + "\\" + fileChooser.getSelectedFile().getName();
			if(path.indexOf(".xlsx") >= 0) {
				saveFilePath = path;
			}
			else {
				saveFilePath = path + ".xlsx";
			}
			
			System.out.println("Save file to: " + saveFilePath);
			saveThongKeExcelTo(saveFilePath, data);
		}
	}
	
	private void saveThongKeExcelTo(String path, String data[][]) {
		try {
			FileOutputStream fos = new FileOutputStream(new File(path));
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("ThongKeXe");
			sheet.setColumnWidth(0, 8400);
			sheet.setColumnWidth(1, 5600);
			sheet.setColumnWidth(2, 8400);
			
			Row row = sheet.createRow(0);
			Cell cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("TRƯỜNG ĐẠI HỌC BÁCH KHOA HÀ NỘI");
			cell.setCellStyle(createStyleDefault(workbook));
			cell = row.createCell(2, CellType.STRING);
			cell.setCellValue("Cộng hòa xã hội chủ nghĩa Việt Nam");
			cell.setCellStyle(createStyleDefault(workbook));
			
			row = sheet.createRow(1);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("Thư viện Tạ Quang Bửu");
			cell.setCellStyle(createStyleDefault(workbook));
			cell = row.createCell(2, CellType.STRING);
			cell.setCellValue("Độc lập - Tự do - Hạnh phúc");
			cell.setCellStyle(createStyleDefault(workbook));
			
			row = sheet.createRow(2);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("Nhóm 14");
			cell.setCellStyle(createStyleDefault(workbook));
			
			LocalDate localDate   = LocalDate.now();
			String ngayHT     = Integer.toString(localDate.getDayOfMonth());
			String thangHT    = Integer.toString(localDate.getMonthValue());
			String namHT      = Integer.toString(localDate.getYear());
			String now = "Ngày " + ngayHT + "-" + thangHT + "-" + namHT;
			row = sheet.createRow(3);
			cell = row.createCell(2, CellType.STRING);
			cell.setCellValue(now);
			cell.setCellStyle(createStyleForDate(workbook));
			
			row = sheet.createRow(5);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue(tkkhInformation.getLbTitle().getText().toString().toUpperCase());
			cell.setCellStyle(createStyleForTitle(workbook));
			sheet.addMergedRegion(new CellRangeAddress(5, 5, 0, 2));
			
			row = sheet.createRow(7);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("STT");
			cell.setCellStyle(createStyleForTableTitle(workbook));
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue(cbTKKH.getSelectedItem().toString().toUpperCase());
			cell.setCellStyle(createStyleForTableTitle(workbook));
			cell = row.createCell(2, CellType.STRING);
			cell.setCellValue("SỐ LƯỢNG");
			cell.setCellStyle(createStyleForTableTitle(workbook));
			
			int rowNum = 8;
			for (int i = 0; i < data.length; i++) {
				row = sheet.createRow(rowNum);
				for (int j = 0; j < 3; j++) {
					cell = row.createCell(j, CellType.STRING);
					cell.setCellValue(data[i][j]);
					cell.setCellStyle(createStyleDataTable(workbook));
				}
				rowNum++;
			}
			
			row = sheet.createRow(rowNum + 2);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("Người lập");
			cell.setCellStyle(createStyleDefault(workbook));
			cell = row.createCell(2, CellType.STRING);
			cell.setCellValue("Chữ kí quản lý");
			cell.setCellStyle(createStyleDefault(workbook));
			
			row = sheet.createRow(rowNum + 3);
			cell = row.createCell(2, CellType.STRING);
			cell.setCellValue("Admin");
			cell.setCellStyle(createStyleDefault(workbook));
			
			workbook.write(fos);
			System.out.println("Create file: " + path);
			fos.close();
		} 
		catch (IOException e) {
			JOptionPane.showMessageDialog(new JDialog(), "Lỗi File");
			e.printStackTrace();
		}
	}
	
	private XSSFCellStyle createStyleForDate (XSSFWorkbook workbook) {
		XSSFCellStyle styleDate = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setItalic(true);
		styleDate.setFont(font);
		styleDate.setAlignment(HorizontalAlignment.CENTER);
		return styleDate;
	}
	
	private XSSFCellStyle createStyleForTitle (XSSFWorkbook workbook) {
		XSSFCellStyle styleTitle = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setColor(IndexedColors.BLUE.index);
		font.setFontHeight(18);
		styleTitle.setFont(font);
		styleTitle.setAlignment(HorizontalAlignment.CENTER);
		return styleTitle;
	}
	
	private XSSFCellStyle createStyleForTableTitle (XSSFWorkbook workbook) {
		XSSFCellStyle styleTableTitle = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(12);
		styleTableTitle.setFont(font);
		styleTableTitle.setAlignment(HorizontalAlignment.CENTER);
		styleTableTitle.setAlignment(HorizontalAlignment.CENTER);
		styleTableTitle.setBorderBottom(BorderStyle.MEDIUM);
		styleTableTitle.setBorderTop(BorderStyle.MEDIUM);
		styleTableTitle.setBorderRight(BorderStyle.MEDIUM);
		styleTableTitle.setBorderLeft(BorderStyle.MEDIUM);
		return styleTableTitle;
	}
	
	private XSSFCellStyle createStyleDefault(XSSFWorkbook workbook) {
		XSSFCellStyle styleDefault = workbook.createCellStyle();
		styleDefault.setAlignment(HorizontalAlignment.CENTER);
		return styleDefault;
	}
	
	private XSSFCellStyle createStyleDataTable(XSSFWorkbook workbook) {
		XSSFCellStyle styleData = workbook.createCellStyle();
		styleData.setAlignment(HorizontalAlignment.CENTER);
		styleData.setBorderBottom(BorderStyle.MEDIUM);
		styleData.setBorderTop(BorderStyle.MEDIUM);
		styleData.setBorderRight(BorderStyle.MEDIUM);
		styleData.setBorderLeft(BorderStyle.MEDIUM);
		return styleData;
	}

	private void huy() {
		this.tkkhView.setVisible(false);
	}
}
