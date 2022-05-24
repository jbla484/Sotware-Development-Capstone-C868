package test;

import javafx.embed.swing.JFXPanel;
import org.junit.Assert;
import org.junit.Test;

import sample.Controller;
import sample.JDBC;

import java.sql.*;
import java.time.LocalDate;
import java.time.Period;

public class UnitTest {

    //Declare controllers and connection to database.
    Controller controller;
    Controller controller2;
    Controller controller3;
    Connection connection = JDBC.getConnection();

    public UnitTest() {
        // Initialize and declare panel to set texts.
        JFXPanel fxPanel = new JFXPanel();

        // Initialize controllers.
        controller = new Controller();
        controller2 = new Controller();
        controller3 = new Controller();
    }

    @Test
    public void testEdgeCase() throws Exception {

        // Make a connection to the database.
        JDBC.makeConnection();

        // Get connection to database and get next appointment id.
        String query7 = "SELECT * FROM appointments;";
        connection = JDBC.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query7);
        int count = 1;

        while (rs.next()) {
            count += 1;
        }

        // Set controllers values.
        Controller.testing = true;
        Controller.createdByName = "Unit Test";

        controller3.appointmentIDText.setText(String.valueOf(count));
        controller3.appointmentTitleText.setText("Unit Test");
        controller3.appointmentDescriptionText.setText("Unit Test");
        controller3.appointmentLocationText.setText("Unit Test");

        controller3.appointmentTypeText.setText("Unit Test");
        controller3.appointmentStartDate.setValue(LocalDate.now().plus(Period.ofYears(2)));
        controller3.appointmentEndDate.setValue(LocalDate.now().plus(Period.ofYears(2)));
        controller3.appointmentCustomerIDText.setText("2");

        controller3.appointmentUserIDText.setText("1");
        controller3.contactComboBox.getSelectionModel().select("Anika Costa");
        controller3.appointmentStartTimeComboBox.getSelectionModel().select("12:00");
        controller3.appointmentEndTimeComboBox.getSelectionModel().select("14:00");

        controller3.important.setSelected(true);

        System.out.println("\n------- Testing edge-case appointments -------");
        System.out.println("\nAdding first appointment, printing appointment type: ");

        // Call add appointment method in Controller class.
        controller3.handleAddAppointmentButtonAction();

        // Set controllers values.
        controller3.appointmentIDText.setText(String.valueOf(count + 1));
        controller3.appointmentStartTimeComboBox.getSelectionModel().select("14:00");
        controller3.appointmentEndTimeComboBox.getSelectionModel().select("14:30");

        System.out.println("\nAdding second appointment, printing appointment type: ");

        // Call add appointment method in Controller class.
        controller3.handleAddAppointmentButtonAction();

        // Assert if the test case passes or fails.
        if (!Controller.overlap) {
            Assert.assertTrue("No overlapping appointments.", true);
        } else {
            Assert.fail("Overlapping appointments.");
        }
    }

    @Test
    public void testOverlap() throws Exception {

        // Get connection to database and get next appointment id.
        String query7 = "SELECT * FROM appointments;";
        connection = JDBC.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query7);
        int count = 1;

        while (rs.next()) {
            count += 1;
        }

        // Set controllers values.
        Controller.testing = true;
        Controller.createdByName = "Unit Test";

        controller.appointmentIDText.setText(String.valueOf(count));
        controller.appointmentTitleText.setText("Unit Test");
        controller.appointmentDescriptionText.setText("Unit Test");
        controller.appointmentLocationText.setText("Unit Test");

        controller.appointmentTypeText.setText("Unit Test");
        controller.appointmentStartDate.setValue(LocalDate.now());
        controller.appointmentEndDate.setValue(LocalDate.now());
        controller.appointmentCustomerIDText.setText("2");

        controller.appointmentUserIDText.setText("1");
        controller.contactComboBox.getSelectionModel().select("Anika Costa");
        controller.appointmentStartTimeComboBox.getSelectionModel().select("12:00");
        controller.appointmentEndTimeComboBox.getSelectionModel().select("14:00");

        controller.important.setSelected(true);

        System.out.println("\n------- Testing overlapping appointments -------");
        System.out.println("\nAdding first appointment, printing appointment type: ");

        // Call add appointment method in Controller class.
        controller.handleAddAppointmentButtonAction();

        // Set controllers values.
        controller.appointmentIDText.setText(String.valueOf(count + 1));
        controller.appointmentStartTimeComboBox.getSelectionModel().select("13:00");
        controller.appointmentEndTimeComboBox.getSelectionModel().select("15:00");

        System.out.println("\nAdding second appointment, should overlap and not be added to the database: ");

        // Call add appointment method in Controller class.
        controller.handleAddAppointmentButtonAction();

        // Assert if the test case passes or fails.
        if (Controller.overlap) {
            Assert.assertTrue("Overlapping appointments.", true);
        } else {
            Assert.fail("No overlapping appointments.");
        }
    }

    @Test
    public void testNoOverlap() throws Exception {

        // Get connection to database and get next appointment id.
        String query7 = "SELECT * FROM appointments;";
        connection = JDBC.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query7);
        int count = 1;

        while (rs.next()) {
            count += 1;
        }

        // Set controllers values.
        Controller.testing = true;
        Controller.createdByName = "Unit Test";

        controller2.appointmentIDText.setText(String.valueOf(count));
        controller2.appointmentTitleText.setText("Unit Test");
        controller2.appointmentDescriptionText.setText("Unit Test");
        controller2.appointmentLocationText.setText("Unit Test");

        controller2.appointmentTypeText.setText("Unit Test");
        controller2.appointmentStartDate.setValue(LocalDate.now().plus(Period.ofYears(1)));
        controller2.appointmentEndDate.setValue(LocalDate.now().plus(Period.ofYears(1)));
        controller2.appointmentCustomerIDText.setText("2");

        controller2.appointmentUserIDText.setText("1");
        controller2.contactComboBox.getSelectionModel().select("Anika Costa");
        controller2.appointmentStartTimeComboBox.getSelectionModel().select("12:00");
        controller2.appointmentEndTimeComboBox.getSelectionModel().select("14:00");

        controller2.important.setSelected(true);

        System.out.println("\n------- Testing non-overlapping appointments -------");
        System.out.println("\nAdding first appointment, printing appointment type: ");

        // Call add appointment method in Controller class.
        controller2.handleAddAppointmentButtonAction();

        // Set controllers values.
        controller2.appointmentIDText.setText(String.valueOf(count + 1));
        controller2.appointmentStartTimeComboBox.getSelectionModel().select("10:00");
        controller2.appointmentEndTimeComboBox.getSelectionModel().select("11:30");

        System.out.println("\nAdding second appointment, printing appointment type: ");

        // Call add appointment method in Controller class.
        controller2.handleAddAppointmentButtonAction();

        // Assert if the test case passes or fails.
        if (Controller.overlap) {
            Assert.fail("Overlapping appointments.");
        } else {
            Assert.assertTrue("No overlapping appointments.", true);
        }
    }
}
