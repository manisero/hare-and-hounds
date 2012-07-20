package com.fARmework.core.data.Impl;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fARmework.core.data.IDataFactory;
import com.google.inject.Singleton;

@Singleton
public class DataFactory implements IDataFactory
{
	private Map<String, Class<?>> _mappings = new LinkedHashMap<String, Class<?>>();
	
	@Override
	public void register(Class<?> dataClass)
	{
		_mappings.put(dataClass.getCanonicalName(), dataClass);
	}
	
	@Override
	public boolean isRegistered(String dataType)
	{
		return _mappings.containsKey(dataType);
	}
	
	@Override
	public Class<?> getDataClass(String dataType)
	{
		return isRegistered(dataType) ? _mappings.get(dataType) : null;
	}
}
