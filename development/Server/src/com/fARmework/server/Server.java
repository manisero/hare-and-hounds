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
		
		PrintWriter out = null;
		
		try
		{
			out = new PrintWriter(clientSocket.getOutputStream(), true);
		}
		catch(IOException e)
		{
			System.out.print("Could not get client's output stream");
			System.exit(1);
		}
		
		BufferedReader standardInput = new BufferedReader(new InputStreamReader(System.in));
		
		String clientInput;
		
		while((clientInput = standardInput.readLine()) != null)
		{
			out.println(clientInput);
		}
		
		out.close();
		standardInput.close();
		clientSocket.close();
		serverSocket.close();					
	}

}
