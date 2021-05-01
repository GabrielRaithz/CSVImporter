package com.gerimedica.csvimporter.CSVImporter.record.service;

import com.gerimedica.csvimporter.CSVImporter.record.exception.FileIsNotCsvException;
import com.gerimedica.csvimporter.CSVImporter.record.exception.MedicalRecordNotFound;
import com.gerimedica.csvimporter.CSVImporter.record.helper.HelperCsv;
import com.gerimedica.csvimporter.CSVImporter.record.helper.ResponseCSVImport;
import com.gerimedica.csvimporter.CSVImporter.record.message.ResponseMedicalRecordImport;
import com.gerimedica.csvimporter.CSVImporter.record.model.MedicalRecord;
import com.gerimedica.csvimporter.CSVImporter.record.repository.MedicalRecordRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.gerimedica.csvimporter.CSVImporter.record.model.MedicalRecord.createMedicalRecord;
import static java.util.Objects.requireNonNull;

@Service
public class MedicalRecordService {

    private final String[] headerToBeCompared = {"source", "codeListCode", "code", "displayValue", "longDescription", "fromDate", "toDate", "sortingPriority"};

    final MedicalRecordRepository medicalRecordRepository;

    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    public ResponseMedicalRecordImport uploadData(MultipartFile file) throws IOException {
        if(!isTypeCSV(file)) throw new FileIsNotCsvException("Not CSV type file: " + file.getOriginalFilename());
        ResponseMedicalRecordImport responseMedicalRecordImport =
                createMedicalRecordForEachLine(file);
        this.medicalRecordRepository.saveAll(responseMedicalRecordImport.getImportedMedicalRecords());
        return responseMedicalRecordImport;
    }

    private ResponseMedicalRecordImport createMedicalRecordForEachLine(MultipartFile file) throws IOException {
        ResponseMedicalRecordImport responseMedicalRecordImport = new ResponseMedicalRecordImport();
        ResponseCSVImport responseCSVImport = HelperCsv.readCSVFile(file);
        for (String[] line : responseCSVImport.getSplittedLines()) {
            try {
                MedicalRecord medicalRecord = createMedicalRecord(responseCSVImport.getHeaderIndexes(), line);
                responseMedicalRecordImport.addImportedLine(medicalRecord);
            }catch (Exception ex){
                responseMedicalRecordImport.addInvalidLine(line + "reason: " + ex.getMessage());
            }
        }
        return responseMedicalRecordImport;
    }

    public MedicalRecord fetchByCode(Long code){
        return this.medicalRecordRepository.getMedicalRecordByCode(code)
                .orElseThrow(() -> new MedicalRecordNotFound("Medical record not found code: " + code));
    }

    public List<MedicalRecord> fetchAll(){
        return this.medicalRecordRepository.findAll();
    }

    public void deleteAll(){
        this.medicalRecordRepository.deleteAll();
    }

    private boolean isTypeCSV(MultipartFile file) {
        return requireNonNull(file.getOriginalFilename()).endsWith(".csv");
    }

}
