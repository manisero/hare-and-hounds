package com.fARmework.HareAndHounds.Client.RoboGuiceModules;

import com.fARmework.modules.SpaceGraphics.Android.*;
import com.google.inject.*;

public class SpaceGraphicsModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		bind(IDirectionProvider.class).to(com.fARmework.HareAndHounds.Client.Logic.IDirectionProvider.class);
	}
}
