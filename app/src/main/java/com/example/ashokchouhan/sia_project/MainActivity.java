package com.example.ashokchouhan.sia_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {



   private EditText tikcet_number,name,desti;
    private Button buttn;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tikcet_number= (EditText)findViewById(R.id.user_tkt);
        name= (EditText)findViewById(R.id.user_name);
        desti=(EditText)findViewById(R.id.user_desti);



    }
}
