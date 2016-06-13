package com.codegenerator.common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import com.codegenerator.db.SqlTable;

public class TemplateProcessor extends ApplicationObject {
	private Properties properties;
	private VelocityEngine vengine = null;
	private static PackageNameResolver packageResolver = null;
	private static FileLocationResolver fileResolver = null;
	private static FileNameResolver fileNameResolver = null;

	public TemplateProcessor(Properties inputProperties) {
		super();
		properties = inputProperties;
		initResolvers();
	}

	/**
	 * 
	 */
	public void process(VelocityContext context) throws Exception {
		initVelocity(properties.getProperty("templateLocation"));
		// Initialize request properties used for resolving package names
		// file locations and field types
		initRequestProperties(context);
		generateSourceFiles(context);
		generatePropertyFiles(context);
		generateNoParseFiles(context);
		generateFileLists(context);
	}

	public void processInitTemplates(VelocityContext context) throws Exception {
		initVelocity(properties.getProperty("templateLocation"));
		// Initialize request properties used for resolving package names
		// file locations and field types
		generateInitFiles(context);
	}

	private void generateInitFiles(VelocityContext context) throws Exception {
		// Create Java Model First - to set up any data
		Properties templateList = Functions.extractProperties(properties,
				"InitTpl");

		if (templateList.size() == 0)
			return;

		// String modelTemplate = ApplicationProperties.getModelTemplate();
		Enumeration enum1 = templateList.keys();
		while (enum1.hasMoreElements()) {
			String aKey = (String) enum1.nextElement();
			String aValue = (String) templateList.get(aKey);
			Vector oneParm = StringUtil.parseToVector(aValue, "^");
			String template = (String) oneParm.elementAt(0);//
			String location = (String) oneParm.elementAt(1);//
			System.out.println("TemplateProcessor...template: " + template
					+ "/n  location: " + location);
			processInitTemplate(context, aKey, template, location);
		}

	}

