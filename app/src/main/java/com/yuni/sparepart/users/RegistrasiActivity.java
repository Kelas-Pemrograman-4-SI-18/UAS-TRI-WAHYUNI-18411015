package com.yuni.sparepart.users;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ornach.nobobutton.NoboButton;
import com.yuni.sparepart.R;
import com.yuni.sparepart.server.BaseURL;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class RegistrasiActivity extends AppCompatActivity {

    Button btnBackLogin;
    NoboButton btnRegistrasi;
    EditText edtuserName, edtnamaLengkap, edtemail, edtnoTelepon, edtpassword;
    ProgressDialog pDialog;

    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        getSupportActionBar().hide();

        mRequestQueue = Volley.newRequestQueue(this);

        edtuserName = (EditText) findViewById(R.id.edtuserName);
        edtnamaLengkap = (EditText) findViewById(R.id.edtnamaLengkap);
        edtemail = (EditText) findViewById(R.id.edtemail);
        edtnoTelepon = (EditText) findViewById(R.id.edtnoTelepon);
        edtpassword = (EditText) findViewById(R.id.edtpassword);

        btnBackLogin = (Button) findViewById(R.id.btnBackLogin);
        btnRegistrasi = (NoboButton) findViewById(R.id.btnRegistrasi);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        btnBackLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegistrasiActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnRegistrasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String struserName = edtuserName.getText().toString();
                String strnamaLengkap = edtnamaLengkap.getText().toString();
                String stremail = edtemail.getText().toString();
                String strnoTelepon = edtnoTelepon.getText().toString();
                String strpassword = edtpassword.getText().toString();

                if (struserName.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "UserName Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                } else if (strnamaLengkap.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Nama Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                } else if (stremail.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Email Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                } else if (strnoTelepon.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "NoTelepon Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                } else if (strpassword.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Password Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                }else {
                    registrasi(struserName, strnamaLengkap, stremail, strnoTelepon, strpassword);
                }
            }
        });
    }

    public void registrasi (String userName, String namaLengkap, String email, String noTelepon, String password){

        // Post params to be sent to the server
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("userName", userName);
        params.put("namaLengkap", namaLengkap);
        params.put("email", email);
        params.put("noTelepon", noTelepon);
        params.put("role", "2");
        params.put("password", password);

        pDialog.setMessage("Mohon Tunggu.....");
        showDialog();

        JsonObjectRequest req = new JsonObjectRequest(BaseURL.registrasi, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        try {
                           String strMsg = response.getString("msg");
                           boolean status = response.getBoolean("error");
                           if(status == false) {
                               Toast.makeText(getApplicationContext(), strMsg, Toast.LENGTH_LONG).show();
                               Intent i = new Intent(RegistrasiActivity.this, LoginActivity.class);
                               startActivity(i);
                               finish();
                           }else {
                               Toast.makeText(getApplicationContext(), strMsg, Toast.LENGTH_LONG).show();
                           }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                hideDialog();
            }
        });
        mRequestQueue.add(req);

    }

    private void showDialog(){
        if(!pDialog.isShowing()){
            pDialog.show();
        }
    }

    private void hideDialog(){
        if(pDialog.isShowing()){
            pDialog.dismiss();
        }
    }
}