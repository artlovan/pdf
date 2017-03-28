package org.pdf.core;

import static org.pdf.core.CoordinatesSupplier.define;

/**
 * Factory class helping to manipulate Rectangle coordinates
 * in case defaulted are not precise.
 */
public class CoordinatesAdapterFactory {

    private CoordinatesAdapterFactory() {

    }

    /**
     * Helper to manipulate coordinates in case default one are not precise
     * @param x coordinate
     * @param y coordinate
     * @param width of the Rectangle
     * @param height of the Rectangle
     * @return CoordinatesAdaptable
     */
    public static CoordinatesAdaptable adapt(float x, float y, float width, float height) {
        return s -> define((s.get()[0] + x), (s.get()[1] + y), (s.get()[2] + width), (s.get()[3] + height));
    }
}
