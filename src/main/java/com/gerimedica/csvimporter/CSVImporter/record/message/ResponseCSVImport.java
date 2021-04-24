package com.gerimedica.csvimporter.CSVImporter.record.message;

import com.gerimedica.csvimporter.CSVImporter.record.model.MedicalRecord;

import java.util.ArrayList;
import java.util.List;

public class ResponseCSVImport {
    private List<MedicalRecord> importedMedicalRecords;
    private List<String> invalidLines;

    public ResponseCSVImport() {
        importedMedicalRecords = new ArrayList<>();
        invalidLines = new ArrayList<>();
    }

    public void addImportedLine(MedicalRecord medicalRecord){
        importedMedicalRecords.add(medicalRecord);
    }

    public void addInvalidLine(String medicalRecord){
        invalidLines.add(medicalRecord);
    }

    public List<MedicalRecord> getImportedMedicalRecords() {
        return importedMedicalRecords;
    }

    public void setImportedMedicalRecords(List<MedicalRecord> importedMedicalRecords) {
        this.importedMedicalRecords = importedMedicalRecords;
    }

    public List<String> getInvalidLines() {
        return invalidLines;
    }

    public void setInvalidLines(List<String> invalidLines) {
        this.invalidLines = invalidLines;
    }
}
