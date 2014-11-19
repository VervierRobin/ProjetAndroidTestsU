package Tests;

import java.sql.Connection;

import myconnections.DBConnection;
import Modele.CategorieDB;
import Modele.CommunauteDB;
import Modele.UtilisateurDB;

public class TestsCategorie {

	public void testCat() {
	DBConnection dbc = new DBConnection();
    Connection con = dbc.getConnection();
     if (con == null) {
         System.err.println("connexion impossible");
         System.exit(0);
     }
     UtilisateurDB.setConnection(con);
     UtilisateurDB adm = new UtilisateurDB("Dupont", "Jules","065123456","DJule","tests");
     UtilisateurDB adm2 = new UtilisateurDB("Dupont2", "Jules2","0655656","DJule2","tests2");
     
     CommunauteDB.setConnection(con);
     CommunauteDB comm1 = new CommunauteDB("Java", "Java",adm);
     CommunauteDB comm2 = new CommunauteDB("Java2", "Java2",adm2);
     CommunauteDB comm3 = new CommunauteDB("Java3", "Java3",adm);
     
     
     
     CategorieDB.setConnection(con);
     CategorieDB cat1 = null, cat2 = null , cat3 = null;  
     
     try{
     	uti.create();
     	uti.read();
     	uti2.create();
     	uti2.read();
     	
     }
     catch(Exception e){
     	   }
      
     
     /**
      * Test d'ajout d'une communaute
      */
     try {
         System.out.println("\n> test ajout"); 
         comm1 = new CommunauteDB("Java", "Java",uti);
         comm1.create();
         int numComm = comm1.getIdCommunaute();
         comm2 = new CommunauteDB(numComm);
         comm2.read();
         System.out.println("OK Communaut� : \n" + comm2+"\nEnregistr�");
     } catch (Exception e) {
         System.err.println("BAD exception d'ajout : \n" + e);
     }
     
     try { 
     	
     	comm1.delete(); 
     			        	
      	} 
     catch (Exception e) {
     }
     
     /**
      * Test d'ajout d'une communaut� (nom de la communaut�)
      */
     try {
         System.out.println("\n> test ajout (nom de la communaut� diff�rent)");
         comm1 = new CommunauteDB("Java", "Java",uti);
         comm1.create();
         comm2 = new CommunauteDB("Java2", "Java",uti);
         comm2.create();
         comm1.read();
         comm2.read();
         System.out.println("OK \n Communaut� 1: \n" + comm1+"\nEnregistr�");
         System.out.println("Communaut� 2: \n" + comm2+"\nEnregistr�");
     } catch (Exception e) {
         System.err.println("BAD exception d'ajout : \n" + e);
     }
     try {
     	comm1.delete(); 
     	comm2.delete();
     } 
     catch (Exception e) {
     }
     
    /**
     * Test de doublon
     */
     try {
         System.out.println("\n> test doublon");
         comm1 = new CommunauteDB("Java", "Java",uti);
         comm1.create();
         comm2 = new CommunauteDB("Java", "Java",uti);
         comm2.create();
         System.err.println("BAD Communaut� avec nom identique ");
     } 
     catch (Exception e) {
         System.out.println("OK exception normale de doublon : " + e);
     }
     try {  
     	comm1.delete(); 
 	    comm2.delete();} 
     catch (Exception e) {
     }
     /**
      * Test effacement d'une communaut�
      */
     try {
         System.out.println("\n> test d'effacement communaut�");
         comm1 = new CommunauteDB("Java", "Java",uti);
         comm1.create();
         int numcomm = comm1.getIdCommunaute();
         comm1.delete(); 
         comm2 = new CommunauteDB(numcomm);
         comm2.read();
         System.err.println("BAD : Utilisateur pas effac� : \n" + comm2);

     } catch (Exception e) {
         System.out.println("OK exception normale d'effacement (Erreur de lecture apr�s l'effacement) : \n" + e);
     }
     try { 
     	comm1.delete(); 
     	comm2.delete();} 
     catch (Exception e) {
     }

     
     /**
      * Test de mise � jour sc�nario nominal
      */
     try {
         System.out.println("\n> test mise � jour fructueux");
         comm1 = new CommunauteDB("Java", "Java",uti);
         comm1.create();
         int numcom = comm1.getIdCommunaute();
         comm1.setNomCommunaute("nomCommunaute");
         comm1.setPassword("password");
         comm1.update();
         comm2 = new CommunauteDB(numcom);
         comm2.read();
         System.out.println("u2=" + comm2);
         comm1.delete();
         System.out.println("OK");
     } 
     catch (Exception e) {
         System.err.println("BAD exception de mise � jour :\n" + e);
     }
     
     /**
      * Test de mise � jour (Nouveau nom existant)
      */
     try {
     	comm3 = new CommunauteDB("Java2", "Java",uti);
     	comm3.create();
         System.out.println("\n> test mise � jour infructueux (Nouveau nom existant)");
         comm1 = new CommunauteDB("Java", "Java",uti);
         comm1.create();
         int numcom = comm1.getIdCommunaute();
         comm1.setNomCommunaute("Java2");
         comm1.setPassword("password");
         comm1.update();
         
         comm2 = new CommunauteDB(numcom);
         comm2.read();
         System.out.println("u2=" + comm2);
         comm1.delete();
         System.out.println("BAD Communaaute avec nom identique");
     } 
     catch (Exception e) {
     	System.out.println("OK exception normale de doublon : " + e);
     }
     
     try {
     	comm1.delete(); 
     	comm2.delete();
     	comm3.delete();
     	} 
     catch (Exception e) {
     }
     
           


     try {
     	uti.delete();
     	uti2.delete();
         con.close();
     } catch (Exception e) {
     }
 }

}
