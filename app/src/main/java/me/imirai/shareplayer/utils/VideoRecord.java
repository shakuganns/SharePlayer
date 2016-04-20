package me.imirai.shareplayer.utils;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Shakugan on 16/4/20.
 */
public class VideoRecord implements Serializable {

    private static VideoRecord ourInstance = new VideoRecord();

    public static VideoRecord getInstance() {
        return ourInstance;
    }

    public static void setInstance(VideoRecord videoRecord) {
        ourInstance = videoRecord;
    }

    private VideoRecord() {
    }

    private ArrayList<String> videoPathList = new ArrayList<>();
    private ArrayList<String> videoNameList = new ArrayList<>();
    private ArrayList<Integer> videoPositions = new ArrayList<>();

    public int getVideoPosition(int index) {
        return videoPositions.get(index);
    }

    public void setVideoPosition(int index,int videoPosition) {
        videoPositions.set(index,videoPosition);
    }

    public void setVideoPosition(int videoPosition) {
        videoPositions.add(videoPosition);
    }

    public String getVideoPath(int index) {
        return videoPathList.get(index);
    }

    public void setVideoPath(String videoPath) {
        videoPathList.add(videoPath);
    }

    public String getVideoName(int index) {
        return videoNameList.get(index);
    }

    public void setVideoName(String videoName) {
        videoNameList.add(videoName);
    }

    public ArrayList<Integer> getVideoPositions() {
        return videoPositions;
    }

    public ArrayList<String> getVideoNameList() {
        return videoNameList;
    }

    public ArrayList<String> getVideoPathList() {
        return videoPathList;
    }

    public void setVideoNameList(ArrayList<String> videoNameList) {
        this.videoNameList = videoNameList;
    }

    public void setVideoPathList(ArrayList<String> videoPathList) {
        this.videoPathList = videoPathList;
    }

    public void setVideoPositions(ArrayList<Integer> videoPositions) {
        this.videoPositions = videoPositions;
    }
}
