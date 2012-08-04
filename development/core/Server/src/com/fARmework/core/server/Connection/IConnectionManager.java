package com.fARmework.core.server.Connection;

import java.util.*;

public interface IConnectionManager 
{
	void startConnection();
	
	<T> void registerDataHandler(Class<T> dataClass, IDataHandler<T> handler);
	<T> void registerDataHandler(Class<T> dataClass, IDataHandler<T> handler, int clientID);
	<T> void registerDataHandler(Class<T> dataClass, IDataHandler<T> handler, List<Integer> clientIDs);
	
	void clearDataHandlers();
	void clearDataHandlers(int clientID);
	void clearDataHandlers(List<Integer> clientIDs);
	<T> void clearDataHandlers(Class<T> dataClass, int clientID);
	<T> void clearDataHandlers(Class<T> dataClass, List<Integer> clientIDs);
	
	void send(Object data);
	void send(Object data, int clientID);
	void send(Object data, List<Integer> clientIDs);
}
