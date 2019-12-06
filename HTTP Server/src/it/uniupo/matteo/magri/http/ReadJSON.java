package it.uniupo.matteo.magri.http;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class ReadJSON {

	//Variabili
	private static final String PATH = "src/file/myDatabase";
	static Type listType = new TypeToken<ArrayList<Studente>>(){}.getType();
	
	public static List<Studente> readJSON() {
		JsonReader reader = null;
		try {
			reader = new JsonReader(new FileReader(PATH));
		}catch (FileNotFoundException e) {
			return null;
		}
		List<Studente> listaStudenti = new Gson().fromJson(reader, listType);
		return listaStudenti;
	}
}
