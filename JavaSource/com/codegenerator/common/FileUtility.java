package com.codegenerator.common;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class FileUtility extends ApplicationObject {

	public static final String crlf = System.getProperties().getProperty(
			"line.separator");
	public static final char eofchar[] = { 0x1A };
	public static final String eofstr = new String(eofchar);
	private StringExclusionFilter filter = null;

	public FileUtility() {
		super();
	}

	public StringBuffer getInputFileAsString(File inFile, String excludeString,
			String includeString) throws IOException {

		StringBuffer fileString = new StringBuffer();
		filter = new StringExclusionFilter(excludeString, includeString);
		if (!inFile.exists()) {
			filter.setNumSections(1);
			return new StringBuffer(includeString);
		}
		FileInputStream inStream = new FileInputStream(inFile);

		BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
		String line = in.readLine();
		int numLines = 0;

		while (line != null && !line.equals(eofstr)) {
			String filterLine = filter.processLine(line);
			if (filterLine != null) {
				if (numLines > 0)
					fileString.append(crlf);
				fileString.append(filterLine);
				++numLines;
			}
			line = in.readLine();
		}
		inStream.close();
		in.close();

		return fileString;
	}

	/**
	 * @return Returns the filter.
	 */
	public StringExclusionFilter getFilter() {
		return filter;
	}

	public List getInputFileAsArray(String inputFileName) throws IOException {

		List fileData = new ArrayList();
		File inFile = new File(inputFileName);
		FileInputStream inStream = new FileInputStream(inFile);

		BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
		String line = in.readLine();
		int numLines = 0;

		while (line != null && !line.equals(eofstr)) {
			fileData.add(line);
			line = in.readLine();
		}
		inStream.close();
		in.close();

		return fileData;
	}

	public ListHashtable getInputFileAsListHashtable(String inputFileName)
			throws IOException {

		ListHashtable aProperties = new ListHashtable();
		updatePropertiesFromFile(inputFileName, aProperties);
		return aProperties;

	}

	public void updatePropertiesFromFile(String inputFileName,
			ListHashtable aProperties) throws FileNotFoundException,
			IOException {
		// System.out.println("Retrieving props from: "+inputFileName);
		File inFile = new File(inputFileName);

		if (!inFile.exists())
			return;

		FileInputStream inStream = new FileInputStream(inFile);

		BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
		String line = in.readLine();
		int numLines = 0;

		while (line != null && !line.equals(eofstr)) {
			Vector tmp = StringUtil.parseToVector(line, "=");
			String property = (String) tmp.elementAt(0);
			String value = "";
			if (tmp.size() > 1)
				value = (String) tmp.elementAt(1);
			aProperties.put(property, value);
			// System.out.println ("Property added: "+property+ "Val:"+value);
			line = in.readLine();
		}
		inStream.close();
		in.close();

	}
}