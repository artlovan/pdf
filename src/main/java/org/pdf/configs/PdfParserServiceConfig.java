package org.pdf.configs;

import org.pdf.services.PdfParserService;
import org.pdf.services.PdfParserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class PdfParserServiceConfig {
    @Bean
    public PdfParserService getPdfParserServiceBean() {
        return new PdfParserServiceImpl();
    }
}
