package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import data.IUserDAO.DALException;



public class Connector
{
	/**
	 * To connect to a MySQL-server
	 * 
	 * @param url must have the form
	 * "jdbc:mysql://<server>/<database>" for default port (3306)
	 * OR
	 * "jdbc:mysql://<server>:<port>/<database>" for specific port
	 * more formally "jdbc:subprotocol:subname"
	 * 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SQLException 
	 */
	
	/**
	 * Connects to a given database, on a Mysql object called Connection, that is, the return type is of "Connection"
	 */
	public static Connection connectToDatabase(String url, String username, String password)
			throws InstantiationException, IllegalAccessException,
					ClassNotFoundException, SQLException
	{
		// call the driver class' no argument constructor
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		
		// get Connection-object via DriverManager
        return (Connection) DriverManager.getConnection(url, username, password);
	}
	
	//Variables for use in Connector constructor
	private static Connection conn;
	private static Statement stm;
	
	/**
	 * Tries to connect to a given database. if so desired, 
	 * otherwize called by the no arguments constructor. (in this class file)
	 * @param server - ex. localhost
	 * @param port - ex 3306
	 * @param database - ex localDBGrp22
	 * @param username - ex root
	 * @param password - ex ""
	 */
	public Connector(String server, int port, String database,
			String username, String password)
				throws InstantiationException, IllegalAccessException,
					ClassNotFoundException, SQLException
	{	
		executeScpDB("database_create.sql"); //calls function that automatically creates a database and two users
		//a bit redundant in terms of code optimization. but due to current code structure, doing a seperate call
		
		conn	= connectToDatabase("jdbc:mysql://"+server+":"+port+"/"+database,
					username, password);
		stm		= conn.createStatement();
	}
	
	/**
	 * Executes a Sql script.
	 */
	public void executeScpDB(String scriptFilePath) throws SQLException
	{
		//in our case, a database creation and user creation so the db isn't empty.
		try 
		{
			BufferedReader bfReader = new BufferedReader(new FileReader(scriptFilePath));
			String str;
			StringBuffer strB = new StringBuffer();
			while ((str = bfReader.readLine()) != null) {
				strB.append(str + "\n ");
			}
			bfReader.close();
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/?user=root&password=");
			Statement s = conn.createStatement();
			s.executeUpdate(strB.toString());
			conn.close();
		} 
		catch (Exception e) {
			System.err.println("Failed to Execute" + scriptFilePath +". The error is"+ e.getMessage());
		} 
	}
	
	/**
	 * calls the class constructor that actually creates the database Connection and executes a table.
	 */
	public Connector() throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, SQLException
	{
		this(Constant.server, Constant.port, Constant.database,
				Constant.username, Constant.password); 
		//calls its own constructor that takes the same parameters, in other words:
		// the other constructor in this class.
	}
	
	/**
	 * executes a SQL command like Select
	 * doesn't execute update, insert, delete or create, use doUpdate instead.
	 */
	public static ResultSet doQuery(String cmd) throws data.IUserDAO.DALException
	{
		try { return stm.executeQuery(cmd); }
		catch (SQLException e) { throw new DALException(e.getMessage(),e); 
		}
	}
	
	/**
	 * executes a SQL command like update, insert, create and delete.
	 * doesn't execute select.
	 */
	public static int doUpdate(String cmd) throws DALException
	{
		try { return stm.executeUpdate(cmd); }
		catch (SQLException e) { throw new DALException(e.getMessage(),e);
		}
	}
}