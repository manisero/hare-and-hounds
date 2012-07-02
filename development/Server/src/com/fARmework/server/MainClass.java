package com.fARmework.server;

public class MainClass 
{
	public static void main(String[] args) 
	{
		IMessanger messanger;
		
		if(args.length > 0 && Boolean.parseBoolean(args[0]))
		{
			messanger = new Client("localhost", "6969");
		}
		else
		{
			messanger = new Server("6969");
		}
		
		messanger.start();
		
		new UserInput(messanger).readAndSend();
		
	}
}
