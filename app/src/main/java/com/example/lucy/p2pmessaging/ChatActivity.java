package com.example.lucy.p2pmessaging;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.lucy.p2pmessaging.Models.ChatMessage;
import com.example.lucy.p2pmessaging.Models.Contact;
import com.example.lucy.p2pmessaging.Models.MessageAdapter;

import java.util.List;

public class ChatActivity extends AppCompatActivity {


    private ListView messaginListView;
    private View sendButton;
    private EditText messageText;
    private List<ChatMessage> messagesList;
    private ArrayAdapter<ChatMessage> adapter;

    Contact contact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);


        contact = (Contact) getIntent().getSerializableExtra("Contact");
        setTitle(contact.first_name);

        messaginListView = (ListView) findViewById(R.id.messagingListView);
        sendButton = findViewById(R.id.sendButton);
        messageText = (EditText) findViewById(R.id.messageText);

        // set adapter for the list view
        adapter = new MessageAdapter(this, R.layout.left, messagesList);
        messaginListView.setAdapter(adapter);

    }

}
