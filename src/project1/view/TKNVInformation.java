package project1.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TKNVInformation extends JPanel{
	private JLabel lbCurrentDate;
	private JLabel lbTitle;
	private TableTKNVView tableTKNVView;
	
	public JLabel getLbCurrentDate() {
		return lbCurrentDate;
	}
	public JLabel getLbTitle() {
		return lbTitle;
	}
	public TableTKNVView getTableTKNVView() {
		return tableTKNVView;
	}
	
	public TKNVInformation() {
		setLayout(new BorderLayout());
		add(createDatePanel (), BorderLayout.PAGE_START);
		add(createTablePanel(), BorderLayout.CENTER);
	}
	
	private JPanel createDatePanel() {
		JPanel datePanel = new JPanel(new GridLayout(1, 2, 10, 10));
		datePanel.add(new JLabel(""));
		lbCurrentDate = new JLabel();
		datePanel.add(lbCurrentDate);
		return datePanel;
	}
	
	private JPanel createTablePanel() {
		JPanel panel = new JPanel(new BorderLayout());
		lbTitle = new JLabel ("THỐNG KÊ");
		tableTKNVView = new TableTKNVView();
		panel.add(lbTitle, BorderLayout.PAGE_START);
		panel.add(tableTKNVView, BorderLayout.CENTER);
		return panel;
	}
}
