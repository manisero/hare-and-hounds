package com.fARmework.HareAndHounds.Client.RoboGuiceModules;

import android.content.*;

import com.fARmework.HareAndHounds.Client.Infrastructure.*;
import com.fARmework.HareAndHounds.Client.Infrastructure._impl.*;
import com.fARmework.HareAndHounds.Client.Logic.*;
import com.fARmework.HareAndHounds.Client.Logic._impl.*;
import com.fARmework.utils.Android.RoboGuice.*;
import com.google.inject.*;

public class HareAndHoundsModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		// RoboGuice
		bind(Context.class).toProvider(IContextProvider.class);
		
		// Infrastructure
		bind(ISettingsProvider.class).to(SettingsProvider.class).asEagerSingleton();
		
		// Logic
		bind(IDirectionProvider.class).to(DirectionProvider.class).asEagerSingleton();
		bind(ICheckpointSoundPeriodCalculator.class).to(CheckpointSoundPeriodCalculator.class);
	}
}
