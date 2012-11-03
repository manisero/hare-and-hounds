package com.fARmework.modules.SpaceGraphics.Android;

import android.content.*;
import android.view.*;

public interface IDisplayResolver
{
	void setContext(Context context);
	
	Display resolve();
}
