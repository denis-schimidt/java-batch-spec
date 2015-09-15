package br.com.schimidtsolutions.producer;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;

import br.com.schimidtsolutions.annotations.Batchlet;
import br.com.schimidtsolutions.annotations.Chunk;

public class InfraProducer {

	private static final String PATH_RELATIVA_ARQUIVO_LEITURA = "src/main/resources/META-INF/carros.csv";
	
	private static final String PATH_RELATIVA_ARQUIVO_SAIDA = "src/main/resources/META-INF/saida.ser";
	
	@Batchlet
	private String nomeXmlBatchlet = "batchlet-job";
	
	@Chunk
	private String nomeXmlChunck = "chunk-job";

	@Produces
	public BufferedReader newReaderInstance() throws Exception{
		
		URI uriBase = getUriArquivoLocal().resolve( "../../".concat( PATH_RELATIVA_ARQUIVO_LEITURA ) );
		
		return Files.newBufferedReader( Paths.get( uriBase ) );
	}
	
	public void encerrarReader( @Disposes BufferedReader reader ) throws IOException{
		reader.close();
	}
	
	@Produces
	public ObjectOutputStream newWriterInstance() throws Exception{
		
		URI uriBase = getUriArquivoLocal().resolve( "../../".concat( PATH_RELATIVA_ARQUIVO_SAIDA ) );
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream( Files.newOutputStream( Paths.get( uriBase ) ) ); 
		
		return new ObjectOutputStream( bufferedOutputStream );
	}
	
	public void encerrarWriter( @Disposes ObjectOutputStream writer ) throws IOException{
		writer.flush();
		writer.close();
	}

	private URI getUriArquivoLocal() throws URISyntaxException {
		return InfraProducer.class.getProtectionDomain().getCodeSource()
				.getLocation().toURI();
	}
}
