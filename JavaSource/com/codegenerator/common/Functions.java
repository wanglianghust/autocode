package com.codegenerator.common;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import java.text.Collator;
import java.text.SimpleDateFormat;
import java.io.*;
import com.codegenerator.db.SqlColumn;
import com.codegenerator.db.SqlTable;

public class Functions extends ApplicationObject {

	public static char crchar[] = { 0x0D, 0x0A };

	public static String crlf = new String(crchar);

	public static char cr = 0x0D;

	public static char lf = 0x0A;

	public static char quchar[] = { '"' };

	public static char quBlank[] = { '"', '"' };

	public static String quote = new String(quchar);

	public static String blankQuote = new String(quBlank);

	public static String fs = File.separator;

	private static char[] hex40 = null;

	static {
		hex40 = new char[4000];
		for (int i = 0; i < 4000; i++) {
			hex40[i] = 0x40;
		}
	}

	// chopstr
	// This function chops a string up into strings tokens

	public static String chopstr(String line, int maxlen) {

		StringTokenizer strtoken = new StringTokenizer(line, " ", true);
		int curlen = 0;
		boolean firstoken = true;
		String retline = "";

		while (strtoken.hasMoreTokens()) {
			String token = strtoken.nextToken();
			curlen += token.length();
			if (curlen <= maxlen | firstoken) {
				retline += token;
			} else {
				retline += crlf + token;
				curlen = token.length();
			}
			if (firstoken)
				firstoken = false;
		}
		return retline;
	}

	public static String firstToUpperCase(String string) {
		String post = string.substring(1, string.length());
		String first = ("" + string.charAt(0)).toUpperCase();
		return first + post;
	}

	// append one file to another

	public static void copyAppend(String fromFile, String toFile)
			throws IOException {
		String lineIn;
		PrintWriter out = new PrintWriter(new FileWriter(toFile, true));
		BufferedReader in = new BufferedReader(new FileReader(fromFile));
		while ((lineIn = in.readLine()) != null)
			out.println(lineIn);
		out.close();
		in.close();
	}

	/**
	 * This method was created in VisualAge.
	 */
	// copy a file
	public static void copyfile(File fileFrom, File fileTo, boolean createdir)
			throws FileNotFoundException, IOException {
		//
		if (!fileFrom.canRead())
			throw new IOException("Cannot read from file " + fileFrom.getPath());
		//
		if (createdir) {
			File todir = new File(fileTo.getParent());
			if (!todir.isDirectory())
				todir.mkdirs();
		}
		//
		if (Functions
				.execproc(
						"filecopy " + fileFrom.getPath() + " "
								+ fileTo.getPath(), true) != 0)
			throw new IOException("Failed to copy to file " + fileTo.getPath());
	}

	// copy a file

	public static void copyfile(String fileFrom, String fileTo,
			boolean createdir) throws FileNotFoundException, IOException {
		copyfile(new File(fileFrom), new File(fileTo), createdir);
	}

	// convert from decimal string to hex string value

	public static String d2x(String numstr, int precision, int scale) {
		String retstr = "";
		String signstr = "";
		String pstr = "";
		String sstr = "";
		boolean beginscale = false;
		char onechar;
		if (numstr.indexOf("-") >= 0)
			signstr = "D";
		else
			signstr = "C";
		int strlen = numstr.length();

		for (int i = 0; i < strlen; i++) {
			onechar = numstr.charAt(i);
			if (onechar == '+' || onechar == '-')
				;
			else if (onechar == '.')
				beginscale = true;
			else if (beginscale)
				sstr += onechar;
			else
				pstr += onechar;
		}
		int diflen = precision - scale;
		if (pstr.length() < diflen)
			pstr = Functions.Pad(pstr, '0', -1 * diflen);
		// pad precision portion if precision is an even number
		if ((precision % 2) == 0)
			pstr = "0" + pstr;
		if (sstr.length() < scale)
			sstr = Functions.Pad(sstr, '0', scale);
		retstr = s2x(pstr + sstr + signstr);
		return retstr;
	}

	// execproc
	// This function executes the specified command
	// and returns a return code

	public static int execproc(String cmd, boolean waitfor) {
		int retcode = 0;
		try {
			Runtime r = Runtime.getRuntime();
			Process p = r.exec(cmd);
			new InputStreamMonitor(p.getErrorStream());
			new InputStreamMonitor(p.getInputStream());

			if (waitfor)
				retcode = p.waitFor();
		} catch (Exception e) {
			System.out.println("Error in executing " + cmd + " -->" + e);
			System.exit(1);
		}
		return (retcode);
	}

