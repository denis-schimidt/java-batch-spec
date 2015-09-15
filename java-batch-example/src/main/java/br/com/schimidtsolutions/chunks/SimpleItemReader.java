package br.com.schimidtsolutions.chunks;

import java.io.BufferedReader;
import java.io.Serializable;

import javax.batch.api.chunk.AbstractItemReader;

import br.com.schimidtsolutions.producer.ArquivoFactory;

//CDI do Glasfish com problemas de injeção no Java Batch
//@Named("SimpleItemReader")
public class SimpleItemReader extends AbstractItemReader {

	//@Inject
	private BufferedReader reader;
	
	public SimpleItemReader() throws Exception {
		reader = ArquivoFactory.newReaderInstance();
	}

	@Override
	public void open(Serializable checkpoint) throws Exception {

		if (checkpoint != null) {
			Integer numeroRegistro = (Integer) checkpoint;
			System.out.println( numeroRegistro + " restartado" );
		}
	}

	@Override
	public Object readItem() throws Exception {
		return reader.readLine();
	}
	
	@Override
	public void close() throws Exception {
		super.close();
		reader.close();
	}
}
