package com.fARmework.modules.SpaceGestures.Java.Processing;

import java.util.*;

import com.fARmework.modules.SpaceGestures.Data.*;
import com.fARmework.modules.SpaceGestures.Java.*;

public interface ISpaceGestureProcessor
{
	public List<Direction> process(SpaceGestureData gesture);
}