	/**
	 * 处理初始化 templateFile
	 * 
	 * @param context
	 *            velocity context
	 * @param template
	 *            template file name
	 * @param relativeFileLoc
	 *            out put file's location relative to javaOutputLocation
	 */
	private void processInitTemplate(VelocityContext context,
			String templateKey, String templateFile, String relativeFileLoc)
			throws Exception {

		String fileName = StringUtil.trimBeforeLastDot(templateFile);
		// fileName = ssb-dao.xml
		String outputFilePath = ApplicationProperties
				.getProperty("javaOutputLocation") + relativeFileLoc + fileName;
		File outputFile = new File(outputFilePath);
		Template template = vengine.getTemplate(templateFile, "UTF-8");

		Functions.createDir(outputFile);

		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(outputFile), "UTF-8"));

		template.merge(context, writer);//
		writer.flush();
		writer.close();

	}

	/**
	 * This method puts the attributes needed to resolve the file location field
	 * types and package names into the request attribute ListHashtable so that
	 * they can be retrieved when processing each template related to the
	 * requested table.
	 * 
	 * @param context
	 */
	public void initRequestProperties(VelocityContext context) {

		SqlTable sqlTable = (SqlTable) context.get("sqlTable");
		ListHashtable resolvedProperties = Functions
				.getResolvedProperties(sqlTable);
		ThreadContext.getCurrentContext().getRequestProperties()
				.merge(resolvedProperties);
		String packageMod = (String) resolvedProperties.get("packageMod");
		String packageModot = (String) resolvedProperties.get("packageModot");

		String packagePrefix = (String) resolvedProperties.get("packagePrefix");
		// save these two properties in the global application properties
		ApplicationProperties.getProperties().put("packageMod", packageMod);
		ApplicationProperties.getProperties().put("packageModot", packageModot);
		ApplicationProperties.getProperties().put("packagePrefix",
				packagePrefix);

	}

	/**
	 * 
	 */
	private void initResolvers() {
		if (fileResolver == null)
			fileResolver = new FileLocationResolver();

		if (packageResolver == null)
			packageResolver = new PackageNameResolver();

		if (fileNameResolver == null)
			fileNameResolver = new FileNameResolver();
	}

	/**
	 * ��ʼ��Velocity
	 * 
	 * @param templateDirectory
	 * @throws Exception
	 */
	protected void initVelocity(String templateDirectory) throws Exception {
		// Init Velocity
		System.out.println("templateDirectory: " + templateDirectory);
		Properties p = new Properties();
		// p.load(getClass().getResourceAsStream("/velocity.properties"));
		p.put("file.resource.loader.path", templateDirectory);
		vengine = new VelocityEngine();
		p.setProperty("input.encoding", "utf-8");
		p.setProperty("output.encoding", "utf-8");
		vengine.init(p);
	}

	private void generatePropertyFiles(VelocityContext context)
			throws Exception {

		SqlTable sqlTable = (SqlTable) context.get("sqlTable");
		String srcName = Functions.makeClassName(sqlTable.getEntityName());
		// process the rest of the templates in the properties file
		Properties embeddedList = Functions.extractProperties(properties,
				"Embedded");
		if (embeddedList.size() == 0) {
			// System.out.println("generatePropertyFiles: Embedded size 0 ...!");
			return;
		}

		Enumeration enum1 = embeddedList.elements();
		while (enum1.hasMoreElements()) {
			String embeddedParms = (String) enum1.nextElement();
			System.out.println("embeddedParms: " + embeddedParms);
			while (Functions.hasMask(embeddedParms, "${")) {
				embeddedParms = PropertiesResolver.resolveParms(embeddedParms);
			}
			processEmbeddedTemplate(context, srcName, embeddedParms);
		}
	}

	private void generateSourceFiles(VelocityContext context) throws Exception {

		// Create Java Model First - to set up any data
		SqlTable sqlTable = (SqlTable) context.get("sqlTable");
		String entityName = Functions.makeClassName(sqlTable.getEntityName());
		Properties templateList = Functions.extractProperties(properties,
				"Template");
		if (templateList.size() == 0) {
			// System.out.println("generateSourceFiles: Template size 0 ...!");
			return;
		}

		// String modelTemplate = ApplicationProperties.getModelTemplate();
		Enumeration enum1 = templateList.keys();
		while (enum1.hasMoreElements()) {
			String aKey = (String) enum1.nextElement();
			String aValue = (String) templateList.get(aKey);
			Vector oneParm = StringUtil.parseToVector(aValue, "^");
			String template = (String) oneParm.elementAt(0);
			processTemplate(context, aKey, template);
		}

	}

	private void processEmbeddedTemplate(VelocityContext context,
			String srcName, String embeddedParms) throws Exception {

		// Note: The embeddedparms is encoded with the templating information
		//
		String quote = Functions.quote;
		String crlf = Functions.crlf;

		Vector parms = StringUtil.parseToVector(embeddedParms, "^");
		String templateFile = ((String) parms.elementAt(0)).trim();
		// Check whether this template should be skipped
		if (ApplicationProperties.isExcludedProperty(templateFile)) {
			System.out.println("Excluded Embedded File: " + templateFile
					+ " .... Skipped ...!");
			return;
		}
		// System.out.println("Embedded File: " + templateFile +
		// " .... Processing ...!");

		String includeCommand = "#parse (" + quote + templateFile + quote + ")";
		String includeString = "";

		String excludeString = " Generated for " + srcName + " From Template: "
				+ templateFile;
		if (Functions.hasMask(templateFile, ".xml")) {
			includeString = "<!-- start: " + excludeString + "  -->" + crlf
					+ includeCommand + crlf + crlf + "<!-- end: "
					+ excludeString + "  -->";
		} else if (Functions.hasMask(templateFile, ".properties")) {
			includeString = "#---- start: " + excludeString + FileUtility.crlf
					+ includeCommand + crlf + crlf + "#--- end: "
					+ excludeString;
		} else if (Functions.hasMask(templateFile, ".jsp")) {
			includeString = "<%-- start: " + excludeString + "  --%>" + crlf
					+ includeCommand + crlf + crlf + "<%-- end: "
					+ excludeString + "  --%>";
		} else {
			includeString = "//  start: " + excludeString + crlf
					+ includeCommand + crlf + crlf + crlf + "//  end: "
					+ excludeString;
		}
		String relativeFileLoc = ((String) parms.elementAt(1)).trim();
		String inFileName = StringUtil.trimBeforeLastDot(templateFile);

		String inputFile = ApplicationProperties
				.getProperty("javaOutputLocation")
				+ relativeFileLoc
				+ inFileName;
		String outputFile = inputFile;

		File inFile = new File(inputFile);

		FileUtility flUtil = new FileUtility();
		StringBuffer stringTemplate = flUtil.getInputFileAsString(inFile,
				excludeString, includeString);

		// if there was no substitution, then look for the last occurence of the
		// following mask String and insert at that location
		if (flUtil.getFilter().getNumSections() == 0) {
			String newMask = "";
			if (parms.size() >= 3) {
				newMask = ((String) parms.elementAt(2)).trim();
			}

			String currString = stringTemplate.toString();
			int pos = 0;
			if (!newMask.equals(""))
				pos = currString.lastIndexOf(newMask);

			if (pos > 0) {
				StringBuffer tempBuffer = new StringBuffer();
				tempBuffer.append(currString.substring(0, pos));
				tempBuffer.append(includeString);
				tempBuffer.append(crlf);
				tempBuffer.append(currString.substring(pos));
				stringTemplate = tempBuffer;
			}

			else
			// insert at beginning
			if (newMask.equalsIgnoreCase("$BOF")) {
				StringBuffer tempBuffer = new StringBuffer(includeString);
				tempBuffer.append(crlf);
				tempBuffer.append(stringTemplate.toString());
				stringTemplate = tempBuffer;
			} else
			// insert at end of file - for default
			if (newMask.equalsIgnoreCase("$EOF") || newMask.equals("")) {
				stringTemplate.append(crlf);
				stringTemplate.append(includeString);
			}
		}

		File outFile = new File(outputFile);
		Functions.createDir(outFile);

		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(outFile), "UTF-8"));

		vengine.evaluate(context, writer, "LOG", stringTemplate.toString());

		writer.flush();
		writer.close();

		System.out.println("Embedded File: " + templateFile + " generated!");
	}

	/**
	 * @param context
	 * @param srcName
	 * @param modelTemplate
	 * @throws FileNotFoundException
	 * @throws ResourceNotFoundException
	 * @throws ParseErrorException
	 * @throws Exception
	 * @throws IOException
	 * @throws MethodInvocationException
	 */
	protected void processTemplate(VelocityContext context, String keyName,
			String modelTemplate) throws Exception {

		// Check whether this template should be skipped
		if (ApplicationProperties.isExcludedProperty(modelTemplate)) {
			System.out.println("Excluded Template File: " + modelTemplate
					+ " .... Skipped ...!");
			return;
		}

		SqlTable table = (SqlTable) context.get("sqlTable");
		// Check whether Primary Key Template should be skipped
		if (modelTemplate.startsWith("PK")) {
			if (!table.getHasCompositeKey()) {
				System.out.println("Excluded Template File: " + modelTemplate
						+ " .... No Composite Key Detected ...!");
				return;
			}
		}

		String locationAndFileName = getLocationAndFileName(keyName, table,
				modelTemplate);
		File outFile = new File(locationAndFileName);
		if (Functions.hasMask(keyName, "NoOverwrite")) {
			if (outFile.exists()) {
				System.out.println(".. Warning.. " + locationAndFileName
						+ " Already exists.. Generation skipped");
				return;
			}
		}
		// Load the model
		System.out.println("Class "
				+ Functions.makeClassName(table.getEntityName())
				+ "..Processing Template = " + modelTemplate + "!");
		Template template = vengine.getTemplate(modelTemplate, "UTF-8");
		Functions.createDir(outFile);

		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(outFile), "UTF-8"));

		template.merge(context, writer);
		writer.flush();
		writer.close();

		System.out.println("Class "
				+ Functions.makeClassName(table.getEntityName())
				+ " generated!");
	}

	/**
	 * @return
	 */
	private String getLocationAndFileName(String keyName, SqlTable table,
			String template) {
		String javaOutputLocation = ApplicationProperties
				.getProperty("javaOutputLocation");
		String defaultOutputLocation = ApplicationProperties
				.getProperty("defaultOutputLocation");

		ThreadContext.setRequestProperties("EntityName", table.getEntityName());
		ThreadContext.setRequestProperties("Table",
				Functions.makeClassName(table.getTable()));
		String temp = template;
		if (Functions.hasMask(keyName, "Interface")) {
			temp = template.substring(1);
		}
		ThreadContext.setRequestProperties("TemplateName",
				StringUtil.trimBeforeLastDot(temp));
		String outputFile = fileNameResolver.getPropertyValue(template);
		if (!temp.trim().equals(template.trim())) {
			outputFile = "I" + outputFile;
		}
		// Default output is an xml file
		String locationAndFileName = defaultOutputLocation + outputFile;
		String lowercaseOutputFile = Functions
				.makeFirstLetterLowerCase(outputFile);
		ThreadContext.setRequestProperties("lowercaseOutputFile",
				lowercaseOutputFile);
		String resolvedLocation = fileResolver.getPropertyValue(template);
		if (!resolvedLocation.equals("")) {
			if (!javaOutputLocation.equals(""))
				locationAndFileName = javaOutputLocation + resolvedLocation;
			else
				locationAndFileName = defaultOutputLocation + resolvedLocation;
		}
		if (locationAndFileName.indexOf(lowercaseOutputFile) < 0)
			locationAndFileName = locationAndFileName + outputFile;
		return locationAndFileName;
	}

	private void generateFileLists(VelocityContext context) throws Exception {

		Properties velocityList = Functions.extractProperties(properties,
				"velocityList");
		if (velocityList.size() == 0) {
			// System.out.println("generateFileLists: velocityList size 0 ...!");
			return;
		}

		Enumeration enum1 = velocityList.elements();
		String framework = ApplicationProperties.getFramework();

		while (enum1.hasMoreElements()) {
			String listFileName = (String) enum1.nextElement();
			System.out
					.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("+++++ Processing template List: "
					+ listFileName);
			System.out
					.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			Properties fileProps = ApplicationProperties
					.getDefaultProperties(framework + "." + listFileName);
			// Properties fileProps =
			// ApplicationProperties.getDefaultProperties("sample.appfuse."+listFileName);
			TemplateProcessor templateProcessor = new TemplateProcessor(
					fileProps);
			templateProcessor.process(context);
		}
	}

	private void processNoParseTemplate(VelocityContext context,
			String srcName, String embeddedParms) throws Exception {

		// Note: The embeddedparms is encoded with the templating information
		//
		String quote = Functions.quote;
		String crlf = Functions.crlf;

		Vector parms = StringUtil.parseToVector(embeddedParms, "^");
		String templateFile = ((String) parms.elementAt(0)).trim();
		// Check whether this template should be skipped
		if (ApplicationProperties.isExcludedProperty(templateFile)) {
			System.out.println("Excluded NoParse File: " + templateFile
					+ " .... Skipped ...!");
			return;
		}
		// System.out.println("NoParse File: " + templateFile +
		// " .... Processing ...!");

		String includeString = "";
		StringWriter writer = new StringWriter();
		Template templ = vengine.getTemplate(templateFile, "UTF-8");
		templ.merge(context, writer);
		String includeFileContents = writer.toString();

		String excludeString = " Generated for " + srcName + " From Template: "
				+ templateFile;
		if (Functions.hasMask(templateFile, ".xml")) {
			includeString = "<!-- start: " + excludeString + "  -->" + crlf
					+ includeFileContents + crlf + "<!-- end: " + excludeString
					+ "  -->";
		} else if (Functions.hasMask(templateFile, ".properties")) {
			includeString = "#---- start: " + excludeString + FileUtility.crlf
					+ includeFileContents + crlf + "#--- end: " + excludeString;
		} else if (Functions.hasMask(templateFile, ".jsp")) {
			includeString = "<%-- start: " + excludeString + "  --%>" + crlf
					+ includeFileContents + crlf + "<%-- end: " + excludeString
					+ "  --%>";
		} else {
			includeString = "//  start: " + excludeString + crlf
					+ includeFileContents + crlf + crlf + crlf + "//  end: "
					+ excludeString;
		}
		String relativeFileLoc = ((String) parms.elementAt(1)).trim();
		String inFileName = StringUtil.trimBeforeLastDot(templateFile);

		String inputFile = ApplicationProperties
				.getProperty("javaOutputLocation")
				+ relativeFileLoc
				+ inFileName;
		String outputFile = inputFile;

		File inFile = new File(inputFile);

		FileUtility flUtil = new FileUtility();
		StringBuffer stringTemplate = flUtil.getInputFileAsString(inFile,
				excludeString, includeString);

		// if there was no substitution, then look for the last occurence of the
		// following mask String and insert at that location
		if (flUtil.getFilter().getNumSections() == 0) {
			String newMask = "";
			if (parms.size() >= 3)
				newMask = ((String) parms.elementAt(2)).trim();

			String currString = stringTemplate.toString();
			int pos = 0;
			if (!newMask.equals(""))
				pos = currString.lastIndexOf(newMask);

			if (pos > 0) {
				StringBuffer tempBuffer = new StringBuffer();
				tempBuffer.append(currString.substring(0, pos));
				tempBuffer.append(includeString);
				tempBuffer.append(crlf);
				tempBuffer.append(currString.substring(pos));
				stringTemplate = tempBuffer;
			}

			else
			// insert at beginning
			if (newMask.equalsIgnoreCase("$BOF")) {
				StringBuffer tempBuffer = new StringBuffer(includeString);
				tempBuffer.append(crlf);
				tempBuffer.append(stringTemplate.toString());
				stringTemplate = tempBuffer;
			} else
			// insert at end of file - for default
			if (newMask.equalsIgnoreCase("$EOF") || newMask.equals("")) {
				stringTemplate.append(crlf);
				stringTemplate.append(includeString);
			}
		}

		File outFile = new File(outputFile);
		Functions.createDir(outFile);

		BufferedWriter outputWriter = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(outFile), "UTF-8"));
		outputWriter.write(stringTemplate.toString());
		outputWriter.flush();
		outputWriter.close();

		System.out.println("NoParse File: " + templateFile + " generated!");
	}

	private void generateNoParseFiles(VelocityContext context) throws Exception {

		SqlTable sqlTable = (SqlTable) context.get("sqlTable");
		String srcName = Functions.makeClassName(sqlTable.getEntityName());
		// process the rest of the templates in the properties file
		Properties noParseList = Functions.extractProperties(properties,
				"NoParse");
		if (noParseList.size() == 0) {
			// System.out.println("generateNoParseFiles: NoParse size 0 ...!");
			return;
		}
		Enumeration enum1 = noParseList.elements();
		while (enum1.hasMoreElements()) {
			String embeddedParms = (String) enum1.nextElement();
			System.out.println("embeddedParms: " + embeddedParms);
			processNoParseTemplate(context, srcName, embeddedParms);
		}
	}

	/**
	 * @return Returns the fileNameResolver.
	 */
	public static FileNameResolver getFileNameResolver() {
		return fileNameResolver;
	}

	/**
	 * @return Returns the fileResolver.
	 */
	public static FileLocationResolver getFileResolver() {
		return fileResolver;
	}

	/**
	 * @return Returns the packageResolver.
	 */
	public static PackageNameResolver getPackageResolver() {
		return packageResolver;
	}
}
