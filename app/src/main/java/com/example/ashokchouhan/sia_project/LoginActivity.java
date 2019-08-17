package com.example.ashokchouhan.sia_project;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class LoginActivity extends AppCompatActivity {

    EditText edtTicketNumber, edtName, edtDestination;
    Button btnLogin;
    ProgressBar progressBar;

    static final String API_KEY = "kkryhwgt7a39x98nym8p8zwm";
    static final String API_URL = "https://apigw.singaporeair.com/api/v3/flightstatus/getbynumber";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtTicketNumber = (EditText) findViewById(R.id.user_tkt);
        edtName = (EditText) findViewById(R.id.user_name);
        edtDestination = (EditText) findViewById(R.id.user_desti);
        btnLogin = (Button)findViewById(R.id.submit_button);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog mDialog= new ProgressDialog(LoginActivity.this);
                mDialog.setMessage("Hold On...");
                mDialog.show();

                new Information().execute();


            }
        });

    }


    public class Information extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {


        }

        @Override
        protected String doInBackground(String... params) {

            try {
                // Creating & connection Connection with url and required Header.
                URL url = new URL(API_URL);
                HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
                urlConnection.setDoOutput(true);
                urlConnection.setRequestProperty("apikey", API_KEY);
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.setRequestProperty("X-Originating-IP", "49.36.134.32");


                urlConnection.setRequestMethod("POST");   //POST or GET
                urlConnection.connect();

                // Create JSONObject Request
                JSONObject jsonRequest = new JSONObject();
                jsonRequest.put("airlineCode", edtTicketNumber.getText().toString());
                jsonRequest.put("flightNumber", edtName.getText().toString());
                jsonRequest.put("scheduledDepartureDate", edtDestination.getText().toString());

                // Write Request to output stream to server.
                OutputStreamWriter out = new OutputStreamWriter(urlConnection.getOutputStream());
                out.write(jsonRequest.toString());
                out.close();

                // Check the connection status.
                int statusCode = urlConnection.getResponseCode();
                String statusMsg = urlConnection.getResponseMessage();


                // Connection success. Proceed to fetch the response.
                if (statusCode == 200) {


                    Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(mainIntent);

                   /* InputStream it = new BufferedInputStream(urlConnection.getInputStream());
                    InputStreamReader read = new InputStreamReader(it);
                    BufferedReader buff = new BufferedReader(read);
                    StringBuilder dta = new StringBuilder();
                    String chunks;
                    while ((chunks = buff.readLine()) != null) {
                        dta.append(chunks);
                    }
                    String returndata = dta.toString();
                    return returndata;*/


                } else {
                    Toast.makeText(LoginActivity.this, "Ohh Sorry Sign in Failed!", Toast.LENGTH_SHORT).show();
                }
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }


    }

}