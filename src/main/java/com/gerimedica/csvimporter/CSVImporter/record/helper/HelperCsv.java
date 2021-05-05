package com.gerimedica.csvimporter.CSVImporter.record.helper;

import com.gerimedica.csvimporter.CSVImporter.record.exception.IncorrectHeaderException;
import com.gerimedica.csvimporter.CSVImporter.record.response.ResponseCSVImport;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class HelperCsv {

    public static ResponseCSVImport readCSVFile(MultipartFile file, String[] headerToBeCompared) throws IOException {
        ResponseCSVImport responseCSVImport = new ResponseCSVImport();
        InputStreamReader inputStreamReader = new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String[] headerSplitted = bufferedReader.readLine().split(",", headerToBeCompared.length);
        checkHeader(Arrays.asList(headerSplitted), headerToBeCompared);

        responseCSVImport.setHeaderIndexes(getHeaderIndex(headerSplitted, headerToBeCompared));
        responseCSVImport.setSplittedLines(getLinesSplitted(bufferedReader, headerToBeCompared));

        inputStreamReader.close();
        bufferedReader.close();

        return responseCSVImport;
    }

    private static List<String[]> getLinesSplitted(BufferedReader bufferedReader, String[] headerToBeCompared) throws IOException {
        List<String[]> splittedLines = new ArrayList<>();
        String line = bufferedReader.readLine();
        while (line != null){
            splittedLines.add(line.split(",", headerToBeCompared.length));
            line = bufferedReader.readLine();
        }
        return splittedLines;
    }

    private static Map<String, Integer> getHeaderIndex(String[] headerSplitted, String[] headerToBeCompared) {
        Map<String, Integer> headerIndex = new HashMap<>();
        for (int i = 0; i < headerSplitted.length; i++){
            headerIndex.put(headerSplitted[i], i);
        }
        return headerIndex;
    }

    private static void checkHeader(List<String> header, String[] headerToBeCompared){
        List<String> headerAsList = Arrays.asList(headerToBeCompared);
        if(!header.containsAll(headerAsList)) {
            throw new IncorrectHeaderException(header, headerAsList);
        }
    }
}
