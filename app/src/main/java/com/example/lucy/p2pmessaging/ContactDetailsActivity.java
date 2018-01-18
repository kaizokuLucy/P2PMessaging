package com.example.lucy.p2pmessaging;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.lucy.p2pmessaging.Models.Contact;
import com.example.lucy.p2pmessaging.Networking.AppSingleton;
import com.example.lucy.p2pmessaging.R;

import java.util.HashMap;
import java.util.Map;

public class ContactDetailsActivity extends AppCompatActivity {

    TextView nameSurname;
    TextView number;
    Contact contact;
    private String userNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        nameSurname = (TextView)findViewById(R.id.imePrezime);
        number = (TextView)findViewById(R.id.brojKontakta);

        contact = (Contact) getIntent().getSerializableExtra("Contact");
        userNumber = (String) getIntent().getSerializableExtra("UserNumber");

        nameSurname.setText(contact.first_name + " " + contact.last_name);
        number.setText(contact.number);
    }

    public void addFriend(View view){
        final ProgressDialog dialog = ProgressDialog.show(ContactDetailsActivity.this, "", "Adding to friend list...", true);

        String url ="http://p2pmessenger.azurewebsites.net/api/users/addfriend";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        dialog.dismiss();
                        Toast.makeText(ContactDetailsActivity.this, "Friend is added to list", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();
                Toast.makeText(ContactDetailsActivity.this, "Error" + error.networkResponse.toString(), Toast.LENGTH_LONG).show();

            }

        }) {

            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                //number of current user on this device
                params.put("user_number", userNumber);
                //friend number
                params.put("friend_number", contact.number);
                return params;
            }


        };
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest, "add friend");
       // finish();
    }

    public void startConversation(View view) {

        if(!contact.status.equals("1")) {
            Toast.makeText(ContactDetailsActivity.this, contact.first_name + " is not online", Toast.LENGTH_LONG).show();
            finish();
            //Intent intent = new Intent(ContactDetailsActivity.this, HomeActivity.class);
            //startActivity(intent);
        } else {
            Intent intent = new Intent(ContactDetailsActivity.this, ChatActivity.class);
            startActivity(intent);
        }

    }
}
