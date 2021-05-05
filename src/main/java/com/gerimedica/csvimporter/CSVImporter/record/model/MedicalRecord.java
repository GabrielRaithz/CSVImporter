package com.gerimedica.csvimporter.CSVImporter.record.model;

import com.gerimedica.csvimporter.CSVImporter.record.helper.DataHandler;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Entity
@Table(name = "medical_record")
public class MedicalRecord {

    @Id
    @NotNull
    @Column(unique = true)
    private Long code;
    @NotNull
    private String source;
    @NotNull
    private String codeListCode;
    @NotNull
    private String displayValue;
    @Nullable
    private String longDescription;
    @Nullable
    private Date fromDate;
    @Nullable
    private Date toDate;
    @Nullable
    private Integer sortingPriority;

    public MedicalRecord(Long code, String source, String codeListCode, String displayValue, String longDescription, Date fromDate, Date toDate, Integer sortingPriority) {
        this.code = code;
        this.source = source;
        this.codeListCode = codeListCode;
        this.displayValue = displayValue;
        this.longDescription = longDescription;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.sortingPriority = sortingPriority;
    }

    public static MedicalRecord MedicalRecordFactory(Map<String, Integer> headerIndexes, String[] splittedLine) throws ParseException {
        return new MedicalRecord(
                Long.parseLong(splittedLine[headerIndexes.get("code")]),
                splittedLine[headerIndexes.get("source")],
                splittedLine[headerIndexes.get("codeListCode")],
                splittedLine[headerIndexes.get("displayValue")],
                splittedLine[headerIndexes.get("longDescription")],
                DataHandler.getDateFormatted(splittedLine[headerIndexes.get("fromDate")]),
                DataHandler.getDateFormatted(splittedLine[headerIndexes.get("toDate")]),
                DataHandler.integerParser(splittedLine[headerIndexes.get("sortingPriority")])
        );
    }

    @Override
    public String toString() {
        SimpleDateFormat sdff = new SimpleDateFormat("dd-MM-yyyy");
        return  source + ',' +
                codeListCode + ',' +
                code + ',' +
                displayValue + ',' +
                longDescription + ',' +
                (fromDate == null ? null : sdff.format(fromDate)) + ',' +
                (toDate == null ? null : sdff.format(toDate)) + ',' +
                sortingPriority;
    }
}
