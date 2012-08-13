package com.fARmework.modules.ScreenGestures.Java.Matching.PatternMatchers;

public class PlainScreenPatternMatcher extends ScreenPatternMatcherBase<Boolean> 
{
	@Override
	public boolean matchPattern(Boolean[][] input, Boolean[][] pattern) 
	{
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
