package com.fARmework.server.impl;

import com.fARmework.server.*;
import com.google.inject.*;
import org.jboss.netty.channel.group.*;

public class ServerModule extends AbstractModule 
{
	@Override
	protected void configure() 
	{
		bind(GroupChannelHandler.class).to(ServerHandler.class);
		
		bind(IMessageProcessor.class).to(DummyMessageProcessor.class);
		
		bind(ChannelGroup.class).to(DefaultChannelGroup.class);
		
		bind(ISettingsProvider.class).to(DefaultSettingsProvider.class);
	}
}
