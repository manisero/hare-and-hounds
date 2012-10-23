package com.fARmework.modules.SpaceGestures.Java._impl;

import java.util.*;

import com.fARmework.modules.SpaceGestures.Java.ISpaceGestureRegistry;
import com.fARmework.modules.SpaceGestures.Java.Gestures.*;

public class SpaceGestureRegistry implements ISpaceGestureRegistry
{
	private List<SpaceGesture> _gestures = new LinkedList<SpaceGesture>();
	
	@Override
	public List<SpaceGesture> getGestures()
	{
		return _gestures;
	}

	@Override
	public boolean register(SpaceGesture gesture)
	{
		_gestures.add(gesture);
		
		return true;
	}

	@Override
	public boolean unregister(SpaceGesture gesture)
	{
		if (!_gestures.contains(gesture))
		{
			return false;
		}
		
		_gestures.remove(gesture);
		
		return true;
	}
}
