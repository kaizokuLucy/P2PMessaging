package com.example.lucy.p2pmessaging.Models;

import java.io.Serializable;

/**
 * Created by Tomislav on 1/13/2018.
 */

public class Contact implements Serializable {

    public String first_name;
    public String last_name;
    public String number;
    public String status;

    public Contact(String first_name, String last_name, String number, String status){
        this.first_name = first_name;
        this.last_name = last_name;
        this.number = number;
        this.status = status;
    }

    public Contact(){
        super();
    }
}
