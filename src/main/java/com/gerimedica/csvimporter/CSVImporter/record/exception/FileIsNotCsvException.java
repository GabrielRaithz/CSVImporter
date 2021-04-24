package com.gerimedica.csvimporter.CSVImporter.record.exception;

public class FileIsNotCsvException extends RuntimeException {
    public FileIsNotCsvException(String message) {
        super(message);
    }
}
