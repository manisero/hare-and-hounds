package com.fARmework.HareAndHounds.Server.Logic._impl;

import com.fARmework.HareAndHounds.Server.Logic.*;

import java.util.*;

public class GameList implements IGameList
{
	private Map<Integer, GameListItem> _games = new LinkedHashMap<Integer, GameListItem>();

	@Override
	public void clear()
	{
		_games.clear();
	}

	@Override
	public boolean containsKey(Object key)
	{
		return _games.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value)
	{
		return _games.containsValue(value);
	}

	@Override
	public Set<java.util.Map.Entry<Integer, GameListItem>> entrySet()
	{
		return _games.entrySet();
	}

	@Override
	public GameListItem get(Object key)
	{
		return _games.get(key);
	}

	@Override
	public boolean isEmpty()
	{
		return _games.isEmpty();
	}

	@Override
	public Set<Integer> keySet()
	{
		return _games.keySet();
	}

	@Override
	public GameListItem put(Integer key, GameListItem value)
	{
		return _games.put(key, value);
	}

	@Override
	public void putAll(Map<? extends Integer, ? extends GameListItem> m)
	{
		_games.putAll(m);
	}

	@Override
	public GameListItem remove(Object key)
	{
		return _games.remove(key);
	}

	@Override
	public int size()
	{	
		return _games.size();
	}

	@Override
	public Collection<GameListItem> values()
	{
		return _games.values();
	}
}
