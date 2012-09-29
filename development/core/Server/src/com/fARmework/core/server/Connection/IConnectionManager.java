package com.fARmework.core.server.Connection;

import java.util.*;

public interface IConnectionManager 
{
	void startConnection();
	
	<T> void registerDataHandler(Class<T> dataClass, IDataHandler<T> handler);
	<T> void registerDataHandler(Class<T> dataClass, int clientID, IDataHandler<T> handler);
	<T> void registerDataHandler(Class<T> dataClass, List<Integer> clientIDs, IDataHandler<T> handler);
	
	void unregisterDataHandlers();
	void unregisterDataHandlers(int clientID);
	void unregisterDataHandlers(List<Integer> clientIDs);
	<T> void unregisterDataHandlers(Class<T> dataClass);
	<T> void unregisterDataHandlers(Class<T> dataClass, int clientID);
	<T> void unregisterDataHandlers(Class<T> dataClass, List<Integer> clientIDs);
	
	void send(Object data);
	void send(Object data, int clientID);
	void send(Object data, List<Integer> clientIDs);
}
