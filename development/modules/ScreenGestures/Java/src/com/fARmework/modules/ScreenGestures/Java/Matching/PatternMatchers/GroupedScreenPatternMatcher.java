package com.fARmework.modules.ScreenGestures.Java.Matching.PatternMatchers;

import java.util.*;

public class GroupedScreenPatternMatcher extends ScreenPatternMatcherBase<Character> 
{
	private static final Character FORBIDDEN = '-';
	private static final Character OPTIONAL = '?';
	private static final Character REQUIRED = '+';
	
	private static final Character FALSE = 'f';
	private static final Character TRUE = 't';
	
	@Override
	protected boolean matchPattern(Character[][] input, Character[][] pattern) 
	{
		Map<Character, Integer> groupUtilisation = new LinkedHashMap<Character, Integer>();
		
		for(int x = 0; x < pattern.length; ++x)
		{
			for(int y = 0; y < pattern[x].length; ++y)
			{
				if(pattern[x][y] >= 'a' && pattern[x][y] <= 'z')
				{
					groupUtilisation.put(pattern[x][y], 0);
				}
			}
		}
		
		for(int x = 0; x < pattern.length; ++x)
		{
			for(int y = 0; y < pattern[x].length; ++y)
			{
				if(pattern[x][y].equals(OPTIONAL))
				{
					continue;
				}				
				
				if(pattern[x][y].equals(REQUIRED) && input[x][y].equals(FALSE))
				{
					return false;
				}
				
				if(pattern[x][y].equals(FORBIDDEN) && input[x][y].equals(TRUE))
				{
					return false;
				}
				
				if(pattern[x][y] >= 'a' && pattern[x][y] <= 'z' && input[x][y].equals(TRUE))
				{
					int value = groupUtilisation.get(pattern[x][y]);
					
					value++;
					
					groupUtilisation.put(pattern[x][y], value);
				}
			}
		}
		
		for(Map.Entry<Character, Integer> entry : groupUtilisation.entrySet())
		{
			if(entry.getValue() < 1)
			{
				return false;
			}
		}
		
		return true;
	}	
}
