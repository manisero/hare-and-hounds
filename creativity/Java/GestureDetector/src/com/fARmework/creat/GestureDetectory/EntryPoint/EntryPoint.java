package com.fARmework.creat.GestureDetectory.EntryPoint;

import com.fARmework.creat.GestureDetector.*;
import com.fARmework.creat.GestureDetector.Utilities.*;
import com.fARmework.create.GestureDetector.DefaultImpl.*;
import com.fARmework.modules.ScreenGestures.Data.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class EntryPoint extends JFrame
{
	private static final long serialVersionUID = 1L;

	private GestureFileReader _fileReader;
	private GestureDrawer _gestureDrawer;
	
	private IGesturesDetector _gesturesDetector;
	
	public EntryPoint()
	{
		_fileReader = new GestureFileReader(new File("samples/square_clockwise.dat"));
		_gestureDrawer = new GestureDrawer();
		
		_gesturesDetector = new DefaultGesturesDetector();
		new ClockwiseSquareRecognizer(_gesturesDetector, new DefaultGestureProcessor());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void detect()
	{
		LinkedList<GestureData> gestures = _fileReader.getGestures();
		
		for(GestureData data : gestures)
		{	
			String gestureType = _gesturesDetector.recognizeGesture(data);
			
			if(_gesturesDetector.unrecognizedGestureString().equals(gestureType))
			{
				System.out.println("Unrecognized gesture");
				continue;
			}
				
			BufferedImage image = _gestureDrawer.drawGesture(data);
			
			JFrame frame = new JFrame();
			JLabel label = new JLabel();
			
			label.setIcon(new ImageIcon(image));
			frame.add(label);
			frame.setTitle("Recognized: " + gestureType);
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
				new EntryPoint().detect();
			}
		});
	}
}
