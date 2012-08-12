package com.fARmework.modules.ScreenGestures.Java.Matching.PatternMatchers;

public class DirectionalPatternMatcher extends PatternMatcherBase<Integer> 
{
	@Override
	public boolean match(Integer[][] input, Integer[][] pattern) 
	{
		if(!sizeCheck(input, pattern))
		{
			return false;
		}
		
		int gridSize = input.length;
		
		int inputMaximum = 0;
		int xBeginPattern = 0;
		int yBeginPattern = 0;
		
		for(int x = 0; x < gridSize; ++x)
		{
			for(int y = 0; y < gridSize; ++y)
			{
				if(input[x][y] > inputMaximum)
				{
					inputMaximum = input[x][y];
				}
				
				if(pattern[x][y] == 1)
				{
					xBeginPattern = x;
					yBeginPattern = y;
				}
			}
		}
		
		int initialDifference = input[xBeginPattern][yBeginPattern] - 1;
		
		for(int x = 0; x < gridSize; ++x)
		{
			for(int y = 0; y < gridSize; ++y)
			{
				if(input[x][y] == 0 && pattern[x][y] == 0)
				{
					continue;
				}
				
				int patternWithOffset = (pattern[x][y] + initialDifference) % inputMaximum;
				patternWithOffset = (patternWithOffset == 0) ? inputMaximum : patternWithOffset;
				
				if(input[x][y] != patternWithOffset)
				{
					return false;
				}
			}
		}		
		
		return true;
	}	
}
