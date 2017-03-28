package org.pdf.core;


import java.util.function.Supplier;

@FunctionalInterface
public interface CoordinatesSupplier extends Supplier<float[]> {

    static CoordinatesSupplier define(float x, float y, float width, float height) {
        return () -> new float[]{x, y, width, height};
    }
}
