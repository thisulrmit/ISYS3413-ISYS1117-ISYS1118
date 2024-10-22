package com.mycompany.app;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Date;

public class PrescriptionTest {

    @Test
    public void testValidPrescription() {
        Prescription presc = new Prescription();
        
        // Assign values that meet all validation conditions
        presc.setFirstName("John");  // First name should be between 4 to 15 characters
        presc.setLastName("Doe");    // Last name should be between 4 to 15 characters
        presc.setAddress("123 Main Street, Suburb, City, Country");  // Address should be at least 20 characters
        presc.setSphere(-5.0f);      // Sphere should be between -20.0 and 20.0
        presc.setAxis(90);           // Axis should be between 0 and 180
        presc.setCylinder(-2.0f);    // Cylinder should be between -4.0 and 4.0
        presc.setOptometrist("Dr. Smith"); // Optometrist name should be between 8 to 25 characters
        presc.setExaminationDate(new Date());  // Examination date (valid)
        
        // Expect this to pass
        boolean result = presc.addPrescription();
        assertTrue(result); // Test should pass if inputs are valid
    }


    @Test
    public void testInvalidFirstName() {
        Prescription presc = new Prescription();
        presc.setFirstName("Jo");  // Invalid name, less than 4 characters
        presc.setLastName("Doe");
        presc.setAddress("123 Main Street, Suburb, City, Country");
        presc.setSphere(-5.0f);
        presc.setAxis(90);
        presc.setCylinder(-2.0f);
        presc.setOptometrist("Dr. Smith");
        presc.setExaminationDate(new Date());

        boolean result = presc.addPrescription();
        assertFalse(result);
    }

    @Test
    public void testValidRemark() {
        Prescription presc = new Prescription();
        String remark = "Client has requested a follow-up check.";

        boolean result = presc.addRemark(remark);
        assertTrue(result);
    }

    @Test
    public void testInvalidRemarkLength() {
        Prescription presc = new Prescription();
        String remark = "Too short";

        boolean result = presc.addRemark(remark);
        assertFalse(result);
    }

    @Test
    public void testInvalidRemarkCase() {
        Prescription presc = new Prescription();
        String remark = "client requested follow-up."; // Invalid case

        boolean result = presc.addRemark(remark);
        assertFalse(result);
    }

    @Test
    public void testMaxRemarksExceeded() {
        Prescription presc = new Prescription();
        presc.addRemark("First remark, perfectly valid.");
        presc.addRemark("Second remark, also valid.");

        boolean result = presc.addRemark("Third remark, should fail."); // Should exceed limit
        assertFalse(result);
    }
}
