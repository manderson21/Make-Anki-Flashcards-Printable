package com.mattapplications;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
import java.util.ArrayList;

public class FlashCards {

    private final PDDocument document;
    private PDPage page;
    private PDPageContentStream stream;

    private final double length;
    private final double width;

    public FlashCards(PDDocument document, double length, double width) {
        final double POINTS_PER_INCH = 72.0d;

        this.document = document;
        this.length = length * POINTS_PER_INCH;
        this.width = width * POINTS_PER_INCH;
    }

    public void createFlashCards(ArrayList<String> itemsList) throws IOException {
        PDRectangle dimensions = new PDRectangle((float) width, (float) length);
        PDFont font = PDType1Font.HELVETICA;
        int fontSize = 26;

        for (int i = 0; i < itemsList.size(); i++) {
            String item = itemsList.get(i);
            page = new PDPage(dimensions);
            document.addPage(page);
            stream = new PDPageContentStream(document, page);

            centerText(item, font, fontSize);
            addCardNumber(i, itemsList.size(), font);

            stream.close();
        }

    }

    void centerText(String text, PDFont font, int fontSize) throws IOException {
        PDRectangle mediaBox = page.getMediaBox();

        double textWidth = font.getStringWidth(text) / 1000 * fontSize;
        double textHeight = font.getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * fontSize;

        float startX = (float) ((mediaBox.getWidth() - textWidth) / 2);
        float startY = (float) ((mediaBox.getHeight() - textHeight) / 2);

        stream.beginText();
        stream.setFont(font, fontSize);
        stream.newLineAtOffset(startX, startY);
        stream.showText(text);
        stream.endText();
    }

    private void addCardNumber(int number, int size, PDFont font) throws IOException {
        PDRectangle mediaBox = page.getMediaBox();

        number++;

        // Makes the front and back of the card the same number
        int cardNumber = (int) Math.ceil(((double) (number) / size) * (double) (size / 2));

        int fontSize = 10;
        int margin = 10;
        String front = "a";
        String back = "b";
        String fullCardNumber = cardNumber + ((number % 2 != 0) ? front : back);

        double textWidth = font.getStringWidth(fullCardNumber) / 1000 * fontSize;

        float startX = (float) (mediaBox.getWidth() - textWidth - margin);

        stream.beginText();
        stream.setFont(font, fontSize);
        stream.newLineAtOffset(startX, margin);
        stream.showText(fullCardNumber);
        stream.endText();
    }

    public void saveFlashCards(String outputFile) throws IOException {
        document.save(outputFile);
        document.close();
    }

}
