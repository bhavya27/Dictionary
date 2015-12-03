import java.sql.*;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import java.util.regex.*;
public class Database {
    static final String JDBC_DRIVER="com.mysql.jdbc.Driver";
    static final String DB_URL="jdbc:mysql://localhost/dictionary";
    static final String USER="root";
    static final String PASS="";
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        String define="";
        String sql="";
        String word;
        String category;
    public String main(String word,String category){
        this.word=word;
        this.category=category;
        try{
        Class.forName(JDBC_DRIVER);
        conn=DriverManager.getConnection(DB_URL,USER,PASS);
        stmt=conn.createStatement();
        sql="select value from "+category+" where word='"+word+"'";
        rs=stmt.executeQuery(sql);
        rs.next();
        define=rs.getString("value");
        rs.close();
        stmt.close();
        conn.close();
        }catch(SQLException e){            
            define=new fetch().get(word,category);
            if(!define.contains("Ergh."))
           enterIntoDatabase(define);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            
            try{
                if(stmt!=null){
                    stmt.close();
                }
            }catch(SQLException e){e.printStackTrace();}
            try{
                if(conn!=null)
                    conn.close();                
                System.out.println("Closed");
            }catch(SQLException e){e.printStackTrace();}
            
        }
        return define; 
    }
    public void enterIntoDatabase(String definition){
        Pattern pat=Pattern.compile("--.*.com");        
        Matcher mat=pat.matcher(definition);
        String s=mat.replaceAll("--fetched from Database");
        pat=Pattern.compile("'");
        mat=pat.matcher(s);
        s=mat.replaceAll("''");
        sql="insert into "+category+" values('"+word+"','"+s+"');";
        try{
        stmt.execute(sql);
        System.out.println("saved into database");
        }catch(com.mysql.jdbc.MysqlDataTruncation e){
           StringBuilder strBuil=new StringBuilder();
           strBuil.append(s.substring(0,9900));
           strBuil.append("\n--Powered by Dictionary.com"); 
           enterIntoDatabase(strBuil.toString());           
        }catch(SQLException e){
            System.out.println("Problem entering data into database");    
        }catch(NullPointerException e){
            System.out.println("Problem Entering data into database.Please check if database is loaded or not !");
        }    
    }
}
