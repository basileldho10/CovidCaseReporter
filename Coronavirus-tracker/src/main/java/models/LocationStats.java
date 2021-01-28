package models;

public class LocationStats {
	
	private String state;
	private String country;
	private int reportedCases;
	
	
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getReportedCases() {
		return reportedCases;
	}
	public void setReportedCases(int reportedCases) {
		this.reportedCases = reportedCases;
	}
	@Override
	public String toString() {
		return "LocationStats [state=" + state + ", country=" + country + ", Latest reportedCases=" + reportedCases + "]";
	}
	
	

}
