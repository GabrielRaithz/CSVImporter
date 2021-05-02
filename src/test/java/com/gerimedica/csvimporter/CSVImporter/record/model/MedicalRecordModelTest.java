package com.gerimedica.csvimporter.CSVImporter.record.model;

import com.gerimedica.csvimporter.CSVImporter.record.helper.HelperCsv;
import com.gerimedica.csvimporter.CSVImporter.record.helper.ResponseCSVImport;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class MedicalRecordModelTest {

    @Test
    void createRecordUsingStaticConstructor() throws IOException, ParseException {
        String fileMock = "source,codeListCode,code,displayValue,longDescription,fromDate,toDate,sortingPriority"
                +System.getProperty("line.separator")
                +"ZIB,ZIB001,11111,Polsslag regelmatig,test,24-04-21,24-04-21,1";
        MockMultipartFile mmf = new MockMultipartFile("file", "test-file.csv",
                "text/csv", (fileMock).getBytes());

        ResponseCSVImport responseCSVImport = HelperCsv.readCSVFile(mmf);

        MedicalRecord medicalRecord = MedicalRecord.createMedicalRecord(responseCSVImport.getHeaderIndexes(),
                responseCSVImport.getSplittedLines().get(0));
        assertEquals("ZIB,ZIB001,11111,Polsslag regelmatig,test,24-04-0021,24-04-0021,1", medicalRecord.toString());
    }

    @Test
    void throwsOnInvalidlines() throws IOException {
        String fileMock = "source,codeListCode,code,displayValue,longDescription,fromDate,toDate,sortingPriority"
                +System.getProperty("line.separator")
                +"ZIB,ZIB001,AAAAA,Polsslag regelmatig,test,24-04-21,24-04-21,1";
        MockMultipartFile mmf = new MockMultipartFile("file", "test-file.csv",
                "text/csv", (fileMock).getBytes());

        ResponseCSVImport responseCSVImport = HelperCsv.readCSVFile(mmf);

        assertThrows(
                NumberFormatException.class,
                () -> MedicalRecord.createMedicalRecord(
                        responseCSVImport.getHeaderIndexes(), responseCSVImport.getSplittedLines().get(0)));
    }

}