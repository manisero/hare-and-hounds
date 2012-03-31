
import java.net.*;
import java.io.*;

public class Client 
{
	
	public static void main(String[] args) throws IOException 
	{
		String hostname = "localhost";
		int port = 6666;
		
		Socket socket = null;
		
		try
		{
			socket = new Socket(hostname, port);
		}
		catch(UnknownHostException e)
		{
			System.err.println("Could not connect to host: " + hostname + " on port: " + port);
			System.exit(1);
		}
		
		BufferedReader in = new BufferedReader(	new InputStreamReader(
												socket.getInputStream()));
		
		String message;
		
		while((message = in.readLine()) != null)
		{
			System.out.println(message);
		}
		
		in.close();
		socket.close();
	}

}
