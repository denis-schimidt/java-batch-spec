package br.com.schimidtsolutions.chunks;

import java.io.ObjectOutputStream;
import java.util.List;

import javax.batch.api.chunk.AbstractItemWriter;

import br.com.schimidtsolutions.producer.ArquivoFactory;

/*CDI do Glasfish com problemas de injeção no Java Batch
@Named("SimpleItemWriter")*/
public class SimpleItemWriter extends AbstractItemWriter {

	private ObjectOutputStream writer;
	
	public SimpleItemWriter() throws Exception {
		writer = ArquivoFactory.newWriterInstance();
	}

	@Override
	public void writeItems(List<Object> items) throws Exception {

		for (Object item : items) {
			writer.writeObject( item );
		}
		
		writer.flush();
	}
	
	@Override
	public void close() throws Exception {
		writer.close();
	}
}
