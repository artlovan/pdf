package org.pdf.utils;

import org.pdf.core.CoordinatesSupplier;
import org.pdf.models.Field;

import java.util.function.Function;

import static org.pdf.core.CoordinatesSupplier.define;
import static org.pdf.core.utils.UnitConversions.mm2pt;

/**
 * Helper class that converts coordinates from one precision to another
 * and returns type CoordinatesSupplier.
 */
public class Coordinates {

    private Coordinates() {

    }

    /**
     * Convenience method to supply CoordinatesSupplier
     * @param f type Field
     * @return type CoordinatesSupplier
     */
    public static CoordinatesSupplier defineSupplier(Field f) {
        return define(f.getX(), f.getY(), f.getWidth(), f.getHeight());
    }

    /**
     * Convenience method to supply CoordinatesSupplier which takes a function
     * so user can define to what and coordinates should be converted.
     * @param f type Field
     * @param function
     * @return type CoordinatesSupplier
     */
    public static CoordinatesSupplier defineSupplier(Field f, Function<Field, CoordinatesSupplier> function) {
        return function.apply(f);
    }

    /**
     * Convenience method to supply CoordinatesSupplier with already converted
     * coordinates from millimeters (mm) to points (pt).
     */
    public static Function<Field, CoordinatesSupplier> convertMm2Pt = field -> define(
            mm2pt(field.getX()),
            mm2pt(field.getY()),
            mm2pt(field.getWidth()),
            mm2pt(field.getHeight()));

    /**
     * Default behavior, coordinates are passed through without modification.
     * If higher order method required argument of Function<Field, CoordinatesSupplier>
     * this function can be used.
     */
    public static Function<Field, CoordinatesSupplier> retainCoordinates = field -> define(
            field.getX(),
            field.getY(),
            field.getWidth(),
            field.getHeight());
}
