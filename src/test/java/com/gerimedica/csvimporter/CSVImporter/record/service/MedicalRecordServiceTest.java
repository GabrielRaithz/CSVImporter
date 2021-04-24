package com.gerimedica.csvimporter.CSVImporter.record.service;

import com.gerimedica.csvimporter.CSVImporter.record.model.MedicalRecord;
import com.gerimedica.csvimporter.CSVImporter.record.repository.MedicalRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class MedicalRecordServiceTest {

    @Mock
    MedicalRecordRepository medicalRecordRepository;

    MedicalRecordService medicalRecordService;

    @BeforeEach
    void setUp() {
        medicalRecordService = new MedicalRecordService(medicalRecordRepository);
    }

    @Test
    void fetchByCodeTest(){
        MedicalRecord expected = new MedicalRecord(
                271636001L, "ZIB", "ZIB001", "Polsslag regelmatig", "test", new Date(),new Date(),1);
        when(medicalRecordRepository.getMedicalRecordByCode(1L)).thenReturn(Optional.of(expected));
        MedicalRecord actual = medicalRecordService.fetchByCode(1L);
        assertEquals(expected, actual);
    }

    @Test
    void fetchAllTest(){
        MedicalRecord medicalRecord = new MedicalRecord(
                271636001L, "ZIB", "ZIB001", "Polsslag regelmatig", "test", new Date(),new Date(),1);
        List<MedicalRecord> expected = Arrays.asList(medicalRecord);
        when(medicalRecordRepository.findAll()).thenReturn(expected);
        List<MedicalRecord> actual = medicalRecordService.fetchAll();
        assertEquals(expected, actual);
    }

}