package com.codegenerator.common;

import java.io.*;
import java.util.*;

public class StringUtil extends ApplicationObject {
	public static final String START_CAPTURE_TAG = "<!-- StartReportCapture -->";
	public static final String END_CAPTURE_TAG = "<!-- EndReportCapture -->";
	// add substitution for crlf
	public static char crchar[] = { 0x0D, 0x0A };
	public static String crlf = new String(crchar);
	public static char cr = 0x0D;
	public static char lf = 0x0A;
	public static String $crlf = "$crlf";

	/**
	 * This method was created in VisualAge.
	 */
	public static final String assignString(String inString, int startPosition,
			int charLength) {
		String retString = new String("");
		int size = inString.length();
		if (startPosition <= size) {
			int endPosition = charLength + startPosition - 1;
			if (endPosition >= size)
				retString = inString.substring(startPosition - 1);
			else
				retString = inString.substring(startPosition - 1, endPosition);
		}
		return retString;
	}

	/**
	 * ----------------------------------------------------------------- Extract
	 * content between the Start and End tags provided.
	 * -----------------------------------------------------------------
	 */
	public final static String captureFromHtml(String inString,
			String startTag, String endTag) {
		//
		String result = "";
		String inUcase = inString.toUpperCase();
		//
		int startCaptureIdx = 0;
		int endCaptureIdx = inString.length();
		int remainIdx = 0;
		//
		startTag = StringUtil.replaceString(startTag, ">", "").toUpperCase();
		endTag = StringUtil.replaceString(endTag, ">", "").toUpperCase();
		//
		// look for start and end tag ignoring case
		int startTagIdx = inUcase.indexOf(startTag.toUpperCase());
		if (startTagIdx >= 0)
			startCaptureIdx = inUcase.indexOf(">", startTagIdx) + 1;
		int endTagIdx = inUcase.indexOf(endTag.toUpperCase());
		if (endTagIdx >= 0) {
			endCaptureIdx = endTagIdx;
			remainIdx = inUcase.indexOf(">", endTagIdx) + 1;
		}
		//
		// both start and tag missing, just return ""
		if (startTagIdx < 0 && endTagIdx < 0)
			return result;
		//
		result += inString.substring(startCaptureIdx, endCaptureIdx);
		//
		// recusively capture the portion after the end tag
		if (endTagIdx >= 0)
			result += captureFromHtml(inString.substring(remainIdx), startTag,
					endTag);
		//
		return result;
	}

	/**
	 * chop the String into chunks of the given length and return a Vector of
	 * chunks.
	 */
	public static final Vector chopString(String inString, int size) {
		Vector result = new Vector();
		if (inString.length() == 0)
			return result;
		//
		String curString = inString;
		//
		while (true) {
			if (curString.length() <= size) {
				result.addElement(curString);
				return result;
			} else {
				result.addElement(curString.substring(0, size));
				curString = curString.substring(size);
			}
		}
	}

	/**
	 * ----------------------------------------------------------------- Strips
	 * off all commas and negative signs except for decimal point
	 * -----------------------------------------------------------------
	 */
	public final static String convToNum(String inString) {
		StringBuffer retStr = new StringBuffer();
		// add a negative sign if negative symbols exists
		String tempStr = inString.toUpperCase();
		if (tempStr.indexOf("-") >= 0)
			retStr.append("-");
		for (int i = 0; i < inString.length(); i++) {
			char c = inString.charAt(i);
			if (Character.isDigit(c) || c == '.')
				retStr.append(c);
		}
		return retStr.toString();
	}

	/**
	 * This method converts a string, of a serialized object, back to the
	 * object.
	 * 
	 * @return Serializable
	 * @param aString
	 *            java.lang.String
	 */
	public static final Serializable deserializeFromString(String aString)
			throws Exception {
		ByteArrayInputStream inStream = new ByteArrayInputStream(
				aString.getBytes());
		ObjectInputStream objInStream = new ObjectInputStream(inStream);
		Serializable myObject = (Serializable) objInStream.readObject();
		return myObject;
	}

	// search parmStr for searchStr=value and return value if found, otherwise
	// return blank

	public static String getParameter(String parmStr, String searchStr,
			String separator) {
		String result = "";
		if (searchStr == null || parmStr == null)
			return result;
		//
		StringTokenizer tline = new StringTokenizer(parmStr, separator);
		while (tline.hasMoreTokens()) {
			String value = tline.nextToken().trim();
			if (value.equals(searchStr)) {
				if (tline.hasMoreTokens())
					result = tline.nextToken().trim();
				break;
			}
		}
		return result;
	}

