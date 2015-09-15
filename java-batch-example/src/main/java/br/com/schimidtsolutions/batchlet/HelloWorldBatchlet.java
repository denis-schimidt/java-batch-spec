package br.com.schimidtsolutions.batchlet;

import javax.batch.api.Batchlet;
import javax.batch.runtime.context.JobContext;
import javax.batch.runtime.context.StepContext;
import javax.inject.Inject;

public class HelloWorldBatchlet implements Batchlet {
	
	 @Inject 
	 private JobContext jobContext;
	 
	 @Inject 
	 private StepContext stepContext;

	@Override
	public String process() throws Exception {
		long executionId = jobContext.getExecutionId();
		
		if( isEven( executionId ) )   {
            throw new Exception( "Foda-se números ímpares!" );
        }
		
		System.out.println( stepContext.getStepExecutionId() );
		
        return "SUCCESS";
	}
	
	private boolean isEven( long num ) {
        return ( num % 2 == 0 );
    }

	@Override
	public void stop() throws Exception {
		System.out.println( "Parando o batch..." );
	}
}
