package com.fARmework.client.Logic.Impl;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.fARmework.client.Logic.ISocketCreator;

public class SocketCreator implements ISocketCreator
{
	@Override
	public Socket create(String address, int port) throws UnknownHostException, IOException
	{
		return new Socket(address, port);
	}
}
