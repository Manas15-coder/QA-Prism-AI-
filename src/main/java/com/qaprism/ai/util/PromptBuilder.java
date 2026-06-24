package com.qaprism.ai.util;

public final class PromptBuilder {

    private PromptBuilder() {

    }

    public static String buildArtifactPrompt(
            String prdContent,
            String action) {

        String prd =
                prdContent.length() > 3000
                        ? prdContent.substring(
                        0,
                        3000)
                        : prdContent;

        return switch (action.toLowerCase()) {

            case "executive summary" ->
                    buildExecutiveSummaryPrompt(
                            prd);

            case "gap analysis" ->
                    buildGapAnalysisPrompt(
                            prd);

            case "risk analysis" ->
                    buildRiskAnalysisPrompt(
                            prd);

            case "regression impact" ->
                    buildRegressionImpactPrompt(
                            prd);

            case "test scenarios" ->
                    buildTestScenariosPrompt(
                            prd);

            case "test cases" ->
                    buildTestCasesPrompt(
                            prd);

            case "jira stories" ->
                    buildJiraStoriesPrompt(
                            prd);

            default ->
                    buildCustomPrompt(
                            prd,
                            action);
        };
    }

    private static String buildExecutiveSummaryPrompt(
            String prd) {

        return """
                /no_think

                You are a Senior QA Architect.

                Generate a concise Executive Summary.

                Include:

                - Feature Overview
                - Business Goal
                - Key Functionalities

                Use bullet points.

                PRD:

                %s
                """
                .formatted(prd);
    }

    private static String buildGapAnalysisPrompt(
            String prd) {

        return """
                /no_think

                You are a Senior QA Architect.

                Identify:

                - Missing Requirements
                - Ambiguous Requirements
                - Missing Validations
                - Missing Error Handling
                - Missing Security Requirements
                - Missing Non Functional Requirements

                Use bullet points.

                PRD:

                %s
                """
                .formatted(prd);
    }

    private static String buildRiskAnalysisPrompt(
            String prd) {

        return """
                /no_think

                You are a QA Risk Analyst.

                Identify:

                - Functional Risks
                - Integration Risks
                - Security Risks
                - Performance Risks

                Use bullet points.

                PRD:

                %s
                """
                .formatted(prd);
    }

    private static String buildRegressionImpactPrompt(
            String prd) {

        return """
                /no_think

                Identify:

                - Impacted Modules
                - Impacted Workflows
                - Impacted APIs
                - Recommended Regression Areas

                Use bullet points.

                PRD:

                %s
                """
                .formatted(prd);
    }

    private static String buildTestScenariosPrompt(
            String prd) {

        return """
                /no_think

                Generate 10 QA Test Scenarios.

                Include:

                - Positive
                - Negative
                - Boundary
                - Security

                Use numbered list.

                PRD:

                %s
                """
                .formatted(prd);
    }

    private static String buildTestCasesPrompt(
            String prd) {

        return """
                /no_think

                Generate ONLY 10 High Priority Test Cases.

                Format:

                TC001
                Scenario:
                Expected Result:

                PRD:

                %s
                """
                .formatted(prd);
    }

    private static String buildJiraStoriesPrompt(
            String prd) {

        return """
                /no_think

                Generate Jira Stories.

                Format:

                Story Title

                Description

                Acceptance Criteria

                Priority

                PRD:

                %s
                """
                .formatted(prd);
    }

    private static String buildCustomPrompt(
            String prd,
            String action) {

        return """
            You are a Senior QA Architect.

            TASK:
            %s

            IMPORTANT RULES:

            - Return final answer only.
            - Do not think.
            - Do not explain your reasoning.
            - Do not show analysis process.
            - Do not show chain of thought.
            - Use concise bullet points.
            - Maximum 300 words.

            OUTPUT:
            Generate the requested artifact immediately.

            PRD:

            %s
            """
                .formatted(
                        action,
                        prd);
    }
}