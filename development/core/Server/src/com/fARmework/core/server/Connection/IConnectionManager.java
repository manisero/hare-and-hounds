package com.fARmework.core.server.Connection;

import java.util.*;

public interface IConnectionManager 
{
	void startConnection();
	
	<T> void registerDataHandler(Class<T> dataClass, IDataHandler<T> handler);
	void clearDataHandlers();
	
	void send(Object data);
	void send(Object data, int clientID);
	void send(Object data, List<Integer> clientIDs);
}
