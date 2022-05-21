import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
public class sqLite {
	
	public void createDatabase(String dbName) {     //METHOD TO CREATE NEW DATABASE
		   
        String url = "jdbc:sqlite:/C:\\sqlite3\\db\\" + dbName;       //URL of database
   
        try {  
            Connection connect = DriverManager.getConnection(url);  
            if (connect != null) {    
                System.out.println("A new database has been created.");  
            }  
        } catch (SQLException e) {  
            System.out.println(e);  
        }  
    } 
	
	
	private Connection connect(){           //METHOD TO CONNECT TO A DATABASE
		String jdbcUrl= "jdbc:sqlite:/C:\\sqlite3\\db\\dbProject.db";
		Connection connection=null;
		try {
			connection= DriverManager.getConnection(jdbcUrl);
		} catch (SQLException e) {
			System.out.println("Error connecting to database");
			e.printStackTrace();
		}
		return connection;
	}
	
	public void createTable(){      //METHOD TO CREATE A TABLE IN DATABASE
		String jdbcUrl= "jdbc:sqlite:/C:\\sqlite3\\db\\dbProject.db";  
         
        String sql = "CREATE TABLE IF NOT EXISTS MOVIES (NAME VARCHAR(30),ACTOR VARCHAR(20),ACTRESS VARCHAR(20),DIRECTOR VARCHAR(20),YEAR_OF_RELEASE NUMBER(4));";
              //QUERY TO CREATE A TABLE NAMES 'MOVIES' IN DATABASE 
        try{  
            Connection conn = DriverManager.getConnection(jdbcUrl);  
            Statement stmt = conn.createStatement();  
            stmt.execute(sql); 
            System.out.println("\nTable has been created.");
        } catch (SQLException e) {  
            System.out.println(e);  
        }  
	}
	
	public void insert(String name, String actor,String actress,String director,int date) {  
		                // METHOD TO INSERT VALUES TO THE DATABASE 
        String sql = "INSERT INTO MOVIES (NAME,ACTOR, ACTRESS, DIRECTOR, YEAR_OF_RELEASE) VALUES(?,?,?,?,?)";  
                        //QUERY TO INSERT VALUES TO THE DATABASE 
        try{  
            Connection conn = this.connect();  
            PreparedStatement pstmt = conn.prepareStatement(sql);  
            pstmt.setString(1, name);  
            pstmt.setString(2, actor);
            pstmt.setString(3, actress);
            pstmt.setString(4, director);
            pstmt.setInt(5, date);
            pstmt.executeUpdate();  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    } 
	
	
	public void selectAll(){   //METHOD TO DISPLAY ALL THE ROWS OF THE DATABASE
		System.out.println("\nDisplaying all rows of database:\n");
        String sql = "SELECT rowid,* FROM MOVIES";             //QUERY TO DISPLAY ALL THE ROWS OF THE DATABASE
        try {  
            Connection conn = this.connect();  
            java.sql.Statement stmt = conn.createStatement();  
            ResultSet res    = stmt.executeQuery(sql);  
            System.out.println("NAME \t\tACTOR \t\tACTRESS \t\tDIRECTOR \t\tYEAR_OF_RELEASE") ;
            while (res.next()) {  
                System.out.println(res.getString("name") + "\t" +
                                   res.getString("actor") + "\t" +
                                   res.getString("actress") + "\t" +
                                   res.getString("director") + "\t" +
                                   res.getInt("year_of_release"));  
            }  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    }  
	
	public void selectQuery(){           //METHOD TO DISPLAY MOVIE NAME BASED ON THE ACTOR'S NAME
		System.out.println("\nQuery to select movie name from the database where actor's name is Joseph vijay:");
		String sql = "SELECT name FROM MOVIES WHERE ACTOR= 'Joseph vijay' ";
		try {  
            Connection connect = this.connect();  
            java.sql.Statement stmt = connect.createStatement(); 
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) { 
                System.out.println(res.getString("name"));
            }
		} catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }
		
		System.out.println("\nQuery to select movie name from the database where actor's name is Leonardo DiCaprio:");
		String sql1 = "SELECT name FROM MOVIES WHERE ACTOR= 'Leonardo DiCaprio' ";
		try {  
            Connection connect = this.connect();  
            java.sql.Statement stmt = connect.createStatement(); 
            ResultSet res = stmt.executeQuery(sql1);
            while (res.next()) { 
                System.out.println(res.getString("name"));
            }
		} catch (SQLException e) {  
            System.out.println(e.getMessage());  
        } 
	}
	

	public static void main(String[] args) {
		
		sqLite dataBase= new sqLite();
		dataBase.createDatabase("SqliteDb");
		dataBase.connect();
		dataBase.createTable();
		dataBase.insert("Chennai Express","Shan Rukh Khan","Deepika","Rohit Shetty",2013);
		dataBase.insert("Interstellar","Matthew McConaughey","Anne Hathaway","Christopher Nolan",2014);
		dataBase.insert("Hridayam","Marion Cotillard","Kalyani Priyadarshan","Vineeth Sreenivasan",2022);
		dataBase.insert("June","Sarjano Khalid","Rajisha Vijayan","Ahammed Khabeer",2019);
		dataBase.insert("Master","Joseph vijay","Malavika Mohanan","Lokesh Kanagaraj",2021);
		dataBase.insert("Lucy","Morgan Freeman","Scarlett Johansson","Luc Besson",2014);
		dataBase.insert("Thor","Chris Hemsworth","Natalie Portman","Kenneth Branagh",2011);
		dataBase.insert("Sanam Teri Kasam","Harshvardhan Rane","Mawra Hocane","Radhika Rao",2016);
		dataBase.insert("Inception","Leonardo DiCaprio","Marion Cotillard","Christopher Nolan",2010);
		dataBase.insert("Titanic","Leonardo DiCaprio","Kate Winslet","James Cameron",1997);
		dataBase.selectAll();
		dataBase.selectQuery();

	}

}
