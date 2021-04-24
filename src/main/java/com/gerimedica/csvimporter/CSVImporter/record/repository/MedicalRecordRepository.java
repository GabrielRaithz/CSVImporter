package com.gerimedica.csvimporter.CSVImporter.record.repository;

import com.gerimedica.csvimporter.CSVImporter.record.model.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
    //query method
    Optional<MedicalRecord> getMedicalRecordByCode(Long code);
}
