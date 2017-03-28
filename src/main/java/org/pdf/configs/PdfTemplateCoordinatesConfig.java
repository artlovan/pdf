package org.pdf.configs;

import org.pdf.core.PdfTemplateCoordinates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class PdfTemplateCoordinatesConfig {
    @Bean
    public PdfTemplateCoordinates getPdfTemplateCoordinatesBean() {
        return new PdfTemplateCoordinates();
    }
}