	/**
	 * ----------------------------------------------------------------- return
	 * the stack trace as a string
	 * -----------------------------------------------------------------
	 */
	public final static String getStackTrace(Throwable e) {
		// get the stack trace
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		String stackTrace = sw.toString();
		return stackTrace;

	}

	/**
	 * ----------------------------------------------------------------- Encode
	 * the string with html escape code sequance.
	 * -----------------------------------------------------------------
	 */
	public final static String htmlEncode(String inString) {
		//
		String result = inString;

		result = replaceString(result, "\"", "&quot;");
		result = replaceString(result, ">", "&gt;");
		result = replaceString(result, "<", "&lt;");
		result = replaceString(result, "\n", "<BR>");
		return result;
	}

	/**
	 * ----------------------------------------------------------------- Encode
	 * the double quote to \" Added for ticket 622
	 * -----------------------------------------------------------------
	 */
	public final static String javaScriptEncode(String inString) {
		//
		String result = inString;

		result = replaceString(result, "\"", "\\\"");
		return result;
	}

	/**
	 * ----------------------------------------------------------------- return
	 * true if the string contains all digits
	 * -----------------------------------------------------------------
	 */
	public final static boolean isDigit(String inString) {
		for (int i = 0; i < inString.length(); i++) {
			char c = inString.charAt(i);
			if (!Character.isDigit(c))
				return false;
		}
		return true;
	}

	/**
	 * ----------------------------------------------------------------- return
	 * true if the string contains all letters
	 * -----------------------------------------------------------------
	 */
	public final static boolean isLetter(String inString) {
		for (int i = 0; i < inString.length(); i++) {
			char c = inString.charAt(i);
			if (!Character.isLetter(c))
				return false;
		}
		return true;
	}

	// search for all "." and convert the char after each "." to upperCase

	public static String makeUpperCaseAfterDot(String aStr) {

		String result = aStr;
		int i = 0;

		while (i < aStr.length()) {
			int dotPos = result.indexOf(".", i);
			if (dotPos < 0)
				i = aStr.length();
			else {

				i = dotPos + 1;
				result = result.substring(0, i)
						+ result.substring(i, i + 1).toUpperCase()
						+ result.substring(i + 1);
			}

		}
		return result;
	}

	// strips blank/special characters from a string and makes first char after
	// a special character
	// upper case (excluding the very first character of the string)

	public static String makeVarName(String newStr) {
		StringBuffer retstr = new StringBuffer("");
		String specialCharacters = " _/.,#'%-";
		String numericCharacters = "0123456789";
		int strlen = newStr.length();
		char[] onechar = new char[1];
		boolean nextUpper = false;
		boolean firstChar = true;

		for (int i = 0; i < strlen; i++) {
			onechar[0] = newStr.charAt(i);
			String charString = new String(onechar);
			if (specialCharacters.indexOf(charString) >= 0) {
				if (charString.equals("'"))
					nextUpper = false;
				else
					nextUpper = true;
			} else {
				if (nextUpper) {
					if (!firstChar)
						retstr.append(charString.toUpperCase());
					else
						retstr.append(charString.toLowerCase());
					firstChar = false;
					nextUpper = false;
				} else {
					retstr.append(charString.toLowerCase());
					firstChar = false;
				}
			}

		}
		// if first character is numeric, insert an 'x' at the first position
		if (numericCharacters.indexOf(String.valueOf(retstr.charAt(0))) >= 0)
			retstr = retstr.insert(0, 'x');

		return retstr.toString();
	}

	// -------------------------------------------------------------------------
	// Pad function - pads strings up to length n (left or right)
	// len > 0: result = line + padchar(s)
	// len < 0: result = padchar(s) + line
	// -------------------------------------------------------------------------
	public final static String pad(String line, char padchar, int len) {
		int templen = len;
		if (templen < 0)
			templen = -1 * templen;
		int numchar = templen - line.length();
		String retline = line;
		if (numchar > 0) {
			for (int i = 0; i < numchar; i++) {
				if (len >= 0)
					retline = retline + padchar;
				else
					retline = padchar + retline;
			}
		} else {
			retline = line.substring(0, templen);
		}
		return retline;
	}

	/**
	 * This method parse a string in the format "parm1=value1&parm2=value2&..."
	 * to a hastable.
	 * 
	 * @return java.util.Hashtable
	 * @param in
	 *            java.lang.String
	 */
	public static final Hashtable parseQueryString(String aQueryString) {
		Hashtable result = new Hashtable();
		//
		if (aQueryString == null)
			return result;
		//
		StringTokenizer tline = new StringTokenizer(aQueryString, "&");
		while (tline.hasMoreTokens()) {
			String tokstr = tline.nextToken();
			int pos = tokstr.indexOf("=");
			if (pos > 0) {
				String key = tokstr.substring(0, pos);
				String value = tokstr.substring(pos + 1);
				Object obj = result.get(key);
				if (obj == null)
					result.put(key, value);
				else if (obj instanceof Vector) {
					Vector v = (Vector) obj;
					v.addElement(value);
				} else {
					Vector v = new Vector();
					v.addElement(obj);
					v.addElement(value);
					result.put(key, v);
				}
			}
		}
		return result;
	}

