package br.com.schimidtsolutions.service;

import br.com.schimidtsolutions.annotations.Batchlet;

@Batchlet
public class BatchletExecutionService extends AbstractBatchExecutionService{

	@Override
	String getNomeXmlBatch() {
		return "batchlet-job";
	}
}
