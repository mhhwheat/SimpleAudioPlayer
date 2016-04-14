package com.wheat.mobile.simpleaudioplayer;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/11.
 */
public class SimpleAudioPlayer extends Activity
{
    private MediaPlayer myMediaPlayer;
    private List<String> myMusicList=new ArrayList<String>();
    private int currentListItem=0;
    //“Ù¿÷µƒ¬∑æ∂
    private static final String MUSIC_PATH=new String("/sdcard/");

    private ListView musicList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audio_play_main);
        myMediaPlayer=new MediaPlayer();

        initComponent();
        musicList();
    }

    private void initComponent(){
        musicList=(ListView)findViewById(R.id.musicList);
        Button start=(Button)findViewById(R.id.start);
        Button stop=(Button)findViewById(R.id.stop);
        Button next=(Button)findViewById(R.id.next);
        Button pause=(Button)findViewById(R.id.pause);
        Button last=(Button)findViewById(R.id.last);

        musicList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currentListItem=position;

            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myMediaPlayer.isPlaying()) {
                    myMediaPlayer.reset();
                }
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void musicList(){
        File home=new File(MUSIC_PATH);
        if(home.listFiles(new MusicFilter()).length>0){
            for(File file:home.listFiles(new MusicFilter())){
                myMusicList.add(file.getName());
            }
            ArrayAdapter<String> musicArrayAdapter=new ArrayAdapter<String>
                    (SimpleAudioPlayer.this,R.layout.musicitme,myMusicList);
            musicList.setAdapter(musicArrayAdapter);
        }
    }

    private void playMusic(String path){
        try{
            myMediaPlayer.reset();
            myMediaPlayer.setDataSource(path);
            myMediaPlayer.prepare();
            myMediaPlayer.start();
            myMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    nextMusic();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void nextMusic(){
        if(++currentListItem>=myMusicList.size()){
            currentListItem=0;
        }else{
            playMusic(MUSIC_PATH+myMusicList.get(currentListItem));
        }
    }

    private void lastMusic(){
        if(--currentListItem<0){
            currentListItem=myMusicList.size()-1;
        }
        playMusic(MUSIC_PATH+myMusicList.get(currentListItem));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }
}
