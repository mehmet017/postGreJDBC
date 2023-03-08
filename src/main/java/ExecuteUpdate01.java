import java.sql.*;

public class ExecuteUpdate01 {

    public static void main(String[] args) throws SQLException {

        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","147852");
        Statement st = con.createStatement();

        //ÖRNEK1:developers tablosunda maaşı ortalama maaştan az olanların maaşını ortalama maaş ile güncelleyiniz

        /*
        String sql = "update developers set salary=(select avg(salary) from developers)"+
                "where salary<(select avg(salary) from developers)";


        int updated=st.executeUpdate(sql);
        System.out.println("Guncellenen kayit sayisi; "+updated);


        ResultSet rs = st.executeQuery("select id,name,salary from developers");
        while (rs.next()){
            System.out.println(rs.getInt("id")+"--"+rs.getString("name")+"--"+rs.getDouble("salary"));
        }

        String sql2 ="insert into developers(name,salary,prog_lang) values('Ilker',5300,'React')";
        int inserted =st.executeUpdate(sql2);
        System.out.println(inserted);

        String sql3 = "delete from developers where id=14";
        int deleted = st.executeUpdate(sql3);
        System.out.println("Silinen kayit sayisi: "+deleted);

         */

        String sql4 = "delete from developers where prog_lang ilike 'Css'";
        int silinen1= st.executeUpdate(sql4);
        System.out.println("Silenen " + silinen1);

        st.close();
        con.close();

    }
}
