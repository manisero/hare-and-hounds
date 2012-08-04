package com.fARmework.core.server.Connection.Impl;

import com.fARmework.core.data.*;
import com.fARmework.core.server.Connection.*;
import com.fARmework.core.server.Data.ClientConnectedInfo;
import com.fARmework.core.server.Data.ClientDisconnectedInfo;
import com.fARmework.core.server.Data.ConnectionExceptionInfo;
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
			handleData(channel.getId(), new ClientConnectedInfo());
		}
		
		@Override
		public void channelDisconnected(ChannelHandlerContext context, ChannelStateEvent event)
		{
			int channelID = context.getChannel().getId();
			_channels.remove(channelID);
			handleData(channelID, new ClientDisconnectedInfo());
		}
		
		@Override
		public void messageReceived(ChannelHandlerContext context, MessageEvent event)
		{
			Message message = _dataService.deserializeMessage(event.getMessage().toString());
			handleData(event.getChannel().getId(), _dataService.fromMessage(message));
		}
		
		@Override
		public void exceptionCaught(ChannelHandlerContext context, ExceptionEvent event)
		{
			event.getChannel().close();
			handleData(event.getChannel().getId(), new ConnectionExceptionInfo(event.getCause()));
		}
	}
	
	private ISettingsProvider _settingsProvider;
	private IDataService _dataService;
	
	@SuppressWarnings("rawtypes")
	private Map<Class<?>, IDataHandler> _dataHandlers = new LinkedHashMap<Class<?>, IDataHandler>();
	private Map<Integer, Channel> _channels = new LinkedHashMap<Integer, Channel>();
	
	@Inject
	public ConnectionManager(ISettingsProvider settingsProvider, IDataService dataService)
	{
		_settingsProvider = settingsProvider;
		_dataService = dataService;
	}
	
	@Override
	public void startConnection()
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
	public <T> void registerDataHandler(Class<T> dataClass, IDataHandler<T> handler)
	{
		_dataHandlers.put(dataClass, handler);
	}
	
	@Override
	public <T> void registerDataHandler(Class<T> dataClass, IDataHandler<T> handler, int clientID)
	{
		// TODO Auto-generated method stub
	}

	@Override
	public <T> void registerDataHandler(Class<T> dataClass, IDataHandler<T> handler, List<Integer> clientIDs)
	{
		// TODO Auto-generated method stub
	}
	
	@Override
	public void clearDataHandlers()
	{
		_dataHandlers.clear();
	}
	
	@Override
	public void clearDataHandlers(int clientID)
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void clearDataHandlers(List<Integer> clientIDs)
	{
		// TODO Auto-generated method stub
	}

	@Override
	public <T> void clearDataHandlers(Class<T> dataClass)
	{
		// TODO Auto-generated method stub
	}
	
	@Override
	public <T> void clearDataHandlers(Class<T> dataClass, int clientID)
	{
		// TODO Auto-generated method stub
	}

	@Override
	public <T> void clearDataHandlers(Class<T> dataClass, List<Integer> clientIDs)
	{
		// TODO Auto-generated method stub
	}
	
	@Override 
	public void send(Object data)
	{
		for (Map.Entry<Integer, Channel> channel : _channels.entrySet())
		{
			channel.getValue().write(_dataService.toSerializedMessage(data));
		}
	}
	
	@SuppressWarnings("unchecked")
	private void handleData(int clientID, Object data)
	{
		if (_dataHandlers.containsKey(data.getClass()))
		{
			_dataHandlers.get(data.getClass()).handle(clientID, data);
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
