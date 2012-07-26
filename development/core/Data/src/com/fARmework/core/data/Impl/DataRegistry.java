package com.fARmework.core.data.Impl;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fARmework.core.data.IDataRegistry;

public class DataRegistry implements IDataRegistry
{
	private Map<String, Class<?>> _typeToClassMappings = new LinkedHashMap<String, Class<?>>();
	private Map<Class<?>, String> _classToTypeMappings = new LinkedHashMap<Class<?>, String>();
	
	public DataRegistry()
	{
		register(String.class);
	}
	
	@Override
	public void register(Class<?> dataClass)
	{
		String dataType = dataClass.getCanonicalName();
		
		_typeToClassMappings.put(dataType, dataClass);
		_classToTypeMappings.put(dataClass, dataType);
	}
	
	@Override
	public boolean isRegistered(String dataType)
	{
		return _typeToClassMappings.containsKey(dataType);
	}
	
	@Override
	public boolean isRegistered(Class<?> dataClass)
	{
		return _classToTypeMappings.containsKey(dataClass);
	}
	
	@Override
	public String getDataType(Class<?> dataClass)
	{
		return isRegistered(dataClass) ? _classToTypeMappings.get(dataClass) : null;
	}
	
	@Override
	public Class<?> getDataClass(String dataType)
	{
		return isRegistered(dataType) ? _typeToClassMappings.get(dataType) : null;
	}
}
