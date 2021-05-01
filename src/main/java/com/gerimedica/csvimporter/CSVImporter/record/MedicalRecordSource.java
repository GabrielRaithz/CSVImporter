package com.gerimedica.csvimporter.CSVImporter.record;

import com.gerimedica.csvimporter.CSVImporter.record.message.MessageResponse;
import com.gerimedica.csvimporter.CSVImporter.record.message.ResponseMedicalRecordImport;
import com.gerimedica.csvimporter.CSVImporter.record.model.MedicalRecord;
import com.gerimedica.csvimporter.CSVImporter.record.service.MedicalRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/medicalrecord")
public class MedicalRecordSource {

    /*
    upload the data     ok
    Fetch all data      ok
    Fetch by code       ok
    Delete all data     ok
    */

    final MedicalRecordService medicalRecordService;

    public MedicalRecordSource(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    @PostMapping("/csv/import")
    public ResponseEntity<ResponseMedicalRecordImport> importCSV(@RequestParam MultipartFile file) throws IOException, ParseException {
        ResponseMedicalRecordImport responseMedicalRecordImport = this.medicalRecordService.uploadData(file);
        return new ResponseEntity<>(responseMedicalRecordImport, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<MedicalRecord>> getAll(){
        List<MedicalRecord> medicalRecords = medicalRecordService.fetchAll();
        return new ResponseEntity<>(medicalRecords, HttpStatus.OK);
    }

    @GetMapping("/{code}")
    public ResponseEntity<MedicalRecord> getByCode(@PathVariable("code") Long code){
        MedicalRecord medicalRecord = medicalRecordService.fetchByCode(code);
        return new ResponseEntity<>(medicalRecord, HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<MessageResponse> deleteAll(){
        medicalRecordService.deleteAll();
        return new ResponseEntity<>(new MessageResponse("All medical records deleted"), HttpStatus.OK);
    }
}
