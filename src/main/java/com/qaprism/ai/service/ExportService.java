package com.qaprism.ai.service;

import com.qaprism.ai.model.AnalysisResponse;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;

@Service
public class ExportService {

    @Value("${report.path}")
    private String reportPath;

    public String exportReport(
            AnalysisResponse analysis) {

        try {

            Workbook workbook =
                    new XSSFWorkbook();

            createSheet(
                    workbook,
                    "Summary",
                    analysis.getSummary());

            createSheet(
                    workbook,
                    "Gap Analysis",
                    analysis.getGapAnalysis());

            createSheet(
                    workbook,
                    "Test Scenarios",
                    analysis.getTestScenarios());

            createSheet(
                    workbook,
                    "Test Cases",
                    analysis.getTestCases());

            createSheet(
                    workbook,
                    "Risk Analysis",
                    analysis.getRiskAnalysis());

            createSheet(
                    workbook,
                    "Regression Impact",
                    analysis.getRegressionImpact());

            File directory =
                    new File(reportPath);

            if (!directory.exists()) {

                directory.mkdirs();
            }

            String fileName =
                    reportPath +
                            "/QA_PRD_ANALYSIS.xlsx";

            FileOutputStream outputStream =
                    new FileOutputStream(
                            fileName);

            workbook.write(
                    outputStream);

            outputStream.close();

            workbook.close();

            return fileName;

        } catch (Exception exception) {

            throw new RuntimeException(
                    "Failed to export report",
                    exception);
        }
    }

    private void createSheet(
            Workbook workbook,
            String sheetName,
            String content) {

        Sheet sheet =
                workbook.createSheet(
                        sheetName);

        Row row =
                sheet.createRow(0);

        row.createCell(0)
                .setCellValue(content);
    }
}