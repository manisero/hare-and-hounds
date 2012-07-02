import com.fARmework.server.*;
import com.fARmework.server.impl.Message;
import com.fARmework.server.impl.MessageFactory;
import com.fARmework.server.impl.UnregisteredObjectTypeException;

import static org.junit.Assert.*;
import org.junit.Test;

public class MessageFactoryTest 
{
	@Test (expected = UnregisteredObjectTypeException.class)
	public void testGetUnregisteredMessage() 
	{
		MessageFactory messageFactory = new MessageFactory();
		
		messageFactory.getMessage(new Integer(1));
	}
	
	@Test
	public void testGetRegisteredMessage()
	{
		MessageFactory messageFactory = new MessageFactory();
		
		messageFactory.register(String.class, "STRING_MESSAGE");
		
		Message message = messageFactory.getMessage(new String("Test message"));
		
		assertNotNull("Message is null", message);
		
		assertEquals("Message type does not match", message.getType(), "STRING_MESSAGE");
		
		assertEquals("Message object does not match", message.getObject(), "Test message");
	}
}
