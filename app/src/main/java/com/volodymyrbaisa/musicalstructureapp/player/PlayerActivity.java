package com.volodymyrbaisa.musicalstructureapp.player;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.volodymyrbaisa.musicalstructureapp.R;
import com.volodymyrbaisa.musicalstructureapp.playlist.PlaylistActivity;
import com.volodymyrbaisa.musicalstructureapp.playlist.PlaylistFragment;
import com.volodymyrbaisa.utils.ActivityUtils;

import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PlayerActivity extends AppCompatActivity implements PlayerContract.View {
    public static final String PLAYLIST_FILE = "playlist.json";
    public static final String PLAYLIST_DATA_KEY = "playlist_data";

    private PlayerContract.Presenter presenter;
    private int playlistPosition;

    @BindView(R.id.player_cover_image)
    ImageView playerCoverImage;

    @BindView(R.id.player_records)
    TextView playerRecords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        ButterKnife.bind(this);
        new PlayerPresenter(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.start();

        setSong();
        setNextSongFragment();
    }

    private void setSong() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            playlistPosition = bundle.getInt(PlaylistFragment.PLAYLIST_POSITION_KEY);
            presenter.setNextSong(playlistPosition);
        } else {
            presenter.setNextSong(playlistPosition);
        }
    }

    private void setNextSongFragment() {
        PlaylistFragment playlistFragment = (PlaylistFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_next_song_container);
        if (playlistFragment == null) {
            playlistFragment = PlaylistFragment.newInstance(presenter.getPlaylistItems(), playlistPosition + 1);
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    playlistFragment, R.id.fragment_next_song_container);
        }
    }

    @Override
    public void setPresenter(PlayerContract.Presenter presenter) {
        this.presenter = presenter;
    }

    public InputStream getPlaylistFile() throws IOException {
        return getAssets().open(PLAYLIST_FILE);
    }

    @Override
    public void setCoverImage(String coverImage) {
        int imageId = getResources().getIdentifier(coverImage, "drawable", getPackageName());
        playerCoverImage.setImageResource(imageId);
    }

    @Override
    public void setPlayerRecords(String records) {
        playerRecords.setText(records);
    }

    @OnClick(R.id.playlist)
    public void onClickPlaylist(View view) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(PLAYLIST_DATA_KEY, presenter.getPlaylistItems());
        ActivityUtils.launchActivity(this, PlaylistActivity.class, bundle);
    }
}
