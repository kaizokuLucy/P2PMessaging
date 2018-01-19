package com.example.lucy.p2pmessaging.TCPCommunication;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.lucy.p2pmessaging.Models.ChatMessage;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

/**
 * Created by lucy on 1/18/18.
 */

public class ChatServer implements Runnable {
    private ServerSocket serverSocket;
    private Socket client;
    private String line;
    private BufferedReader inFromClient;
    private List<ChatMessage> messageList;
    private DataOutputStream outToClient;
    private ArrayAdapter<ChatMessage> adapter;

    public ChatServer(int port, List<ChatMessage> messageList, ArrayAdapter<ChatMessage> adapter) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            Log.d("CONSTRUCTOR EXC", e.getMessage());
        }
        this.messageList = messageList;
        this.adapter = adapter;
    }


    public void run() {
        try {
            client = serverSocket.accept();
            inFromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
//            outToClient = new DataOutputStream(client.getOutputStream());
        } catch (IOException e) {
            Log.e("RUN EXC", e.getMessage());
        }

        while (client.isConnected()) {
            try {
                while ((line = inFromClient.readLine()) != null) {
                    messageList.add(new ChatMessage(line, false));
                    adapter.notifyDataSetChanged();
                }
            } catch (Exception e) {
                Log.e("RUN ERROR", e.getMessage());
            }
        }
    }

}
