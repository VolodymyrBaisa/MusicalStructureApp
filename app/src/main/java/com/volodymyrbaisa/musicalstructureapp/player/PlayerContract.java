package com.volodymyrbaisa.musicalstructureapp.player;

import com.volodymyrbaisa.musicalstructureapp.BasePresenter;
import com.volodymyrbaisa.musicalstructureapp.BaseView;
import com.volodymyrbaisa.musicalstructureapp.model.PlaylistItem;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Bios on 2/4/2018.
 */

public class PlayerContract {
    interface View extends BaseView<Presenter> {
        InputStream getPlaylistFile() throws IOException;

        void setCoverImage(String coverImage);

        void setPlayerRecords(String records);
    }

    interface Presenter extends BasePresenter {
        void start();

        void setNextSong(int position);

        PlaylistItem[] getPlaylistItems();
    }
}
