
import java.net.*;
import java.io.*;

public class Client 
{
	
	public static void main(String[] args) throws IOException 
	{
		String hostname = "localhost";
		int port = 6666;
		
		Socket socket = null;
		PrintWriter out = null;
		
		try
		{
			socket = new Socket(hostname, port);
			out = new PrintWriter(socket.getOutputStream(), true);
		}
		catch(UnknownHostException e)
		{
			System.err.println("Could not connect to host: " + hostname + " on port: " + port);
			System.exit(1);
		}
		catch(IOException e)
		{
			System.err.println("Could not get I/O for the connection to: " + hostname);
			System.exit(1);
		}
		
		BufferedReader standardInput = new BufferedReader(new InputStreamReader(System.in));
		
		String fromUser;
		
		while((fromUser = standardInput.readLine()) != null)
		{
			out.println(fromUser);
		}
		
		out.close();
		standardInput.close();
		socket.close();
	}

}
