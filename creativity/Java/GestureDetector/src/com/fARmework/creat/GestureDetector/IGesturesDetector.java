package com.fARmework.creat.GestureDetector;

import com.fARmework.modules.ScreenGestures.Data.*;
import java.util.*;

public interface IGesturesDetector 
{
	boolean attach(IGestureRecognizer gestureRecognizer);
	
	boolean detach(IGestureRecognizer gestureRecognizer);
	
	Set<String> getGestures();
	
	String recognizeGesture(GestureData data);
	
	String unrecognizedGestureString();
}
