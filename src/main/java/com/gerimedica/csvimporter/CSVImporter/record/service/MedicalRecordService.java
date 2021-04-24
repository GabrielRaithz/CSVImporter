package com.gerimedica.csvimporter.CSVImporter.record.service;

import com.gerimedica.csvimporter.CSVImporter.record.message.ResponseCSVImport;
import com.gerimedica.csvimporter.CSVImporter.record.exception.FileIsNotCsvException;
import com.gerimedica.csvimporter.CSVImporter.record.exception.MedicalRecordNotFound;
import com.gerimedica.csvimporter.CSVImporter.record.helper.HelperCsv;
import com.gerimedica.csvimporter.CSVImporter.record.model.MedicalRecord;
import com.gerimedica.csvimporter.CSVImporter.record.repository.MedicalRecordRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@Service
public class MedicalRecordService {

    MedicalRecordRepository medicalRecordRepository;

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

    public ResponseCSVImport uploadData(MultipartFile file) throws IOException, ParseException {
        if(!file.getOriginalFilename().endsWith(".csv")) throw new FileIsNotCsvException("Not CSV type file: " + file.getOriginalFilename());
        ResponseCSVImport responseCSVImport = HelperCsv.importCsv(file);
        this.medicalRecordRepository.saveAll(responseCSVImport.getImportedMedicalRecords());
        return responseCSVImport;
    }

}
