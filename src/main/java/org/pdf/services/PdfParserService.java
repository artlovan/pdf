package org.pdf.services;

import org.pdf.models.PdfContext;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface PdfParserService {
    Map<String, String> getPdfData(PdfContext context);
}
