package br.com.schimidtsolutions.chunks;

import java.util.Iterator;

import javax.batch.api.chunk.ItemProcessor;

import com.google.common.base.Splitter;

import br.com.schimidtsolutions.model.Carro;

/*CDI do Glasfish com problemas de injeção no Java Batch
@Named("SimpleItemProcessor")*/
public class SimpleItemProcessor implements ItemProcessor {

	@Override
	public Object processItem(Object item) throws Exception {
		System.out.println( item );
		
		Splitter pipeSpliter = Splitter.on('|').trimResults();
		Iterator<String> stringIterator = pipeSpliter.split( (String) item ).iterator();
		
		return new Carro( 
				Long.valueOf( stringIterator.next() ), 
				stringIterator.next(), 
				Integer.valueOf( stringIterator.next() ) );
	}
}
