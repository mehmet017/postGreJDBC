import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","147852");
        Statement st = con.createStatement();
        //System.out.println("Connection Success");



        //boolean sql1 =st.execute("CREATE TABLE workers(worker_id INT,worker_name VARCHAR(50),salary REAL)");
        //System.out.println("sql1 = " + sql1);

        //execute() methodu DDL vaye DQL icin kullanilabilir
        // eger DDL icin kullanilmissa: (tablo olusturma, tablo degistirme) geriye boolean bir deger olrak 'false' dondurur
        // eger DQL (SELECT) icin kullanilirsa: ResulSet nesnesi alirsa geriye  true dondurur aksi halde false dondurur.

        //ÖRNEK 2:"workers" tablosuna VARCHAR(20) tipinde "city" sütununu ekleyiniz.
        //String query2="ALTER TABLE workers ADD COLUMN city VARCHAR(20)";
        //st.execute(query2);

        //String query3 = "DROP TABLE worker";
        //st.execute(query3);

        st.close();
        con.close();
    }

}
