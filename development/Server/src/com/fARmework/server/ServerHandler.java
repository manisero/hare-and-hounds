package com.fARmework.server;

import com.google.gson.*;
import org.jboss.netty.channel.*;

public class ServerHandler extends SimpleChannelUpstreamHandler
{
	private IMessageProcessor _messageProcessor;
	
	public ServerHandler(IMessageProcessor processor)
	{
		_messageProcessor = processor;
	}
	
	@Override
	public void messageReceived(ChannelHandlerContext context, MessageEvent event)
	{		
		Gson gson = new Gson();
		
		Message message = gson.fromJson((String) event.getMessage(), Message.class);
		
		_messageProcessor.process(message);
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext context, ExceptionEvent event)
	{
		event.getChannel().close();
	}
}
