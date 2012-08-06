package com.fARmework.create.GestureDetector.DefaultImpl;

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
		
		if(matchPercentage >= 0.95)
		{
			return true;
		}
		
		return false;
	}

	@Override
	public String getType() 
	{
		return "CLOCKWISE_SQUARE";
	}
}
