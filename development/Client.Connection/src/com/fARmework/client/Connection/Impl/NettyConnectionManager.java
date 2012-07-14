package com.fARmework.client.Connection.Impl;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.serialization.*;

import com.fARmework.client.Connection.*;
import com.fARmework.client.Connection.ConnectionEventHandlers.*;
import com.fARmework.client.Infrastructure.ISettingsProvider;
import com.google.inject.Inject;

import android.os.AsyncTask;
import android.util.Log;

public class NettyConnectionManager extends AsyncTask<Void, IConnectionEventHandler, Void> implements IConnectionManager
{
	private class ChannelHandler extends SimpleChannelUpstreamHandler
	{
		@Override
		public void channelConnected(ChannelHandlerContext context, ChannelStateEvent event)
		{
			Log.i("Connected", "Connected to server");
			publishProgress(new ConnectionSuccessHandler());
		}
		
		@Override
		public void messageReceived(ChannelHandlerContext context, MessageEvent event)
		{
			Log.i("Message", event.getMessage().toString());
			publishProgress(new MessageHandler(event.getMessage().toString()));
		}
		
		@Override
		public void exceptionCaught(ChannelHandlerContext context, ExceptionEvent event)
		{
			event.getCause().printStackTrace();
			event.getChannel().close();
			publishProgress(new ExceptionHandler(event.getCause()));
		}
	}
	
	private ISettingsProvider _settingsProvider;
	
	private IConnectionHandler _connectionHandler;
	private Channel _channel;
	
	@Inject
	public NettyConnectionManager(ISettingsProvider settingsProvider)
	{
		_settingsProvider = settingsProvider;
	}
	
	@Override
	public void connect(IConnectionHandler connectionHandler)
	{
		_connectionHandler = connectionHandler;
		execute();
	}
	
	@Override
	protected Void doInBackground(Void... params)
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
										 new ChannelHandler());
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
			publishProgress(new ConnectionFaultHandler());
		}
		
		return null;
	}
	
	@Override
	protected void onProgressUpdate(IConnectionEventHandler... eventHandlers)
	{
		for (IConnectionEventHandler eventHandler : eventHandlers)
		{
			eventHandler.handleWith(_connectionHandler);
		}
	}
	
	@Override
	public void disconnect()
	{
		if (_channel != null)
		{
			_channel.close();
		}
	}
}