	public static int exp(int in, int power) {
		int retval = in;

		if (power > 0) {
			for (int i = 1; i < power; i++) {
				retval = retval * in;
			}
		} else
			retval = 1;

		return retval;
	}

	// return the argument, converted to upper case, at a specific position
	// (counting from zero)

	public static String getArg(String[] arg, int argNum) {
		return ((argNum >= 0 && argNum < arg.length) ? arg[argNum]
				.toUpperCase() : "");
	}

	// return the concatenation of arguments starting from a specific position
	// to the last one

	public static String getArg(String[] arg, int argNum, boolean lastArg) {
		String retstr = "";

		retstr = getArg(arg, argNum);
		if (lastArg && argNum >= 0)
			for (int i = argNum + 1; i < arg.length; i++)
				retstr += " " + arg[i];
		return retstr.toUpperCase();
	}

	/**
	 * This method was created in VisualAge.
	 */
	public static String getFmtString(String varName, String fmt) {

		int numDigits = 0;
		int numDecimals = 0;

		StringTokenizer tline = new StringTokenizer(fmt, " (),");
		String tokstr = "";
		boolean isNumDigits = true;

		while (tline.hasMoreTokens()) {
			tokstr = tline.nextToken().trim();
			try {
				Integer num = new Integer(tokstr);
				if (isNumDigits) {
					numDigits = num.intValue();
					isNumDigits = false;
				} else
					numDecimals = num.intValue();
			} catch (Exception e) {
			}
		} // end while

		return getFmtString(varName, numDigits, numDecimals);
	}

	// search an option from the options string and return the value given for
	// that option termination by a " " or "/"

	/**
	 * @param varName
	 * @param numDigits
	 * @param numDecimals
	 * @return
	 */
	public static String getFmtString(String varName, int numDigits,
			int numDecimals) {
		// the number in the data type DECIMAL(A,B) has A-B integer digits and B
		// decimal digits
		int numSig = numDigits - numDecimals;
		StringBuffer retStr = new StringBuffer("");
		int numThree = 0;
		for (int c = 0; c < numSig; c++) {
			if (numThree == 3) {
				retStr.insert(0, ",");
				numThree = 0;
			}
			if (c == 0)
				retStr.insert(0, "0");
			else
				retStr.insert(0, "#");
			++numThree;
		}

		for (int c = 0; c < numDecimals; c++) {
			if (c == 0)
				retStr.append(".0");
			else
				retStr.append("0");
		}

		return ("   private static String " + varName + "Fmt " + " = "
				+ Functions.quote + retStr.toString() + Functions.quote + ";");
	}

	public static String getFmtString(String varName, String digits,
			String decimals) {
		int numDigits = new Integer(digits).intValue();
		int numDecimals = new Integer(decimals).intValue();
		return getFmtString(varName, numDigits, numDecimals);
	}

