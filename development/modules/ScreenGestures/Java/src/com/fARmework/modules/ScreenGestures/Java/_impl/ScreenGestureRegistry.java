package com.fARmework.modules.ScreenGestures.Java._impl;

import com.fARmework.modules.ScreenGestures.Java.*;
import com.fARmework.modules.ScreenGestures.Java.Gestures.ScreenGesture;

import java.util.*;

public class ScreenGestureRegistry implements IScreenGestureRegistry 
{
	private Map<String, ScreenGesture<?>> _gestures;
	
	public ScreenGestureRegistry()
	{
		_gestures = new LinkedHashMap<String, ScreenGesture<?>>();
	}

	@Override
	public boolean register(ScreenGesture<?> gesture) 
	{
		if(_gestures.containsKey(gesture.getName()))
		{
			return false;
		}
		
		_gestures.put(gesture.getName(), gesture);
		
		return true;
	}

	@Override
	public boolean unregister(ScreenGesture<?> gesture) 
	{
		if(!_gestures.containsKey(gesture.getName()))
		{
			return false;
		}
		
		_gestures.remove(gesture.getName());
		
		return true;
	}

	@Override
	public List<ScreenGesture<?>> getGestures() 
	{
		List<ScreenGesture<?>> gesturesList = new LinkedList<ScreenGesture<?>>(_gestures.values());
		
		Collections.sort(gesturesList, new Comparator<ScreenGesture<?>>() 
		{
			@Override
			public int compare(ScreenGesture<?> firstGesture, ScreenGesture<?> secondGesture) 
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
