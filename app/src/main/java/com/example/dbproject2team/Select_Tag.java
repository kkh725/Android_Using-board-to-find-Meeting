package com.example.dbproject2team;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Select_Tag extends AppCompatActivity {

    CheckBox tag1,tag2,tag3,tag4,tag5,tag6,tag7,tag8,tag9;
    Button btn_register;
    String str1,str2,str3,str4,str5,str6,str7,str8,str9;
    List<String> arrlist;

    String Split[] ;
    int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_tag);
        setTitle("Select Your Tags");

        arrlist = new ArrayList<>(9);
        String result2 = null;

        btn_register = (Button) findViewById(R.id.btn_register);
        tag1 = (CheckBox)findViewById(R.id.tag1);   str1 = tag1.getText().toString();
        tag2 = (CheckBox)findViewById(R.id.tag2);   str2 = tag2.getText().toString();
        tag3 = (CheckBox)findViewById(R.id.tag3);   str3 = tag3.getText().toString();
        tag4 = (CheckBox)findViewById(R.id.tag4);   str4 = tag4.getText().toString();
        tag5 = (CheckBox)findViewById(R.id.tag5);   str5 = tag5.getText().toString();
        tag6 = (CheckBox)findViewById(R.id.tag6);   str6 = tag6.getText().toString();
        tag7 = (CheckBox)findViewById(R.id.tag7);   str7 = tag7.getText().toString();
        tag8 = (CheckBox)findViewById(R.id.tag8);   str8 = tag8.getText().toString();
        tag9 = (CheckBox)findViewById(R.id.tag9);   str9 = tag9.getText().toString();

        Intent intent_login = new Intent(Select_Tag.this,Login.class);
        Intent id = getIntent();
        String ID = id.getStringExtra("id");

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // 회원가입버튼을 눌렀을때 체크되어있는 체크박스 개수체크 및 태그 확인해서 db에 보냄.
                if(tag1.isChecked()==true){
                    arrlist.add(str1);
                    count++;
                }

                if(tag2.isChecked()==true){
                    arrlist.add(str2);
                    count++;
                }

                if(tag3.isChecked()==true){
                    arrlist.add(str3);
                    count++;
                }

                if(tag3.isChecked()==true){
                    arrlist.add(str3);
                    count++;
                }

                if(tag4.isChecked()==true){
                    arrlist.add(str4);
                    count++;
                }

                if(tag5.isChecked()==true){
                    arrlist.add(str5);
                    count++;
                }

                if(tag6.isChecked()==true){
                    arrlist.add(str6);
                    count++;
                }

                if(tag7.isChecked()==true){
                    arrlist.add(str7);
                    count++;
                }

                if(tag8.isChecked()==true){
                    arrlist.add(str8);
                    count++;
                }
                if(tag9.isChecked()==true){
                    arrlist.add(str9);
                    count++;
                }

                Toast.makeText(Select_Tag.this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();

                String str = String.join(" ",arrlist); // 원소값 하나 늘어날때마다 띄어쓰기 ex - tag1 tag2 tag3;
                Split = new String[9];
                String Tags;

                try {
                    Select_TagDB task = new Select_TagDB();
                    Tags = task.execute(ID,Integer.toString(count),arrlist.get(0),arrlist.get(1),arrlist.get(2)).get(); // tags에 jsp에서 리턴해주는값은 tag문자열들
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