package com.fARmework.modules.PositionTracking.Android;

import android.content.Context;
import android.location.LocationManager;

public interface ILocationManagerResolver
{
	void setContext(Context context);
	
	LocationManager resolve();
}
