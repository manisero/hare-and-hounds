package com.fARmework.core.server.impl;

import com.fARmework.core.data.IDataService;
import com.fARmework.core.data.Impl.DataService;
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
		
		bind(IDataService.class).to(DataService.class);
	}
}
