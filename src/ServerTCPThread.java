import java.io.*;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class ServerTCPThread extends Thread{
    Socket mySocket;
    static int connectedClients = 0;
    int questionCount;
    Connection con;
    String name;

    public ServerTCPThread(Socket socket, Connection con)
    {
        super();
        mySocket = socket;
        this.con = con;
    }

    public void run()
    {
        try{

            //przesylanie do klienta
            PrintWriter pw = new PrintWriter(mySocket.getOutputStream());

            //odbieranie od klienta
            InputStreamReader in = new InputStreamReader(mySocket.getInputStream());
            BufferedReader bf = new BufferedReader(in);

            String answer;
            int punkty = 0;
            questionCount = questionsCount(con);

            pw.println("Podaj imie i nazwisko: ");
            pw.flush();

            while((answer = bf.readLine()) == null){}
            name = answer;

            for (int i = 1; i <= questionCount; i++) {

                pw.println(selectQuestion(con,i));
                pw.flush();

                Instant now = Instant.now();
                Instant now2 = now.plus(5, ChronoUnit.SECONDS);

                while((answer = bf.readLine()) == null){}

                Instant later = Instant.now();
                var duration = Duration.between(now, later);
                var MAX_RESPONSE_TIME = Duration.between(now, now2);
                String correctAnswer = selectCorrectAnswer(con,i);
                if(duration.compareTo(MAX_RESPONSE_TIME)>0){
                    pw.println("timeoutError");
                    pw.flush();
                    insertAnswer(con,name,"timeout",i);
                }else {
                    insertAnswer(con,name,answer,i);
                    if(answer.equals(correctAnswer)){
                        punkty++;
                    }
                }
                    System.out.println(answer);
            }
            pw.println("Ilosc zdobytych punktow " + punkty +"/"+ questionCount + ", klikni enter aby zakonczyc");
            pw.flush();
            while((answer = bf.readLine()) == null){}

            insertResult(con,name,punkty);

            mySocket.close();
            connectedClients--;



        }catch (Exception e){
            System.err.println(e);
        }
    }
    static public void insertAnswer(Connection con, String name, String answer, int questionNumber) throws SQLException {
        String query = "INSERT INTO odpowiedzi_uczniów (imie, odpowiedz, numerPytania) VALUES(?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setString(1,name);
        stm.setString(2,answer);
        stm.setInt(3,questionNumber);
        stm.executeUpdate();
    };

    static public void insertResult(Connection con, String name, int result) throws SQLException {
        String query = "INSERT INTO wyniki (imie, punkty) VALUES(?,?)";

        PreparedStatement stm = con.prepareStatement(query);
        stm.setString(1,name);
        stm.setInt(2,result);
        stm.executeUpdate();
    };

    static public int selectResult(Connection con, String name) throws SQLException {

        String query = "SELECT punkty FROM wyniki WHERE imie = ? ORDER BY idWyniku DESC LIMIT 1";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setString(1,name);
        ResultSet result = stm.executeQuery();
        result.next();
        return result.getInt("punkty");
    };

    static public String selectQuestion(Connection con, int questionNumber) throws SQLException {
        String query = "SELECT * FROM pytania WHERE numerPytania = ?";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setInt(1,questionNumber);
        ResultSet result = stm.executeQuery();
        result.next();
        String question = new String();
        question += result.getInt("numerPytania");
        question += ". ";
        question += result.getString("pytanie");
        question += " a)";
        question += result.getString("a");
        question += " b)";
        question += result.getString("b");
        question += " c)";
        question += result.getString("c");
        question += " d)";
        question += result.getString("d");

        return question;
    };

    static public String selectCorrectAnswer(Connection con, int questionNumber) throws SQLException {
        String query = "SELECT odpowiedz FROM poprawne_odpowiedzi WHERE numerPytania = ?";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setInt(1,questionNumber);
        ResultSet result = stm.executeQuery();
        result.next();
        String question = new String();
        question = result.getString("odpowiedz");
        return question;
    };

    static public int questionsCount (Connection con) throws SQLException {
        String query = "SELECT Count(pytanie) as number FROM pytania";
        PreparedStatement stm = con.prepareStatement(query);
        ResultSet result = stm.executeQuery();
        result.next();
        return result.getInt("number");
    };

}
