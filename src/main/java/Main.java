

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.MaxCategory;
import org.example.Statistics;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    private static int PORT = 8089;
    static String tsvFile = new String("categories.tsv");
    public static File dataFile = new File("data.bin");

    public static void main(String[] args) {

        Statistics.loadTsvFile(tsvFile);


        try (ServerSocket serverSocket = new ServerSocket(PORT);) { // стартуем сервер один(!) раз
            while (true) { // в цикле(!) принимаем подключения

                try (
                        Socket socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                ) {

                    out.println("Сервер запущен");
                    String jsonString = "";

                    while (!"q".equals(jsonString)) {
                        jsonString = in.readLine();
                        Gson gson = new Gson();
                        Statistics statistics = gson.fromJson(jsonString, Statistics.class);

                        GsonBuilder builder = new GsonBuilder();
                        gson = builder.setPrettyPrinting().create();
                        out.println(gson.toJson(statistics.max()));

                    }

                }
            }
        } catch (
                IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}