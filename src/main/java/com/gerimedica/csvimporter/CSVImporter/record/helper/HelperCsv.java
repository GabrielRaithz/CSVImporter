package com.gerimedica.csvimporter.CSVImporter.record.helper;

import com.gerimedica.csvimporter.CSVImporter.record.message.ResponseCSVImport;
import com.gerimedica.csvimporter.CSVImporter.record.exception.IncorrectHeaderException;
import com.gerimedica.csvimporter.CSVImporter.record.model.MedicalRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class HelperCsv {
    private static final String[] headerToBeCompared = {"source", "codeListCode", "code", "displayValue", "longDescription", "fromDate", "toDate", "sortingPriority"};

    public static ResponseCSVImport importCsv(MultipartFile file) throws IOException {
        ResponseCSVImport responseCSVImport = new ResponseCSVImport();

        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
        String[] headerSplitted = bufferedReader.readLine().replace("\"","").split(",");
        checkHeader(Arrays.asList(headerSplitted));
        Map<String, Integer> headerIndexes = getHeaderIndex(headerSplitted);
        String line = bufferedReader.readLine();

        while (line!=null){
            String[] splittedLine = line.replace("\"","").split(",");
            try {
                responseCSVImport.addImportedLine(createMedicalRecord(headerIndexes, splittedLine));
            }catch (Exception ex){
               responseCSVImport.addInvalidLine(line);
            }
            line = bufferedReader.readLine();
        }

        return responseCSVImport;
    }

    private static MedicalRecord createMedicalRecord(Map<String, Integer> headerIndexes, String[] splittedLine) throws ParseException {
        return new MedicalRecord(
                Long.parseLong(splittedLine[headerIndexes.get("code")]),
                splittedLine[headerIndexes.get("source")],
                splittedLine[headerIndexes.get("codeListCode")],
                splittedLine[headerIndexes.get("displayValue")],
                splittedLine[headerIndexes.get("longDescription")],
                getDate(splittedLine[headerIndexes.get("fromDate")]),
                getDate(splittedLine[headerIndexes.get("toDate")]),
                Integer.parseInt(splittedLine[headerIndexes.get("sortingPriority")])
        );
    }

    private static Date getDate(String dateParam) throws ParseException {
        DateFormat formatNR = new SimpleDateFormat("dd-MM-yyyy");
        if(dateParam.equals("")) return null;
        return formatNR.parse(dateParam);
    }

    private static Map<String, Integer> getHeaderIndex(String[] headerSplitted) {
        Map<String, Integer> headerIndex = new HashMap<>();
        for (int i = 0; i < headerSplitted.length; i++){
            headerIndex.put(headerSplitted[i], i);
        }
        return headerIndex;
    }

    private static void checkHeader(List<String> header){
        List<String> headerAsList = Arrays.asList(headerToBeCompared);
        if(!header.containsAll(headerAsList)) {
            throw new IncorrectHeaderException(header, headerAsList);
        }
    }
}
