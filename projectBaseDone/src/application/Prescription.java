package application;

import java.util.Date;

public class Prescription {
	private int prescriptionId;
	private String medicationName;
	private String dosage;
	private int quantity;
	private int patientId;
	private int doctorId;
	private Date date;

	public Prescription(int prescriptionId, String medicationName, String dosage, int quantity, int patientId,
			int doctorId, Date date) {
		super();
		this.prescriptionId = prescriptionId;
		this.medicationName = medicationName;
		this.dosage = dosage;
		this.quantity = quantity;
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.date = date;
	}

	public int getPrescriptionId() {
		return prescriptionId;
	}

	public void setPrescriptionId(int prescriptionId) {
		this.prescriptionId = prescriptionId;
	}

	public String getMedicationName() {
		return medicationName;
	}

	public void setMedicationName(String medicationName) {
		this.medicationName = medicationName;
	}

	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Prescription [prescriptionId=" + prescriptionId + ", medicationName=" + medicationName + ", dosage="
				+ dosage + ", quantity=" + quantity + ", patientId=" + patientId + ", doctorId=" + doctorId + ", date="
				+ date + "]";
	}
}
