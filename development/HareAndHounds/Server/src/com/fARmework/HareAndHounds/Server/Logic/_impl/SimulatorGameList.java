package com.fARmework.HareAndHounds.Server.Logic._impl;

import com.fARmework.HareAndHounds.Server.Infrastructure.*;
import com.fARmework.HareAndHounds.Server.Logic.*;

import java.util.*;

public class SimulatorGameList implements IGameList
{
	private ISimulatorDataProvider _dataProvider;
	
	public SimulatorGameList(ISimulatorDataProvider dataProvider)
	{
		_dataProvider = dataProvider;
	}
	
	@Override
	public void clear()
	{		
	}

	@Override
	public boolean containsKey(Object key)
	{
		if (key.equals(_dataProvider.getHareID()))
		{
			return true;
		}
		
		return false;
	}

	@Override
	public boolean containsValue(Object value)
	{
		GameListItem gameListItem = new GameListItem(_dataProvider.getHareID(), _dataProvider.getHareHostname(), _dataProvider.getInitialHarePosition());
		
		if (value.equals(gameListItem))
		{
			return true;
		}
		
		return false;
	}

	@Override
	public Set<Map.Entry<Integer, GameListItem>> entrySet()
	{	
		Map.Entry<Integer, GameListItem> entry = new Map.Entry<Integer, GameListItem>()
		{
			@Override
			public Integer getKey()
			{
				return _dataProvider.getHareID();
			}

			@Override
			public GameListItem getValue()
			{
				return new GameListItem(_dataProvider.getHareID(), _dataProvider.getHareHostname(), _dataProvider.getInitialHarePosition());
			}

			@Override
			public GameListItem setValue(GameListItem value)
			{
				return value;
			}
		};
		
		Set<Map.Entry<Integer, GameListItem>> entrySet = new HashSet<Map.Entry<Integer,GameListItem>>();
		
		entrySet.add(entry);
		
		return entrySet;
	}

	@Override
	public GameListItem get(Object key)
	{
		if (key.equals(_dataProvider.getHareID()))
		{
			return new GameListItem(_dataProvider.getHareID(), _dataProvider.getHareHostname(), _dataProvider.getInitialHarePosition());
		}
		
		return null;
	}

	@Override
	public boolean isEmpty()
	{
		return false;
	}

	@Override
	public Set<Integer> keySet()
	{
		Set<Integer> keySet = new HashSet<Integer>();
		
		keySet.add(_dataProvider.getHareID());
		
		return keySet;
	}

	@Override
	public GameListItem put(Integer key, GameListItem value)
	{
		return value;
	}

	@Override
	public void putAll(Map<? extends Integer, ? extends GameListItem> m)
	{	
	}

	@Override
	public GameListItem remove(Object key)
	{
		return null;
	}

	@Override
	public int size()
	{
		return 1;
	}

	@Override
	public Collection<GameListItem> values()
	{
		Collection<GameListItem> items = new ArrayList<GameListItem>();
		
		items.add(new GameListItem(_dataProvider.getHareID(), _dataProvider.getHareHostname(), _dataProvider.getInitialHarePosition()));
		
		return items;
	}
}
