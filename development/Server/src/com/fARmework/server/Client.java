package com.fARmework.server;

import com.google.gson.Gson;
import java.net.*;
import java.util.concurrent.*;
import org.jboss.netty.bootstrap.*;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.*;
import org.jboss.netty.handler.codec.serialization.*;

public class Client implements IMessanger
{
	private String _hostname;
	
	private int _port;
	
	private Channel _channel;
	
	public Client(String hostname, String port)
	{
		_hostname = hostname;
		
		_port = Integer.parseInt(port);
	}
	
	public void start()
	{
		ClientBootstrap bootstrap = new ClientBootstrap(
				new NioClientSocketChannelFactory(
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
						new ClientHandler());
			}
		});
		
		bootstrap.setOption("tcpNoDelay", true);
        bootstrap.setOption("keepAlive", true);
		
		ChannelFuture future = bootstrap.connect(new InetSocketAddress(_hostname, _port));
		
		_channel = future.awaitUninterruptibly().getChannel();
	}
	
	public void send(Message message)
	{
		Gson gson = new Gson();
		
		_channel.write(gson.toJson(message));
	}
}
