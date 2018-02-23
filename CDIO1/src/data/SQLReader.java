package data;


import java.io.BufferedReader;  
 
import java.io.File;  
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
 
/*
 * ATTENTION: SQL file must not contain column names, etc. including comment signs (#, --, /* etc.)
 *          like e.g. a.'#rows' etc. because every characters after # or -- in a line are filtered 
 *          out of the query string the same is true for every characters surrounded by /* and */
/**/
 
public class SQLReader
{ 
    private static ArrayList<String> listOfQueries = null;
    
    /*
     * @param   path    Path to the SQL file
     * @return          List of query strings 
     */
    public ArrayList<String> createQueries(String path)  
    { 
        String queryLine =      new String();
        StringBuffer sBuffer =  new StringBuffer();
        listOfQueries =         new ArrayList<String>();
         
        try 
        {  
            FileReader fr =     new FileReader(new File(path));       
            BufferedReader br = new BufferedReader(fr);  
       
            //read the SQL file line by line
            while((queryLine = br.readLine()) != null)  
            {  
                // ignore comments beginning with #
                int indexOfCommentSign = queryLine.indexOf('#');
                if(indexOfCommentSign != -1)
                {
                    if(queryLine.startsWith("#"))
                    {
                        queryLine = new String("");
                    }
                    else
                        queryLine = new String(queryLine.substring(0, indexOfCommentSign-1));
                }
                // ignore comments beginning with --
                indexOfCommentSign = queryLine.indexOf("--");
                if(indexOfCommentSign != -1)
                {
                    if(queryLine.startsWith("--"))
                    {
                        queryLine = new String("");
                    }
                    else
                        queryLine = new String(queryLine.substring(0, indexOfCommentSign-1));
                }
                // ignore comments surrounded by /* */
                indexOfCommentSign = queryLine.indexOf("/*");
                if(indexOfCommentSign != -1)
                {
                    if(queryLine.startsWith("#"))
                    {
                        queryLine = new String("");
                    }
                    else
                        queryLine = new String(queryLine.substring(0, indexOfCommentSign-1));
                     
                    sBuffer.append(queryLine + " "); 
                    // ignore all characters within the comment
                    do
                    {
                        queryLine = br.readLine();
                    }
                    while(queryLine != null && !queryLine.contains("*/"));
                    indexOfCommentSign = queryLine.indexOf("*/");
                    if(indexOfCommentSign != -1)
                    {
                        if(queryLine.endsWith("*/"))
                        {
                            queryLine = new String("");
                        }
                        else
                            queryLine = new String(queryLine.substring(indexOfCommentSign+2, queryLine.length()-1));
                    }
                }
                 
                //  the + " " is necessary, because otherwise the content before and after a line break are concatenated
                // like e.g. a.xyz FROM becomes a.xyzFROM otherwise and can not be executed 
                if(queryLine != null)
                    sBuffer.append(queryLine + " ");  
            }  
            br.close();
             
            // here is our splitter ! We use ";" as a delimiter for each request 
            String[] splittedQueries = sBuffer.toString().split(";");
             
            // filter out empty statements
            for(int i = 0; i<splittedQueries.length; i++)  
            {
                if(!splittedQueries[i].trim().equals("") && !splittedQueries[i].trim().equals("\t"))  
                {
                    listOfQueries.add(new String(splittedQueries[i]));
                }
            }
        }  
        catch(Exception e)  
        {  
            System.out.println("*** Error : "+e.toString());  
            System.out.println("*** ");  
            System.out.println("*** Error : ");  
            e.printStackTrace();  
            System.out.println("################################################");  
            System.out.println(sBuffer.toString());  
        }
        return listOfQueries;
    } 
    
    public void tester(Statement s)
    {
    	ArrayList<String> queries_01;
    	String pathname_01;
    	 
    	//... get queries from sql file .................
    	        pathname_01 = "../database_create.sql";
    	        queries_01 = createQueries(pathname_01);
    	             
    	        PreparedStatement pps = null;
    	        Statement s2 = null;
    	        ArrayList<ResultSet> RSList_01 = new ArrayList<ResultSet>();
    	        for(String query: queries_01)
    	        {
    	            try {
						RSList_01.add(s2.executeQuery(query));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    	        }
    	         
    	        try {
    	            ResultSet tmpRS = RSList_01.get(0);
    	            if(!tmpRS.isClosed())
    	            {
    	                if(tmpRS.next())
    	                    System.out.println("Query 1: id = " + tmpRS.getInt("id"));
    	            }
    	             
    	        } catch (SQLException e) {
    	            // TODO Auto-generated catch block
    	            e.printStackTrace();
    	        }
    }
}

/**
SQLReader st = new SQLReader();
Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/?user=root&password=");
Statement s = conn.createStatement();
st.tester(s);
*/