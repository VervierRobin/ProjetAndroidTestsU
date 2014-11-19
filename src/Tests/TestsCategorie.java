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
			System.err.println("Connection impossible");
			System.exit(0);
		}

		UtilisateurDB.setConnection(con);
		CommunauteDB.setConnection(con);
		CategorieDB.setConnection(con);

		UtilisateurDB admin1 = new UtilisateurDB("Lefevre", "Julien",
				"02585415", "juju", "007");
		UtilisateurDB admin2 = new UtilisateurDB("Vervier", "Robin",
				"0254854115", "Roro", "51854");

		UtilisateurDB user1 = new UtilisateurDB("Lebleu", "Marie", "1854854",
				"LM", "147852");
		UtilisateurDB user2 = new UtilisateurDB("Lerouge", "Th�r�se",
				"025487989", "Thethe", "475869");

		CommunauteDB commu1 = null, commu2 = null;
		CategorieDB cat1 = null, cat2 = null, cat3 = null;

		try {
			admin1.create();
			admin1.read();

			admin2.create();
			admin2.read();

			user1.create();
			user1.read();

			user2.create();
			user2.read();

			commu1 = new CommunauteDB("Java", "Java", admin1);
			commu2 = new CommunauteDB("Java2", "Java2", admin2);

			commu1.create();
			commu1.read();

			commu2.create();
			commu2.read();

		} catch (Exception e) {

		}

		/**
		 * Test d'ajout d'une cat�gorie
		 */

		try {
			System.out.println("\n> Test d'ajout");
			cat1 = new CategorieDB("Rendez-vous", commu1);
			cat1.create();
			int idCat = cat1.getIdCategorie();
			cat2 = new CategorieDB(idCat);
			cat2.read();
			System.out.println("OK Message : \n" + cat2 + "\nEnregistr�");
		} catch (Exception e) {
			System.err.println("BAD Exception d'ajout : \n" + e);
		}
		try {
			cat1.delete();

		} catch (Exception e) {

		}

		/**
		 * Test d'ajout d'une communaut� (plusieurs communaute m�me nom
		 * categorie)
		 */
		try {
			System.out
					.println("\n> test ajout (plusieurs communaute m�me nom categorie)");
			cat1 = new CategorieDB("Rendez-vous", commu1);
			cat1.create();
			cat2 = new CategorieDB("Rendez-vous", commu2);
			cat2.create();
			cat1.read();
			cat2.read();
			System.out.println("OK \n Cat�gorie 1: \n" + cat1 + "\nEnregistr�");
			System.out.println("Cat�gorie 2: \n" + cat2 + "\nEnregistr�");
		} catch (Exception e) {
			System.err.println("BAD exception d'ajout : \n" + e);
		}
		try {
			cat1.delete();
			cat2.delete();
		} catch (Exception e) {
		}

		/**
		 * Test de doublon
		 */
		try {
			System.out.println("\n> test doublon");
			cat1 = new CategorieDB("Rendez-vous", commu1);
			cat1.create();
			cat2 = new CategorieDB("Rendez-vous", commu1);
			cat2.create();
			System.err
					.println("BAD Cat�gorie avec nom identique dans une m�me communaut� ");
		} catch (Exception e) {
			System.out.println("OK exception normale de doublon : " + e);
		}
		try {
			cat1.delete();
			cat2.delete();
		} catch (Exception e) {
		}
		
		/**
         * Test effacement d'une cat�gorie
         */
        try {
            System.out.println("\n> test d'effacement cat�gorie");
            cat1 = new CategorieDB("Rendez-vous", commu1);
            cat1.create();
            int numcat = cat1.getIdCategorie();
            cat1.delete(); 
            cat2 = new CategorieDB(numcat);
            cat2.read();
            System.err.println("BAD : Cat�gorie pas effac�e : \n" + cat2);

        } catch (Exception e) {
            System.out.println("OK exception normale d'effacement (Erreur de lecture apr�s l'effacement) : \n" + e);
        }
        try { 
        	cat1.delete(); 
        	cat2.delete();} 
        catch (Exception e) {
        }
		
		
        /**
         * Test de mise � jour sc�nario nominal
         */
        try {
            System.out.println("\n> test mise � jour fructueux");
            cat1 = new CategorieDB("Rendez-vous", commu1);
            cat1.create();
            cat1.read();
            int numcat = cat1.getIdCategorie();
            cat1.setNomCategorie("nomCategorie");
            cat1.update();
            cat2 = new CategorieDB(numcat);
            cat2.read();
            System.out.println("Cat2=" + cat2);
            cat1.delete();
            System.out.println("OK");
        } 
        catch (Exception e) {
            System.err.println("BAD exception de mise � jour :\n" + e);
        }
        
        try { 
        	cat1.delete(); 
        	cat2.delete();} 
        catch (Exception e) {
        }
        
        
        /**
         * Test de mise � jour (Nouveau nom existant dans m�me communaut�)
         */
        try {
        	cat3 = new CategorieDB("Rendez-vous", commu1);
        	cat3.create();
            System.out.println("\n> test mise � jour infructueux (Nouveau nom existant m�me communaut�)");
            cat1 = new CategorieDB("F�te", commu1);
            cat1.create();
            cat1.read();
            int numcat = cat1.getIdCategorie();
            cat1.setNomCategorie("Rendez-vous");
            cat1.update();
            
            cat2 = new CategorieDB(numcat);
            cat2.read();
            System.out.println("cat2=" + cat2);
            cat1.delete();
            System.out.println("BAD Cat�gorie avec nom identique");
        } 
        catch (Exception e) {
        	System.out.println("OK exception normale de doublon : " + e);
        }
        
         try { 
        	cat1.delete(); 
        	cat2.delete();
        	cat3.delete();} 
        catch (Exception e) {
        }
        
         /**
          * Test de mise � jour (Nouveau nom existant dans communaut� dif�rente)
          */
         try {
        	 System.out.println("\n> test mise � jour infructueux (Nouveau nom existant communaut� diff�rent)");
             cat3 = new CategorieDB("Rendez-vous", commu2);
             cat3.create();
             cat1 = new CategorieDB("F�te", commu1);
             cat1.create();
             cat1.read();
             int numcat = cat1.getIdCategorie();
             cat1.setNomCategorie("Rendez-vous");
             cat1.update();
             
             cat2 = new CategorieDB(numcat);
             cat2.read();
             System.out.println("OK cat 2=" + cat2);
             cat1.delete();
            } 
         catch (Exception e) {
             System.err.println("BAD exception de mise � jour :\n" + e);
         }
         
         try { 
         	cat1.delete(); 
         	cat2.delete();} 
         catch (Exception e) {
         }
        
		try {
			admin1.delete();
			admin2.delete();
			user1.delete();
			user2.delete();
			commu1.delete();
			commu2.delete();
			con.close();
		} catch (Exception e) {

		}
	}

}
