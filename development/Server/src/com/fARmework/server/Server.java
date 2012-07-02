package com.fARmework.server;

import com.google.gson.*;
import java.net.*;
import java.util.concurrent.*;
import org.jboss.netty.bootstrap.*;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.*;
import org.jboss.netty.handler.codec.serialization.*;

public class Server implements IMessanger
{	
	private int _port;
	
	private Channel _channel;
	
	public Server(String port)
	{
		_port = Integer.parseInt(port);
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
						new ServerHandler());
			}
		});
		
		bootstrap.setOption("tcpNoDelay", true);
        bootstrap.setOption("keepAlive", true);
		
		_channel = bootstrap.bind(new InetSocketAddress(_port));
	}
	
	public void send(Message message)
	{
		Gson gson = new Gson();
		
		_channel.write(gson.toJson(message));
	}
}
