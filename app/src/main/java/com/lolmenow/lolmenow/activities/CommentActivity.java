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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.lolmenow.lolmenow.R;
import com.lolmenow.lolmenow.adapters.CommentListViewAdapter;
import com.lolmenow.lolmenow.adapters.FeedListViewAdapter;
import com.lolmenow.lolmenow.models.Comment;
import com.lolmenow.lolmenow.models.Post;
import com.lolmenow.lolmenow.models.Reaction;
import com.lolmenow.lolmenow.models.Share;
import com.lolmenow.lolmenow.utils.Constants;
import com.lolmenow.lolmenow.utils.Gen;
import com.lolmenow.lolmenow.utils.JsonObjectRequestWithAuth;
import com.lolmenow.lolmenow.utils.VolleySingelton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by shubhamagrawal on 03/06/17.
 */

public class CommentActivity extends AppCompatActivity {

    private Integer postId;
    private ListView commentListView;
    private static final String TAG = CommentActivity.class.getSimpleName();
    private ArrayAdapter<Comment> adapter;
    private ArrayList<Comment> arrayList;
    private boolean loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final AppCompatActivity that = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_screen);
        arrayList = new ArrayList<>();
        loading = false;
        commentListView = (ListView) findViewById(R.id.feed_list_view);

        adapter = new CommentListViewAdapter(that, R.layout.comment, arrayList);
        commentListView.setAdapter(adapter);
        getMoreData();
    }

    public void getMoreData() {
        Log.d(TAG, "getting more data");
        if(!loading){
            final Activity activity = this;
            RequestQueue requestQueue = VolleySingelton.getInstance().getRequestQueue();
            JsonObjectRequest request = new JsonObjectRequestWithAuth(Request.Method.GET, getCommentURL(),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // get the data, save pagination info and update the adapter list
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Gen.showVolleyError(error);
                }
            });
            requestQueue.add(request);
        }
    }

    public String getCommentURL() {
        return Constants.COMMENT_URL + "?post_id="+this.postId;
    }

}
