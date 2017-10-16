package com.example.android.i_am_baker;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.i_am_baker.network.Json_Type;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

/**
 * Created by ROHAN on 04-10-2017.
 */

public class VideoFragment extends Fragment {

    public VideoFragment(){
        //Mandantory constructor for instantiating
    }

    SimpleExoPlayerView exoPlayerView;
    SimpleExoPlayer exoPlayer;

    Uri videoUri;
    String position;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.video_fragment, container, false);

        exoPlayerView = (SimpleExoPlayerView) rootView.findViewById(R.id.exoplayer_view);

         position = this.getArguments().getString("pos").toString();




        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
        exoPlayer = ExoPlayerFactory.newSimpleInstance(getActivity(), trackSelector);
        return rootView;
    }
    Json_Type obj = new Json_Type();

    public void set_video() {
               videoUri = Uri.parse(obj.return_video(Integer.parseInt(position)));
               DefaultHttpDataSourceFactory dataSource = new DefaultHttpDataSourceFactory("exoplayer");
               ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
               MediaSource videoSource = new ExtractorMediaSource(videoUri, dataSource, extractorsFactory, null, null);

               exoPlayerView.setPlayer(exoPlayer);
               exoPlayer.prepare(videoSource);
               exoPlayer.setPlayWhenReady(true);

           }








    long position1;

    @Override
    public void onPause() {
        super.onPause();
        if (exoPlayer!=null) {
            position1 = exoPlayer.getCurrentPosition();
            exoPlayer.stop();
            exoPlayer.release();

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (exoPlayer != null);
        exoPlayer.seekTo(position1);
        set_video();


    }
    @Override
    public void onSaveInstanceState(Bundle currentState) {
        super.onSaveInstanceState(currentState);

        currentState.putLong("position_of_player",position1);

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState!=null) {
           position1 = savedInstanceState.getLong("position_of_player");

        }
    }


}
