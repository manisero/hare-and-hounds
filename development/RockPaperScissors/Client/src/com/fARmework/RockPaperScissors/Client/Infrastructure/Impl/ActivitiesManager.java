package com.fARmework.RockPaperScissors.Client.Infrastructure.Impl;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fARmework.RockPaperScissors.Client.R;
import com.fARmework.RockPaperScissors.Client.Activities.BoundActivity;
import com.fARmework.RockPaperScissors.Client.Activities.GameListActivity;
import com.fARmework.RockPaperScissors.Client.Activities.GameModeActivity;
import com.fARmework.RockPaperScissors.Client.Activities.HostingActivity;
import com.fARmework.RockPaperScissors.Client.Infrastructure.IActivitiesManager;
import com.fARmework.RockPaperScissors.Client.ViewModels.GameListViewModel;
import com.fARmework.RockPaperScissors.Client.ViewModels.GameModeViewModel;
import com.fARmework.RockPaperScissors.Client.ViewModels.HostingViewModel;
import com.fARmework.RockPaperScissors.Client.ViewModels.ViewModel;

import android.content.Intent;

@SuppressWarnings("rawtypes")
public class ActivitiesManager implements IActivitiesManager
{
	private Map<Class<? extends ViewModel>, Integer> _layouts = new LinkedHashMap<Class<? extends ViewModel>, Integer>();
	private Map<Class<? extends ViewModel>, Class<? extends BoundActivity>> _activities = new LinkedHashMap<Class<? extends ViewModel>, Class<? extends BoundActivity>>();
	
	private BoundActivity _currentActivity;

	public ActivitiesManager()
	{
		// GameMode
		_layouts.put(GameModeViewModel.class, R.layout.game_mode);
		_activities.put(GameModeViewModel.class, GameModeActivity.class);
		
		// Hosting
		_layouts.put(HostingViewModel.class, R.layout.hosting);
		_activities.put(HostingViewModel.class, HostingActivity.class);
		
		// GameList
		_layouts.put(GameListViewModel.class, R.layout.game_list);
		_activities.put(GameListViewModel.class, GameListActivity.class);
	}
	
	@Override
	public int getLayout(Class<? extends ViewModel> viewModelClass)
	{
		return _layouts.get(viewModelClass);
	}
	
	@Override
	public void setCurrentActivity(BoundActivity activity)
	{
		_currentActivity = activity;
	}
	
	@Override
	public <T extends ViewModel> void startActivity(Class<T> viewModelClass)
	{
		if (!_activities.containsKey(viewModelClass))
		{
			return;
		}
		
		_currentActivity.startActivity(new Intent(_currentActivity, _activities.get(viewModelClass)));
	}
}
