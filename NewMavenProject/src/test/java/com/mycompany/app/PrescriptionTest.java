package com.mycompany.app;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Date;

public class PrescriptionTest {

    // Test cases for addPrescription

    @Test
    public void testValidPrescription() {
        Prescription presc = new Prescription();
        presc.setFirstName("John");
        presc.setLastName("Doe");
        presc.setAddress("123 Main Street, Suburb, City, Country");
        presc.setSphere(-5.0f);
        presc.setAxis(90);
        presc.setCylinder(-2.0f);
        presc.setOptometrist("Dr. Smith");
        presc.setExaminationDate(new Date());

        boolean result = presc.addPrescription();
        assertTrue(result);
    }

    @Test
    public void testInvalidFirstName() {
        Prescription presc = new Prescription();
        presc.setFirstName("Jo");  // Invalid, less than 4 characters
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
    public void testInvalidLastName() {
        Prescription presc = new Prescription();
        presc.setFirstName("John");
        presc.setLastName("D");  // Invalid, less than 4 characters
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
    public void testInvalidAddress() {
        Prescription presc = new Prescription();
        presc.setFirstName("John");
        presc.setLastName("Doe");
        presc.setAddress("Short Address");  // Invalid, less than 20 characters
        presc.setSphere(-5.0f);
        presc.setAxis(90);
        presc.setCylinder(-2.0f);
        presc.setOptometrist("Dr. Smith");
        presc.setExaminationDate(new Date());

        boolean result = presc.addPrescription();
        assertFalse(result);
    }

    @Test
    public void testInvalidSphere() {
        Prescription presc = new Prescription();
        presc.setFirstName("John");
        presc.setLastName("Doe");
        presc.setAddress("123 Main Street, Suburb, City, Country");
        presc.setSphere(-25.0f);  // Invalid, less than -20
        presc.setAxis(90);
        presc.setCylinder(-2.0f);
        presc.setOptometrist("Dr. Smith");
        presc.setExaminationDate(new Date());

        boolean result = presc.addPrescription();
        assertFalse(result);
    }

    @Test
    public void testInvalidOptometristName() {
        Prescription presc = new Prescription();
        presc.setFirstName("John");
        presc.setLastName("Doe");
        presc.setAddress("123 Main Street, Suburb, City, Country");
        presc.setSphere(-5.0f);
        presc.setAxis(90);
        presc.setCylinder(-2.0f);
        presc.setOptometrist("Smith");  // Invalid, less than 8 characters
        presc.setExaminationDate(new Date());

        boolean result = presc.addPrescription();
        assertFalse(result);
    }

    // Test cases for addRemark

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
        String remark = "client requested follow-up.";  // Invalid, lowercase first letter

        boolean result = presc.addRemark(remark);
        assertFalse(result);
    }

    @Test
    public void testMaxRemarksExceeded() {
        Prescription presc = new Prescription();
        presc.addRemark("First remark, perfectly valid.");
        presc.addRemark("Second remark, also valid.");

        boolean result = presc.addRemark("Third remark, should fail.");  // Should exceed limit
        assertFalse(result);
    }

    @Test
    public void testValidMaxRemarkLength() {
        Prescription presc = new Prescription();
        String remark = "This is a perfectly valid remark with exactly twenty words.";

        boolean result = presc.addRemark(remark);
        assertTrue(result);
    }

    @Test
    public void testAddRemarkAfterTwoValidRemarks() {
        Prescription presc = new Prescription();
        presc.addRemark("First remark, perfectly valid.");
        presc.addRemark("Second remark, also valid.");

        boolean result = presc.addRemark("Third remark, invalid due to limit.");
        assertFalse(result);
    }
}
