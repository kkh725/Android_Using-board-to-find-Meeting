package com.example.dbproject2team;



import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity { //회원가입 클래스

    Button NextBtn;
    EditText idet, pwet, Nameet, Phoneet,Birthet,Mailet,Cityet;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        setTitle("Register");

        NextBtn = (Button)findViewById(R.id.Next_btn);
        idet = (EditText)findViewById(R.id.register_id);
        pwet = (EditText)findViewById(R.id.register_pw);
        Nameet = (EditText)findViewById(R.id.register_NAME);
        Phoneet = (EditText)findViewById(R.id.register_PHONE);
        Birthet = (EditText)findViewById(R.id.register_BIRTH);
        Mailet = (EditText)findViewById(R.id.register_MAIL);
        Cityet = (EditText)findViewById(R.id.register_CITY);

        NextBtn.setOnClickListener(new View.OnClickListener() {
            Intent intent_Tags = new Intent(MainActivity.this,Select_Tag.class);

            public void onClick(View view) {
                try {
                    String result;
                    String id = idet.getText().toString();
                    String pw = pwet.getText().toString();
                    String Name = Nameet.getText().toString();
                    String Phone =  Phoneet.getText().toString();
                    String Birth = Birthet.getText().toString();
                    String Mail = Mailet.getText().toString();
                    String City = Cityet.getText().toString();

                    RegisterActivity task = new RegisterActivity();
                    result = task.execute(id, pw, Name, Phone,Birth,Mail,City).get(); // 그리고 레지스터 activity도 실행시킴. jsp에서 받아오는 정보.
                    if (result.equals("true")){
                        intent_Tags.putExtra("id",id);
                        startActivityForResult(intent_Tags,1);
                    }
                    else if (result.equals("false")){
                        Toast.makeText(MainActivity.this,"아이디가 중복되었습니다.",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(MainActivity.this,result,Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Log.i("DBtest", ".....ERROR.....!");
                }
            }
        });

    }
}
