package com.fARmework.core.server.impl;

import com.fARmework.core.data.ISerializationService;
import com.fARmework.core.data.Message;
import com.fARmework.core.server.*;
import com.google.inject.*;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.group.*;

public class ServerHandler extends GroupChannelHandler
{
	private IMessageProcessor _messageProcessor;
	private ISerializationService _serializationService;
	
	private ChannelGroup _channelGroup;
	
	private MessageFactory _factory;
	
	@Inject
	public ServerHandler(
			IMessageProcessor processor,
			ISerializationService serializationService,
			ChannelGroup channelGroup, 
			MessageFactory factory)
	{
		_messageProcessor = processor;
		_serializationService = serializationService;
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
		Message message = _serializationService.deserializeMessage((String)event.getMessage());
		
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
		
		_channelGroup.write(_serializationService.serialize(message));
	}
}
