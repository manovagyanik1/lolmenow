package com.lolmenow.lolmenow.adapters;

/**
 * Created by shubhamagrawal on 02/06/17.
 */

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import com.lolmenow.lolmenow.R;
import com.lolmenow.lolmenow.activities.CommentActivity;
import com.lolmenow.lolmenow.models.Comment;
import com.lolmenow.lolmenow.utils.Constants;
import com.squareup.picasso.Picasso;


public class CommentListViewAdapter extends ArrayAdapter<Comment>  {

    private AppCompatActivity activity;
    private List<Comment> commentList;
    private static final String TAG = CommentListViewAdapter.class.getSimpleName();

    public CommentListViewAdapter(AppCompatActivity context, int resource, ArrayList<Comment> objects) {
        super(context, resource, objects);
        this.activity = context;
        this.commentList = objects;
    }

    @Override
    public Comment getItem(int position) {
        return commentList.get(position);
    }

    @Override
    public int getCount() {
        return commentList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.comment, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Comment comment = commentList.get(position);
        Picasso.with(activity)
                .load(comment.getProfile().getPhotoUrl())
                .into(holder.profilePhoto);
        holder.profileName.setText(comment.getProfile().getName());
        holder.timestamp.setText(comment.getTimeStampMessage());
        holder.commentText.setText(comment.getText());
        holder.numberOfReplies.setText(comment.getNumberOfRepliesMessage());
        holder.numberOfLikes.setText(comment.getNumberOfLikesMessage());

        if (position + Constants.FETCH_OFFSET > commentList.size()){
            CommentActivity commentActivity = (CommentActivity) activity;
            commentActivity.getMoreData();
        }
        return convertView;
    }

    private static class ViewHolder {
        private ImageView profilePhoto;
        private TextView profileName;
        private TextView timestamp;
        private TextView commentText;
        private TextView numberOfReplies;
        private TextView numberOfLikes;

        public ViewHolder(View view) {
            profilePhoto = (ImageView) view.findViewById(R.id.profile_photo);
            profileName = (TextView) view.findViewById(R.id.profile_name);
            timestamp = (TextView) view.findViewById(R.id.comment_timestamp);
            commentText = (TextView) view.findViewById(R.id.comment_text);
            numberOfLikes = (TextView) view.findViewById(R.id.comment_like_count);
            numberOfReplies = (TextView) view.findViewById(R.id.comment_reply_count);
        }
    }
}