package com.mycompany.app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

public class PrescriptionTest {

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
        
        boolean result = presc.addRemark(remark, "Client");
        assertTrue(result);
    }

    @Test
    public void testInvalidRemarkLength() {
        Prescription presc = new Prescription();
        String remark = "Too short";
        
        boolean result = presc.addRemark(remark, "Client");
        assertFalse(result);
    }

    @Test
    public void testInvalidRemarkCase() {
        Prescription presc = new Prescription();
        String remark = "client requested follow-up."; // Invalid case
        
        boolean result = presc.addRemark(remark, "Client");
        assertFalse(result);
    }

    @Test
    public void testMaxRemarksExceeded() {
        Prescription presc = new Prescription();
        presc.addRemark("First remark, perfectly valid.", "Client");
        presc.addRemark("Second remark, also valid.", "Client");
        
        boolean result = presc.addRemark("Third remark, should fail.", "Client"); // Should exceed limit
        assertFalse(result);
    }

    @Test
    public void testInvalidRemarkType() {
        Prescription presc = new Prescription();
        String remark = "Valid remark with invalid type.";
        
        boolean result = presc.addRemark(remark, "InvalidType");
        assertFalse(result); // Should fail because of invalid type
    }
}