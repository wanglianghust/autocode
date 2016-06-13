package com.codegenerator.common;

public class StringExclusionFilter {
	private boolean excludeSectionFound = false;
	private String excludeFilter = "";
	private String includeString = "";
	private int numSections = 0;

	/**
	 * 
	 */
	public StringExclusionFilter(String exc, String inc) {
		excludeFilter = exc;
		includeString = inc;
	}

	/**
	 * This method processes a line in the file and returns one of the following
	 * 
	 * 1. The same line - if the exclude section has not yet been started 2. A
	 * null line - if the exclude section has started but not yet ended 3. The
	 * include String - if the exclude section is now going to be turned off on
	 * the next line
	 * 
	 * @param inputLine
	 * @return
	 */
	public String processLine(String inputLine) {
		String outputLine = inputLine;
		boolean lineHasMask = inputLine.indexOf(excludeFilter) >= 0;
		if (excludeSectionFound) {
			outputLine = null;
			if (lineHasMask) {
				outputLine = includeString;
				excludeSectionFound = false;
				return outputLine;
			}
		} else {
			if (lineHasMask) {
				excludeSectionFound = true;
				outputLine = null;
				++numSections;
			}
		}

		return outputLine;
	}

	/**
	 * @return Returns the numSections.
	 */
	public int getNumSections() {
		return numSections;
	}

	/**
	 * @return Returns the excludeSectionFound.
	 */
	public boolean isExcludeSectionFound() {
		return excludeSectionFound;
	}

	/**
	 * @param excludeSectionFound
	 *            The excludeSectionFound to set.
	 */
	public void setExcludeSectionFound(boolean excludeSectionFound) {
		this.excludeSectionFound = excludeSectionFound;
	}

	/**
	 * @param numSections
	 *            The numSections to set.
	 */
	public void setNumSections(int numSections) {
		this.numSections = numSections;
	}
}