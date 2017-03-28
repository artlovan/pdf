package org.pdf.configs;

import org.pdf.core.PdfExtractable;
import org.pdf.core.PdfTextExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PdfTextExtractorConfig {
    @Bean
    public PdfExtractable getPdfTextExtractorBean() {
        return new PdfTextExtractor();
    }
}
