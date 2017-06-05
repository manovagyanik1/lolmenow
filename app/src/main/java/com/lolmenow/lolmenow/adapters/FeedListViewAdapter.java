package com.lolmenow.lolmenow.adapters;

/**
 * Created by shubhamagrawal on 02/06/17.
 */

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.lolmenow.lolmenow.R;
import com.lolmenow.lolmenow.models.Post;
import com.lolmenow.lolmenow.models.Reaction;
import com.squareup.picasso.Picasso;


public class FeedListViewAdapter extends ArrayAdapter<Post>  {

    private AppCompatActivity activity;
    private List<Post> postList;
    private static final String TAG = FeedListViewAdapter.class.getSimpleName();

    public FeedListViewAdapter(AppCompatActivity context, int resource, ArrayList<Post> objects) {
        super(context, resource, objects);
        this.activity = context;
        this.postList = objects;
    }

    private class ReactionClickListenter implements View.OnClickListener{

        private ViewHolder holder;

        public ReactionClickListenter(ViewHolder viewHolder) {
            this.holder = viewHolder;
        }

        @Override
        public void onClick(View v) {
            holder.reactionCounts.animate().alpha(1);
            holder.reactionImages.setAlpha((float) 0.5);

            // update the data in firebase
            switch (v.getId()){
                case R.id.lolImage: {

                    break;
                }
                case R.id.xdImage: {

                    break;
                }
                case R.id.wowImage: {

                    break;
                }
                case R.id.clapImage: {

                    break;
                }
                default:
                    break;
            }
        }
    }

    @Override
    public Post getItem(int position) {
        return postList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.post, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Reaction reaction = postList.get(position).getData().getReaction();
        holder.lolCount.setText(reaction.getLol().toString());
        holder.xdCount.setText(reaction.getXd().toString());
        holder.wowCount.setText(reaction.getWow().toString());
        holder.clapCount.setText(reaction.getClaps().toString());

        holder.reactionCounts.setAlpha(0);
        ReactionClickListenter reactionClickListener = new ReactionClickListenter(holder);

        holder.lolImage.setOnClickListener(reactionClickListener);
        holder.xdImage.setOnClickListener(reactionClickListener);
        holder.wowImage.setOnClickListener(reactionClickListener);
        holder.clapImage.setOnClickListener(reactionClickListener);
        final View view = convertView;
        convertView.setAlpha(0);

        Picasso.with(activity)
                .load(postList.get(position).getData().getMediaUrl())
                .into(holder.imageView, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        //do smth when picture is loaded successfully
                        view.setAlpha(1);
                    }

                    @Override
                    public void onError() {
                        //do smth when there is picture loading error
                    }
                });
        return convertView;
    }

    private static class ViewHolder {
        private ImageView imageView;
        private LinearLayout reactionImages;
        private LinearLayout reactionCounts;

        private ImageView lolImage;
        private ImageView xdImage;
        private ImageView wowImage;
        private ImageView clapImage;
        private TextView lolCount;
        private TextView xdCount;
        private TextView wowCount;

        private TextView clapCount;

        public ViewHolder(View view) {
            imageView = (ImageView) view.findViewById(R.id.post_image);
            reactionImages = (LinearLayout) view.findViewById(R.id.reaction_images);
            reactionCounts = (LinearLayout) view.findViewById(R.id.reaction_counts);

            lolImage = (ImageView) reactionImages.findViewById(R.id.lolImage);
            xdImage = (ImageView) reactionImages.findViewById(R.id.xdImage);
            wowImage = (ImageView) reactionImages.findViewById(R.id.wowImage);
            clapImage = (ImageView) reactionImages.findViewById(R.id.clapImage);

            lolCount = (TextView) reactionCounts.findViewById(R.id.lolCount);
            xdCount = (TextView) reactionCounts.findViewById(R.id.xdCount);
            wowCount = (TextView) reactionCounts.findViewById(R.id.wowCount);
            clapCount = (TextView) reactionCounts.findViewById(R.id.clapCount);
        }
    }
}