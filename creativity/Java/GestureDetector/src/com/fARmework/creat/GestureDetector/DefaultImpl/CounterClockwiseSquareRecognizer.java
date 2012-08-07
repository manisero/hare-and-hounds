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
	
	private IGestureProcessor _processor;
	
	private int _xNext = 0;
	private int _yNext = 0;
	
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
		
		if(matchPercentage >= 0.95)
		{
			int[][] orientedPattern = _processor.getOrientedGrid(data, GRID_SIZE);
			
			int maximum = findMaximumInOrientedPattern(orientedPattern, GRID_SIZE);
			
			int xStart = 0;
			int yStart = 0;
			int counter = 0;
			
			boolean finish = false;
			
			for(int i = 1; i < maximum; ++i)
			{
				if(finish)
				{
					break;
				}
				
				for(int x = 0; x < GRID_SIZE; ++x)
				{
					if(finish)
					{
						break;
					}
					
					for(int y = 0; y < GRID_SIZE; ++y)
					{
						if(orientedPattern[x][y] == i && samplePattern[x][y] == true)
						{
							xStart = x;
							yStart = y;
							counter = i;
							
							finish = true;
							break;
						}
					}
				}
			}
			
			if(!finish)
			{
				return false;
			}
			
			while(counter <= maximum)
			{
				if(	orientedPattern[xStart][yStart] == counter || 
					orientedPattern[xStart][yStart] == 0)
				{
					if(orientedPattern[xStart][yStart] != 0)
					{
						counter++;
					}
					
					findNext(xStart, yStart);
					xStart = _xNext;
					yStart = _yNext;
				}
				else
				{
					return false;
				}
			}
			
			return true;
		}
		
		return false;
	}

	@Override
	public String getType() 
	{
		return "COUNTER_CLOCKWISE_SQUARE";
	}
	
	private int findMaximumInOrientedPattern(int[][] orientedPattern, int gridSize)
	{
		int maximum = 0;
		
		for(int x = 0; x < gridSize; ++x)
		{
			for(int y = 0; y < gridSize; ++y)
			{
				if(orientedPattern[x][y] > maximum)
				{
					maximum = orientedPattern[x][y];
				}
			}
		}
		
		return maximum;
	}
	
	private void findNext(int x, int y)
	{
		int lastGrid = GRID_SIZE - 1;
		
		if(x == 0)
		{
			if(y != lastGrid)
			{
				_xNext = 0;
				_yNext = y + 1;
			}
			else
			{
				_xNext = 1;
				_yNext = lastGrid;
			}
		}
		else if(y == lastGrid)
		{
			if(x != lastGrid)
			{
				_xNext = x + 1;
				_yNext = lastGrid;
			}
			else
			{
				_xNext = lastGrid;
				_yNext = lastGrid - 1;
			}
		}
		else if(x == lastGrid)
		{
			if(y != 0)
			{
				_xNext = lastGrid;
				_yNext = y - 1;
			}
			else
			{
				_xNext = lastGrid - 1;
				_yNext = 0;
			}
		}
		else if(y == 0)
		{
			if(x != 0)
			{
				_xNext = x - 1;
				_yNext = 0;
			}
			else
			{
				_xNext = 0;
				_yNext = 1;
			}
		}
	}
}
