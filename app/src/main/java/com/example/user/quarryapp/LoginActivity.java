package com.example.user.quarryapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.user.quarryapp.Network.AppController;
import com.example.user.quarryapp.Network.CustomRequest;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.user.quarryapp.Network.URLs.Links;


public class LoginActivity extends AppCompatActivity {
    EditText username,password;
    String username1,password1;
    ProgressDialog pDialog;
    public static final String MYSHAREDPREFS="MyPrefs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences editorget=getSharedPreferences(MYSHAREDPREFS, MODE_PRIVATE);
        String user_id= editorget.getString("user_id",null);

        if (user_id=="null")
        {
//            Intent i = new Intent(getApplicationContext(), MainActivity.class);
//            startActivity(i);
//            finish();
        }

        username= (EditText) findViewById(R.id.username);
        password= (EditText) findViewById(R.id.password);  

        Button login= (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AdminMainActivity.class);
                startActivity(i);
               // attempLogin();
            }
        });

       // LinearLayout register= (LinearLayout) findViewById(linearRegister);

//        register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                 Intent i=new Intent(getApplicationContext(),RegisterActivity.class);
////                startActivity(i);
//            }
//        });
}

    public void attempLogin(){
        username1 = username.getText().toString();
        password1 = password.getText().toString();
        //Check for empty Edit Text fields
        //Check Phone Number
        if (TextUtils.isEmpty(username1)) {
            username.setError("Enter username");
            return;
        }
        if (TextUtils.isEmpty(password1)) {
            password.setError("Enter password");
            return;
        }
        login();
//        if(AppStatus.getInstance(getApplicationContext()).isOnline()){
//
////            login();
//        }else{
////            new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE)
////                    .setTitleText("Oops...")
////                    .setContentText("No Internet! Enable Mobile Data or WiFi")
////                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
////                        @Override
////                        public void onClick(SweetAlertDialog sDialog) {
////                            sDialog.dismissWithAnimation();
////                        }
////                    })
////                    .show();
//            Toast.makeText(this, "No Internet! Enable Mobile Data or WiFi", Toast.LENGTH_SHORT).show();
//
//        }

    }

    public void login(){

         pDialog = new ProgressDialog(LoginActivity.this);
        pDialog.setMessage("Signing In...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        Map<String, String> params = new HashMap<String, String>();
        params.put("username", username1);
        params.put("password", password1);

        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Links+"/login", params,
                new Response.Listener<JSONObject>() {
            int success;
            String suc;

            @Override
            public void onResponse(JSONObject response) {
                pDialog.dismiss();
                //google.setVisibility(View.INVISIBLE);
                try {
                    success = response.getInt("id");
                        if (success >= 1) {
                            Log.d("Login Successful!", response.toString());
                            final String id = response.getString("id");
                            final String firstname = response.getString("firstname");
                            final String email = response.getString("email");

//                            Toast.makeText(LoginActivity.this, "WELCOME " + firstname, Toast.LENGTH_SHORT).show();
//                                final String lastname = response.getString("last_name");

                            // Creating user login session
                            // For testing i am stroing name, email as follow
                            // Use user real data
//                            session.createLoginSession(firstname, email, id);
//
//                            String firstn=getSharedPreferences(email,0);

                            SharedPreferences.Editor editor=getSharedPreferences(MYSHAREDPREFS, MODE_PRIVATE).edit();
                            editor.putString("user_id",id);
                            editor.putString("firstname",firstname);
                            editor.putString("email",email);
                            editor.commit();

                            Toast.makeText(LoginActivity.this, "WELCOME " + firstname, Toast.LENGTH_SHORT).show();

                            // Staring MainActivity
//                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
//                            startActivity(i);
//                            finish();

                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this, "Credentials Doesnt Match", Toast.LENGTH_SHORT).show();
                        }



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError response) {
                pDialog.dismiss();
              //  google.setVisibility(View.INVISIBLE);
                Log.d("Response: ", response.toString());
//                new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE)
//                        .setTitleText("OOPS")
//                        .setContentText("INCORRECT USERNAME OR PASSWORD")
//                        .show();
                  Toast.makeText(LoginActivity.this, "NETWORK ERROR", Toast.LENGTH_SHORT).show();

            }
        });
        AppController.getInstance().addToRequestQueue(jsObjRequest);

    }
    @Override
    public void onBackPressed() {
    }
}
