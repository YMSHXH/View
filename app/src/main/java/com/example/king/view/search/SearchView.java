package com.example.king.view.search;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.king.view.R;

public class SearchView extends LinearLayout {

    private ImageView backIv;
    private TextView searchTv;
    private EditText editText;
    private int color;
    private int size;
    private SearchViewCallback searchViewCallback;

    public SearchView(Context context) {
        this(context,null);
    }

    public SearchView(Context context,AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);;
    }

    private void init(Context context,AttributeSet attributeSet){
        initAttrs(context,attributeSet);
        initView();
        initData();

    }

    private void initData() {
    }

    private void initView() {
        //获取控件
        View view = LayoutInflater.from(getContext()).inflate(R.layout.search_layout,this,true);

        backIv = view.findViewById(R.id.back);
        searchTv = view.findViewById(R.id.tv_search);
        editText = view.findViewById(R.id.et_search);

        backIv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                searchViewCallback.onClickListener(v);
            }
        });
        searchTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String edit = editText.getText().toString();
                if (TextUtils.isEmpty(edit)) {
                    if (searchViewCallback != null) {
                        searchViewCallback.keywordsEmpty();
                    }
                    return;
                }
                if (searchViewCallback != null) {
                    searchViewCallback.searchClick(edit);
                }
            }
        });
    }

    private void initAttrs(Context context, AttributeSet attributeSet) {
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet,R.styleable.SearchView);
        color = typedArray.getColor(R.styleable.SearchView_searchcolor,Color.RED);
        size = typedArray.getDimensionPixelSize(R.styleable.SearchView_searchsize,100);
    }



    /**
     * 调用者去调用
     * @param searchViewCallback
     */
    public void setSearchViewCallback(SearchViewCallback searchViewCallback) {
        this.searchViewCallback = searchViewCallback;
    }

    public interface SearchViewCallback{
        void keywordsEmpty();
        void onClickListener(View v);//点击事件
        void searchClick(String keywords);//搜索点击事件
    }

}
