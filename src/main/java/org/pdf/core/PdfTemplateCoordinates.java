package org.pdf.core;

import org.pdf.models.Field;
import org.pdf.models.Page;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;


public class PdfTemplateCoordinates {
    private PdfReader reader;

    public PdfTemplateCoordinates() {
    }

    public PdfTemplateCoordinates(PdfReader reader) {
        this.reader = reader;
    }

    public PdfTemplateCoordinates(byte[] pdfBinary) {
        try { this.reader = new PdfReader(pdfBinary);
        } catch (IOException e) { e.printStackTrace(); }
    }

    public PdfReader getReader() {
        return reader;
    }

    public void setReader(byte[] pdfBinary) {
        try { this.reader = new PdfReader(pdfBinary);
        } catch (IOException e) { e.printStackTrace(); }
    }

    /**
     * Method maps each Pdf Field with Pdf Fields coordinates
     * @return List of Pages, where a Page holds  List of Fields belonging to the Page.
     */
    public List<Page> mapFields() {
        return mapFields(s -> s);
    }

    /**
     * Additionally to mapFields() with no arguments, this method take PdfFieldNameParsable
     * so the Pdf Field Name can be parsed first and resulting String if added to the List.
     * @return List of Pages, where a Page holds  List of Fields belonging to the Page.
     */
    public List<Page> mapFields(PdfFieldNameParsable parsable) {
        AcroFields acroFields = reader.getAcroFields();

        return groupByPage(acroFields, acroFields.getFields()).entrySet()
            .stream()
            .collect(ArrayList::new,
                (pages, e) -> pages.add(new Page(getFields(parsable, acroFields, e.getValue()), e.getKey())),
                (pages, pages2) -> {});
    }

    private List<Field> getFields(PdfFieldNameParsable parsable, AcroFields acroFields, List<String> stringList) {
        return stringList.stream()
            .collect(ArrayList::new, (objects, s) -> {
                        float[] r = getRectangle(acroFields.getFieldPositions(s));
                        objects.add(new Field(parsable.apply(s), r[0], r[1], r[2], r[3]));
                    }, (objects, objects2) -> {});
    }

    private Map<Integer, List<String>> groupByPage(AcroFields acroFields, Map<String, AcroFields.Item> fields) {
        return fields.keySet()
                .stream()
                .collect(groupingBy(s -> acroFields.getFieldPositions(s).get(0).page));
    }

    private float[] getRectangle(List<AcroFields.FieldPosition> fieldPositions) {
        int page = fieldPositions.get(0).page;
        Rectangle rect = fieldPositions.get(0).position;

        Rectangle pageSize = reader.getPageSize(page);
        float pageHeight = pageSize.getTop();

        float x = rect.getLeft();
        float y = pageHeight - rect.getTop();
        float width = rect.getWidth();
        float height = rect.getHeight();

        return new float[]{x, y, width, height};
    }
}
