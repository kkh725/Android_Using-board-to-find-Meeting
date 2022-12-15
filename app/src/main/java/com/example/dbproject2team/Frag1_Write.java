package com.example.dbproject2team;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Frag1_Write extends AppCompatActivity {
    EditText edt_writer,edt_title,edt_content,edt_PW,edt_tags;
    String tag1,tag2,tag3,tags;
    Button btn_write,btn_cancel;
    RadioButton r1,r2,r3;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("test","test7");
        setContentView(R.layout.activity_frag1_write);
        setTitle("Posting");

        btn_write = findViewById(R.id.post_list);
        btn_cancel = findViewById(R.id.post_modify);

        edt_writer=findViewById(R.id.edt_writer1);
        edt_title = findViewById(R.id.edt_title1);
        edt_content = findViewById(R.id.edt_content1);
        edt_PW = findViewById(R.id.edt_PW1);
        //edt_tags =findViewById(R.id.edt_tags1);
        r1 = findViewById(R.id.R_TAG1);
        r2 = findViewById(R.id.R_TAG2);
        r3 = findViewById(R.id.R_TAG3);

        Intent intend_getid = getIntent();
        String id = intend_getid.getStringExtra("id"); //로그인시 사용한 아이디값 가져오기
        tag1 = intend_getid.getStringExtra("tag1");
        tag2 = intend_getid.getStringExtra("tag2");
        tag3 = intend_getid.getStringExtra("tag3");

        r1.setText(tag1);
        r2.setText(tag2);
        r3.setText(tag3);

        Intent intent_main = new Intent(Frag1_Write.this,Matching.class);



        btn_write.setOnClickListener(new View.OnClickListener() { // 글쓰기하는페이지. write테이블에 넣음.
            @Override
            public void onClick(View view) {
                if(r1.isChecked()==true) tags=r1.getText().toString();
                else if(r2.isChecked()==true) tags=r2.getText().toString();
                else if (r3.isChecked()==true) tags=r3.getText().toString();
                else tags = "#notify";

                long now = System.currentTimeMillis();
                Date date = new Date(now);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                String writer = edt_writer.getText().toString();
                String title = edt_title.getText().toString();
                String content = edt_content.getText().toString();
                String PW = edt_PW.getText().toString();
                String ptime = dateFormat.format(date); //현재시간 알려주는코드짜기.
                //String tags = edt_tags.getText().toString();

                Frag_WriteDB frag_writeDB = new Frag_WriteDB();
                frag_writeDB.execute(id,title,content,ptime,tags);

                Toast.makeText(Frag1_Write.this,"작성 완료",Toast.LENGTH_SHORT).show();
                startActivityForResult(intent_main, 0);
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

}

