package com.fARmework.modules.ScreenGestures.Java._impl;

import com.fARmework.modules.ScreenGestures.Java.*;
import com.fARmework.modules.ScreenGestures.Java.Gestures.Gesture;

import java.util.*;

public class GestureRegistry implements IGestureRegistry 
{
	private Map<String, Gesture<?>> _gestures;
	
	public GestureRegistry()
	{
		_gestures = new LinkedHashMap<String, Gesture<?>>();
	}

	@Override
	public boolean register(Gesture<?> gesture) 
	{
		if(_gestures.containsKey(gesture.getName()))
		{
			return false;
		}
		
		_gestures.put(gesture.getName(), gesture);
		
		return true;
	}

	@Override
	public boolean unregister(Gesture<?> gesture) 
	{
		if(!_gestures.containsKey(gesture.getName()))
		{
			return false;
		}
		
		_gestures.remove(gesture.getName());
		
		return true;
	}

	@Override
	public List<Gesture<?>> getGestures() 
	{
		List<Gesture<?>> gesturesList = new LinkedList<Gesture<?>>(_gestures.values());
		
		Collections.sort(gesturesList, new Comparator<Gesture<?>>() 
		{
			@Override
			public int compare(Gesture<?> firstGesture, Gesture<?> secondGesture) 
			{
				int sizeFirst = firstGesture.getPattern().length;
				int sizeSecond = secondGesture.getPattern().length;
				
				if(sizeFirst > sizeSecond)
				{
					return -1;
				}
				else if(sizeSecond > sizeFirst)
				{
					return 1;
				}

				return 0;
			}
		});
		
		return gesturesList;
	}
}
