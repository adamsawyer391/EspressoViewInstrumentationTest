package com.cosmic.espressotest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView ivOne;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivOne = findViewById(R.id.ivOne);
        button = findViewById(R.id.button);
    }

    private void alterImageViewVisiblity(View button){
        ivOne.setVisibility(View.VISIBLE);
    }

}