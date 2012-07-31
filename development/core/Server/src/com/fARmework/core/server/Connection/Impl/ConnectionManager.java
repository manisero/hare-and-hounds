package com.fARmework.core.server.Connection.Impl;

import com.fARmework.core.data.*;
import com.fARmework.core.server.Connection.*;
import com.fARmework.core.server.Infrastructure.ISettingsProvider;
import com.google.inject.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import org.jboss.netty.bootstrap.*;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.*;
import org.jboss.netty.handler.codec.serialization.*;

public class ConnectionManager implements IConnectionManager
{	
	private class ChannelHandler extends SimpleChannelUpstreamHandler
	{
		@Override
		public void channelConnected(ChannelHandlerContext context, ChannelStateEvent event)
		{
			Channel channel = context.getChannel();
			_channels.put(channel.getId(), channel);
			_connectionHandler.onClientConnected(channel.getId());
		}
		
		@Override
		public void channelDisconnected(ChannelHandlerContext context, ChannelStateEvent event)
		{
			int channelID = context.getChannel().getId();
			_channels.remove(channelID);
			_connectionHandler.onClientDisconnected(channelID);
		}
		
		@Override
		public void messageReceived(ChannelHandlerContext context, MessageEvent event)
		{
			Message message = _dataService.deserializeMessage(event.getMessage().toString());
			_connectionHandler.onDataReceived(event.getChannel().getId(), message.getType(), _dataService.fromMessage(message));
		}
		
		@Override
		public void exceptionCaught(ChannelHandlerContext context, ExceptionEvent event)
		{
			event.getChannel().close();
			_connectionHandler.onClientConnectionException(event.getChannel().getId(), event.getCause());
		}
	}
	
	private ISettingsProvider _settingsProvider;
	private IDataService _dataService;
	
	private IConnectionHandler _connectionHandler;
	private Map<Integer, Channel> _channels = new LinkedHashMap<Integer, Channel>();
	
	@Inject
	public ConnectionManager(ISettingsProvider settingsProvider, IDataService dataService)
	{
		_settingsProvider = settingsProvider;
		_dataService = dataService;
	}
	
	@Override
	public void startConnection(IConnectionHandler connectionHandler)
	{
		_connectionHandler = connectionHandler;
		
		ServerBootstrap bootstrap = new ServerBootstrap(
				new NioServerSocketChannelFactory(
						Executors.newCachedThreadPool(),
						Executors.newCachedThreadPool()));
		
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() 
		{	
			@Override
			public ChannelPipeline getPipeline() throws Exception 
			{
				return Channels.pipeline(new ObjectEncoder(),
										 new ObjectDecoder(ClassResolvers.cacheDisabled(getClass().getClassLoader())),
										 new ChannelHandler());
			}
		});
		
		bootstrap.setOption("tcpNoDelay", true);
        bootstrap.setOption("keepAlive", true);
		
		bootstrap.bind(new InetSocketAddress(_settingsProvider.getPort()));
	}
	
	@Override 
	public void send(Object data)
	{
		for (Map.Entry<Integer, Channel> channel : _channels.entrySet())
		{
			channel.getValue().write(_dataService.toSerializedMessage(data));
		}
	}
	
	@Override
	public void send(Object data, int clientID)
	{	
		Channel channel = _channels.get(clientID);
		channel.write(_dataService.toSerializedMessage(data));
	}
	
	@Override
	public void send(Object data, List<Integer> clientIDs)
	{
		for (Integer clientID : clientIDs)
		{
			send(data, clientID);
		}
	}
}
