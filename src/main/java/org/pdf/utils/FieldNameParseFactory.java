package org.pdf.utils;

/**
 * Factory class to supply common implementations of
 * how a PDF Field Name would be most likely to be parsed.
 */
public class FieldNameParseFactory {

    private FieldNameParseFactory() {

    }

    /**
     * Taking default String from PDF as like "F[0].Page_1[0].field_name_prefix\.field_name_postfix[0]"
     * and parsing to common PDF Field Name as like "vehicle.year".
     * @param s Default PDF Field Name String representation.
     * @return parsed String
     */
    public static String parseToCommon(String s) {
        int secondCurlyIndex = s.indexOf("]", s.indexOf("]") + 1) + 2;
        s = s.substring(secondCurlyIndex);
        int in = s.indexOf("[");
        return s.substring(0, in).replace("\\", "");
    }
}
