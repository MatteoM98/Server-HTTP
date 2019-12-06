package it.uniupo.matteo.magri.http;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class EchoGetHandler implements HttpHandler {

	//Variabili
	private String valNome;
	private String valCognome;
	private String valMatricola;
	private String valNomeCorso;
	private int valAnno;
	
	@Override
	public void handle(HttpExchange he) throws IOException {
		Map<String,Object> parameters = new HashMap<String,Object>();
		URI requestedUri = he.getRequestURI();
		String query = requestedUri.getRawQuery();
		parseQuery(query,parameters);
		String response = "";
		
		//leggo i parametri passati dalla query e setto le variabili interne
		for(String key: parameters.keySet()) {
			if(key.equals("nome")) valNome = parameters.get(key).toString();
			if(key.equals("cognome")) valCognome = parameters.get(key).toString();
			if(key.equals("matricola")) valMatricola = parameters.get(key).toString();
			if(key.equals("corso")) valNomeCorso = parameters.get(key).toString();
			if(key.equals("anno")) valAnno = Integer.parseInt(parameters.get(key).toString());
		}
		
		//leggo la lista degli studenti che ho memorizzato nel mio DB
		List<Studente> studenti = ReadJSON.readJSON();
		
		//per ogni studente controllo se soddisfa i requisiti di ricerca
		for(Studente s : studenti) {
			//ricerca per matricola
			if(s.getMatricola().equals(valMatricola)) response += s.toString();
			//ricerca per nome e cognome
			else if (s.getNome().equals(valNome) && s.getCognome().equals(valCognome)) response += s.toString();
			//ricerca per corso di studi
			else if (s.getNomeCorso().equals(valNomeCorso)) response += s.toString();
			//ricerca per anno di studi
			else if (s.getAnno() == valAnno) response += s.toString();
		}
		
		he.sendResponseHeaders(200,response.length());
		OutputStream os = he.getResponseBody();
		os.write(response.toString().getBytes());
		os.close();
		
	}
	
	private static void parseQuery(String query, Map<String, Object> parameters) throws UnsupportedEncodingException {

        if (query != null) {
                String pairs[] = query.split("[&]");
                for (String pair : pairs) {
                         String param[] = pair.split("[=]");
                         String key = null;
                         String value = null;
                         if (param.length > 0) {
                         key = URLDecoder.decode(param[0], 
                         	System.getProperty("file.encoding"));
                         }

                         if (param.length > 1) {
                                  value = URLDecoder.decode(param[1], 
                                  System.getProperty("file.encoding"));
                         }

                         if (parameters.containsKey(key)) {
                                  Object obj = parameters.get(key);
                                  if (obj instanceof List<?>) {
                                           @SuppressWarnings("unchecked")
											List<String> values = (List<String>) obj;
                                           values.add(value);

                                  } else if (obj instanceof String) {
                                           List<String> values = new ArrayList<String>();
                                           values.add((String) obj);
                                           values.add(value);
                                           parameters.put(key, values);
                                  }
                         } else {
                                  parameters.put(key, value);
                         }
                }
        }
	}

}


