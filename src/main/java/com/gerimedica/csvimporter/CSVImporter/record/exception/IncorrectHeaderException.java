package com.gerimedica.csvimporter.CSVImporter.record.exception;

import java.util.List;

public class IncorrectHeaderException extends RuntimeException {
    public IncorrectHeaderException(List<String> header, List<String> headerAsList) {
        //TODO RENAME
        super("File header should be: \n"
                + header.toString()
                + "\nBut is: \n"
                + headerAsList.toString());
    }
}
