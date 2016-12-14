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
}
