package com.music_helper.musichelper.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.music_helper.musichelper.Presenter.MainActivityPresenter;
import com.music_helper.musichelper.Presenter.SoundRecognisionActivityPresenter;
import com.music_helper.musichelper.R;

public class SoundRecognisionActivity extends AppCompatActivity {
    private SoundRecognisionActivityPresenter presenter;

    //TextView textView = (TextView) findViewById(R.id.textViewGiveSound);
    //textView.setText("Podaj pierwszy dzwięk :-)");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_recognision);

        Bundle extras = getIntent().getExtras();
        int mode = (int) extras.get("mode_id");

        presenter = new SoundRecognisionActivityPresenter(this, mode);
        setButtonsListeners();

        TextView textView = (TextView) findViewById(R.id.textViewGiveSound);
        textView.setText("Podaj pierwszy dzwięk :-)");
    }

    public void setButtonsListeners() {
        Button button_1 = findViewById(R.id.buttonStartRecording);
        button_1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.buttonStartRecordingClicked();
            }
        });
        Button button_2 = findViewById(R.id.buttonStopRecording);
        button_2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.buttonStopRecordingClicked();
            }
        });
    }

    public void showInformDialog(String title, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        showInfoTextView("Podaj pierwszy dzwięk :-)");
                    }
                });
        alertDialog.show();
    }

    public void showInfoTextView(String info) {
        TextView tv = (TextView) findViewById(R.id.textViewGiveSound);
        tv.setText(info);
    }

    public void animation(){
        final Animation a = AnimationUtils.loadAnimation(this,
                R.anim.rotate);
        a.setDuration(3000);
        ImageView tv = (ImageView) findViewById(R.id.imageView);
        tv.startAnimation(a);
    }

    public void animationStop(){
        ImageView tv = (ImageView) findViewById(R.id.imageView);
        tv.clearAnimation();
    }
}