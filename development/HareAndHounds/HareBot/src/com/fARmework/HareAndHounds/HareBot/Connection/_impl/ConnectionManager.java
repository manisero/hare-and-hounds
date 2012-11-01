package com.fARmework.HareAndHounds.HareBot.Connection._impl;

import com.fARmework.HareAndHounds.HareBot.Connection.*;
import com.fARmework.core.client.Connection.*;
import com.fARmework.core.client.Infrastructure.*;
import com.fARmework.core.data.*;
import com.google.inject.*;

import java.net.*;
import java.util.*;
import java.util.concurrent.*;

import org.jboss.netty.bootstrap.*;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.*;
import org.jboss.netty.handler.codec.serialization.*;

public class ConnectionManager implements IConnectionManager, IConnectionListener
{
	private class ChannelHandler extends SimpleChannelUpstreamHandler
	{
		@Override
		public void channelConnected(ChannelHandlerContext context, ChannelStateEvent event)
		{
			System.out.println("Connected to server");
		}
		
		@Override
		public void messageReceived(ChannelHandlerContext context, MessageEvent event)
		{
			System.out.println("Message: " + event.getMessage().toString());
			Message message = _dataService.deserializeMessage(event.getMessage().toString());
			onDataReceived(_dataService.fromMessage(message));	
		}
		
		@Override
		public void exceptionCaught(ChannelHandlerContext context, ExceptionEvent event)
		{
			event.getCause().printStackTrace();
			event.getChannel().close();
		}
	}	
	
	private final ISettingsProvider _settingsProvider;
	private final IDataService _dataService;
	
	@SuppressWarnings("rawtypes")
	private Map<Class<?>, IDataHandler> _dataHandlers = new LinkedHashMap<Class<?>, IDataHandler>();
	private Channel _channel;
	
	@Inject
	public ConnectionManager(ISettingsProvider settingsProvider, IDataService dataService)
	{
		_settingsProvider = settingsProvider;
		_dataService = dataService;
	}
	
	@Override
	public void connect()
	{
		connect(_settingsProvider.getServerAddress());
	}
	
	@Override
	public void connect(String serverAddress)
	{
		disconnect();
		
		ClientBootstrap bootstrap = new ClientBootstrap(new NioClientSocketChannelFactory(Executors.newCachedThreadPool(),
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
		
		ChannelFuture future = bootstrap.connect(
						new InetSocketAddress(serverAddress, _settingsProvider.getPort()));
		
		Channel channel = future.awaitUninterruptibly().getChannel();
		
		if (!future.isSuccess())
		{
			future.getCause().printStackTrace();
			bootstrap.releaseExternalResources();
		}
		
		_channel = channel;
	}
	
	@Override
	public void disconnect()
	{
		if (_channel != null && _channel.isOpen())
		{
			_channel.close();
			_channel = null;
		}
	}
	
	@Override
	public <T> void registerDataHandler(Class<T> dataClass, IDataHandler<T> handler)
	{
		_dataHandlers.put(dataClass, handler);
	}
	
	@Override
	public void unregisterDataHandlers()
	{
		_dataHandlers.clear();
	}
	
	@Override
	public <T> void unregisterDataHandlers(Class<T> dataClass)
	{
		_dataHandlers.remove(dataClass);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void onDataReceived(Object data)
	{
		System.out.println("Received data");
		
		if (_dataHandlers.containsKey(data.getClass()))
		{
			System.out.println("Handler contains key");
			
			_dataHandlers.get(data.getClass()).handle(data);
		}
	}
	
	@Override
	public void send(Object data)
	{
		if (_channel != null)
		{
			_channel.write(_dataService.toSerializedMessage(data));
		}
	}
}
