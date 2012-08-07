package com.fARmework.creat.GestureDetector;

import com.fARmework.creat.GestureDetector.Data.*;
import com.fARmework.modules.ScreenGestures.Data.*;
import java.util.*;

public interface IPatternMatcher<T> 
{	
	String match(GestureData data);
	
	Set<String> getPatterns();
	
	boolean add(GesturePattern<T> pattern);
}
