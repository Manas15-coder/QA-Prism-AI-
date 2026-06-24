package com.qaprism.ai.model;

import lombok.Data;

@Data
public class AnalysisResponse {

    private String summary;

    private String gapAnalysis;

    private String testScenarios;

    private String testCases;

    private String riskAnalysis;

    private String regressionImpact;
}