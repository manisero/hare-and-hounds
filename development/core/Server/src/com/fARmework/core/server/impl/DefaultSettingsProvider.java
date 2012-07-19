package com.fARmework.core.server.impl;

import com.fARmework.core.server.*;

public class DefaultSettingsProvider implements ISettingsProvider 
{
	@Override
	public int getPort()
	{
		return 6969;
	}
}
