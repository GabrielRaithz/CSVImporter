package com.gerimedica.csvimporter.CSVImporter.record.helper;

import java.util.List;
import java.util.Map;

public class ResponseCSVImport {

    Map<String, Integer> headerIndexes;
    List<String[]> splittedLines;

    public ResponseCSVImport() {
    }

    public ResponseCSVImport(Map<String, Integer> headerIndexes, List<String[]> splittedLines) {
        this.headerIndexes = headerIndexes;
        this.splittedLines = splittedLines;
    }

    public Map<String, Integer> getHeaderIndexes() {
        return headerIndexes;
    }

    public void setHeaderIndexes(Map<String, Integer> headerIndexes) {
        this.headerIndexes = headerIndexes;
    }

    public List<String[]> getSplittedLines() {
        return splittedLines;
    }

    public void setSplittedLines(List<String[]> splittedLines) {
        this.splittedLines = splittedLines;
    }
}
