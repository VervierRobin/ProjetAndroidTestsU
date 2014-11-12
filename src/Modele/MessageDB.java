package Modele;

//import static Modele.CommunauteDB.dbConnect;
import java.sql.CallableStatement;
import java.sql.Connection;
//import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//import android.util.Log;

public class MessageDB extends Message implements CRUD {
    
    protected static Connection dbConnect = null;
    
    public static void setConnection(Connection nouvdbConnect) {
      dbConnect = nouvdbConnect;
    }

    public MessageDB(int idMessage, String texte, Categorie categorie) {
        super(idMessage, texte, categorie);
    }
    
    public MessageDB(String texte, Categorie categorie) {
        super(texte, categorie);
    }

    public MessageDB() {
    }

    public MessageDB(int idMessage) {
        super(idMessage);
    }

    @Override
    public void create() throws Exception {
        CallableStatement cstmt = null;
       try
       {  String query = "CALL CREATE_MESSAGE(?,?,?)";
          cstmt = dbConnect.prepareCall(query);
          cstmt.registerOutParameter(1,java.sql.Types.INTEGER);
          cstmt.setString(2,this.texte);
          cstmt.setInt(3,this.categorie.getIdCategorie());
          cstmt.executeUpdate();
          this.idMessage = cstmt.getInt(1); 
       }
       catch(Exception e ) {
          throw new Exception("Erreur de création "+e.getMessage());
       }   
    }

    @Override
    public void update() throws Exception {
        CallableStatement cstmt = null;
        try
        {   String query = "CALL UPDATE_MESSAGE(?,?)";
            cstmt = dbConnect.prepareCall(query);
            cstmt.setInt(1,this.idMessage);
            cstmt.setString(2,this.texte);
            cstmt.executeUpdate();
        }
	catch(Exception e){
            throw new Exception("Erreur de mise à jour : "+e.getMessage());   
        }
    }

    @Override
    public void delete() throws Exception {
        CallableStatement cstmt = null;
        try
        {   String query = "CALL DELETE_MESSAGE(?)";
            cstmt = dbConnect.prepareCall(query);
            cstmt.setInt(1,this.idMessage);
            cstmt.executeUpdate();
        }
	catch(Exception e){
            throw new Exception("Echec de la suppression : "+e.getMessage());   
        }
    }
    
    @Override
    public void read() throws Exception {
    	String req = "SELECT * FROM MESSAGE WHERE ID_MESSAGE = ?"; 
        PreparedStatement pstmt = null;
        try	
        {	pstmt = dbConnect.prepareStatement(req);
            pstmt.setInt(1,this.idMessage);
     	    ResultSet rs = (ResultSet)pstmt.executeQuery();	
        	
     	    if(rs.next()) {
     	    	setTexte(rs.getString("TEXTEMESSAGE"));
            }
     	    else { 
     	    	throw new Exception("Code inconnu");
     	    }

        }
        catch(Exception e) {
        	System.err.println("erreur"+e);
        	//Log.d("connexion","erreur"+e);    
            throw new Exception("Erreur de lecture "+e.getMessage());
        }
        finally	{ 
        	try	{
              pstmt.close();
            }
        	catch (Exception e)	{ }
        }
    }    
    	
    	
    	/*    String req = "{? = call READ_MESSAGE(?)}";
        CallableStatement cstmt = null;   
        try 
        { cstmt = dbConnect.prepareCall(req);
          cstmt.registerOutParameter(1,oracle.jdbc.OracleTypes.CURSOR);
	  cstmt.setInt(2,this.idMessage);
	  cstmt.executeQuery();
          ResultSet rs = (ResultSet)cstmt.getObject(1);
          if(rs.next()) {
              setTexte(rs.getString("TEXTEMESSAGE"));
              //this.setCategorie(new CategorieDB(rs.getInt("CATEGORIE")));
              //((CRUD)this.categorie).read();
          }
	  else { 
	     throw new Exception("Code inconnu");
	  }
        }
	catch(Exception e){          
           throw new Exception("Erreur de lecture "+e.getMessage());
        }
    */
}