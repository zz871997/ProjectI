package project1.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class KhachHangInformation  extends JPanel{
	private JTextField tfIdKH     = new JTextField(30);
	private JTextField tfTenKH    = new JTextField(30);
	private JTextField tfSoCMND   = new JTextField(30);
	private JTextField tfNgaySinh = new JTextField(30);
	private JTextField tfDiaChi   = new JTextField(30);
	private JRadioButton radNam = new JRadioButton("Nam");
	private JRadioButton radNu = new JRadioButton("Nữ");
	private ButtonGroup bg = new ButtonGroup();
	private JTextField tfSDT      = new JTextField(30);
	private JTextField tfEmail    = new JTextField(30);
	
	public KhachHangInformation() {
		setLayout(new BorderLayout());
		setBorder(new TitledBorder("Thông tin"));
		bg.add(radNam);
		bg.add(radNu);
		add(createPanelLabel(), BorderLayout.WEST);
		add(createPanelTF(), BorderLayout.CENTER);
	}

	public JTextField getTfIdKH() {
		return tfIdKH;
	}
	public JTextField getTfTenKH() {
		return tfTenKH;
	}
	public JTextField getTfSoCMND() {
		return tfSoCMND;
	}
	public JTextField getTfNgaySinh() {
		return tfNgaySinh;
	}
	public JTextField getTfDiaChi() {
		return tfDiaChi;
	}
	
	public JRadioButton getRadNam() {
		return radNam;
	}

	public JRadioButton getRadNu() {
		return radNu;
	}

	public ButtonGroup getBg() {
		return bg;
	}

	public JTextField getTfSDT() {
		return tfSDT;
	}
	public JTextField getTfEmail() {
		return tfEmail;
	}
	
	private JPanel createPanelLabel() {
		JPanel panel = new JPanel(new GridLayout(8, 1, 5, 5));
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.add(new JLabel("Mã khách hàng"));
		panel.add(new JLabel("Họ tên khách hàng"));
		panel.add(new JLabel("Số CMND"));
		panel.add(new JLabel("Ngày sinh"));
		panel.add(new JLabel("Địa chỉ"));
		panel.add(new JLabel("Giới tính"));
		panel.add(new JLabel("Số điện thoại"));
		panel.add(new JLabel("Email"));
		return panel;
	}
	
	private JPanel createPanelTF() {
		JPanel panel = new JPanel(new GridLayout(8, 1, 5, 5));
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.add(tfIdKH);
		panel.add(tfTenKH);
		panel.add(tfSoCMND);
		panel.add(tfNgaySinh);
		panel.add(tfDiaChi);
		panel.add(createPanelGT());
		panel.add(tfSDT);
		panel.add(tfEmail);
		
		return panel;
	}
	private JPanel createPanelGT() {
		radNam = new JRadioButton("Nam");
		radNu = new JRadioButton("Nữ");
		bg = new ButtonGroup();
		bg.add(radNam);
		bg.add(radNu);
		JPanel panel = new JPanel();
		((FlowLayout) panel.getLayout()).setAlignment(FlowLayout.LEFT);
		panel.add(radNam, JPanel.LEFT_ALIGNMENT);
		panel.add(radNu, JPanel.LEFT_ALIGNMENT);
		
		return panel;
	}
}
