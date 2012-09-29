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
			SettingsReaderTests.TheGetMethod.class
		})
public class SettingsReaderTests 
{
	public static class TheGetMethod
	{		
		@Test
		public void ReadsExistingSettings() 
		{
			ISettingsReader settingsReader = new SettingsReader();
			
			String settingsFileName = "settings.xml";
			
			String settings =
					"<settings>												\n" +
					"	<setting key=\"ipAddress\">127.0.0.1</setting>		\n" +
					"	<setting key=\"port\">8080</setting>				\n" +
					"	<setting key=\"hostname\">testServer</setting>		\n" +
					"</settings>											\n";
			
			try 
			{
				FileWriter fileWriter = new FileWriter(settingsFileName);
				
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
				
				bufferedWriter.write(settings);
				
				bufferedWriter.close();
			} 
			catch (IOException exception) 
			{
				fail("The IOException has been thrown during the tests");
				exception.printStackTrace();
			}
			
			settingsReader.setSettingsFileName(settingsFileName);
			
			assertEquals("127.0.0.1", settingsReader.get("ipAddress"));
			assertEquals("8080", settingsReader.get("port"));
			assertEquals("testServer", settingsReader.get("hostname"));
			
			File file = new File(settingsFileName);
			
			file.delete();
		}
		
		@Test
		public void ReturnsNullOnNotExistingSettings()
		{
			ISettingsReader settingsReader = new SettingsReader();
			
			String settingsFileName = "settings.xml";
			
			String settings =
					"<settings>												\n" +
					"	<setting key=\"ipAddress\">127.0.0.1</setting>		\n" +
					"	<setting key=\"port\">8080</setting>				\n" +
					"	<setting key=\"hostname\">testServer</setting>		\n" +
					"</settings>											\n";
			
			try 
			{
				FileWriter fileWriter = new FileWriter(settingsFileName);
				
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
				
				bufferedWriter.write(settings);
				
				bufferedWriter.close();
			} 
			catch (IOException exception) 
			{
				fail("The IOException has been thrown during the tests");
				exception.printStackTrace();
			}
			
			settingsReader.setSettingsFileName(settingsFileName);
			
			assertNull(settingsReader.get("macAddress"));
			assertNull(settingsReader.get("defaultGateway"));
			
			File file = new File(settingsFileName);
			
			file.delete();			
		}
	}
}
