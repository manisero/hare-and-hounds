package com.fARmework.modules.ScreenGestures.Java.GesturesReader;

import com.fARmework.modules.ScreenGestures.Data.*;
import com.fARmework.modules.ScreenGestures.Java.*;
import com.fARmework.modules.ScreenGestures.Java.Drawing.IGestureImageViewer;
import com.fARmework.modules.ScreenGestures.Java.Drawing._impl.GestureImageGenerator;
import com.fARmework.modules.ScreenGestures.Java.Drawing._impl.GestureImageViewer;
import com.fARmework.modules.ScreenGestures.Java.Gestures.*;
import com.fARmework.modules.ScreenGestures.Java.Matching.IScreenPatternMatcherFactory;
import com.fARmework.modules.ScreenGestures.Java.Matching.PatternMatchers.*;
import com.fARmework.modules.ScreenGestures.Java.Matching._impl.*;
import com.fARmework.modules.ScreenGestures.Java.Processing.IScreenGestureProcessor;
import com.fARmework.modules.ScreenGestures.Java.Processing._impl.*;
import com.fARmework.modules.ScreenGestures.Java._impl.*;

import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class EntryPoint extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	private static final Character[][] TRIANGLE_PATTERN =
	{
		{	'-',	'a',	'b',	'c',	'-'	},
		{	'-',	'a',	'b',	'c',	'-'	},
		{	'd',	'd',	'?',	'e',	'e'	},
		{	'+',	'?',	'-',	'?',	'+'	},
		{	'+',	'+',	'+',	'+',	'+'	},
	};
	
	private static final Double[][] CROSS_PATTERN =
	{
		{	1.0,	0.5,	1.0	},
		{	0.5,	1.0,	0.5	},
		{	1.0,	0.5,	1.0	}
	};
	
	private static final String DIRECTORY = "samples";
	
	private static final String[] FILES = 
	{
		"circle_counterclockwise.dat",
		"circle_clockwise.dat",
		"cross1.dat",
		"cross2.dat",
		"cross3.dat",
		"square_clockwise.dat",
		"square_counterclockwise.dat",
		"triangle_clockwise.dat",
		"triangle_counterclockwise.dat"
	};
	
	private IScreenGestureRecognizer _recognizer;
	private IScreenGestureProcessor _processor;
	private IScreenPatternMatcherFactory _matcherFactory;
	private IGestureImageViewer _gestureImageViewer;
	
	private GestureFileReader _reader;
	private GestureDrawer _drawer;
	
	public EntryPoint()
	{
		setupGestureRecognition();
		
		_reader = new GestureFileReader();
		_drawer = new GestureDrawer();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);		
	}
	
	public void setupGestureRecognition()
	{
		DiffusedScreenGesture cross = new DiffusedScreenGesture("CROSS", CROSS_PATTERN);
		
		GroupedScreenGesture triangle = new GroupedScreenGesture("TRIANGLE", TRIANGLE_PATTERN);
		
		ScreenGestureRegistry gestureRegistry = new ScreenGestureRegistry();
		
		gestureRegistry.register(cross);
		gestureRegistry.register(triangle);
		
		PlainScreenPatternMatcher plainMatcher = new PlainScreenPatternMatcher();
		DiffusedScreenPatternMatcher diffusedMatcher = new DiffusedScreenPatternMatcher();
		GroupedScreenPatternMatcher groupedMatcher = new GroupedScreenPatternMatcher();
		
		_processor = new ScreenGestureProcessor();
		
		_matcherFactory = new EmptyScreenPatternMatcherFactory();
		_matcherFactory.register(PlainScreenGesture.class, plainMatcher);
		_matcherFactory.register(DiffusedScreenGesture.class, diffusedMatcher);
		_matcherFactory.register(GroupedScreenGesture.class, groupedMatcher);
		
		_gestureImageViewer = new GestureImageViewer(new GestureImageGenerator());
		
		_recognizer = new ScreenGestureRecognizer(gestureRegistry, _processor, _matcherFactory, _gestureImageViewer);
	}
	
	public void recognizeGestures()
	{
		for(String file : FILES)
		{
			_reader.clear();
			_reader.setFile(new File(DIRECTORY + "/" + file));
			
			LinkedList<ScreenGestureData> gestures = _reader.getGestures();
			
			for(ScreenGestureData gesture : gestures)
			{				
				String type = _recognizer.recognize(gesture);
				
				if(type == null)
				{
					continue;
				}
				
				drawGesture(gesture, type, file);
			}
		}
	}
	
	public void drawGesture(ScreenGestureData gesture, String type, String file)
	{
		BufferedImage image = _drawer.drawGesture(gesture);
		
		JLabel label = new JLabel(new ImageIcon(image));
		
		JFrame frame = new JFrame();
		frame.add(label);
		frame.setTitle("Recognized: " + type + " in file: " + file);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void printGestureGrid(ScreenGestureData data, String file, int gridSize)
	{
		Boolean[][] grid = _processor.getGestureGrid(data, gridSize);		
		
		System.out.println("---------- Gesture in file: " + file + " ----------");
		
		for(int x = 0; x < grid.length; ++x)
		{
			for(int y = 0; y < grid[x].length; ++y)
			{
				System.out.print("" + grid[x][y] + "\t");
			}
			
			System.out.println();
		}
		
		System.out.println("---------- End of gesture -----------");
	}
	
	public static void main(String args[])
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				new EntryPoint().recognizeGestures();
			}
		});
	}	
}
