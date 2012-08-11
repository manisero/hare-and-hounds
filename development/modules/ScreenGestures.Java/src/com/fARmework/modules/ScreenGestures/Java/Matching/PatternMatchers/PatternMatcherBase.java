package com.fARmework.modules.ScreenGestures.Java.Matching.PatternMatchers;

import com.fARmework.modules.ScreenGestures.Java.Matching.*;

public abstract class PatternMatcherBase<T> implements IPatternMatcher<T> 
{
	public boolean sizeCheck(T[][] input, T[][] pattern)
	{
		if(input.length != pattern.length || input.length == 0)
		{
			return false;
		}
		
		for(int i = 0; i < input.length; ++i)
		{
			if(input[i].length != pattern[i].length || input[i].length != input.length)
			{
				return false;
			}
		}
		
		return true;
	}	
}
