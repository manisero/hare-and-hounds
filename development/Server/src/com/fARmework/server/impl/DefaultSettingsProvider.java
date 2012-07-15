package com.fARmework.server.impl;

import com.fARmework.server.*;

public class DefaultSettingsProvider implements ISettingsProvider 
{
	@Override
	public int getPort()
	{
		return 6969;
	}
}
