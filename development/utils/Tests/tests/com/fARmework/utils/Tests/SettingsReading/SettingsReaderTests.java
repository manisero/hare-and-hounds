package com.fARmework.utils.Tests.SettingsReading;

import static org.junit.Assert.*;

import com.fARmework.utils.Java.*;
import com.fARmework.utils.Java._impl.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import org.junit.*;
import org.junit.runner.*;
import org.junit.runners.*;
import org.junit.runners.Suite.*;

@RunWith(Suite.class)
@SuiteClasses(
		{
			SettingsReaderTests.TheGetMethod.class,
			SettingsReaderTests.TheGetAggregateMethod.class
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
			
			settingsReader.loadSettings(settingsFileName);
			
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
			
			settingsReader.loadSettings(settingsFileName);
			
			assertNull(settingsReader.get("macAddress"));
			assertNull(settingsReader.get("defaultGateway"));
			
			File file = new File(settingsFileName);
			
			file.delete();			
		}
	}
	
	public static class TheGetAggregateMethod
	{
		@Test
		public void ReadsAggregateSettings()
		{
			ISettingsReader settingsReader = new SettingsReader();
			
			String settingsFileName = "settings.xml";
			
			String settings =
					"<settings>												\n" +
					"	<setting key=\"measurement\">13 cm</setting>		\n" +
					"	<setting key=\"measurement\">7.5 m</setting>		\n" +
					"	<setting key=\"measurement\">10 km</setting>		\n" +
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
			
			settingsReader.loadSettings(settingsFileName);
			
			List<String> positions = settingsReader.getAggregate("measurement");
			
			assertEquals("13 cm", positions.get(0));
			assertEquals("7.5 m", positions.get(1));
			assertEquals("10 km", positions.get(2));
			
			File file = new File(settingsFileName);
			
			file.delete();						
		}
	}
}
