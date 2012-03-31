package com.fARmework.server;

import java.net.*;
import java.io.*;

public class Server 
{
	public static void main(String[] args) throws IOException
	{
		int port = 6666;
		ServerSocket serverSocket = null;
		
		try
		{
			serverSocket = new ServerSocket(port);
		}
		catch(IOException e)
		{
			System.err.print("Could not listen on port: " + port);
			System.exit(1);
		}
		
		Socket clientSocket = null;
		
		try
		{
			clientSocket = serverSocket.accept();
		}
		catch(IOException e)
		{
			System.err.print("Client socket accept failed");
			System.exit(1);
		}
		
		BufferedReader in = new BufferedReader(	new InputStreamReader(
												clientSocket.getInputStream()));
		
		String input;
		
		while((input = in.readLine()) != null)
		{
			System.out.println(input);
		}
		
		in.close();
		clientSocket.close();
		serverSocket.close();					
	}

}
