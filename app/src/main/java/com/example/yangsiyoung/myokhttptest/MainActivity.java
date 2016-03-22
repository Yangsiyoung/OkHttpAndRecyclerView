package com.example.yangsiyoung.myokhttptest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.yangsiyoung.myokhttptest.list.Comment;
import com.example.yangsiyoung.myokhttptest.list.CommentAdapter;

import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public static final String url = "https://api.github.com/repos/Yangsiyoung/AndroidStudy/comments";
    //public static final String url = "http://publicobject.com/helloworld.txt";

    private TextView txtResult;
    private GetRepo client = new GetRepo();
    private String result;

    ArrayList<Comment> commentList = new ArrayList<Comment>();
    private RecyclerView recyclerViewComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewComment = (RecyclerView) findViewById(R.id.recyclerViewComment);

        try {
            client.run(url);

            //  Log.d("aaaa", "result 값은 " + result);
            //  txtResult.setText(result);
        } catch (Exception e) {
            Log.d("error", "request 오류 내용은 " + e.toString());
        }
    }

    public class GetRepo {

        //OkHttpClient 생성
        OkHttpClient client = new OkHttpClient();

        private String result = "";

        //url 입력받아 run
        public void run(String url) throws IOException {
            Request request = new Request.Builder().url(url).build();

            //CallBack 메서드로 request 및 response처리
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.d("error", "request 실패 내용은 " + e.toString());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    //response 내용을 result에 저장
                    result = response.body().string();
                    //Log.d("aaaa", "response 응답은 " + result);

                    //메인 UI쓰레드로 값을 전달하여 UI 작업
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                String myResult = "";
                                JSONArray json = new JSONArray(result);
                                for (int i = 0; i < json.length(); i++) {

                                  //  Comment comment = new Comment(json.getJSONObject(i).getString("body"));

                                   commentList.add(new Comment(json.getJSONObject(i).getString("body")));
                                    Log.d("aaaa", "add 실행 " + i);
                                    //Log.d("aaaa", "commentList 리스트는 " + json.getJSONObject(i).getString("body"));

                                    // myResult += json.getJSONObject(i).getString("body") + "\n\n";

                                }

                                CommentAdapter adapter = new CommentAdapter(commentList);
                                Log.d("aaaa", "commentList의 첫번째 리스트는 " + commentList.get(0).getComment());
                                recyclerViewComment.setAdapter(adapter);
                                recyclerViewComment.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                            } catch (Exception e) {
                                Log.d("error", "Json 에러 내용은 " + e.toString());
                            }
                        }
                    });
                }


            });
        }


    }
}