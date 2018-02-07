package com.volodymyrbaisa.musicalstructureapp.model;

import java.io.Serializable;

/**
 * Created by Bios on 2/4/2018.
 */

public class PlaylistItem implements Serializable {
    private String records;
    private String coverImage;
    private String soundsTitle;
    private String soundsTime;

    public PlaylistItem(String records, String coverImage, String soundsTitle, String soundsTime) {
        this.records = records;
        this.coverImage = coverImage;
        this.soundsTitle = soundsTitle;
        this.soundsTime = soundsTime;
    }

    public String getRecords() {
        return records;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public String getSoundsTitle() {
        return soundsTitle;
    }

    public String getSoundsTime() {
        return soundsTime;
    }
}
