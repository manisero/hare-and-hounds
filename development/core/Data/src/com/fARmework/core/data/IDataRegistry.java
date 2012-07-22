package com.fARmework.core.data;

public interface IDataRegistry
{
	void register(Class<?> dataClass);
	
	boolean isRegistered(String dataType);
	boolean isRegistered(Class<?> dataClass);
	
	String getDataType(Class<?> dataClass);
	Class<?> getDataClass(String dataType);
}
