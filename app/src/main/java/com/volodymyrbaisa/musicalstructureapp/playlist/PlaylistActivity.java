package com.volodymyrbaisa.musicalstructureapp.playlist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.volodymyrbaisa.musicalstructureapp.R;
import com.volodymyrbaisa.musicalstructureapp.model.PlaylistItem;
import com.volodymyrbaisa.musicalstructureapp.player.PlayerActivity;
import com.volodymyrbaisa.utils.ActivityUtils;

import butterknife.ButterKnife;

import static com.volodymyrbaisa.utils.PreconditionsUtils.checkNotNull;

/**
 * Created by Bios on 2/4/2018.
 */

public class PlaylistActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        Bundle bundle = getIntent().getExtras();
        PlaylistItem[] playlistItems = (PlaylistItem[]) checkNotNull(bundle).getSerializable(PlayerActivity.PLAYLIST_DATA_KEY);

        PlaylistFragment playlistFragment = (PlaylistFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_playlist_container);
        if (playlistFragment == null) {
            playlistFragment = PlaylistFragment.newInstance(playlistItems);
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    playlistFragment, R.id.fragment_playlist_container);

            new PlaylistPresenter(playlistFragment);
        }
    }
}
