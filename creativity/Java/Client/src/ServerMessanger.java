import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerMessanger implements Runnable 
{
	private Socket _socket;
	
	public ServerMessanger(Socket socket)
	{
		_socket = socket;
	}
	
	@Override
	public void run() 
	{
		BufferedReader standardInput = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = null;
		String message = null;
		
		try
		{
			out = new PrintWriter(_socket.getOutputStream(), true);
			
			while((message = standardInput.readLine()) != null)
			{
				out.println(message);
			}
		}
		catch(IOException e)
		{
			System.err.println("Could not send a message to server");
			return;
		}
	}
}
