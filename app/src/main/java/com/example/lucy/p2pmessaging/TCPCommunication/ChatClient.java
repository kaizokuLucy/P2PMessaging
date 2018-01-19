package com.example.lucy.p2pmessaging.TCPCommunication;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Created by lucy on 1/18/18.
 */

public class ChatClient {
    ServerSocket serverSocket;
    Socket clientSocket;
    String line;
    String clientIP;
    int clientPort;
    BufferedReader inFromClient;
    DataOutputStream outToClient;

    public ChatClient(String IP, int port) {
        clientIP = IP;
        clientPort = port;
    }

    public void run() {
        try {
            clientSocket = new Socket(clientIP, clientPort);
//            inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            outToClient = new DataOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            Log.e("RUN EXC", e.getMessage());
        }
    }

    public void sendMessage(String msg) throws IOException {
        outToClient.writeBytes(msg);
        outToClient.flush();
        Log.d("MSG", "successfully sent");
    }
}
