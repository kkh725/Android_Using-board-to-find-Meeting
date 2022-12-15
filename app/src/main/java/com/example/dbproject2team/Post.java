package com.example.dbproject2team;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class Post extends AppCompatActivity {
    String post_info[];
    String POST_INFO,id,title,content,date,tag;
    TextView post_writer,post_title,post_content,post_date,post_tag;
    Button btn_list, btn_modify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        setTitle("Your Feed");

        post_writer = findViewById(R.id.post_writer);
        post_title = findViewById(R.id.post_title);
        post_content = findViewById(R.id.post_content);
        post_date = findViewById(R.id.post_date);
        post_tag = findViewById(R.id.post_tag);

        btn_list = (Button)findViewById(R.id.post_list);

        Intent get_tpk = getIntent();
        String tpk = get_tpk.getStringExtra("tpk");

        Intent intent_main = new Intent(Post.this,Matching.class);

        getID_TITLE_CONTENT_DATE_TAG Post_info = new getID_TITLE_CONTENT_DATE_TAG();
        try {
            POST_INFO = Post_info.execute(tpk).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        post_info = new String[5];
        post_info = POST_INFO.split(",");

        id = post_info[0];  post_writer.setText(id);
        title = post_info[1];   post_title.setText(title);
        content = post_info[2];     post_content.setText(content);
        date = post_info[3];    post_date.setText(date);
        tag = post_info[4];     post_tag.setText(tag);


        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();

            }
        });

    }

}