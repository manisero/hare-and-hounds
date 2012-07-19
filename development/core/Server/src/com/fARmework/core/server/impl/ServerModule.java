package com.fARmework.core.server.impl;

import com.fARmework.core.data.ISerializationService;
import com.fARmework.core.data.Impl.SerializationService;
import com.fARmework.core.server.*;
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
		
		bind(ISerializationService.class).to(SerializationService.class);
	}
}
