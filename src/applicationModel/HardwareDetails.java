package applicationModel;

public class HardwareDetails {

	private String hardwareid;
	private String company;
	private int totalCPU;
	private int leftCPU;
	private int totalRAM;
	private int leftRAM;
	
	/**
	 * @param hardwareid
	 * @param company
	 * @param totalCPU
	 * @param leftCPU
	 * @param totalRAM
	 * @param leftRAM
	 */
	public HardwareDetails(String hardwareid, String company, int totalCPU, int leftCPU, int totalRAM, int leftRAM) {
		super();
		this.hardwareid = hardwareid;
		this.company = company;
		this.totalCPU = totalCPU;
		this.leftCPU = leftCPU;
		this.totalRAM = totalRAM;
		this.leftRAM = leftRAM;
	}
	/**
	 * @return the hardwareid
	 */
	public String getHardwareid() {
		return hardwareid;
	}
	/**
	 * @param hardwareid the hardwareid to set
	 */
	public void setHardwareid(String hardwareid) {
		this.hardwareid = hardwareid;
	}
	/**
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}
	/**
	 * @param company the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}
	/**
	 * @return the totalCPU
	 */
	public int getTotalCPU() {
		return totalCPU;
	}
	/**
	 * @param totalCPU the totalCPU to set
	 */
	public void setTotalCPU(int totalCPU) {
		this.totalCPU = totalCPU;
	}
	/**
	 * @return the leftCPU
	 */
	public int getLeftCPU() {
		return leftCPU;
	}
	/**
	 * @param leftCPU the leftCPU to set
	 */
	public void setLeftCPU(int leftCPU) {
		this.leftCPU = leftCPU;
	}
	/**
	 * @return the totalRAM
	 */
	public int getTotalRAM() {
		return totalRAM;
	}
	/**
	 * @param totalRAM the totalRAM to set
	 */
	public void setTotalRAM(int totalRAM) {
		this.totalRAM = totalRAM;
	}
	/**
	 * @return the leftRAM
	 */
	public int getLeftRAM() {
		return leftRAM;
	}
	/**
	 * @param leftRAM the leftRAM to set
	 */
	public void setLeftRAM(int leftRAM) {
		this.leftRAM = leftRAM;
	}
	
}
