package com.qaprism.ai.contoller;

import com.qaprism.ai.service.AnalysisService;
import com.qaprism.ai.service.PdfExtractorService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Controller
public class AnalysisController {

    private final PdfExtractorService pdfExtractorService;

    private final AnalysisService analysisService;

    public AnalysisController(
            PdfExtractorService pdfExtractorService,
            AnalysisService analysisService) {

        this.pdfExtractorService =
                pdfExtractorService;

        this.analysisService =
                analysisService;
    }

    @PostMapping("/analyze")
    public String analyze(
            MultipartFile file,
            Model model,
            HttpSession session) {

        try {

            log.info(
                    "========================================");

            log.info(
                    "PRD Upload Request Received");

            log.info(
                    "File Name : {}",
                    file.getOriginalFilename());

            log.info(
                    "File Size : {} KB",
                    file.getSize() / 1024);

            log.info(
                    "Starting PDF Extraction");

            String prdContent =
                    pdfExtractorService.extractText(
                            file);

            log.info(
                    "PDF Extraction Completed");

            log.info(
                    "PRD Length : {} Characters",
                    prdContent.length());

            session.setAttribute(
                    "PRD_CONTENT",
                    prdContent);

            log.info(
                    "PRD Stored In Session");

            model.addAttribute(
                    "uploaded",
                    true);

            return "result";

        } catch (Exception exception) {

            log.error(
                    "PRD Upload Failed",
                    exception);

            model.addAttribute(
                    "error",
                    exception.getMessage());

            return "result";
        }
    }

    @PostMapping("/generate")
    @ResponseBody
    public String generate(
            @RequestParam String action,
            HttpSession session) {

        try {

            String prdContent =
                    (String) session.getAttribute(
                            "PRD_CONTENT");

            if (prdContent == null) {

                return "Please upload a PRD first.";
            }

            log.info(
                    "Generating Artifact : {}",
                    action);

            return analysisService
                    .generateArtifact(
                            prdContent,
                            action);

        } catch (Exception exception) {

            log.error(
                    "Artifact Generation Failed",
                    exception);

            return "Generation Failed : "
                    + exception.getMessage();
        }
    }
}