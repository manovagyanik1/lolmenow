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
import com.google.firebase.database.ValueEventListener;
import com.lolmenow.lolmenow.R;
import com.lolmenow.lolmenow.adapters.FeedListViewAdapter;
import com.lolmenow.lolmenow.models.Post;
import com.lolmenow.lolmenow.models.Reaction;
import com.lolmenow.lolmenow.models.Share;

import java.util.ArrayList;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final AppCompatActivity that = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feed_screen);
        arrayList = new ArrayList<Post>();

        mDatabase = FirebaseDatabase.getInstance().getReference("feed");
        feedListView = (ListView) findViewById(R.id.feed_list_view);

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<ArrayList<Post>> t = new GenericTypeIndicator<ArrayList<Post>>() {};
                try{
                    arrayList = dataSnapshot.getValue(t);
                } catch (Exception e){
                    Log.e(TAG, e.getCause().toString());
                }

                // TODO: see if we need to do this.
                adapter = new FeedListViewAdapter(that, R.layout.post, arrayList);
                feedListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, databaseError.getDetails());
            }
        };

        mDatabase.addValueEventListener(postListener);
    }
}
