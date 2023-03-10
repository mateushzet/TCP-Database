import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;

public class ServerTCP {
    public static void main(String args[]) throws SQLException {

        String url = "jdbc:mariadb://localhost:3306/kolokwium";
        String user = "root";
        String pwd = "password";
        Connection con = DriverManager.getConnection(url,user,pwd);
        String[] tempArgs = new String[1];
        tempArgs[0] = "4999";
        final int MAX_CLIENT_CAPACITY = 250;
        boolean quit = false;
        if (tempArgs.length == 0)
            System.out.println("Wprowadz numer portu, na ktorym" + "serwer bpdzie oczekiwai na klientOw");
        else {
            int port = 0;
            try {
                port = Integer.parseInt(tempArgs[0]);
            }catch (NumberFormatException e) {
                System.err.println("Wprowadz poprawny numer portu: " + e);
                return;
            }

            try(ServerSocket serverSocket = new ServerSocket(port)) {
                int i = 0;
                while (!quit) {
                    if(ServerTCPThread.connectedClients < MAX_CLIENT_CAPACITY) {
                        ServerTCPThread thread = new ServerTCPThread(serverSocket.accept(),con);
                        i++;
                        ServerTCPThread.connectedClients++;
                        System.out.println("polaczono klienta nr: " + i);
                        thread.start();
                    }else System.out.println("Wszystkie miejsca zajete");
                }
                serverSocket.close();

            }catch (Exception e)
            { System.err.println(e); }
        }
    }


    static public void initTables(Connection con) throws SQLException {
//        String tables [] = new String[4];
//        tables[0] = "Create table odpowiedzi_uczniów (idOdpowiedzi INT PRIMARY KEY AUTO_INCREMENT, imie varchar(30) NOT NULL, odpowiedz varchar(30), numerPytania INT(2) NOT NULL)";
//        tables[1] = "Create table pytania (numerPytania INT(2) PRIMARY KEY, pytanie varchar(100) NOT NULL, a varchar(100) NOT NULL, b varchar(100) NOT NULL, c varchar(100) NOT NULL, d varchar(100) NOT NULL)";
//        tables[2] = "Create table poprawne_odpowiedzi (numerPytania INT(2) PRIMARY KEY, odpowiedz varchar(30) NOT NULL)";
//        tables[3] = "Create table wyniki (idWyniku INT PRIMARY KEY AUTO_INCREMENT, imie varchar(30) NOT NULL, punkty INT(2) NOT NULL)";
//
        Statement stm = con.createStatement();
//
//        for (int i = 0; i < 4; i++) {
//            stm.executeUpdate(tables[i]);
//        }

        String rows [] = new String[4];

        rows[0] = "INSERT INTO pytania VALUES(1, 'Gdzie leży Warszawa?', 'małopolska', 'wielkopolska', 'mazowieckie', 'Podlaskie' )";
        rows[1] = "INSERT INTO pytania VALUES(2, 'Kto wymyślił prawo newtona', 'Orbit', 'Newton', 'Einstein', 'Płaneta')";
        rows[2] = "INSERT INTO pytania VALUES(3, 'Kto spada na cztery łapy', 'pies', 'Zięcina', 'felis catus', 'lew')";
        rows[3] = "INSERT INTO pytania VALUES(4, 'Na ile oceniasz tą aplikacje','5/5', '4/5', '3/5', '0/5 (wybierasz na wlasna odpowiedzialnosc)')";

        for (int i = 0; i < 4; i++) {
            stm.executeUpdate(rows[i]);
        }

        rows[0] = "INSERT INTO poprawne_odpowiedzi VALUES(1, 'c')";
        rows[1] = "INSERT INTO poprawne_odpowiedzi VALUES(2, 'b')";
        rows[2] = "INSERT INTO poprawne_odpowiedzi VALUES(3, 'c')";
        rows[3] = "INSERT INTO poprawne_odpowiedzi VALUES(4, 'a')";

        for (int i = 0; i < 4; i++) {
            stm.executeUpdate(rows[i]);
        }

    }
}