	/**
	 * This method parse a string in the format "parm1=value1&parm2=value2&..."
	 * to a hastable.
	 * 
	 * @return java.lang.String
	 * @param in
	 *            java.lang.String
	 */
	public static final Vector parseString(String aQueryString, String delimiter) {
		Vector result = new Vector();
		//
		if (aQueryString == null)
			return result;
		//
		StringTokenizer tline = new StringTokenizer(aQueryString, delimiter);
		while (tline.hasMoreTokens()) {
			String tokstr = tline.nextToken();
			result.addElement(tokstr);
		}
		return result;
	}

	public static final Vector parseToVector(String aString, int numChars) {
		Vector result = new Vector();
		//
		if (aString == null || numChars <= 0)
			return result;
		//
		int len = aString.length();
		int currPos = 0;
		int endPos = numChars;
		while (currPos <= len) {
			String subStr = aString.substring(currPos, endPos).trim();
			if (!subStr.equals("")) {
				result.addElement(subStr);
			} else
				break;
			currPos = endPos;
			endPos += numChars;
			if (endPos > (len))
				endPos = len;
		}
		return result;
	}

	public static final Vector parseToVector(String aString, String separator) {
		Vector result = new Vector();
		//
		if (aString == null)
			return result;
		//
		StringTokenizer tline = new StringTokenizer(aString, separator);
		while (tline.hasMoreTokens()) {
			String value = tline.nextToken().trim();
			result.addElement(value);
		}
		return result;
	}

	// ticket #973
	// Parses and validates a string in the format "YYYY/MM" or "YYYYMM" and
	// returns "YYYY/MM"or null
	//
	public static final String parseToYearMonth(String aValue) {

		if (aValue.length() != 6 && aValue.length() != 7)
			return null;

		java.text.SimpleDateFormat df = new java.text.SimpleDateFormat();
		df.setLenient(false);
		if (aValue.length() == 7)
			df.applyPattern("yyyy/MM");
		else
			df.applyPattern("yyyyMM");

		try {
			df.parse(aValue);
		} catch (java.text.ParseException pe) {
		}

		if (aValue.length() == 6)
			return aValue.substring(0, 4) + "/" + aValue.substring(4, 6);
		else
			return aValue;

	}

	/**
	 * This method was created in VisualAge.
	 * 
	 * @return java.lang.String
	 * @param inFile
	 *            File
	 */
	public final static String readToString(File inFile) throws IOException {
		//
		return readToString(new FileInputStream(inFile));
	}

	/**
	 * This method was created in VisualAge.
	 * 
	 * @return java.lang.String
	 * @param inputStream
	 *            InputStream
	 */
	public final static String readToString(InputStream inputStream)
			throws IOException {
		BufferedReader r = new BufferedReader(
				new InputStreamReader(inputStream));
		StringBuffer resultBuffer = new StringBuffer();
		//
		char[] cbuf = new char[4096];
		int i;
		do {
			i = r.read(cbuf);
			if (i >= 0)
				resultBuffer.append(cbuf, 0, i);
		} while (i >= 0);
		//
		r.close();
		//
		return resultBuffer.toString();
	}

	public final static String removeLeadingChar(String value, char ch) {

		int len = value.length();
		char[] val = value.toCharArray();
		int st = 0;

		while ((st < len) && (val[st] == ch)) {
			st++;
		}

		return (st > 0) ? value.substring(st, len) : value;

	}

	public final static String removeTrailingBlanks(String value) {

		int len = value.length();
		char[] val = value.toCharArray();

		while ((0 < len) && (val[len - 1] <= ' ')) {
			len--;
		}

		return value.substring(0, len);

	}

	/**
	 * This method replaces values in an input string with parameters specified
	 * using "parm1=value1;parm2=value2;..."
	 * 
	 * @return java.lang.StringBuffer
	 * @param in
	 *            java.lang.StringBuffer
	 * @param in
	 *            java.lang.String
	 */
	public static final String replaceParameterValues(String aString,
			String parameterValues, String separator) {
		String result = aString;
		Vector parmValues = parseString(parameterValues, separator);
		int numParms = parmValues.size();
		for (int i = 0; i < numParms; i++) {
			String aParmValue = (String) parmValues.elementAt(i);
			Vector parmPair = parseString(aParmValue, "=");
			String fromStr = (String) parmPair.elementAt(0);
			String toStr = (String) parmPair.elementAt(1);
			result = replaceString(result, fromStr, toStr);
		}
		return result;
	}

