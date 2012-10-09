package com.fARmework.modules.ScreenGestures.Java.GesturesReader;

import com.fARmework.modules.ScreenGestures.Data.*;
import com.fARmework.modules.ScreenGestures.Java.*;
import com.fARmework.modules.ScreenGestures.Java.Gestures.*;
import com.fARmework.modules.ScreenGestures.Java.Matching.IScreenPatternMatcherFactory;
import com.fARmework.modules.ScreenGestures.Java.Matching.PatternMatchers.*;
import com.fARmework.modules.ScreenGestures.Java.Matching._impl.*;
import com.fARmework.modules.ScreenGestures.Java.Processing.IScreenGestureProcessor;
import com.fARmework.modules.ScreenGestures.Java.Processing.IScreenGestureProcessorFactory;
import com.fARmework.modules.ScreenGestures.Java.Processing.GestureProcessors.*;
import com.fARmework.modules.ScreenGestures.Java.Processing._impl.*;
import com.fARmework.modules.ScreenGestures.Java._impl.*;

import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class EntryPoint extends JFrame
{
	private static final long serialVersionUID = 1L;

	private static final Integer[][] CLOCKWISE_SQUARE_PATTERN = 
	{
		{	1,	2,	3,	4,	5,	6,	7,	8	},
		{	28,	0,	0,	0,	0,	0,	0,	9	},
		{	27,	0,	0,	0,	0,	0,	0,	10	},	
		{	26,	0,	0,	0,	0,	0,	0,	11	},	
		{	25,	0,	0,	0,	0,	0,	0,	12	},	
		{	24,	0,	0,	0,	0,	0,	0,	13	},	
		{	23,	0,	0,	0,	0,	0,	0,	14	},
		{	22,	21,	20,	19,	18,	17,	16,	15	}
	};
	
	private static final Integer[][] COUNTER_CLOCKWISE_SQUARE_PATTERN = 
	{
		{	1,	28,	27,	26,	25,	24,	23, 22	},
		{	2,	0,	0,	0,	0,	0,	0,	21	},
		{	3,	0,	0,	0,	0,	0,	0,	20	},	
		{	4,	0,	0,	0,	0,	0,	0,	19	},	
		{	5,	0,	0,	0,	0,	0,	0,	18	},	
		{	6,	0,	0,	0,	0,	0,	0,	17	},	
		{	7,	0,	0,	0,	0,	0,	0,	16	},
		{	8,	9,	10,	11,	12,	13,	14,	15	}
	};
	
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
	private IScreenGestureProcessorFactory _processorFactory;
	private IScreenPatternMatcherFactory _matcherFactory;
	
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
		DirectionalScreenGesture clockwiseSquare = new DirectionalScreenGesture(
				"CLOCKWISE_SQUARE", CLOCKWISE_SQUARE_PATTERN);
		
		DirectionalScreenGesture counterClockwiseSquare = new DirectionalScreenGesture(
				"COUNTER_CLOCKWISE_SQUARE", COUNTER_CLOCKWISE_SQUARE_PATTERN);
		
		DiffusedScreenGesture cross = new DiffusedScreenGesture("CROSS", CROSS_PATTERN);
		
		GroupedScreenGesture triangle = new GroupedScreenGesture("TRIANGLE", TRIANGLE_PATTERN);
		
		ScreenGestureRegistry gestureRegistry = new ScreenGestureRegistry();
		
		gestureRegistry.register(clockwiseSquare);
		gestureRegistry.register(counterClockwiseSquare);
		gestureRegistry.register(cross);
		gestureRegistry.register(triangle);
				
		PlainScreenGestureProcessor plainProcessor = new PlainScreenGestureProcessor();
		PlainScreenPatternMatcher plainMatcher = new PlainScreenPatternMatcher();
		
		DirectionalScreenGestureProcessor processor = new DirectionalScreenGestureProcessor();
		DirectionalScreenPatternMatcher matcher = new DirectionalScreenPatternMatcher();
		
		DiffusedScreenGestureProcessor diffusedProcessor = new DiffusedScreenGestureProcessor();
		DiffusedScreenPatternMatcher diffusedMatcher = new DiffusedScreenPatternMatcher();
		
		GroupedScreenGestureProcessor groupedProcessor = new GroupedScreenGestureProcessor();
		GroupedScreenPatternMatcher groupedMatcher = new GroupedScreenPatternMatcher();
		
		_processorFactory = new ScreenGestureProcessorFactory();
		_matcherFactory = new ScreenPatternMatcherFactory();
		
		_processorFactory.register(PlainScreenGesture.class, plainProcessor);
		_processorFactory.register(DirectionalScreenGesture.class, processor);
		_processorFactory.register(DiffusedScreenGesture.class, diffusedProcessor);
		_processorFactory.register(GroupedScreenGesture.class, groupedProcessor);
		_matcherFactory.register(PlainScreenGesture.class, plainMatcher);
		_matcherFactory.register(DirectionalScreenGesture.class, matcher);
		_matcherFactory.register(DiffusedScreenGesture.class, diffusedMatcher);
		_matcherFactory.register(GroupedScreenGesture.class, groupedMatcher);
		
		_recognizer = new ScreenGestureRecognizer(gestureRegistry, _processorFactory, _matcherFactory);
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
		IScreenGestureProcessor<Boolean> processor = _processorFactory.get(PlainScreenGesture.class);
		
		Boolean[][] grid = processor.getGestureGrid(data, gridSize);		
		
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
