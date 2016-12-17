package com.example.number27.bnsurat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String buyerid = "01";
    int password = 5403;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void login(View view) {


        EditText txtbuyerid = (EditText) findViewById(R.id.etxtbuyerid);
        EditText txtpassword = (EditText) findViewById(R.id.etxtpassword);



        // TODO start connecting php
        String url = "http://www.thaicreate.com/android/checkLogin.php";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("strUser", txtbuyerid.getText().toString()));
        params.add(new BasicNameValuePair("strPass", txtpassword.getText().toString()));


        // Get result from Server (Return the JSON Code)
        String resultServer  = getHttpPost(url,params);

        // Default Value
        String strStatusID = "0";
        String strMemberID = "0";
        String strError = "Unknow Status!";

        JSONObject c;
        try {
            c = new JSONObject(resultServer);
            strStatusID = c.getString("StatusID");
            strMemberID = c.getString("MemberID");
            strError = c.getString("Error");

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }












        if (txtbuyerid.getText().toString().equals(buyerid) && txtpassword.getText().toString().equals(String.valueOf(password))) {
            Log.d("check", "สำเสร็จ");
            Toast.makeText(this, "สำเร็จ", Toast.LENGTH_LONG).show();
            Intent i = new Intent(getApplicationContext(), Bid.class);
            startActivity(i);
        } else {
            showdialogtxt();
            Toast.makeText(this, "ไม่สำเร็จ", Toast.LENGTH_LONG).show();
        }


    }

    private void showdialogtxt() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("เกิดข้อผิดพลาด");
        builder.setMessage("BuyerId หรือ Password ไม่ถูกต้อง");
        builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        builder.show();

    }




    public String getHttpPost(String url,List<NameValuePair> params) {
        StringBuilder str = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params));
            HttpResponse response = client.execute(httpPost);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) { // Status OK
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                String line;
                while ((line = reader.readLine()) != null) {
                    str.append(line);
                }
            } else {
                Log.e("Log", "Failed to download result..");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str.toString();
    }



}
