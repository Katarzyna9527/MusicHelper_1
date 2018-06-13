package com.music_helper.musichelper.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.music_helper.musichelper.Presenter.MainActivityPresenter;
import com.music_helper.musichelper.R;

public class MainActivity extends AppCompatActivity {

    private MainActivityPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainActivityPresenter(this);
        setButtonsListeners();
    }

    public void setButtonsListeners(){
        Button button_1 = findViewById(R.id.button_interval_recognision);
        button_1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.buttonClicked(R.id.button_interval_recognision);
            }
        });
        Button button_2 = findViewById(R.id.button_triad_recognision);
        button_2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.buttonClicked(R.id.button_triad_recognision);
            }
        });
    }


}
