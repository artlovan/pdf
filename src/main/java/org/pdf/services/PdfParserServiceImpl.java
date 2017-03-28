package org.pdf.services;

import org.pdf.core.CoordinatesSupplier;
import org.pdf.core.PdfExtractable;
import org.pdf.core.PdfTemplateCoordinates;
import org.pdf.custom_exceptions.InvalidPayloadException;
import org.pdf.models.ContextPdfData;
import org.pdf.models.Field;
import org.pdf.models.Page;
import org.pdf.models.PdfContext;
import org.pdf.utils.FieldNameParseFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static org.pdf.core.CoordinatesAdapterFactory.adapt;
import static org.pdf.core.utils.PdfDecoder.getDecodedPdf;
import static java.util.stream.Collectors.toMap;
import static org.pdf.utils.Coordinates.convertMm2Pt;
import static org.pdf.utils.Coordinates.defineSupplier;
import static org.pdf.utils.Coordinates.retainCoordinates;


public class PdfParserServiceImpl implements PdfParserService{
    private static final Logger logger = LoggerFactory.getLogger(PdfParserServiceImpl.class);
    private static final float X = -3, Y = 4, WIDTH = 3, HEIGHT = -4;
    @Autowired private PdfExtractable pdf;
    @Autowired private PdfTemplateCoordinates pdfTemplate;

    public ContextPdfData getPdfData(ContextPdfData context) {
        return context;
    }

    @Cacheable(value = "context", key = "#context.toString()")
    public Map<String, String> getPdfData(PdfContext context) {
        if (context.getBinaryPdf() != null && context.getBinaryTemplate() != null) {
            return collectDataWithTemplate(context);
        } else if (context.getBinaryPdf() != null && context.getPages() != null) {
            return collectDataWithPages(context);
        } else { throw new InvalidPayloadException("invalidPayloadException.message"); }
    }

    @CachePut(value = "context", key = "#result.binaryPdf")
    private Map<String, String> collectDataWithTemplate(PdfContext context) {
        pdfTemplate.setReader(getDecodedPdf(context.getBinaryTemplate()));
        pdf.setDocument(getDecodedPdf(context.getBinaryPdf()));

        return pdfTemplate.mapFields(FieldNameParseFactory::parseToCommon)
                .stream()
                .collect(HashMap::new,
                        (map, page) -> map.putAll(mapPageContext(page, pdf, retainCoordinates)),
                        HashMap::putAll);
    }

    @CachePut(value = "context", key = "#result.binaryPdf")
    private Map<String, String> collectDataWithPages(PdfContext context) {
        pdf.setDocument(getDecodedPdf(context.getBinaryPdf()));

        return context.getPages().stream()
                .collect(HashMap::new,
                        (map, page) -> map.putAll(mapPageContext(page, pdf, convertMm2Pt)),
                        HashMap::putAll);
    }

    private Map<String, String> mapPageContext(Page page,
                                               PdfExtractable pdf,
                                               Function<Field, CoordinatesSupplier> function) {
        return page.getFields()
                .stream()
                .collect(toMap(
                        Field::getField,
                        f -> pdf.getTextAtCoordinate(
                                page.getNumberIndex(),
                                adapt(X, Y, WIDTH, HEIGHT).apply(defineSupplier(f, function))),
                        (key, key2) -> key));
    }

    public static void main(String[] args) throws IOException {
        // transform base64 from multi-line to one line
//        List<String> lines = Files.readAllLines(Paths.get("src/test/java/com/dt/pdf/fixtures/QA3/QA3_NJ_RETL_AHC.AHFCA_FLAT.txt"));
//        StringBuilder sb = new StringBuilder();
//        lines.forEach(sb::append);
//        System.out.println(sb.toString());

        // decode once a double encoded pdf
//        List<String> line = Files.readAllLines(Paths.get("src/test/java/com/dt/pdf/fixtures/QA3/QA_NJ_RETL_AHC.AHFCA_ENCODED_TWICE.txt"));
//        byte[] decode = Base64.getDecoder().decode(line.get(0));
//        System.out.println(Arrays.toString(decode));

        // check if file is accepted by PDF parser lib
//        List<String> line = Files.readAllLines(Paths.get("src/test/java/com/dt/pdf/fixtures/QA3/QA_NJ_RETL_AHC.AHFCA_ENCODED_TWICE.txt"));
//        String as = new String(line.get(0).getBytes(), "UTF8");
//        System.out.println(as.substring(0, 1));
//        System.out.println("\n\n");
//        byte[] decodedOnce = Base64.getDecoder().decode(line.get(0));
//            String s = new String(decodedOnce, "UTF8");
//        System.out.println(s.substring(0, 10));
//        System.out.println("\n\n");
//        byte[] decodeTwice = Base64.getDecoder().decode(decodedOnce);
//        String ss = new String(decodeTwice, "UTF-8");
//        System.out.println(ss.substring(0, 1));
//        System.out.println("\n\n");
//        PdfFieldsToTextMapperiText mapperiText = new PdfFieldsToTextMapperiText(decodeTwice);

        // Takes PDF Template and parser and prints mapped coordinates
//        PdfReader reader = new PdfReader("src/test/java/com/dt/pdf/fixtures/QA3/QA_NJ_RETL_AHC.AHFCA_ENCODED_TWICE.pdf");
//        PdfFieldsToTextMapperiText pdf = new PdfFieldsToTextMapperiText(reader);
//        List<PdfFieldsToTextMapperiText.Page> pages = pdf.mapFields();
//        pages.forEach(p -> System.out.println("Page " + p.getPage() + " Coordinates " + Arrays.toString(p.getMappedFields().getSupplier().get())));
    }
}
