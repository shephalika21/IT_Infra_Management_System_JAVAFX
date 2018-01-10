package applicationModel;

public class StorageDetails {
	
	private String StorageID;
	private String StorageType;
	private String Company;
	private int TotalStorage;
	private int UsedStorage;
	private int LeftStorage;
	/**
	 * @param storageID
	 * @param storageType
	 * @param company
	 * @param totalStorage
	 * @param usedStorage
	 * @param leftStorage
	 */
	public StorageDetails(String storageID, String storageType, String company, int totalStorage, int usedStorage,
			int leftStorage) {
		super();
		StorageID = storageID;
		StorageType = storageType;
		Company = company;
		TotalStorage = totalStorage;
		UsedStorage = usedStorage;
		LeftStorage = leftStorage;
	}
	/**
	 * @return the storageID
	 */
	public String getStorageID() {
		return StorageID;
	}
	/**
	 * @param storageID the storageID to set
	 */
	public void setStorageID(String storageID) {
		StorageID = storageID;
	}
	/**
	 * @return the storageType
	 */
	public String getStorageType() {
		return StorageType;
	}
	/**
	 * @param storageType the storageType to set
	 */
	public void setStorageType(String storageType) {
		StorageType = storageType;
	}
	/**
	 * @return the company
	 */
	public String getCompany() {
		return Company;
	}
	/**
	 * @param company the company to set
	 */
	public void setCompany(String company) {
		Company = company;
	}
	/**
	 * @return the totalStorage
	 */
	public int getTotalStorage() {
		return TotalStorage;
	}
	/**
	 * @param totalStorage the totalStorage to set
	 */
	public void setTotalStorage(int totalStorage) {
		TotalStorage = totalStorage;
	}
	/**
	 * @return the usedStorage
	 */
	public int getUsedStorage() {
		return UsedStorage;
	}
	/**
	 * @param usedStorage the usedStorage to set
	 */
	public void setUsedStorage(int usedStorage) {
		UsedStorage = usedStorage;
	}
	/**
	 * @return the leftStorage
	 */
	public int getLeftStorage() {
		return LeftStorage;
	}
	/**
	 * @param leftStorage the leftStorage to set
	 */
	public void setLeftStorage(int leftStorage) {
		LeftStorage = leftStorage;
	}
}