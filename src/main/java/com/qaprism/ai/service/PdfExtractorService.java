package com.qaprism.ai.service;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PdfExtractorService {

    public String extractText(MultipartFile file) {

        validateFile(file);

        try (PDDocument document =
                     Loader.loadPDF(file.getBytes())) {

            PDFTextStripper pdfTextStripper =
                    new PDFTextStripper();

            String extractedText =
                    pdfTextStripper.getText(document);

            return cleanText(extractedText);

        } catch (IOException exception) {

            throw new RuntimeException(
                    "Unable to extract text from PDF",
                    exception
            );
        }
    }

    private void validateFile(MultipartFile file) {

        if (file == null || file.isEmpty()) {

            throw new RuntimeException(
                    "Uploaded file is empty"
            );
        }

        String fileName = file.getOriginalFilename();

        if (fileName == null ||
                !fileName.toLowerCase().endsWith(".pdf")) {

            throw new RuntimeException(
                    "Only PDF files are supported"
            );
        }
    }

    private String cleanText(String text) {

        if (text == null) {

            return "";
        }

        return text

                .replaceAll("\\r", " ")

                .replaceAll("\\n+", "\n")

                .replaceAll("[ ]+", " ")

                .trim();
    }
}