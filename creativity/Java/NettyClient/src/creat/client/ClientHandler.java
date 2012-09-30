package creat.client;

import com.google.gson.*;
import org.jboss.netty.channel.*;

public class ClientHandler extends SimpleChannelUpstreamHandler
{		
	@Override 
	public void messageReceived(ChannelHandlerContext context, MessageEvent event)
	{
		Gson gson = new Gson();
		
		Message message = gson.fromJson((String) event.getMessage(), Message.class);
		
		System.out.println("Message type: " + message.getType());
		
		System.out.println("Message body: " + message.getObject().toString());
		
		System.out.println("");
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext context, ExceptionEvent event)
	{
		event.getChannel().close();
	}
}
