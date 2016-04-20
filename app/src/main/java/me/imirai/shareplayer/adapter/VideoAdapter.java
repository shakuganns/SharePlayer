package me.imirai.shareplayer.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import me.imirai.shareplayer.R;
import me.imirai.shareplayer.activity.VideoActivity;
import me.imirai.shareplayer.utils.VideoRecord;

/**
 * Created by Shakugan on 16/4/18.
 */
public class VideoAdapter extends RecyclerView.Adapter {

    private ArrayList<String> videoPathList = new ArrayList<>();
    private ArrayList<String> videoNameList = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;

    public VideoAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        videoNameList = VideoRecord.getInstance().getVideoNameList();
        videoPathList = VideoRecord.getInstance().getVideoPathList();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VideoViewHodler(inflater.inflate(R.layout.video_list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((VideoViewHodler)holder).videoName.setText(videoNameList.get(position));
        ((VideoViewHodler)holder).position = position;
//        ((VideoViewHodler)holder).videoImage.setBackgroundDrawable(Drawable.createFromPath(videoPathList.get(position)));
    }

    @Override
    public int getItemCount() {
        return videoPathList.size();
    }

    public void setVideoPathList(ArrayList<String> videoPathList) {
        this.videoPathList = videoPathList;
    }

    public void setVideoNameList(ArrayList<String> videoNameList) {
        this.videoNameList = videoNameList;
    }

    private class VideoViewHodler extends RecyclerView.ViewHolder {

        private ImageView videoImage;
        private TextView videoName;
        private TextView time;
        private int position;

        public VideoViewHodler(final View itemView) {
            super(itemView);
            videoImage = (ImageView) itemView.findViewById(R.id.video_image);
            videoName = (TextView) itemView.findViewById(R.id.video_name);
            time = (TextView) itemView.findViewById(R.id.time_size);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, VideoActivity.class);
                    intent.putExtra("path",videoPathList.get(position));
                    intent.putExtra("position",position);
                    context.startActivity(intent);
                }
            });
        }

    }
}
