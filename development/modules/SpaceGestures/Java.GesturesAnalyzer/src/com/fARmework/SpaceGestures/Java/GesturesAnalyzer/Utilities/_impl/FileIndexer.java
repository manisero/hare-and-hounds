package com.fARmework.SpaceGestures.Java.GesturesAnalyzer.Utilities._impl;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import com.fARmework.SpaceGestures.Java.GesturesAnalyzer.Utilities.IFileIndexer;
import com.google.inject.Inject;

public class FileIndexer implements IFileIndexer
{
	private FileUtilities _fileUtilities;
	
	@Inject
	public FileIndexer(FileUtilities fileUtilities)
	{
		_fileUtilities = fileUtilities;
	}
	
	public List<String> getFilenames(String workingDirectory, String extension)
	{
		List<String> fileNames = new LinkedList<String>();
		
		File[] files = new File(workingDirectory).listFiles();
		
		for (File file : files)
		{
			if (file.isFile())
			{
				String fileExtension = _fileUtilities.getExtension(file.getName());

				if (fileExtension.toLowerCase().equals(extension))
				{
					fileNames.add(file.getName());
				}
			}
		}
		
		return fileNames;
	}
}
