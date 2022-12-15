package com.example.dbproject2team;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Matching extends AppCompatActivity { //메인페이지. 매칭해주는 시스템을 지님.

    Button btn_Feed, btn_Write, btn_myPage;
    String count_tags, ID_TITLE,tpk,POST_INFO;
    int count;
    String Split[], ID_Title[],TPK[],post_info[];


    @SuppressLint({"MissingInflatedId", "SuspiciousIndentation"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("test","test");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching);
        setTitle("Your Feed");

        btn_Feed = (Button) findViewById(R.id.btn1_Feed);
        btn_Write = (Button) findViewById(R.id.btn_Write);
        btn_myPage = (Button) findViewById(R.id.btn_MyPage);

        Intent intent_MyPage = new Intent(Matching.this, MyPage.class);
        Intent intent_Write = new Intent(Matching.this,Frag1_Write.class);
        Intent intent_post = new Intent(Matching.this,Post.class);

        Intent idpw = getIntent();
        String id = idpw.getStringExtra("id"); //id, pw  값 받아옴
        String pw = idpw.getStringExtra("pw");

        // id값 jsp로 보내서 tag값들 받아오는코드.
        getCount_Tags getCountTags = new getCount_Tags();
        try {
            count_tags = getCountTags.execute(id).get(); // 테이블에서 꺼내온 count값과 tag값들 id값으로 tag테이블에 접근해서 받아옴.
            Log.d("test",count_tags);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Split = new String[5];
        Split = count_tags.split(" "); //띄어쓰기 기준으로 단어 쪼개기.
        count = Integer.parseInt(Split[0]); //count는 태그를 몇개를 골랐는지.split[0]은 count값. 1,2,3 중 하나
        Log.d("test","test3");                             //split[1],[2],[3]은 태그값.




        //###### tag1,2,3값을 jsp로 보내서 전체 글의 id와 타이틀값 받아오기.
        getTitle_ID getTitle_id = new getTitle_ID();
        try {
            ID_TITLE = getTitle_id.execute(Split[1],Split[2],Split[3]).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ID_Title = new String[100]; //0,2,4,6,8 ...은 TITLE값, 1,3,5,7,9 ...는 ID값.
        ID_Title = ID_TITLE.split(","); //구독한 태그의 게시글의 제목과 아이디를 , 기준으로 나눈다.






        //###### TAG3개 보내서 TPK값 받아오는 코드 ############
        getTPK gettpk = new getTPK();
        try {
            tpk = gettpk.execute(Split[1],Split[2],Split[3]).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        TPK = new String[100];
        TPK = tpk.split(","); //tpk값을 순서대로 배열에 저장.



        //리스트로 컨텐츠 불러오는 코드.
        ArrayList<String> ContentList = new ArrayList<String>();
        for(int i=0;i<ID_Title.length;i+=2){
            if(!ID_Title[i].equals("null"))
            ContentList.add(ID_Title[i+1]);
        }
        ListView listview = (ListView) findViewById(R.id.list1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Matching.this, android.R.layout.simple_list_item_1,ContentList);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) { //LIST 제목을 클릭하면 그 게시글로 이동.
                intent_post.putExtra("tpk",TPK[i]); //0번째 아이템을 누르면 0번째 tpk값을 그대로 페이지에 보내준다.
                startActivityForResult(intent_post,0);

            } //tpk값 받아와서 넣기.
        });




        btn_Feed.setOnClickListener(new View.OnClickListener() { //내 피드 들어가기
            @Override
            public void onClick(View view) { // 다섯개 인포값 받아온뒤 tag값이 notify면 notify 게시판으로가기.
                Intent intent_notify = new Intent(Matching.this,Notify_Board.class);
                startActivityForResult(intent_notify,0);

                Log.d("test","test5");

            }
        });

        btn_Write.setOnClickListener(new View.OnClickListener() { //글 작성하는 공간으로 들어가기
            @Override
            public void onClick(View view) {

                intent_Write.putExtra("id", id);
                intent_Write.putExtra("tag1",Split[1]);
                intent_Write.putExtra("tag2",Split[2]);
                intent_Write.putExtra("tag3",Split[3]);
                startActivityForResult(intent_Write,0);
                Log.d("test","test6");
            }
        });

        btn_myPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent_MyPage.putExtra("id2", id);
                intent_MyPage.putExtra("pw2", pw);
                startActivityForResult(intent_MyPage, 4);
                Log.d("test","test7");
            }
        });
    }




    @Override // 태그 선택 할 수 있는 옵션 selectoption 오버라이딩 해서 화면 다르게 띄우기.fragment
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_tags, menu);
        Log.d("test","test8");
        if (count==4) count=3;
        for (int i = 1; i <= count; i++) {
            menu.add(0, 1, 0, Split[i]); //메뉴 옵션선택도 똑같이 count까지만들어. tag를 3개를 골랐다고 Spilt[1],[2],[3]이 tag값임.
        }
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) { //item 창에서 메뉴들을 선택했을때 화면전환. tag1,2,3골랐을때.


            return super.onOptionsItemSelected(item);
        }

    }