	public static String getOpt(String options, String searchStr) {
		String retstr = "";
		int i = options.indexOf(searchStr);
		if (i >= 0) {
			int e = options.length();
			int s = i + searchStr.length();
			int temp = options.indexOf(' ', s);
			if (temp >= 0 && temp < e)
				e = temp;
			temp = options.indexOf('/', s);
			if (temp >= 0 && temp < e)
				e = temp;
			retstr = options.substring(s, e);
		} // end if
		return retstr;
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
	 * This method was created in VisualAge.
	 */
	public static String getScale(String fmt) {
		StringBuffer retStr = new StringBuffer("");
		int numDigits = 0;
		int numDecimals = 0;

		StringTokenizer tline = new StringTokenizer(fmt, " (),");
		String tokstr = "";
		boolean isNumDigits = true;

		while (tline.hasMoreTokens()) {
			tokstr = tline.nextToken().trim();
			try {
				Integer num = new Integer(tokstr);
				if (isNumDigits) {
					numDigits = num.intValue();
					isNumDigits = false;
				} else
					numDecimals = num.intValue();
			} catch (Exception e) {
			}
		} // end while

		Integer numDec = new Integer(numDecimals);
		return (numDec.toString());
	}

	/**
	 * This method was created in VisualAge. Search and replace text in all
	 * files in a directory or a single file
	 */
	public static void globalChange(String target, String fromText,
			String toText, boolean includeSubDir) throws Exception {
		File targetFile = new File(target);
		if (targetFile.isFile())
			searchReplace(target, fromText, toText, "");
		else if (targetFile.isDirectory()) {
			String[] fl = targetFile.list();
			for (int i = 0; i < fl.length; i++) {
				File subfile = new File(targetFile, fl[i]);
				if (subfile.isFile() || includeSubDir)
					globalChange(subfile.getAbsolutePath(), fromText, toText,
							includeSubDir);
			}
		} else
			throw new Exception("Error in reading " + target);
	}

	// return the value of the hex char in the argument

	public static int hexval(char in) {
		int retval = in;
		if (retval < 58)
			retval = in & 0x0F;
		else
			retval = (in & 0x0F) + 9;
		return retval;
	}

	// convert from integer string to hex string

	public static String i2x(String numstr, int outlen) {
		Integer integval = new Integer(numstr);
		int intval = integval.intValue();
		String wrkstr = Integer.toHexString(intval);
		wrkstr = wrkstr.toUpperCase();
		int strlen = wrkstr.length();
		int intlen = outlen * 2;
		if (strlen < intlen)
			wrkstr = Functions.Pad(wrkstr, '0', -1 * intlen);
		else if (strlen > intlen)
			wrkstr = wrkstr.substring(strlen - intlen);
		String retstr = s2x(wrkstr);
		return retstr;
	}

	public static void searchReplace(String infilename, String srchstr,
			String replstr, String parmstr) {
		String ctlparms = parmstr.toUpperCase();
		boolean delsw = (ctlparms.indexOf("/D") >= 0);
		boolean addsw = (ctlparms.indexOf("/A") >= 0);
		try {
			// get file
			File chgfile = new File(infilename);
			String basefilename = infilename;
			// remove extension if it exists
			if (basefilename.indexOf(".") > 0) {
				StringTokenizer tline = new StringTokenizer(basefilename, ".");
				basefilename = tline.nextToken().trim();
			}
			// construct backup file name from base file name
			String bkpfilename = basefilename + ".BAK";
			File bakfile = new File(bkpfilename);

			// if the input file name equals the backup file name, just return
			if (infilename.equals(bkpfilename))
				return;

			// if backup file exists, then delete it
			if (bakfile.exists())
				bakfile.delete();
			// rename current file to the backup file
			chgfile.renameTo(bakfile);

			// set up the output file stream in order to write to the file

			PrintWriter out = new PrintWriter(new FileWriter(infilename));

			// read the input file record by record

			BufferedReader in = new BufferedReader(new FileReader(bkpfilename));
			String line = in.readLine();
			int numchg = 0;
			while (line != null) {
				int srchpos = line.indexOf(srchstr);
				if (srchpos >= 0) {
					numchg++;
					if (delsw)
						line = replstr; // replace the whole line if delsw is on
					else
						line = Functions.replaceString(line, srchstr, replstr);
				} // end if srchpos >= 0

				out.println(line);
				line = in.readLine();
			}
			if (addsw && numchg == 0 && !replstr.equals(""))
				out.println(replstr); // add a new line if the search-for
			// string is not found when addsw is on

			in.close();
			out.close();
		} // end of try
		catch (Exception e) {
			System.out.println("Error in jsrchrepl " + " --> " + crlf
					+ e.toString());
			e.printStackTrace();
			System.exit(12);
		}
	} // end of replace()

	// append one file to another

	public static void logMsg(String msg, String logFile) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter(logFile, true));
		out.println(msg);
		out.close();
	}

	// makes first letter of each word upper case

	public static String makeBusName(String newStr) {
		StringBuffer retstr = new StringBuffer("");
		String specialCharacters = " _/.,#'%-";
		String numericCharacters = "0123456789";
		int strlen = newStr.length();
		char[] onechar = new char[1];
		boolean nextUpper = true; // make first char uppercase

		for (int i = 0; i < strlen; i++) {
			onechar[0] = newStr.charAt(i);
			String charString = new String(onechar);
			if (specialCharacters.indexOf(charString) >= 0) {
				if (charString.equals("'"))
					nextUpper = false;
				else
					nextUpper = true;
				retstr.append(charString);
			} else {
				if (nextUpper) {
					retstr.append(charString.toUpperCase());
					nextUpper = false;
				} else {
					retstr.append(charString.toLowerCase());
				}
			}

		}
		// if first character is numeric, insert an 'x' at the first position
		if (numericCharacters.indexOf(String.valueOf(retstr.charAt(0))) >= 0)
			retstr = retstr.insert(0, 'X');

		return retstr.toString();
	}

	// strips blank/special characters from a string and makes first char after
	// a special character
	// upper case (except the very first character of the string)

	public static String makeClassName(String newStr) {
		return makeFirstLetterUpperCase(makeVarName(newStr));
	}

	// makes the first Letter UpperCase in a string

	public static String makeFirstLetterLowerCase(String newStr) {
		if (newStr.length() == 0)
			return newStr;

		char[] oneChar = new char[1];
		oneChar[0] = newStr.charAt(0);
		String firstChar = new String(oneChar);
		return (firstChar.toLowerCase() + newStr.substring(1));
	}

	// makes the first Letter UpperCase in a string

	public static String makeFirstLetterUpperCase(String newStr) {
		if (newStr.length() == 0)
			return newStr;

		char[] oneChar = new char[1];
		oneChar[0] = newStr.charAt(0);
		String firstChar = new String(oneChar);
		return (firstChar.toUpperCase() + newStr.substring(1));
	}

	// strips blank/special characters from a string and makes first char after
	// a special character
	// upper case (excluding the very first character of the string)

	public static String makeVarName(String newStr) {
		try {
			StringBuffer retstr = new StringBuffer("");
			String specialCharacters = " _/.,#'%-";
			String numericCharacters = "0123456789";
			if (newStr == null) {
				return "";
			}
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
			// if first character is numeric, insert an 'x' at the first
			// position
			if (numericCharacters.indexOf(String.valueOf(retstr.charAt(0))) >= 0)
				retstr = retstr.insert(0, 'x');

			return retstr.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}

	// overlay function
	// This function overlays a specified string with another starting at
	// a specific position, for a specific length

	public static String ovly(String line, String ovlystr, int ovlypos,
			int ovlylen) {

		int ovlystrlen = ovlystr.length();
		int actlen = ovlylen;
		int targlen = line.length();
		int templen = 0;
		String retline = line;
		// if the overlay length is zero, then use the full length of the
		// overlay string
		if (actlen == 0)
			actlen = ovlystr.length();
		if (actlen == -1)
			actlen = -1 * ovlystr.length();
		// The final string length is the greater of the line length or the
		// overlay
		// string length plus the starting position
		if (actlen >= 0)
			templen = actlen + ovlypos - 1;
		if (templen > targlen)
			targlen = templen;

		char newlinechar[] = new char[targlen];
		newlinechar = line.toCharArray();

		for (int i = 0; i < ovlystrlen; i++) {
			if (actlen > 0) {
				int j = ovlypos + i - 1;
				if (j >= 0 & j < targlen)
					newlinechar[j] = ovlystr.charAt(i);
			} else if (actlen < 0) {
				int k = ovlystrlen - i - 1;
				int j = ovlypos - 1 - i;
				if (j >= 0 & j < targlen)
					newlinechar[j] = ovlystr.charAt(k);
			}
			retline = new String(newlinechar);
		}
		return retline;
	}

	public static String Pad(String line, char padchar, int len) {

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

	public static String PadHex40(String line, int len) {
		int currlen = line.length();
		int diff = len - currlen;
		int maxlen = hex40.length;
		if (diff <= maxlen && diff > 0) {
			return line + new String(hex40, 0, diff);
		}

		char padChar = 0x40;
		return Pad(line, padChar, len);
	}

	/**
	 * This method was created in VisualAge.
	 */
	public static String Padn(String line, char padchar, int len) {
		int actualLen = len;
		if (len < 0)
			actualLen = len * -1;
		if (line.length() > actualLen)
			return line;
		else
			return (Pad(line, padchar, len));
	}

	/**
	 * This method parse a string in the format "parm1=value1&parm2=value2&..."
	 * to a hastable.
	 * 
	 * @param in
	 *            java.lang.String
	 * @return java.lang.String
	 */
	public static final java.util.Hashtable parseStringToHashtable(
			String aQueryString, String delimiter) {
		java.util.Hashtable result = new java.util.Hashtable();
		//
		if (aQueryString == null)
			return result;
		//
		StringTokenizer tline = new StringTokenizer(aQueryString, delimiter);
		while (tline.hasMoreTokens()) {
			String tokstr = tline.nextToken().trim();
			if (!tokstr.equals(""))
				result.put(tokstr, "");
			// System.out.println("Token = "+tokstr);
		}
		return result;
	}

	/**
	 * This method was created in VisualAge.
	 */
	public static boolean patternMatch(String str, String pattern) {

		// "*" matches everything
		if (pattern.equals("*"))
			return true;

		// for pattern begins with "*", remove the first "*"
		// and try matching it with str starting at every position
		if (pattern.startsWith("*")) {
			String p1 = pattern.substring(1);
			for (int i = 0; i < str.length(); i++)
				if (patternMatch(str.substring(i), p1))
					return true;
			return false;
		}

		// find the next "*"
		int t = pattern.indexOf("*");

		// for pattern without any "*", exact match is required
		if (t < 0)
			if (str.equals(pattern))
				return true;
			else
				return false;

		// for pattern with embedded "*",
		// str must match with the pattern up to the next "*" and the rest of
		// str matches with the rest of pattern
		String p2 = pattern.substring(0, t);
		return (str.startsWith(p2) && patternMatch(str.substring(t),
				pattern.substring(t)));
	}

	/**
	 * This method was created in VisualAge.
	 * 
	 * @param in
	 *            java.lang.String
	 * @param from
	 *            java.lang.String
	 * @param to
	 *            java.lang.String
	 * @return java.lang.String
	 */
	public static String replaceString(String in, String from, String to) {
		int i = in.indexOf(from);
		if ((i < 0) || (from.length() == 0))
			return in;
		else
			return in.substring(0, i) + to
					+ replaceString(in.substring(i + from.length()), from, to);
	}

	// convert a string of hex characters to a hex string value

	public static String s2x(String str) {
		String retstr = "";
		int strlen = str.length();
		int lobyte, hibyte;
		for (int i = 0; i < strlen; i++) {
			hibyte = hexval(str.charAt(i)) * 16;
			i++;
			if (i >= strlen)
				lobyte = 0;
			else
				lobyte = hexval(str.charAt(i));
			retstr += (char) (hibyte + lobyte);
		}
		return retstr;
	}

	public static String getCurrentDate() {
		java.util.Date aDate = new java.util.Date();
		return aDate.toString();
	}

	/**
	 * This method was created in VisualAge.
	 * 
	 * @param srcArray
	 *            java.lang.String[]
	 * @return java.lang.Void
	 */
	public static void sortStringArray(String[] srcArray) {
		Collator c = Collator.getInstance();
		c.setStrength(Collator.PRIMARY);
		int arrayLength = srcArray.length;
		for (int i = arrayLength; i > 1; i--)
			for (int j = 0; j < i - 1; j++)
				if (c.compare(srcArray[j], srcArray[j + 1]) > 0) {
					String temp = srcArray[j];
					srcArray[j] = srcArray[j + 1];
					srcArray[j + 1] = temp;
				}
		return;
	}

	// strips special characters from a string

	public static String stripspecial(String newStr) {
		StringBuffer retstr = new StringBuffer("");
		String specialCharacters = " _/.,#'-%";
		int strlen = newStr.length();
		char[] onechar = new char[1];
		for (int i = 0; i < strlen; i++) {
			onechar[0] = newStr.charAt(i);
			String charString = new String(onechar);
			if (specialCharacters.indexOf(charString) >= 0)
				;
			else
				retstr.append(charString);

		}

		return retstr.toString();
	}

	// strips insignificant zeroes from the front of a numeric string

	public static String stripzero(String numstr) {
		String retstr = "";
		boolean firstnz = true;
		int strlen = numstr.length();
		char onechar;
		for (int i = 0; i < strlen; i++) {
			onechar = numstr.charAt(i);
			if (firstnz && onechar == '0')
				;
			else {
				retstr += onechar;
				firstnz = false;
			}
		}
		if (retstr.equals(""))
			retstr = "0";
		return retstr;
	}

	// convert from hex string value to decimal string

	public static String x2d(ByteArrayInputStream inbytes, int precision,
			int scale) {
		String retstr = "";
		String negsign = "";
		String lastchar = "";
		int onemore = precision % 2;
		if (onemore == 1)
			onemore = 0;
		else
			onemore = 1;

		int neglen = -1 * (precision + scale);
		int diflen = precision - scale;
		String wrkstr = x2s(inbytes);
		int wrklen = wrkstr.length();
		lastchar = wrkstr.substring(wrklen - 1);
		if (lastchar.equals("D"))
			negsign = "-";
		wrkstr = wrkstr.substring(0, wrklen - 1);
		if (diflen > 0)
			retstr = wrkstr.substring(0, diflen + onemore);
		if (scale > 0)
			retstr += "." + wrkstr.substring(diflen + onemore);
		retstr = negsign + stripzero(retstr);
		return retstr;
	}

	// convert from hex string value to decimal string

	public static String x2d(String numstr, int precision, int scale) {
		String retstr = "";
		String negsign = "";
		String lastchar = "";
		int onemore = precision % 2;
		if (onemore == 1)
			onemore = 0;
		else
			onemore = 1;

		int neglen = -1 * (precision + scale);
		int diflen = precision - scale;
		String wrkstr = x2s(numstr);
		int wrklen = wrkstr.length();
		lastchar = wrkstr.substring(wrklen - 1);
		if (lastchar.equals("D"))
			negsign = "-";
		wrkstr = wrkstr.substring(0, wrklen - 1);
		if (diflen > 0)
			retstr = wrkstr.substring(0, diflen + onemore);
		if (scale > 0)
			retstr += "." + wrkstr.substring(diflen + onemore);
		retstr = negsign + stripzero(retstr);
		return retstr;
	}

	// convert from hex string value to integer string

	public static String x2i(ByteArrayInputStream inbytes, boolean smallint) {
		String retstr = "";
		try {
			String wrkstr = x2s(inbytes);
			int strlen = wrkstr.length();
			int tempval = 0;
			long retval = 0;
			for (int i = 0; i < strlen; i++) {
				int k = strlen - i - 1;
				int onebyte = hexval(wrkstr.charAt(k));
				tempval = onebyte * exp(16, i);
				retval += tempval;
			}
			if (smallint) {
				if (retval > 32767)
					retval = retval - 65536;
			} else {
				if (retval > 2147483647)
					retval = retval - 4294967296L;
			}
			Long integval = new Long(retval);
			retstr = integval.toString();
		} // end try
		catch (Exception e) {
			return ("Error: " + e);
		} // end catch
		return retstr;
	}

	// convert from hex string value to integer string

	public static String x2i(String numstr, boolean smallint) {
		String retstr = "";
		try {
			String wrkstr = x2s(numstr);
			int strlen = wrkstr.length();
			int tempval = 0;
			long retval = 0;
			for (int i = 0; i < strlen; i++) {
				int k = strlen - i - 1;
				int onebyte = hexval(wrkstr.charAt(k));
				tempval = onebyte * exp(16, i);
				retval += tempval;
			}
			if (smallint) {
				if (retval > 32767)
					retval = retval - 65536;
			} else {
				if (retval > 2147483647)
					retval = retval - 4294967296L;
			}
			Long integval = new Long(retval);
			retstr = integval.toString();
		} // end try
		catch (Exception e) {
			return ("Error: " + e);
		} // end catch
		return retstr;
	}

	// convert a hex string value to a string of hex characters

	public static String x2s(ByteArrayInputStream inbytes) {
		String retstr = "";
		int strlen = inbytes.available();
		int targlen = strlen * 2;
		String tempstr = "";
		for (int i = 0; i < strlen; i++) {
			int intval = (int) inbytes.read();

			tempstr = Integer.toHexString(intval);
			if (tempstr.length() == 1)
				retstr += "0" + tempstr;
			else
				retstr += tempstr;
		}
		return retstr.toUpperCase();
	}

	public static Properties extractProperties(Properties props, String mask) {
		// Extract a subset of properties from the properties file
		// with keys that contains a mask

		Properties results = new Properties();
		Enumeration enum1 = props.keys();
		while (enum1.hasMoreElements()) {
			String aKey = (String) enum1.nextElement();
			if (aKey != null && aKey.indexOf(mask) >= 0) {
				Object aValue = props.getProperty(aKey);
				results.put(aKey, aValue);
			}
		}
		return results;
	}

	public static List extractPropertyValues(Properties props) {
		// Extract and create a list of property values
		List results = new ArrayList();

		Enumeration enum1 = props.elements();
		while (enum1.hasMoreElements()) {
			results.add(enum1.nextElement());
		}
		return results;
	}

	public static void createDir(File inFile) {
		if (inFile.exists())
			return;

		File inDir = new File(inFile.getParent());
		if (!inDir.isDirectory())
			inDir.mkdirs();
	}

	public static boolean hasMask(String inString, String mask) {
		boolean hasSubstring = inString.toUpperCase().indexOf(
				mask.toUpperCase()) >= 0;
		return hasSubstring;
	}

	public static String makeLowerCase(String aString) {
		return aString.toLowerCase();
	}

	public static String makeUpperCase(String aString) {
		return aString.toUpperCase();
	}

	// convert a hex string value to a string of hex characters

	public static String x2s(String hexstr) {
		String retstr = "";
		int strlen = hexstr.length();
		int targlen = strlen * 2;
		String tempstr = "";
		for (int i = 0; i < strlen; i++) {
			int intval = (int) hexstr.charAt(i);
			tempstr = Integer.toHexString(intval);
			if (tempstr.length() == 1)
				retstr += "0" + tempstr;
			else
				retstr += tempstr;
		}
		return retstr.toUpperCase();
	}

	public static String makeSampleData(String attType, String colname,
			Integer colsizeNum, Short coltypeNum, String testcase,
			String incQuote) {
		SimpleDateFormat dtFormat = new SimpleDateFormat("");
		dtFormat.setLenient(false);
		dtFormat.applyPattern("yyyy-MM-dd");
		return makeSampleDataWithDatePattern(dtFormat, attType, colname,
				colsizeNum, coltypeNum, testcase, incQuote);
	}

	public static String makeSampleDataWithInputDatePattern(String attType,
			String colname, Integer colsizeNum, Short coltypeNum,
			String testcase, String incQuote) {
		SimpleDateFormat dtFormat = new SimpleDateFormat("");
		dtFormat.setLenient(false);
		dtFormat.applyPattern("MM/dd/yy");
		return makeSampleDataWithDatePattern(dtFormat, attType, colname,
				colsizeNum, coltypeNum, testcase, incQuote);
	}

	public static String makeSampleDataPK(SqlTable aTable, String pattern,
			String testcase) {
		int numKeys = aTable.getPrimaryKeys().size();
		String className = makeClassName(aTable.getEntityName());
		String pkey = "new " + className + "PK (";
		String comma = "";
		for (int i = 0; i < numKeys; i++) {
			SqlColumn col = aTable.getPrimaryKey(i);
			Short coltype = new Short(col.getColtype());
			Integer colsize = new Integer(col.getColsize());
			String coldata = makeSampleData(pattern, col.getAttType(),
					col.getColname(), colsize, coltype, testcase, "Y");
			pkey = pkey + comma + coldata;
			comma = ",";
		}
		pkey = pkey + ")";
		return pkey;
	}

	public static String makeSampleData(String pattern, String attType,
			String colname, Integer colsizeNum, Short coltypeNum,
			String testcase, String incQuote) {
		SimpleDateFormat dtFormat = new SimpleDateFormat("");
		dtFormat.setLenient(false);
		dtFormat.applyPattern(pattern);
		return makeSampleDataWithDatePattern(dtFormat, attType, colname,
				colsizeNum, coltypeNum, testcase, incQuote);
	}

	/**
	 * @param attType
	 * @param colname
	 * @param colsizeNum
	 * @param coltypeNum
	 * @param testcase
	 * @param incQuote
	 * @return
	 */
	private static String makeSampleDataWithDatePattern(
			SimpleDateFormat dtFormat, String attType, String colname,
			Integer colsizeNum, Short coltypeNum, String testcase,
			String incQuote) {
		String sampleData = "";
		int colsize = colsizeNum.intValue();
		short coltype = coltypeNum.shortValue();

		boolean includeQuotes = incQuote.equalsIgnoreCase("Y");
		String s = "";
		long lng = 0;
		if (includeQuotes)
			s = quote;
		try {
			lng = (new Long(testcase)).longValue();
		} catch (Exception e) {
			lng = 0;
		}

		String longString = "(new Long(" + testcase + ")).longValue()";
		SimpleDateFormat tsFormatter = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss.SSS'000'");

		SimpleDateFormat timeFormat = new SimpleDateFormat("");
		timeFormat.setLenient(false);
		timeFormat.applyPattern("hh:mm:ss");

		if (Functions.hasMask(attType, "Integer")) {
			if (includeQuotes)
				sampleData = "new Integer(" + testcase + ")";
			else
				sampleData = testcase + "";
		} else if (Functions.hasMask(attType, "BigDecimal")) {
			if (includeQuotes)
				sampleData = "new BigDecimal(" + testcase + ".0 )";
			else
				sampleData = testcase + ".0";
		} else if (Functions.hasMask(attType, "Short")) {

			if (includeQuotes)
				sampleData = "(new Short((new Long(" + testcase
						+ ")).shortValue()))";
			else
				sampleData = testcase + "";
		} else if (Functions.hasMask(attType, "Long")) {

			if (includeQuotes)
				sampleData = "new Long(" + testcase + ")";
			else
				sampleData = testcase + "";
		} else if (Functions.hasMask(attType, "Double")) {
			if (includeQuotes)
				sampleData = "new Double(" + testcase + ")";
			else
				sampleData = testcase + "";
		} else if (Functions.hasMask(attType, "Float")) {
			if (includeQuotes)
				sampleData = "new Float(" + testcase + ")";
			else
				sampleData = testcase + "";
		} else if (Functions.hasMask(attType, "String")) {
			if (colsize > 1)
				sampleData = StringUtil.makeVarName(colname);
			else
				sampleData = "";

			if (colsize > 1 && sampleData.length() > colsize - 1) {
				sampleData = sampleData.substring(0, colsize - 1);
			}
			sampleData = s + sampleData + testcase + s;
		} else if (Functions.hasMask(attType, "Date")) {
			if (includeQuotes)
				sampleData = "(new java.util.Date(" + longString + "))";
			else
				sampleData = dtFormat.format(new java.util.Date(lng));
		} else if (Functions.hasMask(attType, "Timestamp")) {
			if (includeQuotes)
				sampleData = "(new java.sql.Timestamp(" + longString + "))";
			else
				sampleData = tsFormatter.format(new java.sql.Timestamp(lng));
		} else if (Functions.hasMask(attType, "Time")) {
			if (includeQuotes)
				sampleData = "(new java.sql.Time(" + longString + "))";
			else
				sampleData = timeFormat.format(new java.sql.Time(lng));
		} else
			sampleData = testcase + "";

		return sampleData;
	}

	public static String quote(String aString) {
		return quote + aString + quote;
	}

	/**
	 * @param sqlTable
	 */
	public static ListHashtable getResolvedProperties(SqlTable sqlTable) {
		String FS = File.separator;
		String tableName = sqlTable.getTable();
		String srcName = makeClassName(sqlTable.getEntityName());
		String lowercaseSrcName = makeFirstLetterLowerCase(srcName);
		String entityName = makeClassName(sqlTable.getEntityName());

		// Default output is an xml file
		String defaultPackagePrefix = sqlTable.getPackagePrefix();

		// String defaultPackagePrefix =
		// ApplicationProperties.getProperty("packagePrefix");
		String defaultPackageLocation = replaceString(defaultPackagePrefix,
				".", FS);

		// Get package prefix depending on table name
		String packageMod = TemplateProcessor.getPackageResolver()
				.getPropertyValue(tableName);
		String packageModot = "";
		String packageModLocation = "";

		// if (!packageMod.equals("")) {
		// packageModot = "." + packageMod;
		// packageModLocation = replaceString(packageMod, ".", FS) + FS;
		// }

		packageModot = sqlTable.getPackageModot();
		packageModLocation = replaceString(packageModot, ".", FS) + FS;

		// Populate request context attributes
		String packageLocationFS = replaceString(defaultPackagePrefix, ".", "/");
		ListHashtable reqProperties = new ListHashtable();
		reqProperties.put("srcName", srcName);
		reqProperties.put("entityName", entityName);
		reqProperties.put("packagePrefix", defaultPackagePrefix);
		reqProperties.put("packageLocation", defaultPackageLocation);
		reqProperties.put("packageLocationFS", packageLocationFS);
		reqProperties.put("packageModLocation", packageModLocation);
		reqProperties.put("packageMod", packageMod);
		reqProperties.put("packageModot", packageModot);
		return reqProperties;
	}

	public static void main(String[] args) {
		System.out.println(Functions.makeClassName("Implementation_Para"));
	}
} // end of class Function
