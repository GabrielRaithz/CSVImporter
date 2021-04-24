package com.gerimedica.csvimporter.CSVImporter.record.exception;

import java.util.List;

public class IncorrectHeaderException extends RuntimeException {
    public IncorrectHeaderException(List<String> headerShouldBe, List<String> headerAsIs) {
        super("File headerShouldBe should be: \n"
                + headerShouldBe.toString()
                + "\nBut is: \n"
                + headerAsIs.toString());
    }
}
