package com.lolmenow.lolmenow.activities;

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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feed_screen);

        mDatabase = FirebaseDatabase.getInstance().getReference("feed");
        feedListView = (ListView) findViewById(R.id.feed_list_view);
        setListData();

        adapter = new FeedListViewAdapter(this, R.layout.post, arrayList);
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Long value = dataSnapshot.getChildrenCount();
                GenericTypeIndicator<List<Post>> t = new GenericTypeIndicator<List<Post>>() {};
                try{
                    List<Post> posts = dataSnapshot.getValue(t);
                } catch (Exception e){
                    Log.d(TAG, e.getCause().toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, databaseError.getDetails());
            }
        };

        mDatabase.addValueEventListener(postListener);
        feedListView.setAdapter(adapter);
    }

    private void setListData() {
        arrayList = new ArrayList<>();
        Reaction reaction = Reaction.builder().rofl(new Integer(5))
                .xd(5)
                .wow(899)
                .claps(89)
                .build();
        Share share = Share.builder().shareCount(89).shareUrl("https://scontent.fmaa1-1.fna.fbcdn.net/v/t1.0-9/18767780_134603470430819_685348563835972705_n.jpg?oh=b7b3fb241586d58d4f1dac4076ed3fee&oe=599BB1F0").build();

        Post post = Post.builder().mediaUrl("https://scontent.fmaa1-1.fna.fbcdn.net/v/t1.0-9/18767780_134603470430819_685348563835972705_n.jpg?oh=b7b3fb241586d58d4f1dac4076ed3fee&oe=599BB1F0")
                .share(share)
                .reaction(reaction)
                .build();

        arrayList.add(post);
        arrayList.add(post);
        arrayList.add(post);
        arrayList.add(post);
        arrayList.add(post);
        arrayList.add(post);
        arrayList.add(post);
        arrayList.add(post);
        arrayList.add(post);
        arrayList.add(post);
        arrayList.add(post);

    }
}
