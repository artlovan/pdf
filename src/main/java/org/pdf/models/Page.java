package org.pdf.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"number", "fields"})
public class Page {

    @JsonProperty("fields")
    @NotNull(message = "page.FieldsList")
    @Valid
    private List<Field> fields;

    @JsonProperty("number")
    @Min(value = 1, message = "page.number.min")
    @NotNull(message = "page.number.null")
    private Integer number;

    public Page() {
    }

    public Page(List<Field> fields, Integer number) {
        this.fields = fields;
        this.number = number;
    }

    @JsonProperty("fields")
    public List<Field> getFields() {
        return fields;
    }

    @JsonProperty("fields")
    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    @JsonProperty("number")
    public Integer getNumber() {
        return number;
    }

    public Integer getNumberIndex() {
        return number - 1;
    }

    @JsonProperty("number")
    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Page{" +
                "fields=" + fields +
                ", number=" + number +
                '}';
    }
}