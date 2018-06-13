package com.music_helper.musichelper.Presenter;

import android.content.Intent;

import com.music_helper.musichelper.R;
import com.music_helper.musichelper.View.MainActivity;
import com.music_helper.musichelper.View.SoundRecognisionActivity;

public class MainActivityPresenter {

    private MainActivity view;

    public MainActivityPresenter(MainActivity view) {
        this.view = view;
    }

    public void startSuondRecognizeingActivity(int id){
        Intent intent = new Intent(view, SoundRecognisionActivity.class);
        intent.putExtra("mode_id", id);
        view.startActivity(intent);
    }

    public void buttonClicked(int R_id){
                startSuondRecognizeingActivity(R_id);
    }
}
