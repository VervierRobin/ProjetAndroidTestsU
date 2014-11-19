package Tests;

import java.sql.Connection;

import myconnections.DBConnection;
import Modele.CategorieDB;
import Modele.CommunauteDB;
import Modele.MessageDB;
import Modele.UtilisateurDB;

public class TestsMessage {

	public void testMsg() {
		DBConnection dbc = new DBConnection();
		Connection con = dbc.getConnection();
		if ( con == null ) {
			System.err.println("Connection impossible");
			System.exit(0);
		}
		
		MessageDB.setConnection(con);
		UtilisateurDB.setConnection(con);
		CommunauteDB.setConnection(con);
		CategorieDB.setConnection(con);
		
		UtilisateurDB admin1 = new UtilisateurDB("Lefevre","Julien","02585415","juju","007");
		UtilisateurDB admin2 = new UtilisateurDB("Vervier","Robin","0254854115","Roro","51854");
		
		UtilisateurDB user1 = new UtilisateurDB("Lefevre","Celine","1854854","Cece","147852");
		UtilisateurDB user2 = new UtilisateurDB("Dochez","Anthony","1541854","Antho","81962");
		
		CommunauteDB commu1 = null, commu2 = null;
		CategorieDB cat1 = null, cat2 = null, cat3 = null, cat4 = null;
		MessageDB msg1 = null, msg2 = null, msg3 = null, msg4 = null;
		
		try
		{	admin1.create();
			admin1.read();
			
			admin2.create();
			admin2.read();
			
			user1.create();
			user1.read();
			
			user2.create();
			user2.read();
			
			commu1 = new CommunauteDB("Java","Java",admin1);
			commu2 = new CommunauteDB("Java2","Java2",admin2);
			
			commu1.create();
			commu1.read();
			
			commu2.create();
			commu2.read();
			
			cat1 = new CategorieDB("Rendez-vous",commu1);
			cat2 = new CategorieDB("Retard",commu1);
			
			cat3 = new CategorieDB("Rendez-vous",commu2);
			cat4 = new CategorieDB("Fête",commu2);
			
			cat1.create();
			cat1.read();
			
			cat2.create();
			cat2.read();
			
			cat3.create();
			cat3.read();
			
			cat4.create();
			cat4.read();
		}
		catch ( Exception e ) {	
			
		}
		
		/**
         * Test d'ajout d'un message
        */
        
		try 
        {   System.out.println("\n> Test d'ajout"); 
            msg1 = new MessageDB("blablabla $$ blabla $$",cat1);
            msg1.create();
            msg2 = new MessageDB(msg1.getIdMessage());
            msg2.read();
            System.out.println("OK Message : \n" + msg2+"\nEnregistré");
        } 
		catch (Exception e) {
            System.err.println("BAD Exception d'ajout : \n" + e);
        }
        try 
        {	msg1.delete();
        	msg2.delete();
        } 
        catch (Exception e) {
        	
        }
		
		
		
        try
        { admin1.delete();
          admin2.delete();
          user1.delete();
          user2.delete();
          commu1.delete();
          commu2.delete();
          cat1.delete();
          cat2.delete();
          cat3.delete();
          cat4.delete();
          con.close();
        }
        catch ( Exception e ) {
        
        }
		
	}

}
