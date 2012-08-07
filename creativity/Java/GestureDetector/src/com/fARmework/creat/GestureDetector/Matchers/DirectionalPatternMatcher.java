package com.fARmework.creat.GestureDetector.Matchers;

import com.fARmework.creat.GestureDetector.*;
import com.fARmework.creat.GestureDetector.Data.*;
import com.fARmework.modules.ScreenGestures.Data.*;
import java.util.*;

public class DirectionalPatternMatcher implements IPatternMatcher<Integer> 
{
	private Set<GesturePattern<Integer>> _patterns;
	private IGestureProcessor<Integer> _processor;
	
	public DirectionalPatternMatcher(IGestureProcessor<Integer> processor)
	{
		_processor = processor;
		_patterns = new LinkedHashSet<GesturePattern<Integer>>();
	}
	
	@Override
	public String match(GestureData data) 
	{
		for(GesturePattern<Integer> pattern : _patterns)
		{
			Integer[][] directionalPattern = pattern.getPattern();
			
			int gridSize = directionalPattern.length;
			
			Integer[][] gesturePattern = _processor.getGestureGrid(data, gridSize);
			
			if(getMatchPercentage(gesturePattern, directionalPattern) == 1.0)
			{
				return pattern.getName();
			}
		}

		return null;
	}

	private double getMatchPercentage(Integer[][] input, Integer[][] pattern) 
	{
		if(!sizeCheck(input, pattern))
		{
			return 0.0;
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
		
		int total = 0;
		int matches = 0;
		
		for(int x = 0; x < gridSize; ++x)
		{
			for(int y = 0; y < gridSize; ++y)
			{
				if(input[x][y] == 0 && pattern[x][y] == 0)
				{
					continue;
				}
				
				++total;
				
				int patternWithOffset = (pattern[x][y] + initialDifference) % inputMaximum;
				patternWithOffset = (patternWithOffset == 0) ? inputMaximum : patternWithOffset;
				
				if(input[x][y] == patternWithOffset)
				{
					++matches;
				}
			}
		}
		
		return (double) matches / (double) total;
	}	
	
	private boolean sizeCheck(Integer[][] input, Integer[][] pattern)
	{
		int inputGridSize = input.length;
		int patternGridSize = pattern.length;
		
		if(inputGridSize != patternGridSize)
		{
			return false;
		}
		
		for(int i = 0; i < inputGridSize; ++i)
		{
			if(input[i].length != inputGridSize || pattern[i].length != inputGridSize)
			{
				return false;
			}
		}		
		
		return true;
	}	
	
	@Override
	public Set<String> getPatterns() 
	{
		Set<String> patterns = new LinkedHashSet<String>();
		
		for(GesturePattern<Integer> pattern : _patterns)
		{
			patterns.add(pattern.getName());
		}
		
		return patterns;
	}

	@Override
	public boolean add(GesturePattern<Integer> pattern) 
	{
		if(_patterns.contains(pattern))
		{
			return false;
		}
		
		_patterns.add(pattern);
		
		return true;
	}
}
