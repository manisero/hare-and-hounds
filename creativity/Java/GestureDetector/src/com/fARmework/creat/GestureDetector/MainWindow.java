package com.fARmework.creat.GestureDetector;

import java.io.*;
import javax.swing.*;

public class MainWindow extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	public MainWindow()
	{
		GestureFileReader fileReader = new GestureFileReader(new File("testFile.txt"));
		
		fileReader.readFromFile();
		
		fileReader.print();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String args[])
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				new MainWindow().setVisible(true);
			}
		});
	}
}
