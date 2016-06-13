package com.codegenerator.common;

public class FileLocationResolver extends PropertiesResolver {

	private static FileLocationResolver fileLocationResolver = null;

	/**
	 * 
	 */
	public FileLocationResolver() {
		super();
	}

	public String getFileKey() {
		return "fileLocationResolver";
	}

	public static void destroyResolver() {
		fileLocationResolver = null;
	}

	public static FileLocationResolver getResolver() {
		if (fileLocationResolver == null) {
			fileLocationResolver = new FileLocationResolver();
		}
		return fileLocationResolver;
	}
}