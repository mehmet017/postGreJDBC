package StudenManagementSystem;

import java.sql.*;
//4-DB ile iletisimde olan class:connection,statement, prepared statement
public class StudentRepository {

    private Connection conn;
    private Statement st;
    private PreparedStatement prst;

    //5-connection icin bir method olustur
    private void getConnection(){
        try {
            this.conn= DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","147852");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }



    }
    //6-statement olusturmak icin method olustur
    private void getStatement(){
        try {
            this.st=conn.createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    //7-prepared statement
    private void getPreparedStatement(String sql){
        try {
            this.prst=conn.prepareStatement(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //8-tablo olusturma
    public void createTable(){
        getConnection();
        getStatement();
        try {
            st.execute("CREATE TABLE İF NOT EXISTS t_student(id SERIAL, name VARCHAR(50), lastname VARCHAR(50), city VARCHAR(20), age int)");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                st.close();
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }



}
