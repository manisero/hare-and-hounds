package com.fARmework.server.impl;

import com.fARmework.server.*;
import java.net.*;

public class DefaultSettingsProvider implements ISettingsProvider 
{
	@Override
	public InetSocketAddress getSocketAddress() 
	{
		return new InetSocketAddress(6969);
	}
}
