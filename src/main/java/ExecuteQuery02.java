import java.sql.*;

public class ExecuteQuery02 {

    public static void main(String[] args) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","147852");
        Statement st = con.createStatement();

        System.out.println("----------Ornek1---------");
        //ÖRNEK1:bolumler tablosunda taban puanı en yüksek 2. bölümün ismini ve puanını yazdırınız.

        String sql = "select bolum,taban_puanı from bolumler order by taban_puanı desc offset 1 limit 1";
        ResultSet resultSet =st.executeQuery(sql);

        while (resultSet.next()){
            System.out.println("Bolum : "+resultSet.getString("bolum")+"--"+resultSet.getInt("taban_puanı"));

        }
        //subQuery kullanimi
        String sql1="SELECT bolum,taban_puanı FROM bolumler WHERE taban_puanı="+
                "(SELECT max(taban_puanı) FROM bolumler WHERE taban_puanı<(SELECT max(taban_puanı) FROM bolumler))";

        //ÖRNEK2:Bölüm isimlerini, kampüslerini ve
        //her bölümde okuyan öğrencilerin en yüksek puanlarını listeleyiniz.
        String sql2= "select bolum,kampus,(select max(puan) from ogrenciler o where o.bolum=b.bolum) max_puan FROM bolumler b";
        ResultSet rs2=st.executeQuery(sql2);

        while (rs2.next()){
            System.out.println(rs2.getString("bolum")+"--"+rs2.getString("kampus")+"--"+rs2.getInt("max_puan"));
        }



        st.close();
        con.close();





    }



}
