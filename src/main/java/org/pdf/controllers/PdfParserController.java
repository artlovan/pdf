package org.pdf.controllers;

import org.pdf.models.PdfContext;
import org.pdf.services.PdfParserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping("/api/pdf")
public class PdfParserController {

    @Autowired
    private PdfParserService service;

    @ApiOperation(value = "Returns map of pdf field names and corresponding value")
    @PostMapping(value = "parser", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPdfDataByBinaryAndPages(@Valid @RequestBody PdfContext context) {
        return new ResponseEntity<>(service.getPdfData(context), HttpStatus.OK);
    }
}

