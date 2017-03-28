package org.pdf.core;

public interface PdfExtractable {
    void setDocument(byte[] decodedPdf);

    int size();

    String getTextAtCoordinate(int pageIndex, CoordinatesSupplier coordinates);
}
