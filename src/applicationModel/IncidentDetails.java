package applicationModel;

public class IncidentDetails {
	
	private int IncidentID;
	private int Severity;
	private String ImpactedCI;
	private int ResolvedDays;
	private String Team;
	private String RootCause;
	
	/**
	 * @param incidentID
	 * @param severity
	 * @param impactedCI
	 * @param resolvedDays
	 * @param team
	 * @param rootCause
	 */
	public IncidentDetails(int incidentID, int severity, String impactedCI, int resolvedDays, String team,
			String rootCause) {
		super();
		IncidentID = incidentID;
		Severity = severity;
		ImpactedCI = impactedCI;
		ResolvedDays = resolvedDays;
		Team = team;
		RootCause = rootCause;
	}
	/**
	 * @return the incidentID
	 */
	public int getIncidentID() {
		return IncidentID;
	}
	/**
	 * @param incidentID the incidentID to set
	 */
	public void setIncidentID(int incidentID) {
		IncidentID = incidentID;
	}
	/**
	 * @return the severity
	 */
	public int getSeverity() {
		return Severity;
	}
	/**
	 * @param severity the severity to set
	 */
	public void setSeverity(int severity) {
		Severity = severity;
	}
	/**
	 * @return the impactedCI
	 */
	public String getImpactedCI() {
		return ImpactedCI;
	}
	/**
	 * @param impactedCI the impactedCI to set
	 */
	public void setImpactedCI(String impactedCI) {
		ImpactedCI = impactedCI;
	}
	/**
	 * @return the resolvedDays
	 */
	public int getResolvedDays() {
		return ResolvedDays;
	}
	/**
	 * @param resolvedDays the resolvedDays to set
	 */
	public void setResolvedDays(int resolvedDays) {
		ResolvedDays = resolvedDays;
	}
	/**
	 * @return the team
	 */
	public String getTeam() {
		return Team;
	}
	/**
	 * @param team the team to set
	 */
	public void setTeam(String team) {
		Team = team;
	}
	/**
	 * @return the rootCause
	 */
	public String getRootCause() {
		return RootCause;
	}
	/**
	 * @param rootCause the rootCause to set
	 */
	public void setRootCause(String rootCause) {
		RootCause = rootCause;
	}
	
	
}