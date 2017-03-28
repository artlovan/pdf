package org.pdf.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;


@JsonIgnoreProperties
public class PdfContext {
    @JsonProperty("binary_template")
    private String binaryTemplate;

    @JsonProperty("binary_pdf")
    @NotNull(message = "binaryPdf.notNull")
    @NotEmpty(message = "binaryPdf.notEmpty")
    private String binaryPdf;

    @Valid
    @JsonProperty("pages")
    private List<Page> pages;

    public String getBinaryTemplate() {
        return binaryTemplate;
    }

    public void setBinaryTemplate(String binaryTemplate) {
        this.binaryTemplate = binaryTemplate;
    }

    public String getBinaryPdf() {
        return binaryPdf;
    }

    public void setBinaryPdf(String binaryPdf) {
        this.binaryPdf = binaryPdf;
    }

    public List<Page> getPages() {
        return pages;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "PdfContext{" +
                "binaryTemplate='" + binaryTemplate + '\'' +
                ", binaryPdf='" + binaryPdf + '\'' +
                ", pages=" + pages +
                '}';
    }
}
