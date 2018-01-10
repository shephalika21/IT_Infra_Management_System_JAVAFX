package applicationModel;

public class Apps {
	String appName;
	String appCritcality;
	String appVersion;
	String appDBhosted;
	String appRelatedServer;
	String apptype;
	/**
	 * @param appName
	 * @param appCritcality
	 * @param appVersion
	 * @param appDBhosted
	 * @param appRelatedServer
	 * @param apptype
	 */
	public Apps(String appName, String appCritcality, String appVersion, String appDBhosted, String appRelatedServer,
			String apptype) {
		super();
		this.appName = appName;
		this.appCritcality = appCritcality;
		this.appVersion = appVersion;
		this.appDBhosted = appDBhosted;
		this.appRelatedServer = appRelatedServer;
		this.apptype = apptype;
	}
	/**
	 * @return the appName
	 */
	public String getAppName() {
		return appName;
	}
	/**
	 * @param appName the appName to set
	 */
	public void setAppName(String appName) {
		this.appName = appName;
	}
	/**
	 * @return the appCritcality
	 */
	public String getAppCritcality() {
		return appCritcality;
	}
	/**
	 * @param appCritcality the appCritcality to set
	 */
	public void setAppCritcality(String appCritcality) {
		this.appCritcality = appCritcality;
	}
	/**
	 * @return the appVersion
	 */
	public String getAppVersion() {
		return appVersion;
	}
	/**
	 * @param appVersion the appVersion to set
	 */
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	/**
	 * @return the appDBhosted
	 */
	public String getAppDBhosted() {
		return appDBhosted;
	}
	/**
	 * @param appDBhosted the appDBhosted to set
	 */
	public void setAppDBhosted(String appDBhosted) {
		this.appDBhosted = appDBhosted;
	}
	/**
	 * @return the appRelatedServer
	 */
	public String getAppRelatedServer() {
		return appRelatedServer;
	}
	/**
	 * @param appRelatedServer the appRelatedServer to set
	 */
	public void setAppRelatedServer(String appRelatedServer) {
		this.appRelatedServer = appRelatedServer;
	}
	/**
	 * @return the apptype
	 */
	public String getApptype() {
		return apptype;
	}
	/**
	 * @param apptype the apptype to set
	 */
	public void setApptype(String apptype) {
		this.apptype = apptype;
	}
	
	

}
