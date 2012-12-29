package com.fARmework.modules.SpaceGestures.Java.Processing;

import java.util.*;

import com.fARmework.modules.SpaceGestures.Data.*;

public interface ISpaceGestureFilter
{
	public List<GestureRange> getFilteredSegments(SpaceGestureData gesture, List<GestureRange> segments);
}
