package com.fARmework.creat.GestureDetector;

import com.fARmework.modules.ScreenGestures.Data.*;
import java.util.*;

public interface IGestureRecognizer 
{
	Set<String> getGestures();
	
	String recognize(GestureData data);
	
	boolean add(IPatternMatcher<?> matcher);
}
