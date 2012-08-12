package com.fARmework.modules.ScreenGestures.Java.Matching.PatternMatchers;

public class PlainPatternMatcher extends PatternMatcherBase<Boolean> 
{
	@Override
	public boolean match(Boolean[][] input, Boolean[][] pattern) 
	{
		if(!sizeCheck(input, pattern))
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
