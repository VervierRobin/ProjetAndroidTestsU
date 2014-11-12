package Tests;

import java.sql.Connection;


import myconnections.DBConnection;
import Modele.UtilisateurDB;

public class TestsUtilisateur {

	public void testUti() {
		   DBConnection dbc = new DBConnection();
	        Connection con = dbc.getConnection();
	        if (con == null) {
	            System.err.println("connexion impossible");
	            System.exit(0);
	        }
	        UtilisateurDB.setConnection(con);
	        UtilisateurDB u1 = null, u2 = null;
	        
	        /**
	         * Test d'ajout d'un utilisateur
	         */
	        try {
	            System.out.println("\n> test ajout"); 
	            u1 = new UtilisateurDB("Dupont", "Jules","065123456","DJule","tests");
	            u1.create();
	            int numUtilisateur = u1.getIdUtilisateur();
	            u2 = new UtilisateurDB(numUtilisateur);
	            u2.read();
	            System.out.println("OK Utilisateur : \n" + u2+"\nEnregistré");
	        } catch (Exception e) {
	            System.err.println("BAD exception d'ajout : \n" + e);
	        }
	        try { u1.delete(); } 
	        catch (Exception e) {
	        }
	        /**
	         * Test d'ajout d'un utilisateur (pseudo différente)
	         */
	        try {
	            System.out.println("\n> test ajout (localité différente)");
	            u1 = new UtilisateurDB("Dupont", "Jules","065123456","DJule","tests");
	            u1.create();
	            u2 = new UtilisateurDB("Dupont", "Jules","065123456","DJules","tests");
	            u2.create();
	            u1.read();
	            u2.read();
	            System.out.println("OK \n Utilisateur 1: \n" + u1+"\nEnregistré");
	            System.out.println("Utilisateur 2: \n" + u2+"\nEnregistré");
	        } catch (Exception e) {
	            System.err.println("BAD exception d'ajout : \n" + e);
	        }
	        try { u1.delete(); u2.delete();} 
	        catch (Exception e) {
	        }
	        
	       /**
	        * Test de doublon
	        */
	        try {
	            System.out.println("\n> test doublon");
	            u1 = new UtilisateurDB("Dupont", "Jules","065123456","DJule","tests");
	            u1.create();
	            u2 = new UtilisateurDB("Dupont", "Jules","065123456","DJule","tests");
	            u2.create();
	            System.err.println("BAD utilisateur entré 2x ");
	        } 
	        catch (Exception e) {
	            System.out.println("OK exception normale de doublon : " + e);
	        }
	        try {  u1.delete(); } 
	        catch (Exception e) {
	        }
	        /**
	         * Test effacement d'un utilisateur 
	         */
	        try {
	            System.out.println("\n> test d'effacement utilisateur");
	            u1 = new UtilisateurDB("Dupont", "Jules","065123456","DJule","tests");
	            u1.create();
	            int numcli = u1.getIdUtilisateur();
	            u1.delete(); 
	            u2 = new UtilisateurDB(numcli);
	            u2.read();
	            System.err.println("BAD : Utilisateur pas effacé : \n" + u2);

	        } catch (Exception e) {
	            System.out.println("OK exception normale d'effacement (Erreur de lecture après l'effacement) : \n" + e);
	        }
	        try { u1.delete(); } 
	        catch (Exception e) {
	        }

	        
	        /**
	         * Test de mise à jour
	         */
	        try {
	            System.out.println("\n> test mise à jour fructueux");
	            u1 = new UtilisateurDB("Dupont", "Jules","065123456","DJule","tests");
	            u1.create();
	            int numcli = u1.getIdUtilisateur();
	            u1.setNom("nouvnom");
	            u1.setPrenom("nouvprenom");
	            u1.setTelephone("nouvtel");
	            u1.setPseudonyme("nouvpseudo");
	            u1.setPassword("nouvpassword");
	            u1.update();
	            u2 = new UtilisateurDB(numcli);
	            u2.read();
	            System.out.println("u2=" + u2);
	            u1.delete();
	            System.out.println("OK");
	        } 
	        catch (Exception e) {
	            System.err.println("BAD exception de mise à jour :\n" + e);
	        }
	        
	        try { u1.delete(); } 
	        catch (Exception e) {
	        }
	        
	       


	        try {
	            con.close();
	        } catch (Exception e) {
	        }
	    }

}
