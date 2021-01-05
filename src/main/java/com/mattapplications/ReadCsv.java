package com.mattapplications;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadCsv {

   public static ArrayList<String[]> readCsv(String filePath) {
       try {
           BufferedReader csvReader = new BufferedReader(new FileReader(filePath));
           ArrayList<String[]> list = new ArrayList<>();
           String rowFetched;
           String[] data;

           while ((rowFetched = csvReader.readLine()) != null) {
               data = rowFetched.split("\t");
               list.add(data);
           }

           return list;
       } catch (IOException e) {
           System.out.println(e.getMessage());
           return null;
       }
   }
}
