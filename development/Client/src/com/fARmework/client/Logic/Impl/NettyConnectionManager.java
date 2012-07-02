package com.fARmework.client.Logic.Impl;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.serialization.ClassResolvers;
import org.jboss.netty.handler.codec.serialization.ObjectDecoder;
import org.jboss.netty.handler.codec.serialization.ObjectEncoder;

import com.fARmework.client.Infrastructure.IResourcesProvider;
import com.fARmework.client.Infrastructure.ISettingsProvider;
import com.fARmework.client.Logic.IConnectionManager;
import com.fARmework.client.Logic.BackgroundTasks.IProgressListener;
import com.google.inject.Inject;

public class NettyConnectionManager implements IConnectionManager
{
	private ISettingsProvider _settingsProvider;
	private IResourcesProvider _resourcesProvider;
	
	private Channel _channel;
	
	@Inject
	public NettyConnectionManager(ISettingsProvider settingsProvider, IResourcesProvider resourcesProvider)
	{
		_settingsProvider = settingsProvider;
		_resourcesProvider = resourcesProvider;
	}
	
	@Override
	public void connect(final IProgressListener<String> messageListener) throws IOException
	{
		ClientBootstrap bootstrap = new ClientBootstrap(new NioClientSocketChannelFactory(Executors.newCachedThreadPool(),
																						  Executors.newCachedThreadPool()));
		
		bootstrap.setPipelineFactory(new ChannelPipelineFactory()
		{	
			@Override
			public ChannelPipeline getPipeline() throws Exception 
			{
				return Channels.pipeline(new ObjectEncoder(),
										 new ObjectDecoder(ClassResolvers.cacheDisabled(getClass().getClassLoader())),
										 new NettyChannelUpstreamHandler(messageListener));
			}
		});
		
		bootstrap.setOption("tcpNoDelay", true);
        bootstrap.setOption("keepAlive", true);
		
		ChannelFuture future = bootstrap.connect(new InetSocketAddress(_settingsProvider.serverAddress(), _settingsProvider.port()));
		
		_channel = future.awaitUninterruptibly().getChannel();
		
		if (!future.isSuccess())
		{
			future.getCause().printStackTrace();
			bootstrap.releaseExternalResources();
			messageListener.onUpdate("Connection failed");
		}
	}

	@Override
	public void disconnect()
	{
		_channel.close();
	}
}
