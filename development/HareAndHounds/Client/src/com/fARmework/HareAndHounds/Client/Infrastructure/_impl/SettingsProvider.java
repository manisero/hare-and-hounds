package com.fARmework.HareAndHounds.Client.Infrastructure._impl;

import com.fARmework.HareAndHounds.Client.R;
import com.fARmework.HareAndHounds.Client.Infrastructure.*;
import com.fARmework.HareAndHounds.Client.Infrastructure.ISettingsProvider;
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
	public boolean getPlayCheckpointSound()
	{
		return getBool("play_checkpoint_sound", ResourcesProvider.getBoolean(R.bool.defaultPlayCheckpointSound));
	}
	
	@Override
	public void setPlayCheckpointSound(boolean playCheckpointSound)
	{
		setBool("play_checkpoint_sound", playCheckpointSound);
	}

	@Override
	public int getCheckpointSoundMinPeriod()
	{
		return ResourcesProvider.getInteger(R.integer.checkpointSoundMinPeriod);
	}

	@Override
	public int getCheckpointSoundMaxPeriod()
	{
		return ResourcesProvider.getInteger(R.integer.checkpointSoundMaxPeriod);
	}
	
	@Override
	public boolean getDisplayCheckpointCameraPreview()
	{
		return getBool("display_checkpoint_camera_preview", ResourcesProvider.getBoolean(R.bool.defaultDisplayCheckpointCameraPreview));
	}
	
	@Override
	public void setDisplayCheckpointCameraPreview(boolean displayCheckpointCameraPreview)
	{
		setBool("display_checkpoint_camera_preview", displayCheckpointCameraPreview);
	}
	
	@Override
	public int getShortNotificationMaxLength()
	{
		return ResourcesProvider.getInteger(R.integer.shortNotificationMaxLength);
	}
	
	@Override
	public int getSinglePositionUpdateDelay()
	{
		return ResourcesProvider.getInteger(R.integer.singlePositionUpdateDelay);
	}
}
