drop database if exists clinicSystem;
create database clinicSystem ;
		use clinicSystem;
        
	-- drop table if exists PatientsTable;
CREATE TABLE PatientsTable (
  PatientID INT PRIMARY KEY,
  FirstName VARCHAR(50),
  MidName VARCHAR(50),
  LastName VARCHAR(50),
  BirthDate DATE,
  Gender VARCHAR(10),
  PhoneNumber VARCHAR(20),
  Diagnosis VARCHAR(100),
  AgeGroup VARCHAR(20)
);
delete from PatientsTable where PatientID=19911;
Select * from PatientsTable;
delete from PatientsTable where PatientID=19912;
Select * from PatientsTable;
delete from PatientsTable where PatientID=19913;
Select * from PatientsTable;
delete from PatientsTable where PatientID=19914;
Select * from PatientsTable;
delete from PatientsTable where PatientID=19915;
Select * from PatientsTable;
delete from PatientsTable where PatientID=19916;
Select * from PatientsTable;
delete from PatientsTable where PatientID=19917;
Select * from PatientsTable;
delete from PatientsTable where PatientID=19918;
Select * from PatientsTable;
delete from PatientsTable where PatientID=19919;
Select * from PatientsTable;
delete from PatientsTable where PatientID=19920;
INSERT INTO PatientsTable (PatientID, FirstName, MidName, LastName, BirthDate, Gender, PhoneNumber,  Diagnosis, AgeGroup)
VALUES (1, 'Mahammad', 'A', 'Ahmad', '1990-05-10', 'Male', '0568236589', 'Flu', 'Adult');

INSERT INTO PatientsTable (PatientID, FirstName, MidName, LastName, BirthDate, Gender, PhoneNumber, Diagnosis, AgeGroup)
VALUES (2, 'Tala', 'B', 'Salah', '2012-12-15', 'Female', '0598521478',  'Bronchitis', 'Young');

INSERT INTO PatientsTable (PatientID, FirstName, MidName, LastName, BirthDate, Gender, PhoneNumber,  Diagnosis, AgeGroup)
VALUES (3, 'Emad', 'Gh', 'Kareem', '1994-05-16', 'Male', '09-2587412',  'Diabetes', 'Adult');

INSERT INTO PatientsTable (PatientID, FirstName, MidName, LastName, BirthDate, Gender, PhoneNumber, Email, Diagnosis, AgeGroup)
VALUES (19913, 'Alaa', 'E', 'Ali', '2003-07-08', 'Male', '0526321587', 'alaa.ali@gmail.com', 'Diabetes', 'Adult');

INSERT INTO PatientsTable (PatientID, FirstName, MidName, LastName, BirthDate, Gender, PhoneNumber, Email, Diagnosis, AgeGroup)
VALUES (19914, 'Bana', 'J', 'Ali', '2015-01-19', 'Female', '0502369874', 'bana.ali@gmail.com', 'Flu', 'Young');

INSERT INTO PatientsTable (PatientID, FirstName, MidName, LastName, BirthDate, Gender, PhoneNumber, Email, Diagnosis, AgeGroup)
VALUES (19915, 'Israa', 'J', 'Ali', '2002-09-14', 'Female', '0599785841', 'israa.ali@gmail.com', 'Appendix', 'Adult');


INSERT INTO PatientsTable (PatientID, FirstName, MidName, LastName, BirthDate, Gender, PhoneNumber, Email, Diagnosis, AgeGroup)
VALUES (19916, 'Oday', 'M', 'Omar', '2007-05-20', 'Male', '0569321456', 'oday.omar@gmail.com', 'Flu', 'Young');

INSERT INTO PatientsTable (PatientID, FirstName, MidName, LastName, BirthDate, Gender, PhoneNumber, Email, Diagnosis, AgeGroup)
VALUES (19917, 'Omar', 'M', 'Ali', '2015-09-09', 'Male', '0596321547', 'omar.ali@example.com', 'Hypotension', 'Young');

