import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientListener 
{
	private static final String HOSTNAME = "localhost";
	private static final int PORT = 6666;
	
	public void communicate()
	{
		Socket socket = null;
		
		try
		{
			socket = new Socket(HOSTNAME, PORT);
		}
		catch(IOException e)
		{
			System.err.println("Could not connect to host: " + HOSTNAME);
			System.exit(1);
		}
		
		ServerMessanger messanger = new ServerMessanger(socket);
		
		(new Thread(messanger)).start();
		
		try
		{		
			String message;
			
			BufferedReader in = new BufferedReader(	new InputStreamReader(
													socket.getInputStream()));
	
			while((message = in.readLine()) != null)
			{
				System.out.println(message);
			}
		}
		catch(IOException e)
		{
			System.err.println("Could not read message from a server");
			System.exit(1);
		}
	}
}
