package com.mattapplications;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Main {


    public static void main(String[] args) {

        String inputFile = "testDelimited.txt";
        String outputFile = "testFlashCard.pdf";

        System.out.println("Create Flashcards");

        FlashCards flashCards = new FlashCards(new PDDocument(), 3d, 5d);

        try {
            flashCards.createFlashCards(readCsv(inputFile));
            flashCards.saveFlashCards(outputFile);

            System.out.println("File created in: " + System.getProperty("user.dir"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static ArrayList<String> readCsv(String filePath) throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader(filePath));
        ArrayList<String> list = new ArrayList<>();
        String rowFetched;
        String[] data;

        while ((rowFetched = csvReader.readLine()) != null) {
            data = rowFetched.split("\t");
            Collections.addAll(list, data);
        }

        return list;
    }
}
