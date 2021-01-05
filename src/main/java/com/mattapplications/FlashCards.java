package com.mattapplications;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

public class FlashCards {

    private final double POINTS_PER_INCH = 72.0D;

    private PDDocument document;
    private double length;
    private double width;

    public FlashCards(PDDocument document, double length, double width) {
        this.document = document;
        this.length = length * POINTS_PER_INCH;
        this.width = width * POINTS_PER_INCH;
    }



    public PDDocument getDocument() {
        return document;
    }

}