	/**
	 * This method search the "in" string and replace all occurrence of "from"
	 * string by the "to" string.
	 * 
	 * @return java.lang.String
	 * @param in
	 *            java.lang.String
	 * @param from
	 *            java.lang.String
	 * @param to
	 *            java.lang.String
	 */
	public static final String replaceString(String in, String from, String to) {
		int i = in.indexOf(from);
		if ((i < 0) || (from.length() == 0))
			return in;
		else
			return in.substring(0, i) + to
					+ replaceString(in.substring(i + from.length()), from, to);
	}

	/**
	 * This method converts a serializable object to String.
	 * 
	 * @return java.lang.String
	 * @param myObject
	 *            java.io.Serializable
	 */
	public static final String serializeToString(Serializable myObject)
			throws IOException {
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		ObjectOutputStream objOutStream = new ObjectOutputStream(byteStream);
		// write object to the stream
		objOutStream.writeObject(myObject);
		// convert stream to String
		return byteStream.toString();
	}

	/**
	 * This method was created in VisualAge.
	 */
	public final static String trimAfterLastDot(String line) {
		int lastDot = line.lastIndexOf(".");
		int size = line.length();
		if (lastDot >= 0) {
			return line.substring(0, lastDot).trim();
		} else
			return line.trim();

	}

	/**
	 * This method was created in VisualAge.
	 */
	public final static String trimBeforeLastDot(String line) {
		int lastDot = line.lastIndexOf(".");
		int size = line.length();
		if (lastDot >= 0) {
			return line.substring(0, lastDot).trim();
		} else
			return line.trim();

	}

	/**
	 * This method was created in VisualAge.
	 */
	public final static String trimToFirstDot(String line) {
		return trimToFirstMarker(".", line);

	}

	public final static String trimToFirstDash(String line) {
		return trimToFirstMarker("-", line);
	}

	/**
	 * This method was created in VisualAge.
	 */
	public final static String trimToFirstMarker(String marker, String line) {
		int firstMarker = line.indexOf(marker);
		int size = line.length();
		if (firstMarker > 0) {
			return line.substring(0, firstMarker).trim();
		} else
			return line.trim();

	}

	/**
	 * This method was created in VisualAge.
	 */
	public final static String trimToLastDot(String line) {
		return trimToLastMarker(".", line);

	}

	public final static String trimToLastDash(String line) {
		return trimToLastMarker("-", line);
	}

	/**
	 * This method was created in VisualAge.
	 */
	public final static String trimToLastMarker(String marker, String line) {
		int lastMarker = line.lastIndexOf(marker);
		int size = line.length();
		if (lastMarker >= 0) {
			if (size > lastMarker + 1)
				return line.substring(lastMarker + 1).trim();
			else
				return "";
		} else
			return line.trim();

	}

	/**
	 * return first 'size' bytes of the string
	 */
	public static final String truncateString(String inString, int size) {
		String result = "";
		if (inString.length() == 0)
			return result;
		//
		String curString = inString;
		//

		if (curString.length() <= size)
			result = curString;
		else
			result = curString.substring(0, size);
		return result;

	}

	/*
	 * return a string by concatenating the elements in the vector
	 */
	public static final String vectorToString(Vector aVector, String separator) {

		String result = "";
		//
		for (int i = 0; i < aVector.size(); i++) {
			if (i > 0)
				result += separator;
			result += aVector.elementAt(i);
		}

		return result;
	}

	/**
	 * This method was created in VisualAge.
	 * 
	 * @return java.lang.String
	 * @param outFile
	 *            File
	 */
	public final static void writeString(String aString, File outFile)
			throws IOException {
		try {
			//
			FileWriter w = new FileWriter(outFile);
			//
			w.write(aString);
			w.close();
		} catch (IOException e) {
			throw new IOException(outFile.toString() + " cannot be written");
		}
	}

	public static String byteArrayToCommaDelimitedString(byte[] byteArray) {
		int numBytes = byteArray.length;
		StringBuffer output = new StringBuffer();
		for (int i = 0; i < numBytes; i++) {
			if (i > 0)
				output.append(",");
			output.append(byteArray[i]);
		}
		return output.toString();
	}

	public static byte[] commaDelimitedStringToByteArray(String inputString) {

		Vector byteVector = parseToVector(inputString, ",");
		int numBytes = byteVector.size();
		byte[] output = new byte[numBytes];
		for (int i = 0; i < numBytes; i++) {
			String val = (String) byteVector.elementAt(i);
			Byte byteVal = new Byte(val);
			output[i] = byteVal.byteValue();
		}
		return output;
	}

}
