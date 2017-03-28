package org.pdf.core;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


public class PdfTextExtractor implements PdfExtractable {

    private static PDFTextStripperByArea stripper;
    private PDDocument document;

    static {
        try { stripper = new PDFTextStripperByArea();
        } catch (IOException e) { e.printStackTrace(); }
    }

    public PdfTextExtractor() {
    }

    public PdfTextExtractor(byte[] decodedPdf) {
        try { this.document = PDDocument.load(decodedPdf);
        } catch (IOException e) { e.printStackTrace(); }
    }

    public PDDocument getDocument() {
        return document;
    }

    public void setDocument(byte[] decodedPdf) {
        try { this.document = PDDocument.load(decodedPdf);
        } catch (IOException e) { e.printStackTrace(); }
    }

    public PdfTextExtractor(PDDocument documentLoad) {
        this.document = documentLoad;
    }

    @Override
    public int size() {
        return getPages().getCount();
    }

    protected void closeFile() {
        try { document.close(); }
        catch (IOException e) { e.printStackTrace(); }
    }

    public PDPage getPage(int index) {
        return getPages().get(index);
    }

    protected PDPageTree getPages() {
        return document.getDocumentCatalog().getPages();
    }

    public List<String> getAllPagesText() {
        return Stream.iterate(0, i -> i + 1)
                .limit(size())
                .collect(ArrayList::new,
                        (list, i) -> list.add(getPageText(i)),
                        (o1, o2) -> {});
    }

    public String getTextAtCoordinate(PDPage page, CoordinatesSupplier coordinates) {
        return Text.getText(page, Rectangle.getRectangle(coordinates));
    }

    @Override
    public String getTextAtCoordinate(int pageIndex, CoordinatesSupplier coordinates) {
        return Text.getText(getPage(pageIndex), Rectangle.getRectangle(coordinates));
    }

    public String getPageText(int pageIndex) {
        PDPage page = getPages().get(pageIndex);
        Rectangle2D.Float rectangle = Rectangle.getRectangle(page.getMediaBox());
        return getTextAtCoordinate(page, rectangle);
    }

    private String getTextAtCoordinate(PDPage page, Rectangle2D.Float rectangle) {
        return Text.getText(page, rectangle);
    }

    private static final class Rectangle {

        private static Rectangle2D.Float getRectangle(PDPage page) {
            return getRectangle(page.getMediaBox());
        }

        private static Rectangle2D.Float getRectangle(PDRectangle mediaBox) {
            float x = mediaBox.getLowerLeftX();
            float y = mediaBox.getLowerLeftY();
            float w = mediaBox.getWidth();
            float h = mediaBox.getHeight();

            return getRectangle(x, y, w, h);
        }

        private static Rectangle2D.Float getRectangle(float x, float y, float w, float h) {
            return new Rectangle2D.Float(x, y, w, h);
        }

        private static Rectangle2D.Float getRectangle(CoordinatesSupplier rectangle) {
            float[] r = rectangle.get();
            return getRectangle(r[0], r[1], r[2], r[3]);
        }
    }

    private static final class Text {
        private static String getText(PDPage page, Rectangle2D.Float rectangle) {
            return getText(page, rectangle, "class1");
        }

        private static String getText(PDPage page, Rectangle2D.Float rectangle, String regionName) {
            stripper.addRegion(regionName, rectangle);
            try { stripper.extractRegions(page);
            } catch (IOException e) { e.printStackTrace(); }

            return stripper.getTextForRegion(regionName).trim();
        }
    }

}