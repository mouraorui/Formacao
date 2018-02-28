package com.example.e5757.courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public int scoreTeamA = 0;
    private TextView TeamAScoreTextView;

    public int scoreTeamB = 0;
    private TextView TeamBScoreTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button Reset_button = findViewById(R.id.Reset_button);
        Reset_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scoreTeamA = 0;
                scoreTeamB = 0;
                displayScoreA(scoreTeamA);
                displayScoreB(scoreTeamB);
            }
        });

        TeamAScoreTextView = (TextView) findViewById(R.id.TeamA_score_text_view);
        TeamAScoreTextView.setText(String.valueOf(scoreTeamA));

        Button Aplus3_button = findViewById(R.id.Aplus3_button);
        Aplus3_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incrementThree(1);
            }
        });

        Button Aplus2_button = findViewById(R.id.Aplus2_button);
        Aplus2_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incrementTwo(1);
            }
        });

        Button AfreeThrow_button = findViewById(R.id.AfreeThrow_button);
        AfreeThrow_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incrementOne(1);
            }
        });

        TeamBScoreTextView = (TextView) findViewById(R.id.TeamB_score_text_view);
        TeamBScoreTextView.setText(String.valueOf(scoreTeamB));

        Button Bplus3_button = findViewById(R.id.Bplus3_button);
        Bplus3_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incrementThree(2);
            }
        });

        Button Bplus2_button = findViewById(R.id.Bplus2_button);
        Bplus2_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incrementTwo(2);
            }
        });

        Button BfreeThrow_button = findViewById(R.id.BfreeThrow_button);
        BfreeThrow_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incrementOne(2);
            }
        });

    }

    private void incrementThree(int number) {
        if(number == 1){
            scoreTeamA = scoreTeamA + 3 ;
            displayScoreA(scoreTeamA);
        }
        if(number == 2){
            scoreTeamB = scoreTeamB + 3 ;
            displayScoreB(scoreTeamB);
        }

    }

    private void incrementTwo(int number) {
        if(number == 1){
            scoreTeamA = scoreTeamA + 2 ;
            displayScoreA(scoreTeamA);
        }
        if(number == 2){
            scoreTeamB = scoreTeamB + 2 ;
            displayScoreB(scoreTeamB);
        }

    }

    private void incrementOne(int number) {
        if(number == 1){
            scoreTeamA = scoreTeamA + 1 ;
            displayScoreA(scoreTeamA);
        }
        if(number == 2){
            scoreTeamB = scoreTeamB + 1 ;
            displayScoreB(scoreTeamB);
        }

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayScoreA(int number) {

        TeamAScoreTextView.setText(String.valueOf(number));
    }

    private void displayScoreB(int number) {

        TeamBScoreTextView.setText(String.valueOf(number));
    }
}
