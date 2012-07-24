package com.fARmework.core.server.impl;

import com.fARmework.core.data.IDataService;
import com.fARmework.core.data.Message;
import com.fARmework.core.server.*;
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
	private ISettingsProvider _settingsProvider;
	
	private IConnectionHandler _connectionHandler;
	
	private IDataService _dataService;
	
	private Map<Integer, Channel> _channels;
	
	@Inject
	public ConnectionManager(ISettingsProvider settingsProvider, 
			IConnectionHandler connectionHandler, IDataService dataService)
	{
		_settingsProvider = settingsProvider;
		_connectionHandler = connectionHandler;
		_dataService = dataService;
		_channels = new LinkedHashMap<Integer, Channel>();
	}
	
	@Override
	public void start()
	{
		ServerBootstrap bootstrap = new ServerBootstrap(
				new NioServerSocketChannelFactory(
						Executors.newCachedThreadPool(),
						Executors.newCachedThreadPool()));
		
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() 
		{	
			@Override
			public ChannelPipeline getPipeline() throws Exception 
			{
				return Channels.pipeline(
						new ObjectEncoder(),
						new ObjectDecoder(
								ClassResolvers.cacheDisabled(getClass().getClassLoader())),
						new SimpleChannelUpstreamHandler()
						{
							@Override
							public void channelConnected(ChannelHandlerContext context, 
									ChannelStateEvent event)
							{
								Channel channel = context.getChannel();
								
								_channels.put(channel.getId(), channel);
								
								_connectionHandler.onClientConnected(
										channel.getId());
							}
							
							@Override
							public void messageReceived(ChannelHandlerContext context, 
									MessageEvent event)
							{
								int clientID = event.getChannel().getId();
								
								Message message = _dataService.deserializeMessage(
										event.getMessage().toString());
								
								Object data = _dataService.fromMessage(message);
								
								_connectionHandler.onMessageReceived(
										clientID, message.getType(), data);
							}
							
							@Override
							public void exceptionCaught(ChannelHandlerContext context, 
									ExceptionEvent event)
							{
								event.getChannel().close();
								
								_connectionHandler.onClientConnectionException(
										event.getChannel().getId(), event.getCause());
							}							
						});
			}
		});
		
		bootstrap.setOption("tcpNoDelay", true);
        bootstrap.setOption("keepAlive", true);
		
		bootstrap.bind(new InetSocketAddress(_settingsProvider.getPort()));
	}
	
	@Override 
	public void send(Object data)
	{
		for(Map.Entry<Integer, Channel> channel : _channels.entrySet())
		{
			channel.getValue().write(_dataService.toSerializedMessage(data));
		}
	}
	
	@Override
	public void send(int clientID, Object data)
	{	
		Channel channel = _channels.get(clientID);
		
		channel.write(_dataService.toSerializedMessage(data));
	}
	
	@Override
	public void send(List<Integer> clientIDs, Object data)
	{
		for(Integer clientID : clientIDs)
		{
			Channel channel = _channels.get(clientID);
			
			channel.write(_dataService.toSerializedMessage(data));
		}
	}
}
