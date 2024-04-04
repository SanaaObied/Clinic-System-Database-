package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Patient {
	 private int PatientID;
	    private String firstName;
	    private String midName;
	    private String lastName;
	    private Date birthDate;
	    private String gender;
	    private String phoneNumber;
	   
	    private String diagnosis;
	    private String ageGroup;


	    public Patient(int PatientID, String firstName, String midName, String lastName, Date birthDate, String gender, String phoneNumber,  String diagnosis, String ageGroup) throws SQLException {
	    	

	        this.PatientID = PatientID;
	        this.firstName = firstName;
	        this.midName = midName;
	        this.lastName = lastName;
	        this.birthDate = birthDate;
	        this.gender = gender;
	        this.phoneNumber = phoneNumber;
	       
	        this.diagnosis = diagnosis;
	        this.ageGroup = ageGroup;
	    }




	  

	    public int getPatientID() {
			return PatientID;
		}






		public void setPatientID(int patientID) {
			PatientID = patientID;
		}






		public String getFirstName() {
	        return firstName;
	    }

	    public void setFirstName(String firstName) {
	        this.firstName = firstName;
	    }

	    public String getMidName() {
	        return midName;
	    }

	    public void setMidName(String midName) {
	        this.midName = midName;
	    }

	    public String getLastName() {
	        return lastName;
	    }

	    public void setLastName(String lastName) {
	        this.lastName = lastName;
	    }

	    public Date getBirthDate() {
	        return birthDate;
	    }

	    public void setBirthDate(Date birthDate) {
	        this.birthDate = birthDate;
	    }

	    public String getGender() {
	        return gender;
	    }

	    public void setGender(String gender) {
	        this.gender = gender;
	    }

	    public String getPhoneNumber() {
	        return phoneNumber;
	    }

	    public void setPhoneNumber(String phoneNumber) {
	        this.phoneNumber = phoneNumber;
	    }

	  
	    public String getDiagnosis() {
	        return diagnosis;
	    }

	    public void setDiagnosis(String diagnosis) {
	        this.diagnosis = diagnosis;
	    }

	    public String getAgeGroup() {
	        return ageGroup;
	    }

	    public void setAgeGroup(String ageGroup) {
	        this.ageGroup = ageGroup;
	    }
}