package com.gerimedica.csvimporter.CSVImporter.record.helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataHandler {

    public static Integer integerParser(String sortingPriority){
        try{
            return Integer.parseInt(sortingPriority);
        } catch (Exception ex){
            return null;
        }
    }

    public static Date getDate(String dateParam) throws ParseException {
        DateFormat formatNR = new SimpleDateFormat("dd-MM-yyyy");
        if(dateParam.equals("")) return null;
        return formatNR.parse(dateParam);
    }

}
