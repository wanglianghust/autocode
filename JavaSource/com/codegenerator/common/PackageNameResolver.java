package com.codegenerator.common;

public class PackageNameResolver extends PropertiesResolver {

	private static PackageNameResolver packageNameResolver = null;

	/**
	 * 
	 */
	public PackageNameResolver() {
		super();
	}

	public String getFileKey() {
		return "packageNameResolver";
	}

	public static void destroyResolver() {
		packageNameResolver = null;
	}

	public static PackageNameResolver getResolver() {
		if (packageNameResolver == null) {
			packageNameResolver = new PackageNameResolver();
		}
		return packageNameResolver;
	}
}
