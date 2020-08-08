package com.school.person.model;

public class UtilizationModel 
{
	private String personId;
	private int personMonth;
	private int personYear;
	private int waterUtilized;
	private int electricityUtilized;
	private String createDateTime;
	private String lastModifiedDateTime;
	private String status;
	private String message;
	
	
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public int getPersonMonth() {
		return personMonth;
	}
	public void setPersonMonth(int personMonth) {
		this.personMonth = personMonth;
	}
	public int getPersonYear() {
		return personYear;
	}
	public void setPersonYear(int personYear) {
		this.personYear = personYear;
	}
	public int getWaterUtilized() {
		return waterUtilized;
	}
	public void setWaterUtilized(int waterUtilized) {
		this.waterUtilized = waterUtilized;
	}
	public int getElectricityUtilized() {
		return electricityUtilized;
	}
	public void setElectricityUtilized(int electricityUtilized) {
		this.electricityUtilized = electricityUtilized;
	}
	public String getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}
	public String getLastModifiedDateTime() {
		return lastModifiedDateTime;
	}
	public void setLastModifiedDateTime(String lastModifiedDateTime) {
		this.lastModifiedDateTime = lastModifiedDateTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	

}
