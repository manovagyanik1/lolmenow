package com.lolmenow.lolmenow.adapters;

/**
 * Created by shubhamagrawal on 02/06/17.
 */

import android.app.Activity;
import android.media.Image;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import com.lolmenow.lolmenow.MyApplication;
import com.lolmenow.lolmenow.R;
import com.lolmenow.lolmenow.activities.FeedActivity;
import com.lolmenow.lolmenow.models.Post;
import com.lolmenow.lolmenow.models.Reaction;
import com.lolmenow.lolmenow.utils.Constants;
import com.lolmenow.lolmenow.utils.Gen;
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

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onClick(View v) {
            holder.reactionCounts.animate().alpha(1);
            Animation animation = AnimationUtils.loadAnimation(MyApplication.getAppContext(),
                    R.anim.zoom_out_in);
            v.startAnimation(animation);
            // update the data in firebase
            switch (v.getId()){
                case R.id.lolImage: {
                    Gen.playSound(R.raw.lol);
                    break;
                }
                case R.id.xdImage: {
                    Gen.playSound(R.raw.xd);
                    break;
                }
                case R.id.wowImage: {
                    Gen.playSound(R.raw.wow);
                    break;
                }
                case R.id.clapImage: {
                    Gen.playSound(R.raw.clap);
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
    public int getCount() {
        return postList.size();
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

        try{


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

        } catch (Exception e){
            Log.e(TAG, e.getMessage());
        }
        final View view = convertView;
        convertView.setAlpha(0);

        // TODO: Ranji: scroll behaviour issue: user is scrolling up the feed and is not aware of post skip
        Picasso.with(activity)
                .load(postList.get(position).getData().getMediaUrl())
                .into(holder.imageView, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        view.setAlpha(1);
                    }

                    @Override
                    public void onError() {
                    }
                });

        if (position + Constants.FETCH_OFFSET > postList.size()){
            FeedActivity feedActivity = (FeedActivity) activity;
            feedActivity.getMoreData();
        }
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