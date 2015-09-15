package br.com.schimidtsolutions.service;

import br.com.schimidtsolutions.annotations.Chunk;

@Chunk
public class ChunkExecutionService extends AbstractBatchExecutionService{

	@Override
	String getNomeXmlBatch() {
		return "chunk-job";
	}
}
