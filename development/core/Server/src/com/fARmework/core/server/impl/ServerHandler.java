package com.fARmework.core.server.impl;

import com.fARmework.core.data.IDataService;
import com.fARmework.core.data.Message;
import com.fARmework.core.server.*;
import com.google.inject.*;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.group.*;

public class ServerHandler extends GroupChannelHandler
{
	private IMessageProcessor _messageProcessor;
	private IDataService _dataService;
	
	private ChannelGroup _channelGroup;
	
	private MessageFactory _factory;
	
	@Inject
	public ServerHandler(
			IMessageProcessor processor,
			IDataService dataService,
			ChannelGroup channelGroup, 
			MessageFactory factory)
	{
		_messageProcessor = processor;
		_dataService = dataService;
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
		Message message = _dataService.deserializeMessage((String)event.getMessage());
		
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
		_channelGroup.write(_dataService.toSerializedMessage(object));
	}
}
