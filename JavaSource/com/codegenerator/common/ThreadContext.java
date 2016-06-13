package com.codegenerator.common;

import com.codegenerator.db.Dbconn;

public class ThreadContext extends ApplicationObject {
	private static ThreadLocal threadContext = null;
	private ApplicationProperties applicationProperties = null;
	private ListHashtable requestProperties = null;
	private Dbconn dbconn = null;
	
	/**
	 * 
	 */
	private ThreadContext() {
		super();
		// instantiate new requestProperties
		requestProperties = new ListHashtable();
		// get a new application properties object
		applicationProperties = new ApplicationProperties();
		// instantiate connection to be used in the thread
		dbconn = new Dbconn();
		try {
			dbconn.connect();
		} catch (Exception e) {
			throw new RuntimeException("Error connecting to database: " + e.getMessage());
		}
	}
	public static synchronized ThreadContext getCurrentContext() {
		if (getThreadContext().get() == null) {
			ThreadContext aContext = new ThreadContext();
			threadContext.set(aContext);
		}
		return (ThreadContext) getThreadContext().get();
	}

	/**
	 * @return Returns the applicationProperties.
	 */
	public ApplicationProperties getApplicationProperties() {
		return applicationProperties;
	}
	/**
	 *  commit and set current context to null
	 *
	 */
	public void terminate(boolean commit) {
		getCurrentContext().getDbconn().closeconn(commit);
		getThreadContext().set(null);
	}
	public void commit() {
		terminate(true);
	}
	public void rollback() {
		terminate(false);
	}
	/**
	 * @return Returns the dbconn.
	 */
	public Dbconn getDbconn() {
		return dbconn;
	}
	/**
	 * @return Returns the threadContext.
	 */
	private static ThreadLocal getThreadContext() {
		if (threadContext == null) {
			threadContext = new ThreadLocal();
		}
		return threadContext;
	}
	/**
	 * @return Returns the requestProperties.
	 */
	public ListHashtable getRequestProperties() {
		return requestProperties;
	}
	public static void setRequestProperties(Object key, Object value) {
		getCurrentContext().getRequestProperties().put(key,value);
	}
	public static String getRequestPropertyAsString(String key) {
		return (String) getCurrentContext().getRequestProperties().get(key);
	}
}
