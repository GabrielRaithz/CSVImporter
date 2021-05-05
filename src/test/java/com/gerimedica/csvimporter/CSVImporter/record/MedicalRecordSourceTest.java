package com.gerimedica.csvimporter.CSVImporter.record;

import com.gerimedica.csvimporter.CSVImporter.record.message.ResponseMedicalRecordImport;
import com.gerimedica.csvimporter.CSVImporter.record.service.MedicalRecordService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class MedicalRecordSourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MedicalRecordService medicalRecordService;

    @Autowired
    private MedicalRecordSource medicalRecordSource;

    @AfterEach
    void tearDown() {
        medicalRecordService.deleteAll();
    }

    @Test
    void importCSV() throws Exception {
        ResponseMedicalRecordImport responseMedicalRecordImport = new ResponseMedicalRecordImport();
        responseMedicalRecordImport.addInvalidLine("test");
        Mockito.when(medicalRecordService.uploadData(any(MultipartFile.class))).thenReturn(responseMedicalRecordImport);

        String fileMock = "source,codeListCode,code,displayValue,longDescription,fromDate,toDate,sortingPriority"
                +System.getProperty("line.separator")
                +"ZIB,ZIB001,1111,Polsslag regelmatig,test,24-04-21,24-04-21,1";

        MockMultipartFile mmf = new MockMultipartFile("file", "test-file.csv",
                "text/csv", (fileMock).getBytes());

        this.mockMvc.perform(multipart("/medicalrecord/csv/import").file(mmf))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"importedMedicalRecords\":[],\"invalidLines\":[\"test\"]}"));
    }

    @Test
    void getAll() throws Exception {
        this.mockMvc.perform(get("/medicalrecord"))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));
    }

    @Test
    void deleteAll() throws Exception {
        this.mockMvc.perform(delete("/medicalrecord"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"message\":\"All medical records deleted\"}"));
    }

    @Test
    void findByCode() throws Exception {
        this.mockMvc.perform(get("/medicalrecord/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }
}