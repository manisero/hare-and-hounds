package com.fARmework.client.Logic.Impl;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

import com.fARmework.client.Logic.BackgroundTasks.IProgressListener;
import com.google.gson.Gson;

public class NettyChannelUpstreamHandler extends SimpleChannelUpstreamHandler
{
	public class Message 
	{
		private String _type;
		
		private Object _object;
		
		public Message(String type, Object object)
		{
			_type = type;
			
			_object = object;
		}
		
		public String getType()
		{
			return _type;
		}
		
		public Object getObject()
		{
			return _object;
		}
	}
	
	private IProgressListener<String> _messageListener;
	
	public NettyChannelUpstreamHandler(IProgressListener<String> messageListener)
	{
		_messageListener = messageListener;
	}
	
	@Override 
	public void messageReceived(ChannelHandlerContext context, MessageEvent event)
	{
		Message message = new Gson().fromJson((String)event.getMessage(), Message.class);
		
		_messageListener.onUpdate("Type: " + message.getType() + "; Body: " + message.getObject().toString());
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext context, ExceptionEvent event)
	{
		event.getChannel().close();
	}
}
