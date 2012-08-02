
public class Client 
{
	public static void main(String[] args) 
	{
		ClientListener listener = new ClientListener();
		
		listener.communicate();
	}
}
