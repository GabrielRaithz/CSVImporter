package com.gerimedica.csvimporter.CSVImporter.record.response;

import java.util.List;
import java.util.Map;

public class ResponseCSVImport {

    Map<String, Integer> headerIndexes;
    List<String[]> splittedLines;

    public ResponseCSVImport() {
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
