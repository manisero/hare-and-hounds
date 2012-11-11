package com.fARmework.modules.SpaceGestures.Java.Processing;

import java.util.*;

import com.fARmework.modules.SpaceGestures.Data.*;
import com.fARmework.modules.SpaceGestures.Java.*;

public interface ISpaceGestureDirectionRecognizer
{
	public List<Direction> getMoveDirections(SpaceGestureData gesture, List<GestureRange> segments);
}
