package org.pdf.models;

import org.pdf.core.CoordinatesSupplier;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"field", "x", "y", "width", "height"})
public class Field {

    @JsonProperty("field")
    @NotNull(message = "field.field")
    private String field;

    @JsonProperty("height")
    @Min(0)
    private Float height;

    @JsonProperty("width")
    @Min(0)
    private Float width;

    @JsonProperty("x")
    @Min(0)
    private Float x;

    @JsonProperty("y")
    @Min(0)
    private Float y;

    public Field() {
    }

    public Field(String field, Float x, Float y, Float width, Float height) {
        this.field = field;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void setField(String field, CoordinatesSupplier supplier) {
        this.field = field;
        float[] floats = supplier.get();
        this.x = floats[0];
        this.y = floats[1];
        this.width = floats[2];
        this.height = floats[3];
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    @JsonProperty("height")
    public Float getHeight() {
        return height;
    }

    @JsonProperty("height")
    public void setHeight(Float height) {
        this.height = height;
    }

    @JsonProperty("width")
    public Float getWidth() {
        return width;
    }

    @JsonProperty("width")
    public void setWidth(Float width) {
        this.width = width;
    }

    @JsonProperty("x")
    public Float getX() {
        return x;
    }

    @JsonProperty("x")
    public void setX(Float x) {
        this.x = x;
    }

    @JsonProperty("y")
    public Float getY() {
        return y;
    }

    @JsonProperty("y")
    public void setY(Float y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Field{" +
                "field='" + field + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}