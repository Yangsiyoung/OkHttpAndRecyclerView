package com.example.yangsiyoung.myokhttptest.list;

import android.util.Log;

/**
 * Created by Yang Si Young on 2016-03-21.
 */
public class Comment {
    private String comment;

    public Comment(String comment) {
        this.comment = comment;
        Log.d("aaaa", "Comment의 내용은 " + comment);
    }

    public String getComment() {
        return this.comment;
    }
}
