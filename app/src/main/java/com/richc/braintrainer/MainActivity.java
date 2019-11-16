package com.richc.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout clGame;
    Button startButton;
    TextView sumTextView;
    TextView resultTextView;
    TextView scoreTextView;
    TextView timerTextView;
    Button ulButton;
    Button urButton;
    Button dlButton;
    Button drButton;
    Button playAgainButton;
    ArrayList<Integer> answers = new ArrayList<>();
    int correctAnsLocation;
    int score;
    int numberOfQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clGame = findViewById(R.id.gameConstraintLayout);
        startButton = findViewById(R.id.startButton);
        sumTextView = findViewById(R.id.sumTextView);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);
        ulButton = findViewById(R.id.upLeftButton);
        urButton = findViewById(R.id.upRightButton);
        dlButton = findViewById(R.id.downLeftButton);
        drButton = findViewById(R.id.downRightButton);
        playAgainButton = findViewById(R.id.playAgainbutton);

        playAgain(findViewById(R.id.playAgainbutton));
    }


    // startBtn click
    public void start(View view) {
        startButton.setVisibility(View.INVISIBLE);
        clGame.setVisibility(View.VISIBLE);
    }

    // answerBtn click
    public void chooseAnswer(View view) {
        // Check if correct
        if (Integer.valueOf(view.getTag().toString()).equals(correctAnsLocation)) {

            score++;
            resultTextView.setText("Correct !");
        } else {
            resultTextView.setText("Wrong !");
        }

        numberOfQuestion++;
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestion));

        generateQuestion();
    }

    public void generateQuestion() {

        answers.clear();

        Random rand = new Random();
        int a = rand.nextInt(14);
        int b = rand.nextInt(14);

        sumTextView.setText(String.format("%d",a) + " + " + String.format("%d",b));

        correctAnsLocation = rand.nextInt(4);
        int incorrectAns;

        for (int i=0; i<4; i++) {
            if (i==correctAnsLocation) {
                answers.add(a+b);
            } else {
                incorrectAns = rand.nextInt(40);

                while (incorrectAns == a+b) {
                    incorrectAns = rand.nextInt(40);
                }

                answers.add(incorrectAns);
            }
        }

        ulButton.setText(Integer.toString(answers.get(0)));
        urButton.setText(Integer.toString(answers.get(1)));
        dlButton.setText(Integer.toString(answers.get(2)));
        drButton.setText(Integer.toString(answers.get(3)));
    }

    // playAgainBtn click
    public void playAgain(View view) {

        generateQuestion();

        playAgainButton.setVisibility(View.INVISIBLE);

        scoreTextView.setText("0/0");
        resultTextView.setText("");
        timerTextView.setText("");
        score = 0;
        numberOfQuestion = 0;

        new CountDownTimer(3100, 1000) {
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(Integer.toString((int)millisUntilFinished/1000) + " sec");
            }

            public void onFinish() {
                playAgainButton.setVisibility(View.VISIBLE);
                timerTextView.setText("0s");
                resultTextView.setText("Done");
            }
        }.start();
    }
}
