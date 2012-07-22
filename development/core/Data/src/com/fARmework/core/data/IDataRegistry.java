package com.fARmework.core.data;

public interface IDataRegistry
{
	void register(Class<?> dataClass);
	boolean isRegistered(String dataType);
	Class<?> getDataClass(String dataType);
}
