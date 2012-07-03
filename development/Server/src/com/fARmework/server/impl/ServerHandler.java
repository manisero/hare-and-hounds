package com.fARmework.server.impl;

import com.fARmework.server.*;
import com.google.gson.*;
import com.google.inject.*;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.group.*;

public class ServerHandler extends GroupChannelHandler
{
	private IMessageProcessor _messageProcessor;
	
	private ChannelGroup _channelGroup;
	
	private MessageFactory _factory;
	
	@Inject
	public ServerHandler(
			IMessageProcessor processor, 
			ChannelGroup channelGroup, 
			MessageFactory factory)
	{
		_messageProcessor = processor;
		_channelGroup = channelGroup;
		_factory = factory;
	}
	
	@Override
	public void channelConnected(ChannelHandlerContext context, ChannelStateEvent event)
	{
		_channelGroup.add(context.getChannel());
	}
	
	@Override
	public void messageReceived(ChannelHandlerContext context, MessageEvent event)
	{		
		Message message = (new Gson()).fromJson((String) event.getMessage(), Message.class);
		
		_messageProcessor.process(message);
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext context, ExceptionEvent event)
	{
		event.getChannel().close();
	}

	@Override
	public void send(Object object) 
	{
		Message message = _factory.getMessage(object);
		
		_channelGroup.write((new Gson()).toJson(message));
	}
}
