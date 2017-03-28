package org.pdf.core.utils;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Stream;


public class PdfDecoder {
    /**
     * MAX_DECODING_TIMES is setting the limit on infinite iteration
     * when encoded pdf is in decoding loop.
     */
    public static final int MAX_DECODING_TIMES = 5;

    /**
     * Iterates the decoding process and looks for "%" as expected decoded PDF.
     *
     * @param encodedPdfBinary representing PDF
     * @param encodingTimes times to re-decode given binary data
     * @return encoded PDF that can be read to PDF Parser libs.
     */
    public static byte[] getDecodedPdf(String encodedPdfBinary, int encodingTimes) {
        assert encodingTimes <= MAX_DECODING_TIMES;
        List<byte[]> binaryList = new ArrayList<>(1);
        binaryList.add(encodedPdfBinary.getBytes());

        Stream.iterate(1, i -> i + 1)
                .limit(MAX_DECODING_TIMES)
                .filter(i -> {
                    binaryList.set(0, decoder(binaryList.get(0)));
                    return new String(binaryList.get(0)).substring(0, 1).equals("%");})
                .findFirst();

        return binaryList.get(0);
    }

    /**
     * Sets second parameter to MAX_DECODING_TIMES and delegates the decoding.
     * @param encodedPdfBinary representing PDF
     * @return encoded PDF that can be read to PDF Parser libs.
     */
    public static byte[] getDecodedPdf(String encodedPdfBinary) {
        return getDecodedPdf(encodedPdfBinary, MAX_DECODING_TIMES);
    }

    /**
     * Decodes binary with Base64 decoder.
     * @param binary representing PDF
     * @return decoded PDF with Base64
     */
    private static byte[] decoder(byte[] binary) {
        return Base64.getDecoder().decode(binary);
    }
}
