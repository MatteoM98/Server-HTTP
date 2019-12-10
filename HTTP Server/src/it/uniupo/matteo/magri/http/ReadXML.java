package it.uniupo.matteo.magri.http;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class ReadXML {
	
	//Variabili
	private static final String PATH = "src/file/myDatabaseXML";
	private static String nome;
	private static String cognome;
	private static String matricola;
	private static String anno;
	private static String nomeCorso;


	public static ArrayList<Studente> readXML() {
		ArrayList<Studente> studenti = new ArrayList<Studente>();
		
		 try {
	         File inputFile = new File(PATH);
	         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.parse(inputFile);
	         doc.getDocumentElement().normalize();
	         System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
	         NodeList nList = doc.getElementsByTagName("studente");
	         System.out.println("----------------------------");
	         
	         for (int temp = 0; temp < nList.getLength(); temp++) {
	            Node nNode = nList.item(temp);
	            System.out.println("\nCurrent Element :" + nNode.getNodeName());
	            
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	               Element eElement = (Element) nNode;
	               anno = eElement.getElementsByTagName("anno").item(0).getTextContent();
	              /* System.out.println("Anno in corso : " 
	                  + eElement
	                  .getElementsByTagName("anno")
	                  .item(0)
	                  .getTextContent());*/
	               cognome = eElement.getElementsByTagName("cognome").item(0).getTextContent();
	              /* System.out.println("Cognome : " 
	                  + eElement
	                  .getElementsByTagName("cognome")
	                  .item(0)
	                  .getTextContent());*/
	               matricola = eElement.getElementsByTagName("matricola").item(0).getTextContent();
	              /* System.out.println("Matricola : " 
	                  + eElement
	                  .getElementsByTagName("matricola")
	                  .item(0)
	                  .getTextContent());*/
	               nome = eElement.getElementsByTagName("nome").item(0).getTextContent();
	              /* System.out.println("Nome : " 
	                  + eElement
	                  .getElementsByTagName("nome")
	                  .item(0)
	                  .getTextContent());*/
	               nomeCorso = eElement.getElementsByTagName("nomeCorso").item(0).getTextContent();
	               /*System.out.println("Corso : " 
	 	                  + eElement
	 	                  .getElementsByTagName("nomeCorso")
	 	                  .item(0)
	 	                  .getTextContent());*/
	               Studente s = new Studente(nome,cognome,matricola,nomeCorso,Integer.parseInt(anno));
	               studenti.add(s);
	            }
	         }
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
		 return studenti;
	}
}
