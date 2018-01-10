package applicationModel;

public class DatabaseDetails {
	
	private String dbName;
	private String dbType;
	private String dbservername;
	private String dbapplication;
	private String dbVersion;
	private String dbCriticality;
	/**
	 * @param dbName
	 * @param dbType
	 * @param dbservername
	 * @param dbapplication
	 * @param dbVersion
	 * @param dbCriticality
	 */
	public DatabaseDetails(String dbName1, String dbType1, String dbservername1, String dbapplication1, String dbVersion1,
			String dbCriticality1) {
		super();
		dbName = dbName1;
		dbType = dbType1;
		dbservername = dbservername1;
		dbapplication = dbapplication1;
		dbVersion = dbVersion1;
		dbCriticality = dbCriticality1;
	}
	/**
	 * @return the dbName
	 */
	public String getDbName() {
		return dbName;
	}
	/**
	 * @param dbName the dbName to set
	 */
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	/**
	 * @return the dbType
	 */
	public String getDbType() {
		return dbType;
	}
	/**
	 * @param dbType the dbType to set
	 */
	public void setDbType(String dbType) {
		this.dbType = dbType;
	}
	/**
	 * @return the dbservername
	 */
	public String getDbservername() {
		return dbservername;
	}
	/**
	 * @param dbservername the dbservername to set
	 */
	public void setDbservername(String dbservername) {
		this.dbservername = dbservername;
	}
	/**
	 * @return the dbapplication
	 */
	public String getDbapplication() {
		return dbapplication;
	}
	/**
	 * @param dbapplication the dbapplication to set
	 */
	public void setDbapplication(String dbapplication) {
		this.dbapplication = dbapplication;
	}
	/**
	 * @return the dbVersion
	 */
	public String getDbVersion() {
		return dbVersion;
	}
	/**
	 * @param dbVersion the dbVersion to set
	 */
	public void setDbVersion(String dbVersion) {
		this.dbVersion = dbVersion;
	}
	/**
	 * @return the dbCriticality
	 */
	public String getDbCriticality() {
		return dbCriticality;
	}
	/**
	 * @param dbCriticality the dbCriticality to set
	 */
	public void setDbCriticality(String dbCriticality) {
		this.dbCriticality = dbCriticality;
	}
	
	
		
}