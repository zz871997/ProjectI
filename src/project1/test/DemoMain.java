package project1.test;

import project1.controller.AddXeController;
import project1.view.MainUI;

public class DemoMain {
	public static void main(String[] args) {
		MainUI mainUI = new MainUI();
		new AddXeController(mainUI);
	}
}
	