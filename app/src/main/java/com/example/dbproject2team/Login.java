package com.example.dbproject2team;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class Login extends AppCompatActivity {

    String id, pw;
    ArrayList <TextView> list = new ArrayList<TextView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        setTitle("Login");

        Intent intent_Matching = new Intent(Login.this,Matching.class);
        Intent intent_register = new Intent(Login.this,MainActivity.class);
        Intent intent_tags = getIntent();

        EditText edt_id = (EditText) findViewById(R.id.edt_id);
        EditText edt_pw = (EditText) findViewById(R.id.edt_pw);

        Button btn_login = (Button) findViewById(R.id.btn_login);
        Button btn_register = (Button) findViewById(R.id.btn_register);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //로그인 버튼 누르면 작동.
                try {
                    String result;
                    id = edt_id.getText().toString();
                    pw = edt_pw.getText().toString();


                    LoginDB logindb = new LoginDB();
                    result = logindb.execute(id,pw).get(); // id,pw값을 db에서 확인 후 true,or false값 받아온다.
                     // 로그인
                    if (result.equals("true")){
                        Toast.makeText(Login.this,"로그인 진행",Toast.LENGTH_LONG).show();
                        intent_Matching.putExtra("id",id);
                        intent_Matching.putExtra("pw",pw);
                        startActivityForResult(intent_Matching,3);
                        Log.d("err",id);
                        // id와 pw값이 db와 일치한다면 true값을 받아와 main 페이지로 이동함.
                    }
                    else{
                        Toast.makeText(Login.this,"회원가입을 진행하세요.",Toast.LENGTH_LONG).show();
                    }

                } catch (Exception e) {
                    Log.i("DBtest", ".....ERROR.....!"); //오류발생
                }

            }
        });


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //회원가입 버튼 누르면 작동.

                startActivityForResult(intent_register,0);

            }
        });


    }

}