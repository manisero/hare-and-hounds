package com.fARmework.client.Logic;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public interface ISocketCreator
{
	Socket create(String address, int port)  throws UnknownHostException, IOException;
}
