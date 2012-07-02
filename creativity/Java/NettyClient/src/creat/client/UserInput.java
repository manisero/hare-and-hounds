package creat.client;

import java.io.*;

public class UserInput 
{
	private Client _client;
	
	private MessageFactory _factory;
	
	public UserInput()
	{
		_client = new Client("localhost", "6969");
		
		_client.start();
		
		_factory = MessageFactory.getInstance();
		
		_factory.register(String.class, "STRING_MESSAGE");
	}
	
	public void readAndSend()
	{
		BufferedReader bufferedReader = new BufferedReader(
												new InputStreamReader(
														System.in));
		while(true)
		{
			try 
			{
				String message = bufferedReader.readLine();
				
				_client.send(_factory.getMessage(message));
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
}
