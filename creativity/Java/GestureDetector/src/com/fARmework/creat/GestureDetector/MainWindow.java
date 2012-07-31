package com.fARmework.creat.GestureDetector;

import javax.swing.*;

public class MainWindow extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	public MainWindow()
	{
		
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
