package com.codegenerator.common;

public class FieldTypeResolver extends PropertiesResolver {

	private static FieldTypeResolver fieldTypeResolver = null;

	/**
	 * 
	 */
	public FieldTypeResolver() {
		super();
	}

	public String getFileKey() {
		return "fieldTypeResolver";
	}

	public static FieldTypeResolver getResolver() {
		if (fieldTypeResolver == null) {
			fieldTypeResolver = new FieldTypeResolver();
		}
		return fieldTypeResolver;
	}

	public static void destroyResolver() {
		fieldTypeResolver = null;
	}
}