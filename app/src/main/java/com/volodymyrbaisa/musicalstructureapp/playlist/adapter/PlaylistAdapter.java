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

    public PlaylistAdapter(@NonNull Context context, @LayoutRes int layoutId,
                           @NonNull PlaylistItem[] playlistItems) {
        super(context, 0, playlistItems);
        this.layoutId = layoutId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View root, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if(root != null) {
            holder = (ViewHolder) root.getTag();
        } else {
            root = LayoutInflater.from(getContext()).inflate(layoutId, parent, false);
            holder = new ViewHolder(root);
            root.setTag(holder);
        }

        PlaylistItem playlistItem = getItem(position);

        if(playlistItem != null) {
            holder.soundsTitle.setText(playlistItem.getSoundsTitle());
            holder.records.setText(playlistItem.getRecords());
            holder.soundsTime.setText(playlistItem.getSoundsTime());
        }

        return root;
    }

    static final class ViewHolder {
        @BindView(R.id.sounds_title) TextView soundsTitle;

        @BindView(R.id.records) TextView records;

        @BindView(R.id.sounds_time) TextView soundsTime;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
