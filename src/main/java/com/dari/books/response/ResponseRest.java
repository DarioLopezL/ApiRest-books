package com.dari.books.response;

import java.util.*;

public class ResponseRest {

	private ArrayList<HashMap<String, String>> metadata = new ArrayList<>();

	public ArrayList<HashMap<String, String>> getMetadata() {
		
		return metadata;
	}
	
	public void setMetadata(String tipo, String codigo, String date) {
		
		HashMap<String , String> mapa = new HashMap<String, String>();
		mapa.put("tipo", tipo);
		mapa.put("codigo",codigo );
		mapa.put("dato", date);
		
		metadata.add(mapa);
	}
}
