package com.example.dbproject2team;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Notify_Board extends AppCompatActivity {
    String TPK[],ID_Title[];
    String tpk,ID_TITLE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_board);
        setTitle("Notify");

        getTPK gettpk = new getTPK();
        try {
            tpk = gettpk.execute("#notify","#notify","#notify").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        TPK = new String[100];
        TPK = tpk.split(","); //tpk값을 순서대로 배열에 저장.

        //###### tag1,2,3값을 jsp로 보내서 전체 글의 id와 타이틀값 받아오기.
        getTitle_ID getTitle_id = new getTitle_ID();
        try {
            ID_TITLE = getTitle_id.execute("#notify","#notify","#notify").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ID_Title = new String[100]; //0,2,4,6,8 ...은 TITLE값, 1,3,5,7,9 ...는 ID값.
        ID_Title = ID_TITLE.split(","); //구독한 태그의 게시글의 제목과 아이디를 , 기준으로 나눈다.

        ArrayList<String> ContentList = new ArrayList<String>();
        for(int i=0;i<ID_Title.length;i+=2){
            if(!ID_Title[i].equals("null"))
                ContentList.add(ID_Title[i+1]);
        }
        ListView listview = (ListView) findViewById(R.id.list1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Notify_Board.this, android.R.layout.simple_list_item_1,ContentList);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) { //LIST 제목을 클릭하면 그 게시글로 이동.
                Intent intent_post = new Intent(Notify_Board.this,Post.class);
                intent_post.putExtra("tpk",TPK[i]); //0번째 아이템을 누르면 0번째 tpk값을 그대로 페이지에 보내준다.
                startActivityForResult(intent_post,1); //게시글 안에 들어갈 값들 PUT해주기********************************************

            } //tpk값 받아와서 넣기.
        });
    }
}