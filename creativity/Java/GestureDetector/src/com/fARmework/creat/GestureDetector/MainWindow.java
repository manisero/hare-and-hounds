package com.fARmework.creat.GestureDetector;

import com.fARmework.modules.ScreenGestures.Data.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class MainWindow extends JFrame
{
	private static final long serialVersionUID = 1L;

	private GestureFileReader _fileReader;
	
	private GestureDrawer _gestureDrawer;
	
	public MainWindow()
	{
		_fileReader = new GestureFileReader(new File("samples/square_counterclockwise.dat"));
		_gestureDrawer = new GestureDrawer();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void draw()
	{
		LinkedList<GestureData> gestures = _fileReader.getGestures();
		
		for(GestureData data : gestures)
		{			
			BufferedImage image = _gestureDrawer.drawGesture(data);
			
			JFrame frame = new JFrame();
			JLabel label = new JLabel();
			
			label.setIcon(new ImageIcon(image));
			frame.add(label);
			frame.setTitle(_gestureDrawer.getTitle(data));
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.pack();
			frame.setVisible(true);
		}
	}

	public static void main(String args[])
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				new MainWindow().draw();
			}
		});
	}
}
