package com.gerimedica.csvimporter.CSVImporter.record.helper;

import com.gerimedica.csvimporter.CSVImporter.record.exception.IncorrectHeaderException;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class HelperCsvTest {

    //TODO
    @Test
    void happyDay() throws IOException, ParseException {
//        MedicalRecord medicalRecord = new MedicalRecord(
//                271636001L, "ZIB", "ZIB001", "Polsslag regelmatig", "test", new Date(),new Date(),1);
//
//        String fileMock = "source,codeListCode,code,displayValue,longDescription,fromDate,toDate,sortingPriority"
//                +System.getProperty("line.separator")
//                +medicalRecord.toString();
//        MockMultipartFile mmf = new MockMultipartFile("file", "test-file.csv",
//                "text/csv", (fileMock).getBytes());
//
//        ResponseCSVImport responseCSVImport = HelperCsv.importCsv(mmf);
//        assertTrue(responseCSVImport.getImportedMedicalRecords().size() > 0);
    }

    @Test
    void invalidHeader() {
        String fileMock = "sourcee,codeListCode,code,displayValue,longDescription,fromDate,toDate,sortingPriority"
                +System.getProperty("line.separator")
                +"ZIB,ZIB001,271636001,Polsslag regelmatig,test,24-04-21,24-04-21,1";
        MockMultipartFile mmf = new MockMultipartFile("file", "test-file.csv",
                "text/csv", (fileMock).getBytes());

//        assertThrows(
//                IncorrectHeaderException.class,
//                () -> HelperCsv.readCSVFile(mmf));
    }

    @Test
    void invalidLine() throws IOException, ParseException {
//        String fileMock = "source,codeListCode,code,displayValue,longDescription,fromDate,toDate,sortingPriority"
//                +System.getProperty("line.separator")
//                +"ZIB,ZIB001,AAAAA,Polsslag regelmatig,test,24-04-21,24-04-21,1";
//        MockMultipartFile mmf = new MockMultipartFile("file", "test-file.csv",
//                "text/csv", (fileMock).getBytes());
//
//        ResponseCSVImport responseCSVImport = HelperCsv.importCsv(mmf);
//        assertTrue(responseCSVImport.getInvalidLines().size() > 0);
    }
}