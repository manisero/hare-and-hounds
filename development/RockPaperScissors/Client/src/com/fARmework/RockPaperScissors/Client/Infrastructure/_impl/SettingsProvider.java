package com.fARmework.RockPaperScissors.Client.Infrastructure._impl;

import com.fARmework.RockPaperScissors.Client.R;
import com.fARmework.RockPaperScissors.Client.Infrastructure.*;
import com.fARmework.RockPaperScissors.Client.Infrastructure.ISettingsProvider;
import com.fARmework.utils.Android.Infrastructure.*;

public class SettingsProvider extends SettingsProviderBase implements ISettingsProvider
{
	@Override
	public String getServerAddress()
	{
		return getString("server_address", ResourcesProvider.getString(R.string.defaultServerAddress));
	}

	@Override
	public void setServerAddress(String serverAddress)
	{
		setString("server_address", serverAddress);
	}
	
	@Override
	public int getPort()
	{
		return getInt("port", ResourcesProvider.getInteger(R.integer.defaultPort));
	}
	
	@Override
	public void setPort(int port)
	{
		setInt("port", port);
	}
	
	@Override
	public String getUserName()
	{
		return getString("user_name", ResourcesProvider.getString(R.string.defaultUserName));
	}
	
	@Override
	public void setUserName(String userName)
	{
		setString("user_name", userName);
	}

	@Override
	public int getShortNotificationMaxLength()
	{
		return ResourcesProvider.getInteger(R.integer.shortNotificationMaxLength);
	}
}
