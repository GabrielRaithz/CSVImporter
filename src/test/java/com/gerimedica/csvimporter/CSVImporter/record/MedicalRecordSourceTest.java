package com.gerimedica.csvimporter.CSVImporter.record;

import com.gerimedica.csvimporter.CSVImporter.record.service.MedicalRecordService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class MedicalRecordSourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MedicalRecordService medicalRecordService;

    @MockBean
    private MedicalRecordSource medicalRecordSource;

    @AfterEach
    void tearDown() {
        medicalRecordService.deleteAll();
    }

    @Test
    void importCSV() throws Exception {
//        ResponseCSVImport responseCSVImport = new ResponseCSVImport();
//        responseCSVImport.addInvalidLine("test");
//        Mockito.when(medicalRecordService.uploadData(any(MultipartFile.class))).thenReturn(responseCSVImport);
//
//        String fileMock = "source,codeListCode,code,displayValue,longDescription,fromDate,toDate,sortingPriority"
//                +System.getProperty("line.separator")
//                +"ZIB,ZIB001,1111,Polsslag regelmatig,test,24-04-21,24-04-21,1";
//
//        MockMultipartFile mmf = new MockMultipartFile("file", "test-file.csv",
//                "text/csv", (fileMock).getBytes());
//
//        this.mockMvc.perform(multipart("/medicalrecord/csv/import").file(mmf))
//                .andExpect(status().isOk());
    }

    @Test
    void getAll() throws Exception {
        this.mockMvc.perform(get("/medicalrecord")).andExpect(
                status().isOk());
    }

    @Test
    void deleteAll() throws Exception {
        this.mockMvc.perform(delete("/medicalrecord")).andExpect(
                status().isOk());
    }

    @Test
    void findByCode() throws Exception {
        this.mockMvc.perform(get("/medicalrecord/1"))
                .andExpect(status().isOk());
    }
}