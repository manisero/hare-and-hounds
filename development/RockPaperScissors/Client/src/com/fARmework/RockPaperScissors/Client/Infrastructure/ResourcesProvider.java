package com.fARmework.RockPaperScissors.Client.Infrastructure;

import com.fARmework.RockPaperScissors.Client.R;
import com.fARmework.RockPaperScissors.Data.GameResultInfo.GameResult;
import com.fARmework.RockPaperScissors.Data.GestureInfo.GestureType;

import android.content.res.Resources;

public class ResourcesProvider
{
	private static Resources _resources;
	
	public static void setResources(Resources resources)
	{
		_resources = resources;
	}
	
	public static String getString(int resourceID)
	{
		return _resources.getString(resourceID);
	}
	
	public static int getInteger(int resourceID)
	{
		return _resources.getInteger(resourceID);
	}
	
	public static String getGestureString(GestureType gesture)
	{
		switch (gesture)
		{
			case Rock:
				return getString(R.string.gestures_rock);
			case Paper:
				return getString(R.string.gestures_paper);
			default:
				return getString(R.string.gestures_scissors);
		}
	}
	
	public static String getGameResultString(GameResult result)
	{
		switch (result)
		{
			case Victory:
				return getString(R.string.game_victory);
			case Defeat:
				return getString(R.string.game_defeat);
			default:
				return getString(R.string.game_draw);
		}
	}
}
