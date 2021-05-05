package com.gerimedica.csvimporter.CSVImporter.record.service;

import com.gerimedica.csvimporter.CSVImporter.record.exception.FileIsNotCsvException;
import com.gerimedica.csvimporter.CSVImporter.record.exception.MedicalRecordNotFound;
import com.gerimedica.csvimporter.CSVImporter.record.helper.HelperCsv;
import com.gerimedica.csvimporter.CSVImporter.record.response.ResponseCSVImport;
import com.gerimedica.csvimporter.CSVImporter.record.message.ResponseMedicalRecordImport;
import com.gerimedica.csvimporter.CSVImporter.record.model.MedicalRecord;
import com.gerimedica.csvimporter.CSVImporter.record.repository.MedicalRecordRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.gerimedica.csvimporter.CSVImporter.record.model.MedicalRecord.MedicalRecordFactory;
import static java.util.Objects.requireNonNull;

@Service
public class MedicalRecordService {

    private final String[] headerToBeCompared = {"source", "codeListCode", "code", "displayValue", "longDescription", "fromDate", "toDate", "sortingPriority"};

    final MedicalRecordRepository medicalRecordRepository;

    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
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

    public ResponseMedicalRecordImport uploadData(MultipartFile file) throws IOException {
        if(!isTypeCSV(file)) throw new FileIsNotCsvException("Not CSV type file: " + file.getOriginalFilename());
        ResponseMedicalRecordImport responseMedicalRecordImport = this.createMedicalRecordForEachLine(file);
        this.medicalRecordRepository.saveAll(responseMedicalRecordImport.getImportedMedicalRecords());
        return responseMedicalRecordImport;
    }

    private ResponseMedicalRecordImport createMedicalRecordForEachLine(MultipartFile file) throws IOException {
        ResponseCSVImport responseCSVImport = HelperCsv.readCSVFile(file);
        ResponseMedicalRecordImport responseMedicalRecordImport = returnMedicalRecordOrFailedLine(responseCSVImport);
        return responseMedicalRecordImport;
    }

    private ResponseMedicalRecordImport returnMedicalRecordOrFailedLine(ResponseCSVImport responseCSVImport) {
        ResponseMedicalRecordImport responseMedicalRecordImport = new ResponseMedicalRecordImport();
        for (String[] line : responseCSVImport.getSplittedLines()) {
            try {
                MedicalRecord medicalRecord = MedicalRecordFactory(responseCSVImport.getHeaderIndexes(), line);
                responseMedicalRecordImport.addImportedLine(medicalRecord);
            }catch (Exception ex){
                StringBuilder stringBuilder = getConcatenateItensInTheLine(line);
                responseMedicalRecordImport.addInvalidLine( stringBuilder + "reason: " + ex.getMessage());
            }
        }
        return responseMedicalRecordImport;
    }

    private StringBuilder getConcatenateItensInTheLine(String[] line) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String lineItem : line) {
            stringBuilder.append(lineItem);
            stringBuilder.append(" ");
        }
        return stringBuilder;
    }

    private boolean isTypeCSV(MultipartFile file) {
        return requireNonNull(file.getOriginalFilename()).endsWith(".csv");
    }

}
