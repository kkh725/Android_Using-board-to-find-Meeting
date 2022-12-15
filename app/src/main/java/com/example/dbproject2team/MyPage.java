package com.example.dbproject2team;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class MyPage extends AppCompatActivity {

    TextView text_ID, text_PW, text_NAME,text_PHONE,text_BIRTH,text_MAIL,text_CITY;
    Button btn_ChangeInfo, btn_Complete,btn_withdrawal;
    EditText ed_id,ed_pw,ed_name,ed_phone,ed_birth,ed_mail,ed_city;
    String change_id,change_pw,change_name,change_phone,change_birth,change_mail,change_city;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);
        setTitle("MyPage");
        String result;

        text_ID = findViewById(R.id.text_ID);
        text_PW = findViewById(R.id.text_PW);
        text_PHONE = findViewById(R.id.text_PHONE);
        text_NAME = findViewById(R.id.text_NAME);
        text_BIRTH = findViewById(R.id.text_BIRTH);
        text_MAIL = findViewById(R.id.text_MAIL);
        text_CITY = findViewById(R.id.text_CITY);

        btn_ChangeInfo = findViewById(R.id.btn_ChangeInfo);
        btn_Complete = findViewById(R.id.btn_Complete);
        btn_withdrawal = findViewById(R.id.btn_withdrawal);

        ed_id = findViewById(R.id.ed_id);
        ed_pw = findViewById(R.id.ed_pw);
        ed_name = findViewById(R.id.ed_name);
        ed_phone = findViewById(R.id.ed_phone);
        ed_birth = findViewById(R.id.ed_birth);
        ed_mail = findViewById(R.id.ed_mail);
        ed_city = findViewById(R.id.ed_city);

        Intent idpw = getIntent();

        String id = idpw.getStringExtra("id2");
        String pw = idpw.getStringExtra("pw2");

        text_ID.setText("ID : "+id);
        text_PW.setText("PW : "+pw);

        String [] strToArray;

        try {

            MyPageDB task = new MyPageDB();
            result = task.execute(id).get();

            strToArray = result.split(" ");
            text_NAME.setText(strToArray[0]); // PHONE부터 DB에서 값을가져옴.
            text_PHONE.setText(strToArray[1]);
            text_BIRTH.setText(strToArray[2]);
            text_MAIL.setText(strToArray[3]);
            text_CITY.setText(strToArray[4]);


        } catch (Exception e) {
            Log.i("DBtest", ".....ERROR.....!");
        }

        btn_ChangeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_Complete.setVisibility(View.VISIBLE);
                ed_pw.setVisibility(View.VISIBLE);
                ed_name.setVisibility(View.VISIBLE);
                ed_phone.setVisibility(View.VISIBLE);
                ed_birth.setVisibility(View.VISIBLE);
                ed_mail.setVisibility(View.VISIBLE);
                ed_city.setVisibility(View.VISIBLE);


            }
        });

        btn_Complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                change_id = ed_id.getText().toString(); //수정란이 빈칸일 경우 원래 정보 그대로 유지.
                if (change_id.equals("")){
                    change_id = id;
                }
                change_pw = ed_pw.getText().toString();
                if (change_pw.equals("")){
                    change_pw = pw;
                }
                change_name = ed_name.getText().toString();
                if (change_name.equals("")){
                    change_name = text_NAME.getText().toString();
                }
                change_phone = ed_phone.getText().toString();
                if (change_phone.equals("")){
                    change_phone = text_PHONE.getText().toString();
                }
                change_birth = ed_birth.getText().toString();
                if (change_birth.equals("")){
                    change_birth = text_BIRTH.getText().toString();
                }
                change_mail = ed_mail.getText().toString();
                if (change_mail.equals("")){
                    change_mail = text_MAIL.getText().toString();
                }
                change_city = ed_city.getText().toString();
                if (change_city.equals("")){
                    change_city = text_CITY.getText().toString();
                }

                CompleteDB completeDB = new CompleteDB();
                try {
                    String result = completeDB.execute(change_id,change_pw,change_name,change_phone,change_birth,change_mail,change_city).get();
                    Toast.makeText(MyPage.this,"변경이 완료되었습니다.",Toast.LENGTH_SHORT).show();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        btn_withdrawal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Withdrawal withdrawal = new Withdrawal();
                try {
                    withdrawal.execute(id).get();
                    Intent intent_login = new Intent(MyPage.this,Login.class);
                    startActivityForResult(intent_login,1);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
