package me.imirai.shareplayer.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

import me.imirai.shareplayer.R;
import me.imirai.shareplayer.utils.VideoRecord;

public class VideoActivity extends AppCompatActivity {

    private VideoView videoView;
    private MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_video);
        mediaController = new MediaController(this);
        videoView = (VideoView) findViewById(R.id.videoView);
        videoView.setVideoPath(getIntent().getStringExtra("path"));
        videoView.setMediaController(mediaController);
        videoView.start();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        videoView.seekTo(VideoRecord.getInstance().getVideoPosition(getIntent().getIntExtra("position",-1)));
    }

    @Override
    protected void onPause() {
        super.onPause();
        VideoRecord.getInstance().setVideoPosition(getIntent().getIntExtra("position",-1),videoView.getCurrentPosition());
    }
}
