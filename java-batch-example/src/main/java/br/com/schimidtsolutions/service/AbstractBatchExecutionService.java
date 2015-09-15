package br.com.schimidtsolutions.service;

import java.util.Properties;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.JobExecution;

public abstract class AbstractBatchExecutionService {
	
	public long submitJob() {
        JobOperator jobOperator = BatchRuntime.getJobOperator();
        Properties jobProperties = new Properties();
        long executionId = jobOperator.start( getNomeXmlBatch(), jobProperties);
        
        return executionId;
    }
 
    abstract String getNomeXmlBatch();

	public JobExecution getJobExecutionDetails( long executionId ) {
        JobOperator jobOperator = BatchRuntime.getJobOperator();
        JobExecution jobExecution = jobOperator.getJobExecution( executionId );
        
        return jobExecution;
    }
 
    public long restartJob( long executionId ) {
        Properties jobProperties = new Properties();
        
        long newExecutionId = BatchRuntime.getJobOperator().restart(
        		executionId, jobProperties );
        
        return newExecutionId;
    }
}
