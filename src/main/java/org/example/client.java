package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class client {

    private static String host = "Localhost";
    private static int port = 8089;

    public static void main(String[] args) {
        try (Socket clientSocket = new Socket(host, port);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ) {
            String result = in.readLine();
            System.out.println(result);


            out.println("{\"title\": \"булка\", \"date\": \"2022.02.08\", \"sum\": 200}");
            out.println("{\"title\": \"курица\", \"date\": \"2022.02.08\", \"sum\": 200}");
            out.println("{\"title\": \",ferrari\", \"date\": \"2022.02.08\", \"sum\": 1000}");
            out.println("q");

            String response;
            while ((response = in.readLine()) != null) {
                System.out.println(response);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

