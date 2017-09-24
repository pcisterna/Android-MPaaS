package com.eje_c.playerservice;

import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;

/**
 * Created by pcisterna on 24-09-17.
 */

public interface EventListener extends ExoPlayer.EventListener {
    @Override
    void onTimelineChanged(Timeline timeline, Object manifest);

    @Override
    void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections);

    @Override
    void onLoadingChanged(boolean isLoading);

    void onPlayerStateChanged(boolean playWhenReady, int playbackState);
    void onPlayWhenReadyCommitted();
    void onPlayerError(ExoPlaybackException error);

    @Override
    void onPositionDiscontinuity();
}
