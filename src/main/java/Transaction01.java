import java.sql.*;
/*
Transaction: Databasedeki atomik(parcalanamaz) en kucuk islemdir
Birde fazla islem icin custom olarak transaction olusturulabilir
Bu islemlerin tamami basarili bir sekilde gerceklesince  transaction commit edilir
En az birinde problem olursa rollback ile tum islemler iptal edilir
 */

public class Transaction01 {

    public static void main(String[] args) throws Exception {

        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","147852");
        Statement st = con.createStatement();

        con.setAutoCommit(false);

        try {


            //hesap no:1234 ten hesap no:5678 1000 tl para transferi olsun
            String sql = "update hesaplar set bakiye=bakiye+? where hesap_no=?";
            PreparedStatement prst = con.prepareStatement(sql);
            prst.setInt(2, 1234);
            prst.setDouble(1, -1000);
            prst.executeUpdate();

            //sistemde bir hata olustu
            if (false) {
                throw new Exception();
            }

            prst.setDouble(1, 1000);
            prst.setInt(2, 5678);
            prst.executeUpdate();
            prst.close();
            st.close();
            con.commit();
            con.close();
        }catch (Exception e){
            con.rollback();
        }
    }

}
