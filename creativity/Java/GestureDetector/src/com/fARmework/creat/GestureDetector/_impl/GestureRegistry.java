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
		List<IGesture<?>> gesturesList = new LinkedList<IGesture<?>>(_gestures.values());
		
		Collections.sort(gesturesList, new Comparator<IGesture<?>>() 
		{
			@Override
			public int compare(IGesture<?> firstGesture, IGesture<?> secondGesture) 
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
