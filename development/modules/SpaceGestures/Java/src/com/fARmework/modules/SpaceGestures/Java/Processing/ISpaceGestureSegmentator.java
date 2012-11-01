package com.fARmework.modules.SpaceGestures.Java.Processing;

import java.util.*;

import com.fARmework.modules.SpaceGestures.Data.*;

public interface ISpaceGestureSegmentator
{
	public List<GestureRange> getGestureSegments(SpaceGestureData gesture);
}
