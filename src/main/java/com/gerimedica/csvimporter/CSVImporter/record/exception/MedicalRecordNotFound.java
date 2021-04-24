package com.gerimedica.csvimporter.CSVImporter.record.exception;

public class MedicalRecordNotFound extends RuntimeException {
    public MedicalRecordNotFound(String message) {
        super(message);
    }
}
