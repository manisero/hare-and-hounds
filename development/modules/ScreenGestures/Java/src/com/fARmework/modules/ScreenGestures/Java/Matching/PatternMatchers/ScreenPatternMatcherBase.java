package com.fARmework.modules.ScreenGestures.Java.Matching.PatternMatchers;

import com.fARmework.modules.ScreenGestures.Java.Matching.*;

public abstract class ScreenPatternMatcherBase<T> implements IScreenPatternMatcher<T> 
{
	@Override
	public boolean match(Boolean[][] input, T[][] pattern)
	{
		return validateInput(input, pattern) && matchPattern(input, pattern);
	}
	
	protected boolean validateInput(Boolean[][] input, T[][] pattern)
	{
		if (input.length != pattern.length)
		{
			return false;
		}
		
		for (int i = 0; i < input.length; ++i)
		{
			if (input[i].length != pattern[i].length)
			{
				return false;
			}
		}
		
		return true;
	}
	
	protected abstract boolean matchPattern(Boolean[][] input, T[][] pattern);
}
