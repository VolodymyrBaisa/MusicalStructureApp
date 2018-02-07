package com.volodymyrbaisa.musicalstructureapp.playlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.volodymyrbaisa.musicalstructureapp.R;
import com.volodymyrbaisa.musicalstructureapp.model.PlaylistItem;
import com.volodymyrbaisa.musicalstructureapp.player.PlayerActivity;
import com.volodymyrbaisa.musicalstructureapp.playlist.adapter.PlaylistAdapter;
import com.volodymyrbaisa.utils.ActivityUtils;

import butterknife.ButterKnife;
import butterknife.OnItemClick;

import static com.volodymyrbaisa.utils.PreconditionsUtils.checkNotNull;

/**
 * Created by Bios on 2/5/2018.
 */

public class PlaylistFragment extends Fragment implements PlaylistContract.View {
    public static final String PLAYLIST_DATA_KEY = "playlist_data";
    public static final String PLAYLIST_POSITION_KEY = "playlist_position";

    private PlaylistContract.Presenter presenter;
    PlaylistItem[] playlistItems;

    public static PlaylistFragment newInstance(PlaylistItem[] playlistItems) {
        return newInstance(playlistItems, 0);
    }

    public static PlaylistFragment newInstance(PlaylistItem[] playlistItems, int scrollToPosition) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(PLAYLIST_DATA_KEY, playlistItems);
        bundle.putInt(PLAYLIST_POSITION_KEY, scrollToPosition);
        PlaylistFragment playlistFragment = new PlaylistFragment();
        playlistFragment.setArguments(bundle);
        return playlistFragment;
    }

    @Override
    public void setPresenter(PlaylistContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.playlist_fragment, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
        Bundle bundle = getArguments();
        playlistItems = (PlaylistItem[]) checkNotNull(bundle).getSerializable(PLAYLIST_DATA_KEY);
        int scrollToPosition = checkNotNull(bundle).getInt(PLAYLIST_POSITION_KEY, 0);

        PlaylistAdapter arrayAdapter = new PlaylistAdapter(getContext(), R.layout.list_item_view, playlistItems);
        ListView listView = getActivity().findViewById(R.id.playlist_items);
        listView.setAdapter(arrayAdapter);
        listView.setSelection(scrollToPosition);
    }

    @OnItemClick(R.id.playlist_items)
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        Bundle bundle = new Bundle();
        bundle.putInt(PLAYLIST_POSITION_KEY, position);
        ActivityUtils.launchActivity(getContext(), PlayerActivity.class, bundle);
    }
}
