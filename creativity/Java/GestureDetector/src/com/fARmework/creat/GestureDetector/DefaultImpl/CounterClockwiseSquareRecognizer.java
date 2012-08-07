package com.fARmework.creat.GestureDetector.DefaultImpl;

import com.fARmework.creat.GestureDetector.*;
import com.fARmework.modules.ScreenGestures.Data.*;

public class CounterClockwiseSquareRecognizer implements IGestureRecognizer 
{
	private static final int GRID_SIZE = 8;
	
	private static final boolean[][] GESTURE_PATTERN = {
		{ 	true,	true,	true,	true,	true,	true,	true,	true	},
		{	true,	false,	false,	false,	false,	false,	false,	true	},
		{	true,	false,	false,	false,	false,	false,	false,	true	},
		{	true,	false,	false,	false,	false,	false,	false,	true	},
		{	true,	false,	false,	false,	false,	false,	false,	true	},
		{	true,	false,	false,	false,	false,	false,	false,	true	},
		{	true,	false,	false,	false,	false,	false,	false,	true	},
		{ 	true,	true,	true,	true,	true,	true,	true,	true	}
	};
	
	private static final int[][] ORIENTED_GESTURE_PATTERN = {
		{	23,	22,	21,	20,	19,	18,	17,	16	},
		{	24,	0,	0,	0,	0,	0,	0,	15	},
		{	25,	0,	0,	0,	0,	0,	0,	14	},	
		{	26,	0,	0,	0,	0,	0,	0,	13	},	
		{	27,	0,	0,	0,	0,	0,	0,	12	},	
		{	28,	0,	0,	0,	0,	0,	0,	11	},	
		{	1,	0,	0,	0,	0,	0,	0,	10	},
		{	2,	3,	4,	5,	6,	7,	8,	9	}
	};	
	
	private IGestureProcessor _processor;
	
	public CounterClockwiseSquareRecognizer(IGesturesDetector detector, IGestureProcessor processor)
	{
		detector.attach(this);
		
		_processor = processor;
	}
	
	@Override
	public boolean isGesture(GestureData data) 
	{	
		boolean[][] samplePattern = _processor.getGestureGrid(data, GRID_SIZE);
		
		double matchPercentage = _processor.getMatchPercentage(samplePattern, 
				GESTURE_PATTERN);
		
		if(matchPercentage == 1)
		{
			int[][] orientedPattern = _processor.getOrientedGrid(data, GRID_SIZE);
			
			matchPercentage = _processor.getMatchPercentage(orientedPattern, ORIENTED_GESTURE_PATTERN);
			
			if(matchPercentage == 1)
			{
				return true;
			}
		}
			
		return false;
	}

	@Override
	public String getType() 
	{
		return "COUNTER_CLOCKWISE_SQUARE";
	}
}
