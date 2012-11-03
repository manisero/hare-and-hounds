package com.fARmework.modules.SpaceGraphics.Android.Orientation;

import android.content.*;
import android.view.*;

public interface IDisplayResolver
{
	void setContext(Context context);
	
	Display resolve();
}
