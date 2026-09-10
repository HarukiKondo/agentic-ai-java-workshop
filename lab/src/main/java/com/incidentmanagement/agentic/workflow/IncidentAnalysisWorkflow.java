package com.incidentmanagement.agentic.workflow;

import com.incidentmanagement.agentic.agents.IncidentAnalysisAgent;
import com.incidentmanagement.model.IncidentInfo;
import com.incidentmanagement.model.IncidentAnalysisResults;
import com.incidentmanagement.model.AnalysisTask;
import dev.langchain4j.agentic.declarative.Output;
import dev.langchain4j.agentic.declarative.ParallelMapperAgent;
import dev.langchain4j.agentic.scope.AgenticScope;

import java.util.List;

public interface IncidentAnalysisWorkflow {

    @ParallelMapperAgent(
        description = "Analyzes incident reports in parallel for severity, impact, and resolution needs",
        outputKey = "incidentAnalysisResults",
        subAgent = IncidentAnalysisAgent.class,
        itemsProvider = "tasks")
    IncidentAnalysisResults analyzeIncident(List<AnalysisTask> tasks,
                                            IncidentInfo incidentInfo,
                                            Integer incidentNumber,
                                            String report);

    @Output
    static IncidentAnalysisResults output(AgenticScope scope,
                                        List<String> incidentAnalysisResults) {
        return new IncidentAnalysisResults(
                incidentAnalysisResults.get(0),  // severityAnalysis
                incidentAnalysisResults.get(1),  // impactAnalysis
                incidentAnalysisResults.get(2)   // resolutionAnalysis
        );
    }

}
