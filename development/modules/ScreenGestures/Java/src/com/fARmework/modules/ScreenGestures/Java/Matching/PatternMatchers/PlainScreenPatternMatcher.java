package com.fARmework.modules.ScreenGestures.Java.Matching.PatternMatchers;

public class PlainScreenPatternMatcher extends ScreenPatternMatcherBase<Boolean> 
{
	@Override
	public boolean matchPattern(Boolean[][] input, Boolean[][] pattern) 
	{
		for (int x = 0; x < pattern.length; ++x)
		{
			for (int y = 0; y < pattern[x].length; ++y)
			{
				if (!input[x][y].equals(pattern[x][y]))
				{
					return false;
				}
			}
		}
		
		return true;
	}
}
