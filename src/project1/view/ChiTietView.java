package project1.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ChiTietView extends JDialog{
	private MainUI mainUI;
	private ChiTietInformation chiTietInformation;
	
	private JButton btnPrint = new JButton("IN");
	private JButton btnCancel = new JButton("HỦY");
	private JButton btnPay    = new JButton ("TRẢ XE");
	private JButton btnPayAll  = new JButton("TRẢ TẤT CẢ");
	
	public ChiTietInformation getChiTietInformation() {
		return chiTietInformation;
	}
	public JButton getBtnPrint() {
		return btnPrint;
	}
	public JButton getBtnCancel() {
		return btnCancel;
	}
	public JButton getBtnPay() {
		return btnPay;
	}
	public JButton getBtnPayAll() {
		return btnPayAll;
	}

	public ChiTietView(MainUI mainUI) {
		this.mainUI = mainUI;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("Chi tiết mượn trả");
		
		chiTietInformation =  new ChiTietInformation();
		
		add(createMainPanel());
		pack();
		setLocationRelativeTo(null);
	}
	
	private JPanel createMainPanel() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(chiTietInformation, BorderLayout.CENTER);
		mainPanel.add(createButtonPanel(), BorderLayout.PAGE_END);
		
		return mainPanel;
	}
	
	private JPanel createButtonPanel() {
		JPanel panel = new JPanel (new GridLayout(1, 4, 10, 10));
		panel.setBorder(new EmptyBorder(10,10,10,10));
		
		btnPrint.setIcon(new ImageIcon(this.getClass().getResource("/print-icon.png")));
		btnCancel.setIcon(new ImageIcon(this.getClass().getResource("/cancel-icon.png")));
		
		panel.add(btnPay);
		panel.add(btnPayAll);
		panel.add(btnPrint);
		panel.add(btnCancel);
		return panel;
	}
}
