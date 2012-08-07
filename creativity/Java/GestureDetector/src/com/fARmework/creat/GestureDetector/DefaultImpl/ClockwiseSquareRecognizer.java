package com.fARmework.creat.GestureDetector.DefaultImpl;

import com.fARmework.creat.GestureDetector.*;
import com.fARmework.modules.ScreenGestures.Data.*;

public class ClockwiseSquareRecognizer implements IGestureRecognizer 
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
		{	1,	28,	27,	26,	25,	24,	23, 22	},
		{	2,	0,	0,	0,	0,	0,	0,	21	},
		{	3,	0,	0,	0,	0,	0,	0,	20	},	
		{	4,	0,	0,	0,	0,	0,	0,	19	},	
		{	5,	0,	0,	0,	0,	0,	0,	18	},	
		{	6,	0,	0,	0,	0,	0,	0,	17	},	
		{	7,	0,	0,	0,	0,	0,	0,	16	},
		{	8,	9,	10,	11,	12,	13,	14,	15	}
	};
	
	private IGestureProcessor _processor;
	
	public ClockwiseSquareRecognizer(IGesturesDetector detector, IGestureProcessor processor)
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
		return "CLOCKWISE_SQUARE";
	}
}
