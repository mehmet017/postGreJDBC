import java.sql.*;

public class ExecuteQuery01 {

    public static void main(String[] args) throws SQLException {

        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","147852");
        Statement st = con.createStatement();


        String query1 ="select country_name from countries where id between 5 and 10";
        boolean sql1=st.execute(query1);
        System.out.println("sql1 = " + sql1);

        ResultSet resultSet =st.executeQuery(query1);


        while (resultSet.next()) {
            System.out.println("Ulke ismi: "+resultSet.getString("country_name"));
        }
        System.out.println("----------------------------------------");

        String query2="select phone_code,country_name from countries where phone_code>600";
        ResultSet resultSet1 = st.executeQuery(query2);

        while (resultSet1.next()){

            System.out.println(resultSet1.getString("phone_code")+"--"+resultSet1.getString("country_name"));
        }


        System.out.println("------------ÖRNEK3-------------");

        //ÖRNEK 3:developers tablosunda "salary" değeri en düşük salary olan developerların tüm bilgilerini gösteriniz.

        String query3="SELECT * FROM developers WHERE salary=(SELECT min(salary) FROM developers)";
        ResultSet rs3=st.executeQuery(query3);

        while (rs3.next()){
            System.out.println(rs3.getInt("id")+"--"+rs3.getString("name")+"--"+
                    rs3.getDouble("salary")+"--"+rs3.getString("prog_lang"));
        }

        System.out.println("------------ÖRNEK4-------------");
        //ÖRNEK 4:Puanı bölümlerin taban puanlarının ortalamasından yüksek olan öğrencilerin isim ve puanlarını listeleyiniz.
        String query4="SELECT isim,puan FROM ogrenciler WHERE puan>(SELECT AVG(taban_puanı) FROM bolumler)";
        ResultSet rs4=st.executeQuery(query4);

        while (rs4.next()){
            System.out.println(rs4.getString("isim")+"--"+rs4.getInt("puan"));
        }
        st.close();
        con.close();






    }
}