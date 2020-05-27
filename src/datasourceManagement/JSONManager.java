package datasourceManagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import element.Reseau;

public class JSONManager {
	private static JSONManager instance;
	private String json = "";
	private String fichierJson;
	final GsonBuilder builder = new GsonBuilder();
	final Gson gson = builder.setLenient().setPrettyPrinting().create();
	
	private JSONManager()
	{
		connexion();
	}
	
	public static synchronized JSONManager getInstance()
	{
		if(instance == null)
			instance = new JSONManager();
		return instance;
	}
	
	private void connexion()
	{
		fichierJson="C:\\Users\\jimmy\\Documents\\ASI\\projetBus\\src\\view\\reseau.json";
	}
	

	public Reseau getData()
	{
		Reseau reseau;
		try{
			json = "";
			InputStream ips=new FileInputStream(fichierJson); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while((ligne=br.readLine())!=null){
				json+=ligne;
			}
			br.close(); 
		}  
		catch (Exception e){
			System.out.println(e.toString());
		}	
		
		if (json.compareTo("")==0)
		{
			// Si le fichier est vide, on crée un reseau vide vide
			reseau = new Reseau();
		}
		else
		{
			// Le fichier n'est pas vide, on utilise la bibliothèque google
			// pour remplir les objets automatiquement
			Type type = new TypeToken<Reseau>(){}.getType();
			reseau=gson.fromJson(json.toString(), type);
			//lesComptes=gson.fromJson(json, type);
		}
		
		return reseau;
	}
	
	public int setData(Reseau reseau)
	{
		int res = 0;
		Writer writer = null ;
		try {
			json=gson.toJson(reseau);
			// ouverture d'un flux de sortie sur un fichier
			// a pour effet de créer le fichier
			writer =  new FileWriter(fichierJson) ;
			writer.write(json);
			res = 0;
		}  catch (IOException e) {
			res = 1;
			System.out.println("Impossible d'écrire dans le fichier " + e.getMessage()) ;
			e.printStackTrace() ;

		}  finally {
			if (writer != null) {
				try {
					writer.close() ;
				}  catch (IOException e) {
					res = 1;
					System.out.println("Erreur " + e.getMessage()) ;
					e.printStackTrace() ;
				}
			}
		}
		return res;
	}

}

