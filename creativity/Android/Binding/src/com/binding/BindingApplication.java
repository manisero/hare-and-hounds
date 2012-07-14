package com.binding;

import gueei.binding.Binder;
import android.app.Application;

public class BindingApplication extends Application
{
	@Override
    public void onCreate()
	{
		super.onCreate();
		Binder.init(this);
    }
}
