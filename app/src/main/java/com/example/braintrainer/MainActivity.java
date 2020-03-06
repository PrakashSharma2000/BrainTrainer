package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
	TextView GoButton,timertext,textView,questiontext,resulttextview;
	Button Button1,Button2,Button3,Button4,playagainbt;
	ConstraintLayout  gameLayout ;
	ArrayList<Integer> answer = new ArrayList<Integer>();
	int locationofcorrectanswer;
	int score =0;
	int numberofquestion=0;



	TextView scoretext ;

	public void chooseAnswer(View view){
		resulttextview=findViewById(R.id.resulttextview);
		resulttextview.setVisibility(View.VISIBLE);
		if(Integer.toString(locationofcorrectanswer).equals(view.getTag().toString())){
			Log.i("Tag","Correct! You got this");
			resulttextview.setText("Correct! yay");
			score++;

		}

		else {
			Log.i("Tag","Wrong");
			resulttextview.setText("Wrong!!!");
		}
		numberofquestion++;
		scoretext.setText(Integer.toString(score)+"/"+Integer.toString(numberofquestion));
		newQuestion();

	}

	public void start(View view){

        GoButton.setVisibility(View.INVISIBLE);
        PLAYagain(findViewById(R.id.playagainbt));
        gameLayout.setVisibility(View.VISIBLE);
        newQuestion();

	}
	public void newQuestion(){
		Random random = new Random();
		int a=random.nextInt(21);
		int b=random.nextInt(21);

		questiontext.setText(String.valueOf(a)+"+"+String.valueOf(b));
		locationofcorrectanswer =random.nextInt(4);

		answer.clear();
		for (int i =0;i<4;i++){
			if(i==locationofcorrectanswer) {
				answer.add(a+b);
			}
			else{
				int wronganswer= random.nextInt(42);
				while (wronganswer==(a+b))
				{
					wronganswer=random.nextInt(42);

				}
				answer.add(wronganswer);
			}
		}
		Button1.setText(String.valueOf(answer.get(0)));
		Button2.setText(String.valueOf(answer.get(1)));
		Button3.setText(String.valueOf(answer.get(2)));
		Button4.setText(String.valueOf(answer.get(3)));
	}
 public void PLAYagain(View view){
		playagainbt.setVisibility(View.INVISIBLE);

	 newQuestion();

	 score = 0;
	 numberofquestion =0;
	 scoretext.setText(Integer.toString(score)+"/"+Integer.toString(numberofquestion));
	 CountDownTimer countDownTimer = new CountDownTimer(31000,1000) {
		 @Override
		 public void onTick(long millisUntilFinished) {
			 timertext.setText(String.valueOf(millisUntilFinished/1000));

			 playagainbt.setVisibility(View.INVISIBLE);
		 }

		 @Override
		 public void onFinish() {
			 playagainbt.setVisibility(View.VISIBLE);
            resulttextview.setText("Times UP!!!");
		 }
	 }.start();

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		GoButton = (Button)findViewById(R.id.goButton);
		Button1=findViewById(R.id.button1);
		Button2=findViewById(R.id.button2);
		Button3=findViewById(R.id.button3);
		Button4=findViewById(R.id.button4);
		timertext =findViewById(R.id.timertext);
        questiontext =findViewById(R.id.questiontext);
        scoretext = findViewById(R.id.scoreboard);
        playagainbt = findViewById(R.id.playagainbt);
        gameLayout  = findViewById(R.id.gamelayout);
        resulttextview = findViewById(R.id.resulttextview);

        newQuestion();

       GoButton.setVisibility(View.VISIBLE);

        gameLayout.setVisibility(View.INVISIBLE);
	}
}
