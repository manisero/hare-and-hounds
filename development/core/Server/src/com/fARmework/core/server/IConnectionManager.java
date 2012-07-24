package com.fARmework.core.server;

import java.util.*;

public interface IConnectionManager 
{
	void start();
	
	void send(Object data);
	
	void send(int clientID, Object data);
	
	void send(List<Integer> clientIDs, Object data);
}
