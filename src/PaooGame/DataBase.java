package PaooGame;

import PaooGame.Levels.Score;

import java.awt.*;
import java.sql.*;

/*! \class public class DataBase
    \brief Implementeaza baza de date a jocului : aici se stocheaza scorul cumulat de jucator.
 */
public class DataBase {

    private static DataBase                         db;                             /*!< Instanta clasei singleton.*/
    private static Connection                       connection = null;              /*!< Asigura conectiunea cu baza de date.*/
    private static Statement                        stmt = null;                    /*!< Oobiect folosit pentru a executa instrucțiuni SQL.*/
    private final int                               score;                          /*!< Variabila in care se stocheaza scorul.*/
    private final Font font = new Font("Times new Roman", Font.BOLD, 50);


    private DataBase() {

        this.score = Score.GetInstance().GetScore();

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:PaooGame-BomberMan.db");
            stmt = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS SCORE_TABLE " +
                         "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                         "VALOARE INT NOT NULL UNIQUE)";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            System.out.println("DataBase Constructor error!!! :(");
        } catch (ClassNotFoundException f) {
            System.out.println("DataBase Constructor error112234!!! :(");
        }
    }

    /*! \fn public static DataBase GetInstance()
   \brief Metoda statica responsabila de intoarcerea referintei catre instanta obiectului.
   */
    public static DataBase GetInstance() {
        if (db == null)
            db = new DataBase();
        return db;
    }

    /*! \fn public static void RemoveDataBase()
       \brief Metoda statica responsabila de stergerea referintei catre instanta obiectului.
       */
    public static void RemoveDataBase() {
        db = null;
    }


    /*! \fn public void UpdateDataBase()
       \brief Metoda responsabila de actualizarea bazei de date.
       */
    public void UpdateDatabase() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:PaooGame-BomberMan.db");
            int newScore = Score.GetInstance().GetScore();

            stmt = connection.createStatement();
            String sql = "INSERT OR IGNORE INTO SCORE_TABLE (VALOARE) VALUES (" + newScore + ");";
            stmt.executeUpdate(sql);
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("UpdateDatabase error!! :(");
            System.out.println(e.getMessage());
        }

        //highestScore();
    }


    /*! \fn private void highestScore()
           \brief Metoda responsabila de actualizarea celor mai mari 3 scoruri.
    */
    private void highestScore() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:PaooGame-BomberMan.db");
            stmt = connection.createStatement();

            stmt.executeUpdate("DELETE FROM SCORE_TABLE WHERE VALOARE < " + score + ";");

            stmt.close();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("DataBase KeepHighestScore error!!! :(");
            System.out.println(e.getMessage());
        }
    }

    /*! \fn public void Draw(Graphics g)
           \brief Metoda responsabila de desenarea celor mai mari 3 scoruri pe ecran.
    */
    public void Draw(Graphics g) {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:PaooGame-BomberMan.db");
            stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM SCORE_TABLE ORDER BY VALOARE DESC;");

            int i = 0;
            g.setFont(font);
            g.setColor(Color.BLACK);
            while (rs.next() && i < 3) {
                int v = rs.getInt("VALOARE");
                g.drawString(i + 1 + ". " + v, 540, 340 + (i++) * 70);
            }

            stmt.close();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("DataBase Draw error!!! :(");
            System.out.println(e.getMessage());
        }
    }
}