Select * from PatientsTable;
select FirstName,MidName,LastName,BirthDate,Gender,PhoneNumber,Diagnosis,AgeGroup from PatientsTable where PatientID =19915 ;
update PatientsTable set FirstName='Sanaa', MidName='lolo', LastName='Obied', BirthDate='2015-09-09', Gender='Male', PhoneNumber='05933', Diagnosis='flue', AgeGroup='adult' where PatientID=19916;
delete from PatientsTable  where PatientID =19917;
Select * from PatientsTable;
-- drop table if exists DoctorsTable;
CREATE TABLE DoctorsTable (
  DoctorID INT PRIMARY KEY,
  FirstName VARCHAR(50),
  MidName VARCHAR(50),
  LastName VARCHAR(50),
  Specialty VARCHAR(50),
  Gender VARCHAR(10),
  PhoneNumber VARCHAR(20),
  Email VARCHAR(100),
  Address VARCHAR(100),
  WorkHours VARCHAR(100)
);

INSERT INTO DoctorsTable (DoctorID, FirstName, MidName, LastName, Specialty, Gender, PhoneNumber, Email, Address, WorkHours)
VALUES (1, 'John', 'K', 'Doe', 'Internal Medicine Physician', 'Male', '0599874521', 'john.doe@example.com', '123 Main St', '9 AM - 5 PM');

INSERT INTO DoctorsTable (DoctorID, FirstName, MidName, LastName, Specialty, Gender, PhoneNumber, Email, Address, WorkHours)
VALUES (2, 'Jane', 'B', 'Smith', 'Ear, noes and throat', 'Female', '0523698741', 'jane.smith@example.com', '456 Elm St', '10 AM - 6 PM');

INSERT INTO DoctorsTable (DoctorID, FirstName, MidName, LastName, Specialty, Gender, PhoneNumber, Email, Address, WorkHours)
VALUES (3, 'Michael', 'M', 'Johnson', 'Cardiologist', 'Male', '0523654789', 'michael.johnson@example.com', '789 Oak St', '12 PM - 7 PM');


Select * from DoctorsTable;
CREATE TABLE Appointment (
  AppointmentID INT ,
  DoctorID2 INT,
  Notes VARCHAR(255),
  PatientID2 INT,
  AppointmentDate DATE,
  AppointmentTime TIME,
   PRIMARY KEY(AppointmentID),
  FOREIGN KEY (DoctorID2) REFERENCES DoctorsTable(DoctorID),
  FOREIGN KEY (PatientID2) REFERENCES PatientsTable(PatientID)
 
);

INSERT INTO Appointment (AppointmentID, DoctorID, Notes, PatientID, AppointmentDate, AppointmentTime)
VALUES
  (1, 1, 'Follow-up checkup', 3, '2022-03-12', '10:30:00'),
  (2, 2, 'New patient consultation', 2, '2022-06-11', '14:00:00'),
  (3, 3, 'Lab test review', 1, '2022-08-19', '15:30:00');

SELECT * FROM Appointment;

CREATE TABLE Bill (
  BillID INT ,
  PatientID INT,
  LabCharges DECIMAL(10, 2),
  MedicineCharges DECIMAL(10, 2),
  Date DATE,
  PaymentMethod VARCHAR(20),
  PRIMARY KEY(BillID),
  FOREIGN KEY (PatientID) REFERENCES PatientsTable(PatientID)
);
INSERT INTO Bill (BillID, PatientID, LabCharges, MedicineCharges, Date, PaymentMethod)
VALUES
  (1, 1, 100.00, 50.00, '2023-07-01', 'Credit Card'),
  (2, 2, 80.00, 40.00, '2023-07-02', 'Cash'),
  (3, 3, 120.00, 60.00, '2023-07-03', 'Insurance');

-- Update an existing bill
UPDATE Bill SET LabCharges = 120.00, MedicineCharges = 60.00
WHERE BillID = 1;
alter table Bill modify BillID int auto_increment;
INSERT INTO Bill ( PatientID, LabCharges, MedicineCharges, Date, PaymentMethod)
VALUES
  ( 1, 100.00, 50.00, '2023-07-01', 'Credit Card');
 
