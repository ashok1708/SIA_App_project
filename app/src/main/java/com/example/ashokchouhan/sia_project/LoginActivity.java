package com.example.ashokchouhan.sia_project;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ashokchouhan.sia_project.Common.Common;
import com.example.ashokchouhan.sia_project.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class LoginActivity extends AppCompatActivity {

    EditText edtTicketNumber,edtName,edtDestination;
    Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtTicketNumber=(EditText)findViewById(R.id.user_tkt);
        edtName=(EditText)findViewById(R.id.user_name);
        edtDestination=(EditText)findViewById(R.id.user_desti);
        btnLogin=(Button)findViewById(R.id.submit_button);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("user");

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog mDialog= new ProgressDialog(LoginActivity.this);
                mDialog.setMessage("Hold On...");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {


                        if(dataSnapshot.child(edtTicketNumber.getText().toString()).exists())
                        {
                            mDialog.dismiss();
                            User user = dataSnapshot.child(edtTicketNumber.getText().toString()).getValue(User.class);
                            if (user.getTicket_Number().equals(edtTicketNumber.getText().toString())&&
                                    user.getName().equals(edtName.getText().toString())&&
                                     user.getDestination().equals(edtDestination.getText().toString()))
                            {
                                Intent homeIntent = new Intent(LoginActivity.this,MainActivity.class);
                                Common.currentUsder = user;
                                startActivity(homeIntent);
                                finish();

                            } else {
                                Toast.makeText(LoginActivity.this, "Ohh Sorry Sign in Failed!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        else
                        {
                            mDialog.dismiss();
                            Toast.makeText(LoginActivity.this,"Wrong Information!",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

    }

}
