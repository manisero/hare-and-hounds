package com.fARmework.creat.GestureDetector.PatternMatchers;

import com.fARmework.creat.GestureDetector.*;

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
