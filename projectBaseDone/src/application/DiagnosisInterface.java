package application;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.scene.control.TextField;

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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class DiagnosisInterface extends GridPane {
	private TableView<Diagnosis> table;
	ObservableList<Diagnosis> data = FXCollections.observableArrayList();
	private TableView<Diagnosis> table2;
	ObservableList<Diagnosis> data2 = FXCollections.observableArrayList();
	private TextField txfDiagnosisId;
	private TextField txfDiagnosisName;
	private TextField txfDescription;
	private TextField txfPatientId;
	private TextField txfDoctorId;
	private TextField txfDate;
	private TextField textField;
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;

	public void table_load() {
		try {
			pst = con.prepareStatement("SELECT * FROM Diagnosis2");
			rs = pst.executeQuery();

			TableColumn<Diagnosis, Integer> diagnosisIdColumn = new TableColumn<>("Diagnosis ID");
			diagnosisIdColumn.setCellValueFactory(new PropertyValueFactory<>("diagnosisId"));

			TableColumn<Diagnosis, String> diagnosisNameColumn = new TableColumn<>("Diagnosis Name");
			diagnosisNameColumn.setCellValueFactory(new PropertyValueFactory<>("diagnosisName"));

			TableColumn<Diagnosis, String> descriptionColumn = new TableColumn<>("Description");
			descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

			TableColumn<Diagnosis, Integer> patientIdColumn = new TableColumn<>("Patient ID");
			patientIdColumn.setCellValueFactory(new PropertyValueFactory<>("patientId"));

			TableColumn<Diagnosis, Integer> doctorIdColumn = new TableColumn<>("Doctor ID");
			doctorIdColumn.setCellValueFactory(new PropertyValueFactory<>("doctorId"));

			TableColumn<Diagnosis, Date> dateColumn = new TableColumn<>("Date");
			dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

			table2.getColumns().clear(); // Clear existing columns
			table2.getColumns().addAll(diagnosisIdColumn, diagnosisNameColumn, descriptionColumn, patientIdColumn,
					doctorIdColumn, dateColumn);

			data2 = FXCollections.observableArrayList(); // Initialize the data list

			while (rs.next()) {
				int diagnosisId = rs.getInt("DiagnosisID");
				String diagnosisName = rs.getString("DiagnosisName");
				String description = rs.getString("Description");
				int patientId = rs.getInt("PatientID");
				int doctorId = rs.getInt("DoctorID");
				Date date = rs.getDate("Date");

				Diagnosis diagnosis = new Diagnosis(diagnosisId, diagnosisName, description, patientId, doctorId, date);
				data2.add(diagnosis);
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
	
	public String getMinDate() {
        String minDate = null;
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT MIN(Date) AS MinDate FROM Diagnosis2");

            if (rs.next()) {
                minDate = rs.getString("MinDate");
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return minDate;
    }

    public String getMaxDate() {
        String maxDate = null;
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT MAX(Date) AS MaxDate FROM Diagnosis2");

            if (rs.next()) {
                maxDate = rs.getString("MaxDate");
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxDate;
    }

	public Stage getDiag() {
		connect();

		// Get the controller instance
		table2 = new TableView<>();
		table2.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		// Initialize the table
		table_load();

		// Create a form for registration
		GridPane formPane = new GridPane();
		formPane.setHgap(10);
		formPane.setVgap(10);
		formPane.setPadding(new Insets(10));

		Label labDiagnosisName = new Label("Diagnosis Name:");
		txfDiagnosisName = new TextField();
		Label labDescription = new Label("Description:");
		txfDescription = new TextField();
		Label labPatientID = new Label("Patient ID:");
		txfPatientId = new TextField();
		Label labDoctorID = new Label("Doctor ID:");
		txfDoctorId = new TextField();
		Label labDate = new Label("Date:");
		txfDate = new TextField();
		Button btnSave = new Button("Save");
		btnSave.setOnAction(e -> {
			String diagnosisName, description, patientId, doctorId, date;
			diagnosisName = txfDiagnosisName.getText();
			description = txfDescription.getText();
			patientId = txfPatientId.getText();
			doctorId = txfDoctorId.getText();
			date = txfDate.getText();

			try {
				pst = con.prepareStatement(
						"INSERT INTO Diagnosis2 (DiagnosisName, Description, PatientID, DoctorID, Date) VALUES (?,?,?,?,?)");
				pst.setString(1, diagnosisName);
				pst.setString(2, description);
				pst.setString(3, patientId);
				pst.setString(4, doctorId);
				pst.setString(5, date);

				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Diagnosis added successfully!");

				table2.refresh();
				txfDiagnosisName.setText("");
				txfDescription.setText("");
				txfPatientId.setText("");
				txfDoctorId.setText("");
				txfDate.setText("");
				txfDiagnosisName.requestFocus();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});

		Label labDiagnosisID = new Label("Diagnosis ID:");
		textField = new TextField();
		Button btnSearch = new Button("Search");

		btnSearch.setOnAction(e -> {
			String id = textField.getText();
			try {
				pst = con.prepareStatement(
						"SELECT DiagnosisName, Description, PatientID, DoctorID, Date FROM Diagnosis2 WHERE DiagnosisID = ?");
				pst.setString(1, id);
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					String diagnosisName = rs.getString(1);
					String description = rs.getString(2);
					int patientId = rs.getInt(3);
					int doctorId = rs.getInt(4);
					Date date = rs.getDate(5);

					txfDiagnosisName.setText(diagnosisName);
					txfDescription.setText(description);
					txfPatientId.setText(String.valueOf(patientId));
					txfDoctorId.setText(String.valueOf(doctorId));
					txfDate.setText(date.toString());
				} else {
					txfDiagnosisName.setText("");
					txfDescription.setText("");
					txfPatientId.setText("");
					txfDoctorId.setText("");
					txfDate.setText("");
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		});
		
		textField = new TextField();

		
		Label minDateLabel = new Label("Minimum Date: ");
        Label maxDateLabel = new Label("Maximum Date: ");

        Button minDateButton = new Button("Get Minimum Date");
        minDateButton.setOnAction(e -> {
            String minDate = getMinDate();
            minDateLabel.setText("Minimum Date: " + minDate);
        });

        Button maxDateButton = new Button("Get Maximum Date");
        maxDateButton.setOnAction(e -> {
            String maxDate = getMaxDate();
            maxDateLabel.setText("Maximum Date: " + maxDate);
        });
        
        
        
		Button btnDelete = new Button("Delete");
		btnDelete.setOnAction(e -> {
			String id = textField.getText();

			try {
				pst = con.prepareStatement("DELETE FROM Diagnosis2 WHERE DiagnosisID = ?");
				pst.setString(1, id);
				pst.executeUpdate();

				JOptionPane.showMessageDialog(null, "Diagnosis Deleted!");
				table2.refresh();

				txfDiagnosisName.setText("");
				txfDescription.setText("");
				txfPatientId.setText("");
				txfDoctorId.setText("");
				txfDate.setText("");
				textField.setText("");
				txfDiagnosisName.requestFocus();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});

		Button btnClear = new Button("Clear");
		btnClear.setOnAction(e -> {
			txfDiagnosisName.setText("");
			txfDescription.setText("");
			txfPatientId.setText("");
			txfDoctorId.setText("");
			txfDate.setText("");
			textField.setText("");
			txfDiagnosisName.requestFocus();
			System.out.println("Done");
		});

		Button btnExit = new Button("Exit");
		btnExit.setOnAction(e -> {
			System.exit(0);
		});
		Button btnUpdate = new Button("Update");

		btnUpdate.setOnAction(e -> {
		    String id = textField.getText();
		    String diagnosisName = txfDiagnosisName.getText();
		    String description = txfDescription.getText();
		    int patientId, doctorId;
		    Date date = null;

		    // Check if PatientID field is empty
		    if (txfPatientId.getText().isEmpty()) {
		        JOptionPane.showMessageDialog(null, "Please enter a patient ID value.");
		        return;
		    } else {
		        patientId = Integer.parseInt(txfPatientId.getText());
		    }

		    // Check if DoctorID field is empty
		    if (txfDoctorId.getText().isEmpty()) {
		        JOptionPane.showMessageDialog(null, "Please enter a doctor ID value.");
		        return;
		    } else {
		        doctorId = Integer.parseInt(txfDoctorId.getText());
		    }

		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		    try {
		        date = dateFormat.parse(txfDate.getText());
		    } catch (ParseException ex) {
		        ex.printStackTrace();
		        JOptionPane.showMessageDialog(null, "Invalid date format. Please enter the date in yyyy-MM-dd format.");
		        return;
		    }

		    try {
		        PreparedStatement pst = con.prepareStatement(
		                "UPDATE Diagnosis2 SET DiagnosisName=?, Description=?, PatientID=?, DoctorID=?, Date=? WHERE DiagnosisID=?");
		        pst.setString(1, diagnosisName);
		        pst.setString(2, description);
		        pst.setInt(3, patientId);
		        pst.setInt(4, doctorId);
		        pst.setDate(5, new java.sql.Date(date.getTime()));
		        pst.setString(6, id);

		        pst.executeUpdate();
		        JOptionPane.showMessageDialog(null, "Diagnosis Updated!");
		        table2.refresh();

		        txfDiagnosisName.setText("");
		        txfDescription.setText("");
		        txfPatientId.setText("");
		        txfDoctorId.setText("");
		        txfDate.setText("");
		        textField.setText("");
		        txfDiagnosisName.requestFocus();
		    } catch (SQLException e1) {
		        e1.printStackTrace();
		        JOptionPane.showMessageDialog(null, "An error occurred while updating the diagnosis.");
		    }
		});
		formPane.add(table2, 2, 0, 1, 9);

		formPane.add(labDiagnosisName, 0, 0);
		formPane.add(txfDiagnosisName, 1, 0);
		formPane.add(labDescription, 0, 1);
		formPane.add(txfDescription, 1, 1);
		formPane.add(labPatientID, 0, 2);
		formPane.add(txfPatientId, 1, 2);
		formPane.add(labDoctorID, 0, 3);
		formPane.add(txfDoctorId, 1, 3);
		formPane.add(labDate, 0, 4);
		formPane.add(txfDate, 1, 4);
		formPane.add(minDateButton, 0, 6);
		formPane.add(minDateLabel, 1, 6);
		formPane.add(maxDateButton, 0, 7);
		formPane.add(maxDateLabel, 1, 7);
		HBox h = new HBox(10);
		h.getChildren().addAll(btnSearch, btnSave, btnUpdate, btnDelete, btnClear, btnExit);
		formPane.add(labDiagnosisID, 0, 8);
		formPane.add(textField, 1, 8);
		formPane.add(h, 2, 9);
		HBox hDiagnosis = new HBox(10);
		hDiagnosis.getChildren().addAll(btnSearch, btnSave, btnUpdate, btnDelete, btnClear, btnExit);

		formPane.add(hDiagnosis, 2, 15);

		//Label diagnosisTitleLabel = new Label("Diagnosis Management System");
		//diagnosisTitleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

		// Add the title label to the GridPane
		//formPane.add(diagnosisTitleLabel, 0, 0, 2, 1);

		// Set column constraints for center alignment
		ColumnConstraints columnConstraints = new ColumnConstraints();
		columnConstraints.setHalignment(HPos.CENTER);
		formPane.getColumnConstraints().add(columnConstraints);

		Stage primaryStage=new Stage();
		Button goBill = new Button("MAIN PAGE");
		goBill.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 12));// type of The Text
		goBill.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
		formPane.add(goBill, 13, 16);
		goBill.setOnAction(e -> {
			primaryStage.close();
		});
		primaryStage.setTitle("Diagnosis Management System");
		Scene scene = new Scene(formPane, 1100, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
		return primaryStage;
	}

	
	

}
