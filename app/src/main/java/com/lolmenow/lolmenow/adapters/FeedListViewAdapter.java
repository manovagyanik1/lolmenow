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
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;

import com.lolmenow.lolmenow.R;
import com.lolmenow.lolmenow.models.Post;
import com.squareup.picasso.Picasso;


public class FeedListViewAdapter extends ArrayAdapter<Post> {

    private AppCompatActivity activity;
    private List<Post> postList;

    public FeedListViewAdapter(AppCompatActivity context, int resource, List<Post> objects) {
        super(context, resource, objects);
        this.activity = context;
        this.postList = objects;
    }

    @Override
    public Post getItem(int position) {
        return postList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.post, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.reactionImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Picasso.with(activity)
                .load(postList.get(position).mediaUrl())
                .into(holder.imageView);

        return convertView;
    }

    private static class ViewHolder {
        private ImageView imageView;
        private LinearLayout reactionImages;
        private LinearLayout reactionCounts;

        public ViewHolder(View view) {
            imageView = (ImageView) view.findViewById(R.id.post_image);
            reactionImages = (LinearLayout) view.findViewById(R.id.reaction_images);
            reactionCounts = (LinearLayout) view.findViewById(R.id.reaction_counts);
        }
    }
}