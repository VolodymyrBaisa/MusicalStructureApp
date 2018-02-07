package com.volodymyrbaisa.musicalstructureapp.player;

import com.google.gson.Gson;
import com.volodymyrbaisa.musicalstructureapp.model.PlaylistItem;
import com.volodymyrbaisa.utils.IOUtils;

import java.io.IOException;

/**
 * Created by Bios on 2/4/2018.
 */

public class PlayerPresenter implements PlayerContract.Presenter {
    private PlayerContract.View playerView;
    private PlaylistItem[] playlistItems;

    public PlayerPresenter(PlayerContract.View playerView) {
        this.playerView = playerView;
        playerView.setPresenter(this);
    }

    @Override
    public void start() {
        fetchingData();
    }

    @Override
    public void setNextSong(int position) {
        if(position < playlistItems.length) {
            playerView.setCoverImage(playlistItems[position].getCoverImage());
            playerView.setPlayerRecords(playlistItems[position].getRecords());
        }
    }

    @Override
    public PlaylistItem[] getPlaylistItems(){
        return playlistItems;
    }

    private void fetchingData() {
        try {
            playlistItems = new Gson().fromJson(IOUtils.toString(playerView.getPlaylistFile()), PlaylistItem[].class);
        } catch (IOException e) {
            throw new IllegalArgumentException("Unable to load " + PlayerActivity.PLAYLIST_FILE, e);
        }
    }
}
