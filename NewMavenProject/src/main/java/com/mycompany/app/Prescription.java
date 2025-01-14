package com.mycompany.app;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class Prescription {
    private String firstName;
    private String lastName;
    private String address;
    private float sphere;
    private float axis;
    private float cylinder;
    private Date examinationDate;
    private String optometrist;
    private ArrayList<String> postRemarks = new ArrayList<>();

    // Method to add prescription details to presc.txt
    public boolean addPrescription() {
        // Validate conditions for prescription test 
        if (firstName.length() < 4 || firstName.length() > 15) return false;
        if (lastName.length() < 4 || lastName.length() > 15) return false;
        if (address.length() < 20) return false;
        if (sphere < -20.0 || sphere > 20.0) return false;
        if (axis < 0 || axis > 180) return false;
        if (cylinder < -4.0 || cylinder > 4.0) return false;
        if (optometrist.length() < 8 || optometrist.length() > 25) return false;

        // Writeas to presc.txt if valid
        try (FileWriter fw = new FileWriter("presc.txt", true)) {
            fw.write("First Name: " + firstName + "\n");
            fw.write("Last Name: " + lastName + "\n");
            fw.write("Address: " + address + "\n");
            fw.write("Sphere: " + sphere + "\n");
            fw.write("Axis: " + axis + "\n");
            fw.write("Cylinder: " + cylinder + "\n");
            fw.write("Optometrist: " + optometrist + "\n");
            fw.write("Examination Date: " + examinationDate + "\n");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // Method to add remarks to remark.txt
    public boolean addRemark(String remark) {
        // Validates remark
        if (remark.split(" ").length < 6 || remark.split(" ").length > 20) return false;
        if (!Character.isUpperCase(remark.charAt(0))) return false;
        if (postRemarks.size() >= 2) return false;

        postRemarks.add(remark);

        // Write to remark.txt if valid
        try (FileWriter fw = new FileWriter("remark.txt", true)) {
            fw.write("Remark: " + remark + "\n");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // Getters and setters for the fields (to use in the test cases)
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setAddress(String address) { this.address = address; }
    public void setSphere(float sphere) { this.sphere = sphere; }
    public void setAxis(float axis) { this.axis = axis; }
    public void setCylinder(float cylinder) { this.cylinder = cylinder; }
    public void setOptometrist(String optometrist) { this.optometrist = optometrist; }
    public void setExaminationDate(Date examinationDate) { this.examinationDate = examinationDate; }
}
