package com.fARmework.core.client.Connection.Impl;

import java.net.InetSocketAddress;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.serialization.*;

import com.fARmework.core.client.Connection.*;
import com.fARmework.core.client.Data.*;
import com.fARmework.core.client.Infrastructure.ISettingsProvider;
import com.fARmework.core.data.IDataService;
import com.fARmework.core.data.Message;
import com.google.inject.Inject;

import android.os.AsyncTask;
import android.util.Log;

public class NettyConnectionManager implements IConnectionManager
{
	private class ConnectionTask extends AsyncTask<String, Object, Void>
	{
		private class ChannelHandler extends SimpleChannelUpstreamHandler
		{
			@Override
			public void channelConnected(ChannelHandlerContext context, ChannelStateEvent event)
			{
				Log.i("Connected", "Connected to server");
				publishProgress(new ConnectionSuccessInfo());
			}
			
			@Override
			public void messageReceived(ChannelHandlerContext context, MessageEvent event)
			{
				Log.i("Message", event.getMessage().toString());
				Message message = _dataService.deserializeMessage(event.getMessage().toString());
				publishProgress(_dataService.fromMessage(message));
			}
			
			@Override
			public void exceptionCaught(ChannelHandlerContext context, ExceptionEvent event)
			{
				event.getCause().printStackTrace();
				event.getChannel().close();
				publishProgress(new ConnectionExceptionInfo(event.getCause()));
			}
		}
		
		@Override
		protected Void doInBackground(String... params)
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
	        
			ChannelFuture future = bootstrap.connect(new InetSocketAddress(params[0], _settingsProvider.port()));
			
			_channel = future.awaitUninterruptibly().getChannel();
			
			if (!future.isSuccess())
			{
				future.getCause().printStackTrace();
				bootstrap.releaseExternalResources();
				publishProgress(new ConnectionFaultInfo());
			}
			
			return null;
		}
		
		@SuppressWarnings("unchecked")
		@Override
		protected void onProgressUpdate(Object... data)
		{
			for (Object dataPiece : data)
			{
				if (_dataHandlers.containsKey(dataPiece.getClass()))
				{
					_dataHandlers.get(dataPiece.getClass()).handle(dataPiece);
				}
			}
		}
	}
	
	private ISettingsProvider _settingsProvider;
	private IDataService _dataService;
	
	@SuppressWarnings("rawtypes")
	private Map<Class<?>, IDataHandler> _dataHandlers = new LinkedHashMap<Class<?>, IDataHandler>();
	private Channel _channel;
	
	@Inject
	public NettyConnectionManager(ISettingsProvider settingsProvider, IDataService dataService)
	{
		_settingsProvider = settingsProvider;
		_dataService = dataService;
	}
	
	@Override
	public void connect()
	{
		new ConnectionTask().execute(_settingsProvider.serverAddress());
	}
	
	@Override
	public void connect(String serverAddress)
	{
		new ConnectionTask().execute(serverAddress);
	}
	
	@Override
	public void disconnect()
	{
		if (_channel != null)
		{
			_channel.close();
		}
	}
	
	@Override
	public boolean isDisposed()
	{
		return _channel != null && !_channel.isConnected();
	}
	
	@Override
	public <T> void registerDataHandler(Class<T> dataClass, IDataHandler<T> handler)
	{
		_dataHandlers.put(dataClass, handler);
	}
	
	@Override
	public void clearDataHandlers()
	{
		_dataHandlers.clear();
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
