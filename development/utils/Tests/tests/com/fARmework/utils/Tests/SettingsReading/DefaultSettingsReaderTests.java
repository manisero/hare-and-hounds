package com.fARmework.utils.Tests.SettingsReading;

import static org.junit.Assert.*;

import com.fARmework.utils.Java.*;
import com.fARmework.utils.Java._impl.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.*;
import org.junit.runner.*;
import org.junit.runners.*;
import org.junit.runners.Suite.*;

@RunWith(Suite.class)
@SuiteClasses(
		{
			DefaultSettingsReaderTests.TheGetPropertyMethod.class
		})
public class DefaultSettingsReaderTests 
{
	public static class TheGetPropertyMethod
	{		
		@Test
		public void ReadsExistingSettings() 
		{
			ISettingsReader settingsReader = new DefaultSettingsReader();
			
			String settings =
					"<settings>												\n" +
					"	<setting key=\"ipAddress\">127.0.0.1</setting>		\n" +
					"	<setting key=\"port\">8080</setting>				\n" +
					"	<setting key=\"hostname\">testServer</setting>		\n" +
					"</settings>											\n";
			
			try 
			{
				FileWriter fileWriter = new FileWriter(settingsReader.getSettingsFileName());
				
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
				
				bufferedWriter.write(settings);
				
				bufferedWriter.close();
			} 
			catch (IOException exception) 
			{
				fail("The IOException has been thrown during the tests");
				exception.printStackTrace();
			}
			
			assertEquals("127.0.0.1", settingsReader.getProperty("ipAddress"));
			assertEquals("8080", settingsReader.getProperty("port"));
			assertEquals("testServer", settingsReader.getProperty("hostname"));
			
			File file = new File(settingsReader.getSettingsFileName());
			
			file.delete();
		}
		
		@Test
		public void ReturnsNullOnNotExistingSettings()
		{
			ISettingsReader settingsReader = new DefaultSettingsReader();
			
			String settings =
					"<settings>												\n" +
					"	<setting key=\"ipAddress\">127.0.0.1</setting>		\n" +
					"	<setting key=\"port\">8080</setting>				\n" +
					"	<setting key=\"hostname\">testServer</setting>		\n" +
					"</settings>											\n";
			
			try 
			{
				FileWriter fileWriter = new FileWriter(settingsReader.getSettingsFileName());
				
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
				
				bufferedWriter.write(settings);
				
				bufferedWriter.close();
			} 
			catch (IOException exception) 
			{
				fail("The IOException has been thrown during the tests");
				exception.printStackTrace();
			}
			
			assertNull(settingsReader.getProperty("macAddress"));
			assertNull(settingsReader.getProperty("defaultGateway"));
			
			File file = new File(settingsReader.getSettingsFileName());
			
			file.delete();			
		}
	}
}
