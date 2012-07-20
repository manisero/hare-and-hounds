package com.fARmework.core.data;

public interface IDataFactory
{
	void register(Class<?> dataClass);
	boolean isRegistered(String dataType);
	Class<?> getDataClass(String dataType);
}
