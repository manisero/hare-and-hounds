package com.fARmework.creat.GestureDetector.PatternMatchers;

import com.fARmework.creat.GestureDetector.*;
import com.fARmework.creat.GestureDetector.Utilities.*;

public class PlainPatternMatcher implements IPatternMatcher<Boolean> 
{
	@Override
	public boolean match(Boolean[][] input, Boolean[][] pattern) 
	{
		if(!GestureRecognizerUtilities.sizeCheck(input, pattern))
		{
			return false;
		}
		
		int gridSize = input.length;
		
		for(int x = 0; x < gridSize; ++x)
		{
			for(int y = 0; y < gridSize; ++y)
			{
				if(!input[x][y].equals(pattern[x][y]))
				{
					return false;
				}
			}
		}
		
		return true;
	}
}
