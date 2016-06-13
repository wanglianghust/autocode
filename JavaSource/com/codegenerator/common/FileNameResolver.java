package com.codegenerator.common;

public class FileNameResolver extends PropertiesResolver {

	private static FileNameResolver fileNameResolver = null;

	/**
	 * 
	 */
	public FileNameResolver() {
		super();
	}

	public String getFileKey() {
		return "fileNameResolver";
	}

	public static void destroyResolver() {
		fileNameResolver = null;
	}

	public static FileNameResolver getResolver() {
		if (fileNameResolver == null) {
			fileNameResolver = new FileNameResolver();
		}
		return fileNameResolver;
	}
}