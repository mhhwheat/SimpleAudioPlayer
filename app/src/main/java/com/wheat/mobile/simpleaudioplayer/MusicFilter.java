package com.wheat.mobile.simpleaudioplayer;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Created by Administrator on 2016/4/12.
 */
public class MusicFilter implements FilenameFilter
{

    @Override
    public boolean accept(File dir, String filename) {
        return (filename.endsWith(".mp3"));
    }
}
