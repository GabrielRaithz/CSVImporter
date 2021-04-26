package com.gerimedica.csvimporter.CSVImporter.record.model;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public MedicalRecord() {
    }

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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCodeListCode() {
        return codeListCode;
    }

    public void setCodeListCode(String codeListCode) {
        this.codeListCode = codeListCode;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public void setDisplayValue(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Integer getSortingPriority() {
        return sortingPriority;
    }

    public void setSortingPriority(Integer sortingPriority) {
        this.sortingPriority = sortingPriority;
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
