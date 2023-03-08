import java.sql.*;

public class PreparedStatement01 {
/*
PreparedStatement Statement interface'ini extend eder.
Statement ile olusturdugunuz sorgu icin DB de bellekte sorgunun bir ornegi olusturulur.
sorgu her cagirildiginda yeni bir ornegi olusturulur.
PreparedStatement Statement;birden fazla kez calistirilabilen parametrelendirilmis SQL sorgularini temsil eder.
PreparedStatement ile sorgu olusturdugunuzda bu sorgunun ornegi DB de bellekte bir kere tutulur,
sorgu her calistirildiginda ayni onceki ornegi kullanilir.
 */



    public static void main(String[] args) throws SQLException {

        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","147852");
        Statement st = con.createStatement();

        //ÖRNEK1:(Prepared Statement kullanarak) bolumler tablosunda Matematik bölümünün taban_puanı'nı 475 olarak güncelleyiniz.
        //String sql="UPDATE bolumler SET taban_puanı=475 WHERE bolum ILIKE 'matematik'";

        //prepared statement için parametreli queryi yaz
        String sql="UPDATE bolumler SET taban_puanı=? WHERE bolum ILIKE ?";
        //prepared statement oluştur
        PreparedStatement prst= con.prepareStatement(sql);
        //parametrelerin değerlerini gir
        prst.setInt(1,475);
        prst.setString(2,"matematik");
        //prepared statement ile queryi çalıştır.
        int updated=prst.executeUpdate();
        System.out.println("updated: "+updated);

        ResultSet rs =st.executeQuery("select * from bolumler ");

        while (rs.next()){
            System.out.println(rs.getInt("bolum_id")+"--"+rs.getString("bolum")+"--"+rs.getInt("taban_puanı"));
        }
        System.out.println("--------------Ornek2------------");
        //ÖRNEK2:Prepared Statement kullanarak bolumler tablosunda Edebiyat bölümünün taban_puanı'nı 455 olarak güncelleyiniz.
        prst.setInt(1,455);
        prst.setString(2,"Edebiyat");
        int updated2 = prst.executeUpdate();
        System.out.println("updated: "+updated);
        ResultSet rs2 =st.executeQuery("select * from bolumler ");

        while (rs2.next()){
            System.out.println(rs2.getInt("bolum_id")+"--"+rs2.getString("bolum")+"--"+rs2.getInt("taban_puanı"));
        }
        prst.close();
        st.close();
        con.close();
    }

}
