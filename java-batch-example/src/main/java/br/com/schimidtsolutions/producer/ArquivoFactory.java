package br.com.schimidtsolutions.producer;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ArquivoFactory {

	private static final String PATH_RELATIVA_ARQUIVO_LEITURA = "META-INF/carros.csv";
	
	private static final String PATH_RELATIVA_ARQUIVO_SAIDA = "META-INF/saida.ser";
	
	public static BufferedReader newReaderInstance() throws Exception{
		
		URI uriBase = getUriArquivoLocal().resolve( "../../../../".concat( PATH_RELATIVA_ARQUIVO_LEITURA ) );
		
		return Files.newBufferedReader( Paths.get( uriBase ) );
	}
	
	public static ObjectOutputStream newWriterInstance() throws Exception{
		
		URI uriBase = getUriArquivoLocal().resolve( "../../../../".concat( PATH_RELATIVA_ARQUIVO_SAIDA ) );
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream( Files.newOutputStream( Paths.get( uriBase ) ) ); 
		
		return new ObjectOutputStream( bufferedOutputStream );
	}
	
	private static URI getUriArquivoLocal() throws URISyntaxException {
		return ArquivoFactory.class.getProtectionDomain().getCodeSource()
				.getLocation().toURI();
	}
}
