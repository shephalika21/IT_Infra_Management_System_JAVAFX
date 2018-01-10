package applicationModel;

/**
 * @author Sunil
 *
 */
public class ServerDetails {
	
	private String ServerName;
	private String ServerIP;
	private String ServerType;
	private int CPU;
	private int Memory;
	private String OSVersion;
	private String Criticality;
	private String Status;
	//constructor of the class and have setter and getter 
	public ServerDetails(String serverName, String serverIP, String serverType, int cPU, int memory, 
			String criticality, String status) {
		super();
		ServerName = serverName;
		ServerIP = serverIP;
		ServerType = serverType;
		CPU = cPU;
		Memory = memory;
		//OSVersion = oSVersion;
		Criticality = criticality;
		Status = status;
	}
	
	
	public String getServerName() {
		return ServerName;
	}
	
	public void setServerName(String serverName) {
		ServerName = serverName;
	}
	public String getServerIP() {
		return ServerIP;
	}
	public void setServerIP(String serverIP) {
		ServerIP = serverIP;
	}
	public String getServerType() {
		return ServerType;
	}
	public void setServerType(String serverType) {
		ServerType = serverType;
	}
	public int getCPU() {
		return CPU;
	}
	public void setCPU(int cPU) {
		CPU = cPU;
	}
	public int getMemory() {
		return Memory;
	}
	public void setMemory(int memory) {
		Memory = memory;
	}
	public String getOSVersion() {
		return OSVersion;
	}
	public void setOSVersion(String oSVersion) {
		OSVersion = oSVersion;
	}
	public String getCriticality() {
		return Criticality;
	}
	public void setCriticality(String criticality) {
		Criticality = criticality;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	
	
	

}