-- Delete a bill
DELETE FROM Bill WHERE BillID = 1;
SELECT * FROM Bill;
INSERT INTO Bill ( PatientID, LabCharges, MedicineCharges, Date, PaymentMethod)
VALUES (1, 100.00, 50.00, '2023-07-01', 'Credit Card');

CREATE TABLE Test (
    TestCode INT PRIMARY KEY,
    PatientID INT,
    DoctorID INT,
    TestType VARCHAR(50),
    TestResult VARCHAR(50),
    TestDate DATE,
    Notes VARCHAR(100),
FOREIGN KEY (DoctorID) REFERENCES DoctorsTable(DoctorID),
  FOREIGN KEY (PatientID) REFERENCES PatientsTable(PatientID)
);
alter table Test modify TestCode int auto_increment;
SELECT * FROM Test;
INSERT INTO Test (TestCode, PatientID, DoctorID, TestType, TestResult, TestDate, Notes)
VALUES
    (1, 1, 3, 'Blood Test', 'Normal', '2023-07-03', 'No abnormalities detected'),
    (2, 2, 2, 'Urinalysis', 'Abnormal', '2023-07-04', 'High protein levels detected'),
    (3, 3, 1, 'X-ray', 'Normal', '2023-07-05', 'No fractures found');
   

-- Create the DoctorsTable


-- Insert sample data into the DoctorsTable
INSERT INTO DoctorsTable ( FirstName, MidName, LastName, Specialty, Gender, PhoneNumber, Email, Address, WorkHours)
VALUES ( 'John', 'K', 'Doe', 'Internal Medicine Physician', 'Male', '0599874521', 'john.doe@example.com', '123 Main St', '9 AM - 5 PM');

INSERT INTO DoctorsTable ( FirstName, MidName, LastName, Specialty, Gender, PhoneNumber, Email, Address, WorkHours)
VALUES ('Jane', 'B', 'Smith', 'Ear, Nose, and Throat', 'Female', '0523698741', 'jane.smith@example.com', '456 Elm St', '10 AM - 6 PM');

-- Create the PRESCRIPTION table
CREATE TABLE PRESCRIPTION (
    PrescriptionID INT PRIMARY KEY,
    MedicationName VARCHAR(50),
    Dosage VARCHAR(50),
    Quantity INT,
    PatientID INT,
    DoctorID INT,
    Date DATE,
    FOREIGN KEY (PatientID) REFERENCES PatientsTable (PatientID),
    FOREIGN KEY (DoctorID) REFERENCES DoctorsTable (DoctorID) ON DELETE CASCADE
);

-- Insert sample data into the PRESCRIPTION table
INSERT INTO PRESCRIPTION (PrescriptionID, MedicationName, Dosage, Quantity, PatientID, DoctorID, Date)
VALUES
    (100, 'Alendronate', '20mg', 80, 1, 1, '2022-08-20'),
    (200, 'Farxiga', '23mg', 65, 2, 1, '2022-03-22');

-- Create the Appointment table
CREATE TABLE Appointment (
  AppointmentID INT ,
  DoctorID2 INT,
  Notes VARCHAR(255),
  PatientID2 INT,
  AppointmentDate DATE,
  AppointmentTime time,
  PRIMARY KEY(AppointmentID),
  FOREIGN KEY (DoctorID2) REFERENCES DoctorsTable(DoctorID),
  FOREIGN KEY (PatientID2) REFERENCES PatientsTable(PatientID)
);
-- Create the Diagnosis table
CREATE TABLE Diagnosis2 (
  DiagnosisID INT ,
  DiagnosisName VARCHAR(50),
  Description VARCHAR(255),
  PatientID INT,
  DoctorID INT,
  Date DATE,
    PRIMARY KEY(DiagnosisID),
  FOREIGN KEY (PatientID) REFERENCES PatientsTable(PatientID),
  FOREIGN KEY (DoctorID) REFERENCES DoctorsTable(DoctorID)
);
Select* from Diagnosis2;
-- Insert sample data into the Diagnosis table
INSERT INTO Diagnosis2 (DiagnosisID, DiagnosisName, Description, PatientID, DoctorID, Date)
VALUES 
  (1, 'Diagnosis 1', 'Description 1', 1, 1, '2023-07-05'),
  (2, 'Diagnosis 2', 'Description 2', 2, 2, '2023-07-06'),
  (3, 'Diagnosis 3', 'Description 3', 1, 2, '2023-07-07');

