package com.fARmework.creat.GestureDetector.DefaultImpl;

import com.fARmework.creat.GestureDetector.*;
import com.fARmework.modules.ScreenGestures.Data.*;
import java.util.*;

public class DefaultGesturesDetector implements IGesturesDetector 
{
	private static final String UNKNOWN_GESTURE = "NOT_RECOGNIZED";
	
	private Map<String, IGestureRecognizer> _gestureRecognizers;
	
	public DefaultGesturesDetector()
	{
		_gestureRecognizers = new LinkedHashMap<String, IGestureRecognizer>();
	}
	
	@Override
	public boolean attach(IGestureRecognizer gestureRecognizer) 
	{
		String type = gestureRecognizer.getType();
		
		if(_gestureRecognizers.containsKey(type))
		{
			return false;
		}
		
		_gestureRecognizers.put(type, gestureRecognizer);
		
		return true;
	}

	@Override
	public boolean detach(IGestureRecognizer gestureRecognizer) 
	{
		String type = gestureRecognizer.getType();
		
		if(_gestureRecognizers.containsKey(type))
		{
			return false;
		}
		
		_gestureRecognizers.remove(type);
		
		return true;
	}

	@Override
	public Set<String> getGestures() 
	{
		return _gestureRecognizers.keySet();
	}

	@Override
	public String recognizeGesture(GestureData data) 
	{
		for(Map.Entry<String, IGestureRecognizer> entry : _gestureRecognizers.entrySet())
		{
			if(entry.getValue().isGesture(data))
			{
				return entry.getKey();
			}
		}
		
		return UNKNOWN_GESTURE;
	}

	@Override
	public String unrecognizedGestureString() 
	{
		return UNKNOWN_GESTURE;
	}
}
