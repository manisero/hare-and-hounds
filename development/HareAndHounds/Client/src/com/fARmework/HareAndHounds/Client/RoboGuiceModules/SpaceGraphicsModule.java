package com.fARmework.HareAndHounds.Client.RoboGuiceModules;

import com.fARmework.modules.SpaceGraphics.Android.Orientation.*;
import com.fARmework.modules.SpaceGraphics.Android.Orientation._impl.*;
import com.fARmework.modules.SpaceGraphics.Android.Projection.*;
import com.fARmework.modules.SpaceGraphics.Android.Projection._impl.*;
import com.google.inject.*;

public class SpaceGraphicsModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		//bind(ISensorManagerResolver.class).to(SensorManagerResolver.class).asEagerSingleton();
		//bind(IDisplayResolver.class).to(DisplayResolver.class).asEagerSingleton();
		//bind(IOrientationProvider.class).to(SensorOrientationProvider.class);
		//bind(IDirectionProvider.class).to(com.fARmework.HareAndHounds.Client.Logic.IDirectionProvider.class);
		
		//bind(IGLHandler.class).to(GLHandler.class);
		//bind(IGraphicsRenderer.class).to(GraphicsRenderer.class);
	}
}
