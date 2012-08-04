package com.fARmework.core.server.Connection.Impl;

import java.util.List;

import com.fARmework.core.server.Connection.IDataHandler;

public class DataHandlersCollection
{
	public <T> void register(Class<T> dataClass, IDataHandler<T> handler)
	{
		// TODO Auto-generated method stub
	}
	
	public <T> void register(Class<T> dataClass, IDataHandler<T> handler, int clientID)
	{
		// TODO Auto-generated method stub
	}
	
	public <T> void register(Class<T> dataClass, IDataHandler<T> handler, List<Integer> clientIDs)
	{
		// TODO Auto-generated method stub
	}
	
	public void clear()
	{
		// TODO Auto-generated method stub
	}
	
	public void clear(int clientID)
	{
		// TODO Auto-generated method stub
	}
	
	public void clear(List<Integer> clientIDs)
	{
		// TODO Auto-generated method stub
	}
	
	public <T> void clear(Class<T> dataClass, int clientID)
	{
		// TODO Auto-generated method stub
	}
	
	public <T> void clear(Class<T> dataClass, List<Integer> clientIDs)
	{
		// TODO Auto-generated method stub
	}
}
