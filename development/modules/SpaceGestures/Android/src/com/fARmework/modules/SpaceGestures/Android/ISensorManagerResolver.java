package com.fARmework.modules.SpaceGestures.Android;

import android.content.*;
import android.hardware.*;

public interface ISensorManagerResolver
{
	void setContext(Context context);
	
	SensorManager resolve();
}
