package com.volodymyrbaisa.musicalstructureapp.playlist.adapter;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.volodymyrbaisa.musicalstructureapp.R;
import com.volodymyrbaisa.musicalstructureapp.model.PlaylistItem;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bios on 2/5/2018.
 */

public class PlaylistAdapter extends ArrayAdapter<PlaylistItem> {
    private int layoutId;

    @BindView(R.id.sounds_title)
    TextView soundsTitle;

    @BindView(R.id.records)
    TextView records;

    @BindView(R.id.sounds_time)
    TextView soundsTime;

    public PlaylistAdapter(@NonNull Context context, @LayoutRes int layoutId,
                           @NonNull PlaylistItem[] playlistItems) {
        super(context, 0, playlistItems);

        this.layoutId = layoutId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View root = convertView;
        if(root == null) {
         root = LayoutInflater.from(getContext()).inflate(layoutId, parent, false);
        }
        ButterKnife.bind(this, root);
        PlaylistItem playlistItem = getItem(position);

        if(playlistItem != null) {
            soundsTitle.setText(playlistItem.getSoundsTitle());
            records.setText(playlistItem.getRecords());
            soundsTime.setText(playlistItem.getSoundsTime());
        }

        return root;
    }
}
