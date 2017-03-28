package org.pdf.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


/**
 * Support will be potentially developed in future releases.
 * For now pdf service won't make any connections to outside DBs.
 */
public class ContextPdfData {
    @Valid
    @NotNull
    private AppContext context;

    @Valid
    @NotNull
    @JsonProperty("doc_center_context")
    private DocCenterContext docCenterContext;

    @Valid
    @JsonProperty("binary_pdf")
    private String binaryPdf;

    public ContextPdfData() {
    }

    public AppContext getContext() {
        return context;
    }

    public void setContext(AppContext context) {
        this.context = context;
    }

    public DocCenterContext getDocCenterContext() {
        return docCenterContext;
    }

    public void setDocCenterContext(DocCenterContext docCenterContext) {
        this.docCenterContext = docCenterContext;
    }

    public String getBinaryPdf() {
        return binaryPdf;
    }

    public void setBinaryPdf(String binaryPdf) {
        this.binaryPdf = binaryPdf;
    }

    @Override
    public String toString() {
        return "PdfDataPayload{" +
                "context=" + context +
                ", docCenterContext=" + docCenterContext +
                ", binaryPdf='" + binaryPdf + '\'' +
                '}';
    }
}
