package me.imirai.shareplayer.activity;

import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import me.imirai.shareplayer.R;
import me.imirai.shareplayer.adapter.VideoAdapter;
import me.imirai.shareplayer.utils.SharedPreUtil;
import me.imirai.shareplayer.utils.VideoRecord;

public class MainActivity extends AppCompatActivity {

    private RecyclerView videoRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private VideoAdapter adapter;
    private TextView getFileText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreUtil.initSharedPreference(this);
        SharedPreUtil.getInstance().getVideoRecord();

        linearLayoutManager = new LinearLayoutManager(this);
        adapter = new VideoAdapter(this);
        videoRecyclerView = (RecyclerView) findViewById(R.id.video_list);
        assert videoRecyclerView != null;
        videoRecyclerView.setLayoutManager(linearLayoutManager);
        videoRecyclerView.setAdapter(adapter);
        getFileText = (TextView) findViewById(R.id.getfile);
        getFileText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchVideo();
            }
        });
        if (VideoRecord.getInstance().getVideoPathList().size() == 0) {
            searchVideo();
        } else {
            checkList();
        }
    }

    private void checkList() {
        if (adapter.getItemCount() == 0) {
            getFileText.setVisibility(View.VISIBLE);
            Toast.makeText(this, "没有扫描到视频文件", Toast.LENGTH_LONG).show();
        } else {
            getFileText.setVisibility(View.GONE);
        }
    }

    private void searchVideo() {
        File path = null;
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            path = Environment.getExternalStorageDirectory();
            Log.i("tag",path.getPath());
            Toast.makeText(this, "扫描sd卡中", Toast.LENGTH_LONG).show();
            new SearchTask(path).execute();
        }else{
            Toast.makeText(this, "没有SD卡", Toast.LENGTH_LONG).show();
        }
    }

    private void getAllFiles(File root) {
        File files[] = root.listFiles();
        if(files != null) {
            for (File f : files){
                if(f.isDirectory()) {
                    getAllFiles(f);
                } else {
//                    System.out.println(f);
                    if (f.getPath().toLowerCase().endsWith(".mp4")||f.getPath().toLowerCase().endsWith(".avi")) {
                        System.out.println(f.getAbsolutePath());
//                        videoPathList.add(f.getAbsolutePath());
                        VideoRecord.getInstance().setVideoPath(f.getAbsolutePath());
                        System.out.println(f.getName());
                        VideoRecord.getInstance().setVideoName(f.getName());
//                        videoNameList.add(f.getName());
                        VideoRecord.getInstance().setVideoPosition(0);
                    }
                }
            }
        }
    }

    private class SearchTask extends AsyncTask<Void,Void,Void> {

        private File file;

        public SearchTask(File file) {
            this.file = file;
        }

        @Override
        protected Void doInBackground(Void... params) {
            getAllFiles(file);
            adapter.setVideoPathList(VideoRecord.getInstance().getVideoPathList());
            adapter.setVideoNameList(VideoRecord.getInstance().getVideoNameList());
            SharedPreUtil.getInstance().putVideoRecord(VideoRecord.getInstance());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            checkList();
            Log.i("tag",String.valueOf(adapter.getItemCount()));
            adapter.notifyDataSetChanged();
        }
    }
}
