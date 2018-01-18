package com.example.lucy.p2pmessaging.TCPCommunication;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by lucy on 1/18/18.
 */

public class ChatServer implements Runnable {
    ServerSocket serverSocket;
    Socket client;
    String line;
    BufferedReader inFromClient;
    DataOutputStream outToClient;

    public ChatServer(int port) {
        try {
            new ServerSocket(port);
        } catch (IOException e) {
            Log.d("CONSTRUCTOR EXC", e.getMessage());
        }
    }

    public void run() {
        try {
            client = serverSocket.accept();
            inFromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
//            outToClient = new DataOutputStream(client.getOutputStream());
        } catch (IOException e) {
            Log.e("RUN EXC", e.getMessage());
        }

        while (true) {
            try {
                while ((line = inFromClient.readLine()) != null) {

                }
            } catch (Exception e) {

            }
        }
    }

}
