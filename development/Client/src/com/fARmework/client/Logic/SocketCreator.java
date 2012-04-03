package com.fARmework.client.Logic;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketCreator implements ISocketCreator
{
	// ISocketCreator members:
	@Override
	public Socket create(String address, int port) throws UnknownHostException, IOException
	{
		return new Socket(address, port);
	}
}
