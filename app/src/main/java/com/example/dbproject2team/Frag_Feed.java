package com.example.dbproject2team;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

public class Frag_Feed extends Fragment { // 내 피드 보여주는 창

    private TextView test1;
    private View view;
    TextView tv[];
    public Frag_Feed(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.frag_feed,container,false);
        Log.d("test","test6");
        tv = new TextView[20];

        Log.d("test","test7");
        test1 = view.findViewById(R.id.test22);
        String result = getArguments().getString("id");




        return view;
    }



}
