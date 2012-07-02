package com.fARmework.server.impl;

import com.fARmework.server.*;
import com.google.gson.*;
import com.google.inject.*;
import java.util.concurrent.*;
import org.jboss.netty.bootstrap.*;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.group.*;
import org.jboss.netty.channel.socket.nio.*;
import org.jboss.netty.handler.codec.serialization.*;

public class Server
{	
	private ISettingsProvider _settingsProvider;
	
	private ChannelGroup _channelGroup;
	
	private IMessageProcessor _messageProcessor;
	
	@Inject
	public Server(ISettingsProvider settingsProvider, ChannelGroup channelGroup, IMessageProcessor messageProcessor)
	{
		_settingsProvider = settingsProvider;
		
		_channelGroup = channelGroup;
		
		_messageProcessor = messageProcessor;
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
						new ServerHandler(_messageProcessor, _channelGroup));
			}
		});
		
		bootstrap.setOption("tcpNoDelay", true);
        bootstrap.setOption("keepAlive", true);
		
		bootstrap.bind(_settingsProvider.getSocketAddress());
	}
	
	public void send(Message message)
	{
		Gson gson = new Gson();
		
		_channelGroup.write(gson.toJson(message));
	}
}
