package com.fARmework.modules.ScreenGestures.EntryPoint;

import com.fARmework.modules.ScreenGestures.*;
import com.fARmework.modules.ScreenGestures.Data.*;
import com.fARmework.modules.ScreenGestures.Gestures.*;
import com.fARmework.modules.ScreenGestures.Matching.PatternMatchers.*;
import com.fARmework.modules.ScreenGestures.Matching._impl.*;
import com.fARmework.modules.ScreenGestures.Processing.GestureProcessors.*;
import com.fARmework.modules.ScreenGestures.Processing._impl.*;
import com.fARmework.modules.ScreenGestures.Utilities.*;
import com.fARmework.modules.ScreenGestures._impl.*;

import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class EntryPoint extends JFrame
{
	private static final long serialVersionUID = 1L;

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
	
	private IGestureRecognizer _recognizer;
	
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
		DirectionalGesture clockwiseSquare = new DirectionalGesture(
				CLOCKWISE_SQUARE_PATTERN, "CLOCKWISE_SQUARE");
		
		DirectionalGesture counterClockwiseSquare = new DirectionalGesture(
				COUNTER_CLOCKWISE_SQUARE_PATTERN, "COUNTER_CLOCKWISE_SQUARE");
		
		DiffusedGesture cross = new DiffusedGesture(CROSS_PATTERN, "CROSS");
		
		GestureRegistry gestureRegistry = new GestureRegistry();
		
		gestureRegistry.add(clockwiseSquare);
		gestureRegistry.add(counterClockwiseSquare);
		gestureRegistry.add(cross);
				
		DirectionalGestureProcessor processor = new DirectionalGestureProcessor();
		DirectionalPatternMatcher matcher = new DirectionalPatternMatcher();
		
		DiffusedGestureProcessor diffusedProcessor = new DiffusedGestureProcessor();
		DiffusedPatternMatcher diffusedMatcher = new DiffusedPatternMatcher();
		
		GestureProcessorFactory processorFactory = new GestureProcessorFactory();
		PatternMatcherFactory matcherFactory = new PatternMatcherFactory();
		
		processorFactory.register(clockwiseSquare.getClass(), processor);
		processorFactory.register(cross.getClass(), diffusedProcessor);
		matcherFactory.register(clockwiseSquare.getClass(), matcher);
		matcherFactory.register(cross.getClass(), diffusedMatcher);
		
		_recognizer = new GestureRecognizer(gestureRegistry, matcherFactory, processorFactory);
	}
	
	public void recognizeGestures()
	{
		for(String file : FILES)
		{
			_reader.clear();
			_reader.setFile(new File(DIRECTORY + "/" + file));
			
			LinkedList<GestureData> gestures = _reader.getGestures();
			
			for(GestureData gesture : gestures)
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
	
	public void drawGesture(GestureData gesture, String type, String file)
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
