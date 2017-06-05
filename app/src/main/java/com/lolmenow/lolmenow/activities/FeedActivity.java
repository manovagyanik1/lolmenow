package com.lolmenow.lolmenow.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.lolmenow.lolmenow.R;
import com.lolmenow.lolmenow.adapters.FeedListViewAdapter;
import com.lolmenow.lolmenow.models.Post;
import com.lolmenow.lolmenow.models.Reaction;
import com.lolmenow.lolmenow.models.Share;
import com.lolmenow.lolmenow.utils.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by shubhamagrawal on 03/06/17.
 */

public class FeedActivity extends AppCompatActivity {

    private ListView feedListView;
    private static final String TAG = FeedActivity.class.getSimpleName();
    private DatabaseReference mDatabase;
    private ArrayAdapter<Post> adapter;
    private ArrayList<Post> arrayList;
    private ValueEventListener postListener;
    private boolean loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final AppCompatActivity that = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feed_screen);
        arrayList = new ArrayList<>();
        loading = false;

        mDatabase = FirebaseDatabase.getInstance().getReference("feed");
        feedListView = (ListView) findViewById(R.id.feed_list_view);

        adapter = new FeedListViewAdapter(that, R.layout.post, arrayList);
        feedListView.setAdapter(adapter);

        postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "onDataChange called");
                GenericTypeIndicator<List<Post>> t = new GenericTypeIndicator<List<Post>>() {};
                try{
                    List<Post> newPostList = dataSnapshot.getValue(t);
                    arrayList.addAll(newPostList.subList(arrayList.size(), newPostList.size()));
                } catch (Exception e){
                    Log.e(TAG, e.getMessage().toString());

                    GenericTypeIndicator<HashMap<String, Post>> t2 = new GenericTypeIndicator<HashMap<String, Post>>() {};
                    HashMap<String, Post> newPostMap = dataSnapshot.getValue(t2);
                    Log.d(TAG, newPostMap.toString());

                    for (Post post : newPostMap.values()) {
                        arrayList.add(post);
                    }
                }
                loading = false;
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, databaseError.getDetails());
            }
        };
        getMoreData();
    }

    public void getMoreData() {
        Log.d(TAG, "getting more data");
        if(!loading){
            Query query = mDatabase.orderByKey().limitToFirst(Constants.FETCH_FEED_POSTS_SIZE).startAt(arrayList.size()+"");
            loading = true;
            query.addListenerForSingleValueEvent(postListener);
        }
    }
}