-- Create the MedicalHistory table
CREATE TABLE MedicalHistory (
  MedicalHistoryID INT PRIMARY KEY,
  PatientID INT,
  DiagnosisID INT,
  PrescriptionID INT,
  Date DATE,
  FOREIGN KEY (PatientID) REFERENCES PatientsTable(PatientID),
  FOREIGN KEY (DiagnosisID) REFERENCES Diagnosis(DiagnosisID),
  FOREIGN KEY (PrescriptionID) REFERENCES PRESCRIPTION(PrescriptionID)
);



-- Create the DoctorRoom table
CREATE TABLE DoctorRoom (
  RoomNumber INT PRIMARY KEY,
  DoctorID INT,
  NumberOfBeds INT,
  FOREIGN KEY (DoctorID) REFERENCES DoctorsTable(DoctorID)
);

-- Insert data into the DoctorRoom table
INSERT INTO DoctorRoom (RoomNumber, DoctorID, NumberOfBeds)
VALUES (1, 2, 3),
       (2,1, 2),
       (3, 2, 4);
 



ALTER TABLE PRESCRIPTION
  ADD FOREIGN KEY (PatientID) REFERENCES PatientsTable(PatientID) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD FOREIGN KEY (DoctorID) REFERENCES DoctorsTable(DoctorID) ON DELETE CASCADE ON UPDATE CASCADE;

-- Modify the Diagnosis table
ALTER TABLE Diagnosis
  DROP FOREIGN KEY fk_diagnosis_patient,
  DROP FOREIGN KEY fk_diagnosis_doctor;

ALTER TABLE Diagnosis
  ADD FOREIGN KEY (PatientID) REFERENCES PatientsTable(PatientID) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD FOREIGN KEY (DoctorID) REFERENCES DoctorsTable(DoctorID) ON DELETE CASCADE ON UPDATE CASCADE;

-- Modify the MedicalHistory table
ALTER TABLE MedicalHistory
  DROP FOREIGN KEY fk_medicalhistory_patient,
  DROP FOREIGN KEY fk_medicalhistory_diagnosis,
  DROP FOREIGN KEY fk_medicalhistory_prescription;
SELECT COUNT(*) AS NumberOfDoctors
FROM DoctorsTable
WHERE FirstName = 'John';
SELECT COUNT(*) AS NumberOfDoctors
FROM DoctorsTable;
SELECT MIN(WorkHours) AS MinWorkHours, MAX(WorkHours) AS MaxWorkHours
FROM DoctorsTable;

ALTER TABLE Diagnosis
  DROP FOREIGN KEY fk_diagnosis_patient,
  DROP FOREIGN KEY fk_diagnosis_doctor;
SELECT * FROM Diagnosis;
ALTER TABLE Diagnosis
  ADD FOREIGN KEY (PatientID) REFERENCES PatientsTable(PatientID) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD FOREIGN KEY (DoctorID) REFERENCES DoctorsTable(DoctorID) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE DoctorsTable MODIFY COLUMN DoctorID INT DEFAULT 0;

SELECT * FROM DoctorsTable;
SELECT * FROM PatientsTable;
SELECT * FROM Appointment;
SELECT * FROM Diagnosis;
SELECT * FROM Prescription;
SELECT * FROM MedicalHistory;
SELECT * FROM Bill;
SELECT * FROM Test;
SELECT * FROM DoctorRoom;