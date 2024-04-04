package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class DoctorInterface  extends GridPane {
	
	private TableView<Doctor> table;
	ObservableList<Doctor> data = FXCollections.observableArrayList();
	private TableView<Doctor> table2;
	ObservableList<Doctor> data2 = FXCollections.observableArrayList();

	private TextField txFirst;
	private TextField txMid;
	private TextField txfLast;
	private TextField txfBir;

	private TextField txfGen;
	private TextField txfPhone;
	private TextField txfDiag;
	private TextField txfAge;
	private TextField textField;
	private TextField textwork;

	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;

	

	public void table_load() {
		try {
			pst = con.prepareStatement("SELECT * FROM doctorsTable");
			rs = pst.executeQuery();

			TableColumn<Doctor, Integer> idColumn = new TableColumn<>("DoctorID");
			idColumn.setCellValueFactory(new PropertyValueFactory<>("doctorID"));

			TableColumn<Doctor, String> firstNameColumn = new TableColumn<>("First Name");
			firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));

			TableColumn<Doctor, String> midNameColumn = new TableColumn<>("Middle Name");
			midNameColumn.setCellValueFactory(new PropertyValueFactory<>("midName"));

			TableColumn<Doctor, String> lastNameColumn = new TableColumn<>("Last Name");
			lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

			TableColumn<Doctor, String> specialtyColumn = new TableColumn<>("Specialty");
			specialtyColumn.setCellValueFactory(new PropertyValueFactory<>("specialty"));

			TableColumn<Doctor, String> genderColumn = new TableColumn<>("Gender");
			genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));

			TableColumn<Doctor, String> phoneNumbersColumn = new TableColumn<>("Phone Number");
			phoneNumbersColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumbers"));

			TableColumn<Doctor, String> emailColumn = new TableColumn<>("Email");
			emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

			TableColumn<Doctor, String> addressColumn = new TableColumn<>("Address");
			addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

			TableColumn<Doctor, String> workHoursColumn = new TableColumn<>("Work Hours");
			workHoursColumn.setCellValueFactory(new PropertyValueFactory<>("workHours"));

			table2.getColumns().clear(); // Clear existing columns
			table2.getColumns().addAll(idColumn, firstNameColumn, midNameColumn, lastNameColumn, specialtyColumn,
					genderColumn, phoneNumbersColumn, emailColumn, addressColumn, workHoursColumn);

			data2 = FXCollections.observableArrayList(); // Initialize the data list

			while (rs.next()) {
				int doctorID = rs.getInt("DoctorID");
				String firstName = rs.getString("FirstName");
				String midName = rs.getString("MidName");
				String lastName = rs.getString("LastName");
				String specialty = rs.getString("Specialty");
				String gender = rs.getString("Gender");
				String phoneNumbers = rs.getString("PhoneNumber");
				String email = rs.getString("Email");
				String address = rs.getString("Address");
				String workHours = rs.getString("WorkHours");

				Doctor doctor = new Doctor(doctorID, firstName, midName, lastName, specialty, gender, phoneNumbers,
						email, address, workHours);
				data2.add(doctor);
			}

			table2.setItems(data2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic", "root", "1020304050Ss");
			System.out.println("Correct connection");
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}
	}


	private int getNumberOfDoctors() throws SQLException {
		int numberOfDoctors = 0;

		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic", "root", "1020304050Ss");
				Statement statement = connection.createStatement()) {
			ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) AS NumberOfDoctors FROM DoctorsTable");
			if (resultSet.next()) {
				numberOfDoctors = resultSet.getInt("NumberOfDoctors");
			}
		}

		return numberOfDoctors;
	}

	public Stage getDoctor() {
		connect();

		table2 = new TableView<>();
		table2.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		table_load();

		GridPane formPane = new GridPane();
		formPane.setHgap(10);
		formPane.setVgap(10);
		formPane.setPadding(new Insets(10));

		Label labFirst = new Label("First Name:");
		txFirst = new TextField();
		Label labMid = new Label("Mid Name:");
		txMid = new TextField();
		Label labLast = new Label("Last Name:");
		txfLast = new TextField();
		Label labBirth = new Label(" Specialty:");
		txfBir = new TextField();
		Label labGender = new Label("Gender:");
		txfGen = new TextField();
		Label labPhone = new Label("PhoneNumber:");
		txfPhone = new TextField();
		Label labDiagnosis = new Label("Email:");
		txfDiag = new TextField();
		Label labaddress = new Label("Address:");
		txfAge = new TextField();
		Label labwork = new Label("WorkHours:");
		textwork = new TextField();

		
		
		Button btnSave = new Button("Save");
        btnSave.setOnAction(e -> {
            String firstname = txFirst.getText();
            String midname = txMid.getText();
            String lastname = txfLast.getText();
            String Specialty = txfBir.getText();
            String gender = txfGen.getText();
            String PhoneNumber = txfPhone.getText();
            String email = txfDiag.getText();
            String address = txfAge.getText();
            String workHours = textwork.getText();

            try {
                pst = con.prepareStatement(
                        "INSERT INTO DoctorsTable (FirstName, MidName, LastName, Specialty, Gender, PhoneNumber, Email, Address, WorkHours) VALUES (?,?,?,?,?,?,?,?,?)");
                pst.setString(1, firstname);
                pst.setString(2, midname);
                pst.setString(3, lastname);
                pst.setString(4, Specialty);
                pst.setString(5, gender);
                pst.setString(6, PhoneNumber);
                pst.setString(7, email);
                pst.setString(8, address);
                pst.setString(9, workHours);

                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Record Added!");

                txFirst.setText("");
                txMid.setText("");
                txfLast.setText("");
                txfBir.setText("");
                txfGen.setText("");
                txfPhone.setText("");
                txfDiag.setText("");
                txfAge.setText("");
                textwork.setText("");

                txFirst.requestFocus();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });


		Button btnUpdate = new Button("Update");
        btnUpdate.setOnAction(e -> {
            String DoctorID = textField.getText();
            String firstname = txFirst.getText();
            String midname = txMid.getText();
            String lastname = txfLast.getText();
            String Specialty = txfBir.getText();
            String gender = txfGen.getText();
            String PhoneNumber = txfPhone.getText();
            String email = txfDiag.getText();
            String address = txfAge.getText();
            String workHours = textwork.getText();

            try {
                pst = con.prepareStatement(
                        "UPDATE DoctorsTable SET FirstName=?, MidName=?, LastName=?, Specialty=?, Gender=?, PhoneNumber=?, Email=?, Address=?, WorkHours=? WHERE DoctorID=?");
                pst.setString(1, firstname);
                pst.setString(2, midname);
                pst.setString(3, lastname);
                pst.setString(4, Specialty);
                pst.setString(5, gender);
                pst.setString(6, PhoneNumber);
                pst.setString(7, email);
                pst.setString(8, address);
                pst.setString(9, workHours);
                pst.setString(10, DoctorID);

                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Record Updated!");

                txFirst.setText("");
                txMid.setText("");
                txfLast.setText("");
                txfBir.setText("");
                txfGen.setText("");
                txfPhone.setText("");
                txfDiag.setText("");
                txfAge.setText("");
                textField.setText("");
                textwork.setText("");

                txFirst.requestFocus();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
		Label labid = new Label("DoctorID");
		textField = new TextField();
		Button btnSearch = new Button("Search");

		btnSearch.setOnAction(e -> {
			String id = textField.getText();
			try {
				pst = con.prepareStatement(
						"SELECT FirstName, MidName, LastName, Specialty, Gender, PhoneNumber, email, address,workHours FROM doctorsTable WHERE doctoriD = ?");
				pst.setString(1, id);
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					String firstname = rs.getString(1);
					String midname = rs.getString(2);
					String lastname = rs.getString(3);
					String Specialty = rs.getString(4);
					String gender = rs.getString(5);
					String PhoneNumber = rs.getString(6);
					String email = rs.getString(7);
					String address = rs.getString(8);
					String workHours = rs.getString(9);

					txFirst.setText(firstname);
					txMid.setText(midname);
					txfLast.setText(lastname);
					txfBir.setText(Specialty);
					txfGen.setText(gender);
					txfPhone.setText(PhoneNumber);
					txfDiag.setText(email);
					txfAge.setText(address);
					textwork.setText(workHours);

				} else {
					txFirst.setText("");
					txMid.setText("");
					txfLast.setText("");
					txfBir.setText("");
					txfGen.setText("");
					txfPhone.setText("");
					txfDiag.setText("");
					txfAge.setText("");
					textwork.setText("");

				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		});
		
		TextField numDoctorsTextField = new TextField();
		numDoctorsTextField.setEditable(false); // Make the TextField read-only

		Button btnDoctorNum = new Button("Number of Doctors");
		btnDoctorNum.setOnAction(e -> {
		    try {
		        int numberOfDoctors = getNumberOfDoctors();
		        numDoctorsTextField.setText(Integer.toString(numberOfDoctors));
		    } catch (SQLException ex) {
		        ex.printStackTrace();
		    }
		});
		
		
		
		
		Button btnMaxWorkHours = new Button("MaxHours");
		Button btnMinWorkHours = new Button("MinHours");
		Label lblMaxWorkHours = new Label();
		Label lblMinWorkHours = new Label();

		btnMaxWorkHours.setOnAction(e -> {
		    try {
		        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic", "root", "1020304050Ss");

		        // Create and execute the query to find the max work hours
		        String sql = "SELECT MAX(WorkHours) AS MaxWorkHours FROM DoctorsTable";
		        Statement stmt = con.createStatement();
		        ResultSet rs = stmt.executeQuery(sql);

		        // Retrieve the result
		        if (rs.next()) {
		            String maxWorkHours = rs.getString("MaxWorkHours");
		            lblMaxWorkHours.setText("Max Work Hours: " + maxWorkHours);
		        }

		        // Close the resources
		        rs.close();
		        stmt.close();
		        con.close();
		    } catch (SQLException ex) {
		        ex.printStackTrace();
		    }
		});

		btnMinWorkHours.setOnAction(e -> {
		    try {
		        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic", "root", "1020304050Ss");

		        // Create and execute the query to find the min work hours
		        String sql = "SELECT MIN(WorkHours) AS MinWorkHours FROM DoctorsTable";
		        Statement stmt = con.createStatement();
		        ResultSet rs = stmt.executeQuery(sql);

		        // Retrieve the result
		        if (rs.next()) {
		            String minWorkHours = rs.getString("MinWorkHours");
		            lblMinWorkHours.setText("MinHours: " + minWorkHours);
		        }

		        // Close the resources
		        rs.close();
		        stmt.close();
		        con.close();
		    } catch (SQLException ex) {
		        ex.printStackTrace();
		    }
		});
		
		Button btnCountDoctors = new Button("CountDoctors");
		TextArea txtResult = new TextArea();

		btnCountDoctors.setOnAction(e -> {
		    try {
		        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic", "root", "1020304050Ss");

		        // Create and execute the query
		        String sql = "SELECT COUNT(*) AS NumberOfDoctors FROM DoctorsTable WHERE FirstName = 'John'";
		        Statement stmt = con.createStatement();
		        ResultSet rs = stmt.executeQuery(sql);

		        // Retrieve the result
		        if (rs.next()) {
		            int numberOfDoctors = rs.getInt("NumberOfDoctors");
		            txtResult.setText("Number of doctors named John: " + numberOfDoctors);
		        }

		        // Close the resources
		        rs.close();
		        stmt.close();
		        con.close();
		    } catch (SQLException ex) {
		        ex.printStackTrace();
		    }
		});


		Button btnDelete = new Button("Delete");
		btnDelete.setOnAction(e -> {
			String id = textField.getText();

			try {
				pst = con.prepareStatement("DELETE FROM doctorstable WHERE doctorID = ?");
				pst.setString(1, id);
				pst.executeUpdate();

				JOptionPane.showMessageDialog(null, "Record deleted!");
				table2.refresh();

				txFirst.setText("");
				txMid.setText("");
				txfLast.setText("");
				txfBir.setText("");
				txfGen.setText("");
				txfPhone.setText("");
				txfDiag.setText("");
				txfAge.setText("");
				textwork.setText("");

				txFirst.requestFocus();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});
		Button btnClear = new Button("Clear");
		btnClear.setOnAction(e -> {
			txFirst.setText("");
			txMid.setText("");
			txfLast.setText("");
			txfBir.setText("");
			txfGen.setText("");
			txfPhone.setText("");
			txfDiag.setText("");
			txfAge.setText("");
			textwork.setText("");

			txFirst.requestFocus();
			System.out.println("Done");
		});

		Button btnExit = new Button("Exit");
		btnExit.setOnAction(e -> {
			System.exit(0);
		});

		formPane.add(table2, 2, 0, 1, 9);

		formPane.add(labFirst, 0, 0);
		formPane.add(txFirst, 1, 0);
		formPane.add(labMid, 0, 1);
		formPane.add(txMid, 1, 1);
		formPane.add(labLast, 0, 2);
		formPane.add(txfLast, 1, 2);
		formPane.add(labBirth, 0, 3);
		formPane.add(txfBir, 1, 3);
		formPane.add(labGender, 0, 4);
		formPane.add(txfGen, 1, 4);
		formPane.add(labPhone, 0, 5);
		formPane.add(txfPhone, 1, 5);
		formPane.add(labDiagnosis, 0, 6);
		formPane.add(txfDiag, 1, 6);
		formPane.add(labaddress, 0, 7);
		formPane.add(txfAge, 1, 7);

		formPane.add(labwork, 0, 8);
		formPane.add(textwork, 1, 8);
		formPane.add(btnCountDoctors, 0, 9);
		formPane.add(txtResult, 1, 9);
		HBox h = new HBox(20);
		h.getChildren().addAll(btnSearch, btnSave, btnUpdate, btnDelete, btnClear, btnExit);
		formPane.add(labid, 0, 11);
		formPane.add(textField, 1, 11);
		formPane.add(btnDoctorNum, 0, 13);
		formPane.add(numDoctorsTextField, 1, 13);
		formPane.add(btnMaxWorkHours, 0, 14);
		formPane.add(lblMaxWorkHours, 1, 14);
		formPane.add(btnMinWorkHours, 0, 15);
		formPane.add(lblMinWorkHours, 1, 15);
		formPane.add(h, 2, 12);
		//rs.close();
		Label titleLabel = new Label("Doctor Management System");
		titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
		Button goBill = new Button("MAIN PAGE");
		goBill.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 12));// type of The Text
		goBill.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
		formPane.add(goBill, 13, 15);
		Stage primaryStage=new Stage();
		goBill.setOnAction(e -> {
			primaryStage.close();
		});
		ColumnConstraints columnConstraints = new ColumnConstraints();
		columnConstraints.setHalignment(HPos.CENTER);
		formPane.getColumnConstraints().add(columnConstraints);
		primaryStage.setTitle("Doctor Management System");
		Scene scene = new Scene(formPane, 1700, 700);
		primaryStage.setScene(scene);
		primaryStage.show();
		return primaryStage;
	}

	
}
