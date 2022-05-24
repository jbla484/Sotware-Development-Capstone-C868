package sample;

/**
 * The appointment class which allows users to create appointments and get/set values of their appointments.
 *
 * @author James Blankenship
 * @version 1.0
 */

public class ImportantAppointment extends Appointment {

    /**
     * The overloaded constructor.
     * @param id the appointment id to set.
     * @param title the appointment title to set.
     * @param description the appointment description to set.
     * @param location the appointment location to set.
     * @param type the appointment type to set.
     * @param start the appointment start to set.
     * @param end the appointment end to set.
     * @param creationDate the appointment creation date to set.
     * @param createdBy the appointment creator to set.
     * @param updated the appointment updated date to set.
     * @param updatedBy the appointment updater to set.
     * @param customerID the appointment customer id to set.
     * @param userID the appointment user id to set.
     * @param contactID the appointment contact id to set.
     */
    public ImportantAppointment(int id, String title, String description, String location, String type, String start, String end,
                                String creationDate, String createdBy, String updated, String updatedBy, int customerID, int userID,
                                int contactID) {
        super(id, title, description, location, type, start, end, creationDate, createdBy, updated, updatedBy, customerID, userID, contactID);
    }

    /**
     * The overloaded default constructor
     */
    ImportantAppointment() {

    }

    /**
     * Print the appointment object.
     */
    public void printAppointment() {
        System.out.println("IMPORTANT APPOINTMENT");
    }
}
