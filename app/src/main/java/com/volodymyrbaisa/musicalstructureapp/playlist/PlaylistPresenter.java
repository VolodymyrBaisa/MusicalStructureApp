package com.volodymyrbaisa.musicalstructureapp.playlist;

/**
 * Created by Bios on 2/5/2018.
 */

public class PlaylistPresenter implements PlaylistContract.Presenter {
    private PlaylistContract.View playlistView;

    public PlaylistPresenter(PlaylistContract.View playlistView) {
        this.playlistView = playlistView;
        playlistView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
