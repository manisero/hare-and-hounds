package com.fARmework.utils.Android.RoboGuice;

import android.content.*;

import com.google.inject.*;

public interface IContextProvider extends Provider<Context>
{
	void set(Context context);
}
