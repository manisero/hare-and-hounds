package com.fARmework.core.server.impl;

import com.fARmework.core.server.*;
import com.google.inject.*;

import java.net.InetSocketAddress;
import java.util.concurrent.*;
import org.jboss.netty.bootstrap.*;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.*;
import org.jboss.netty.handler.codec.serialization.*;

public class Server implements IServer
{	
	private ISettingsProvider _settingsProvider;
	
	private GroupChannelHandler _groupChannelHandler;
	
	@Inject
	public Server(ISettingsProvider settingsProvider, GroupChannelHandler groupChannelHandler)
	{
		_settingsProvider = settingsProvider;
		_groupChannelHandler = groupChannelHandler;
	}
	
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
						_groupChannelHandler);
			}
		});
		
		bootstrap.setOption("tcpNoDelay", true);
        bootstrap.setOption("keepAlive", true);
		
		bootstrap.bind(new InetSocketAddress(_settingsProvider.getPort()));
	}
	
	public void send(Object object)
	{	
		_groupChannelHandler.send(object);
	}
}
