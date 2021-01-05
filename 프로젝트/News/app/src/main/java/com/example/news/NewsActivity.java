package com.example.news;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.NestedScrollingChild;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String[] mDataset = {"1", "2"};

    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        mRecyclerView = findViewById(R.id.my_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        queue = Volley.newRequestQueue(this);
        getNews();
        // 1. 화면이 로딩 -> 뉴스 정보를 받아온다 -------
        // 2. 정보 -> 어댑터 넘겨준다
        // 3. 어댑터 -> 세팅
    }
    public void getNews() {

        String url ="http://newsapi.org/v2/top-headlines?country=kr&apiKey=40e04c3607994181ad8d25d482697048";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Log.d("NEWS", response);

                        try {

                            JSONObject jsonObject = new JSONObject(response);

                            JSONArray arrayArticles = jsonObject.getJSONArray("articles");

                            // response ->> NewsData Class 분류
                            List<NewsData> news = new ArrayList<>();

                            for(int i = 0, j = arrayArticles.length(); i < j; i++) {
                                JSONObject obj = arrayArticles.getJSONObject(i);

                                Log.d("NEWS", obj.toString());

                                NewsData newsData = new NewsData();
                                newsData.setTitle(obj.getString("title"));
                                newsData.setUrlToImage(obj.getString("urlToImage"));
                                newsData.setContent(obj.getString("content"));

                                news.add(newsData);
                            }

                            // specify an adapter (see also next example)
                            mAdapter = new MyAdapter(news, NewsActivity.this);
                            mRecyclerView.setAdapter(mAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}