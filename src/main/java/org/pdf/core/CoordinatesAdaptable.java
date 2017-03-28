package org.pdf.core;

import java.util.function.Function;

@FunctionalInterface
public interface CoordinatesAdaptable extends Function<CoordinatesSupplier, CoordinatesSupplier> {

}
