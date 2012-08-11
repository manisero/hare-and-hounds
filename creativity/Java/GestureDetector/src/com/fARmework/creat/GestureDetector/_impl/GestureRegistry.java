package com.fARmework.creat.GestureDetector._impl;

import com.fARmework.creat.GestureDetector.*;
import java.util.*;

public class GestureRegistry implements IGestureRegistry 
{
	private Map<String, IGesture<?>> _gestures;
	
	public GestureRegistry()
	{
		_gestures = new LinkedHashMap<String, IGesture<?>>();
	}

	@Override
	public boolean add(IGesture<?> gesture) 
	{
		if(_gestures.containsKey(gesture.getName()))
		{
			return false;
		}
		
		_gestures.put(gesture.getName(), gesture);
		
		return true;
	}

	@Override
	public boolean delete(IGesture<?> gesture) 
	{
		if(!_gestures.containsKey(gesture.getName()))
		{
			return false;
		}
		
		_gestures.remove(gesture.getName());
		
		return true;
	}

	@Override
	public List<IGesture<?>> getGestures() 
	{
		return new LinkedList<IGesture<?>>(_gestures.values());
	}
}
