package com.example.king.view.search;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.king.view.R;

public class SearchActivity extends AppCompatActivity {

    private SearchView mSerach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
    }

    private void initView() {
        mSerach = findViewById(R.id.serach);

        mSerach.setSearchViewCallback(new SearchView.SearchViewCallback() {
            @Override
            public void keywordsEmpty() {
                Toast.makeText(SearchActivity.this,"输入不能为空",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClickListener(View v) {
                finish();
            }

            @Override
            public void searchClick(String keywords) {
                Toast.makeText(SearchActivity.this,keywords,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
