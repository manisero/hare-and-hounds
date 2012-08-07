package com.fARmework.creat.GestureDetector.DefaultImpl;

import com.fARmework.creat.GestureDetector.*;
import com.fARmework.modules.ScreenGestures.Data.*;
import java.util.*;

@SuppressWarnings("rawtypes")
public class DefaultGestureRecognizer implements IGestureRecognizer
{
	private Set<IPatternMatcher> _patternMatchers;
		
	public DefaultGestureRecognizer()
	{
		_patternMatchers = new LinkedHashSet<IPatternMatcher>();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Set<String> getGestures() 
	{
		Set<String> gestures = new LinkedHashSet<String>();
		
		for(IPatternMatcher patternMatcher : _patternMatchers)
		{
			gestures.addAll(patternMatcher.getPatterns());
		}
		
		return gestures;
	}

	@Override
	public String recognize(GestureData data) 
	{
		for(IPatternMatcher patternMatcher : _patternMatchers)
		{
			String pattern = patternMatcher.match(data);
			
			if(pattern != null)
			{
				return pattern;
			}
		}
		
		return null;
	}

	@Override
	public boolean add(IPatternMatcher matcher) 
	{
		if(_patternMatchers.contains(matcher))
		{
			return false;
		}
		
		_patternMatchers.add(matcher);
		
		return true;
	}
}
