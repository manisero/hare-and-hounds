package com.fARmework.server.impl;

import com.fARmework.server.*;
import com.google.gson.*;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.group.*;

public class ServerHandler extends SimpleChannelUpstreamHandler
{
	private IMessageProcessor _messageProcessor;
	
	private ChannelGroup _channelGroup;
	
	public ServerHandler(IMessageProcessor processor, ChannelGroup channelGroup)
	{
		_messageProcessor = processor;
		_channelGroup = channelGroup;
	}
	
	@Override
	public void channelConnected(ChannelHandlerContext context, ChannelStateEvent event)
	{
		_channelGroup.add(context.getChannel());
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
