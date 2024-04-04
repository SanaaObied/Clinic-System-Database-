package application;

public class Bill {
	int  BillID;
	int PatientID;
	double   LabCharges;
	double  MedicineCharges;
	java.util.Date  Date;
	String  PaymentMethod ;
	
	public Bill(int billID, int patientID, double labCharges, double medicineCharges, java.util.Date date,
			String paymentMethod) {
		super();
		BillID = billID;
		PatientID = patientID;
		LabCharges = labCharges;
		MedicineCharges = medicineCharges;
		Date = date;
		PaymentMethod = paymentMethod;
	}
	public int getBillID() {
		return BillID;
	}
	public void setBillID(int billID) {
		BillID = billID;
	}
	public int getPatientID() {
		return PatientID;
	}
	public void setPatientID(int patientID) {
		PatientID = patientID;
	}
	public double getLabCharges() {
		return LabCharges;
	}
	public void setLabCharges(double labCharges) {
		LabCharges = labCharges;
	}
	public double getMedicineCharges() {
		return MedicineCharges;
	}
	public void setMedicineCharges(double medicineCharges) {
		MedicineCharges = medicineCharges;
	}
	public java.util.Date getDate() {
		return Date;
	}
	public void setDate(java.util.Date date) {
		Date = date;
	}
	public String getPaymentMethod() {
		return PaymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		PaymentMethod = paymentMethod;
	}
	
	
}
