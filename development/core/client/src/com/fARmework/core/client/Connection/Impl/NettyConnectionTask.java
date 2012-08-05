package com.fARmework.core.client.Connection.Impl;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.serialization.ClassResolvers;
import org.jboss.netty.handler.codec.serialization.ObjectDecoder;
import org.jboss.netty.handler.codec.serialization.ObjectEncoder;

import android.os.AsyncTask;
import android.util.Log;

import com.fARmework.core.client.Data.ConnectionExceptionInfo;
import com.fARmework.core.client.Data.ConnectionFaultInfo;
import com.fARmework.core.client.Data.ConnectionSuccessInfo;
import com.fARmework.core.data.IDataService;
import com.fARmework.core.data.Message;

public class NettyConnectionTask extends AsyncTask<SocketAddress, Object, Channel>
{
	public interface IConnectionListener
	{
		void onConnected(Channel channel);
	}
	
	public interface IDataListener
	{
		void onDataReceived(Object data);
	}
	
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

	private IConnectionListener _connectionListener;
	private IDataListener _dataListener;
	private IDataService _dataService;
	
	public NettyConnectionTask(IConnectionListener connectionListener, IDataListener dataListener, IDataService dataService)
	{
		_connectionListener = connectionListener;
		_dataListener = dataListener;
		_dataService = dataService;
	}
	
	public void connect(String serverAddress, int port)
	{
		execute(new InetSocketAddress(serverAddress, port));
	}
	
	@Override
	protected Channel doInBackground(SocketAddress... params)
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
        
		ChannelFuture future = bootstrap.connect(params[0]);
		
		Channel channel = future.awaitUninterruptibly().getChannel();
		
		if (!future.isSuccess())
		{
			future.getCause().printStackTrace();
			bootstrap.releaseExternalResources();
			publishProgress(new ConnectionFaultInfo());
		}
		
		return channel;
	}
	
	@Override
	protected void onPostExecute(Channel channel)
	{
		_connectionListener.onConnected(channel);
	}
	
	@Override
	protected void onProgressUpdate(Object... data)
	{
		for (Object dataPiece : data)
		{
			_dataListener.onDataReceived(dataPiece);
		}
	}
}
