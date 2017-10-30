package project1.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelAboutUSView extends JPanel{
	public PanelAboutUSView() {
		setLayout(new BorderLayout());
		add(createTitlePanel("Hello chúng tôi là những coder :)))", 1), BorderLayout.PAGE_START);
		add(createMainPanel(), BorderLayout.CENTER);
	}
	private JPanel createTitlePanel(String title, int a) {
		JPanel panel = new JPanel();
		JLabel label = new JLabel(title);
		label.setFont(new Font("Caribli", Font.BOLD, 18));
		if(a == 1) {
			label.setForeground(Color.YELLOW);
			panel.setBackground(new Color(0x009999));
		}else {
			label.setForeground(Color.BLUE);
		}
		
		panel.add(label);
		
		return panel;
	}
	private JPanel createMainPanel() {
		JPanel panel = new JPanel(new GridLayout(2, 1));
		panel.add(createPanelInfo());
		panel.add(new JPanel());
		
		return panel;
	}
	private JPanel createPanelInfo() {
		JPanel panel = new JPanel(new GridLayout(1, 3, 10, 10));
		panel.add(new JPanel());
		panel.add(new JPanel());
		panel.add(createInfoPerson("Nguyễn Tài Tiêu - CNTT2.1 K60", new ImageIcon(this.getClass().getResource("/tld.png"))));
		return panel;
	}
	
	private JPanel createInfoPerson(String ten, ImageIcon icon) {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(createTitlePanel(ten, 0), BorderLayout.PAGE_START);
		panel.add(createMain(icon));
		return panel;
	}
	private JPanel createMain(ImageIcon icon) {
		JPanel panel = new JPanel();
		JLabel label = new JLabel(icon);
		panel.add(label);
		return panel;
	}
}
