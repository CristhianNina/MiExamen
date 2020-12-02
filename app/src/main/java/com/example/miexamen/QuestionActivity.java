package com.example.miexamen;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView question, qCount, timer;
    private Button option1, option2, option3, option4;
    private List<Question> questionList;
    int quesNum;
    private CountDownTimer countDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        question = findViewById(R.id.question);
        qCount = findViewById(R.id.quest_num);
        timer = findViewById(R.id.countdown);

        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);

        option1.setOnClickListener(this);
        option2.setOnClickListener(this);
        option3.setOnClickListener(this);
        option4.setOnClickListener(this);

        getQuestionsList();

    }

    private void getQuestionsList(){

        questionList = new ArrayList<>();
        questionList.add(new Question("Question 1", "A", "B", "C", "D", 2));
        questionList.add(new Question("Question 2", "B", "B", "D", "A", 2));
        questionList.add(new Question("Question 3", "C", "B", "A", "D", 2));
        questionList.add(new Question("Question 4", "A", "D", "C", "B", 2));
        questionList.add(new Question("Question 5", "B", "D", "A", "D", 2));

    }

    private void setQuestion(){

        timer.setText(String.valueOf(10));

        question.setText(questionList.get(0).getQuestion());
        option1.setText(questionList.get(0).getOptionA());
        option2.setText(questionList.get(0).getOptionB());
        option3.setText(questionList.get(0).getOptionC());
        option4.setText(questionList.get(0).getOptionD());

        qCount.setText(String.valueOf(1)+ "/" + String.valueOf(questionList.size()));   //contador de preguntas 2/5
        startTimer();

        quesNum = 0;    //numero de preguntas comienza en 0

    }

    private void startTimer(){  // cuenta atrás

        countDown = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                timer.setText(String.valueOf(millisUntilFinished / 1000));

            }

            @Override
            public void onFinish() {        //cuando finalizara la cuenta regresiva ejecutará este código
                changeQuestion();
            }
        };

        countDown.cancel();

        countDown.start();

    }


    @Override
    public void onClick(View v) {

        int selectedOption = 0;

        switch (v.getId()){

            case R.id.option1:
                 selectedOption = 1;
                 break;
            case R.id.option2:
                 selectedOption = 2;
                 break;
            case R.id.option3:
                 selectedOption = 3;
                 break;
            case R.id.option4:
                 selectedOption = 4;
                 break;
            default:
        }

        countDown.cancel();
        checkAnswer(selectedOption, v);


    }

    private void checkAnswer(int selectedOption, View view){ //Al seleccionar un botón

        if (selectedOption == questionList.get(quesNum).getCorrectAns())
        {
            //Respuesta Correcta
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ((Button)view).setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
            }


        }
        else {
            //Respuesta incorrecta
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ((Button)view).setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            }

            switch (questionList.get(quesNum).getCorrectAns()){

                case 1:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        option1.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    }
                case 2:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        option1.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    }
                    break;
                case 3:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        option1.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    }
                    break;
                case 4:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        option1.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    }
                    break;

            }
        }


        changeQuestion();

    }

    private void changeQuestion(){

        if (quesNum < questionList.size() - 1){     // si el numero de preguntas es menor a la cantidad de preguntas que hay en la lista

            quesNum++;

            playAnim(question, 0, 0); //animación al cambiar de pregunta
            playAnim(option1, 0,1);
            playAnim(option2, 0,2);
            playAnim(option3, 0,3);
            playAnim(option4, 0,4);

            qCount.setText(String.valueOf(quesNum+1) + "/" + String.valueOf(questionList.size()));

            timer.setText(String.valueOf(10));

            startTimer();

        }
        else {
            //Ir a la activity de puntuación
            Intent intent = new Intent(QuestionActivity.this, ScoreActivity.class);
            startActivity(intent);
            finish();
        }


    }

    //ANIMACIóNes para el cambio de preguntas con los botones
    private void playAnim(View view, final int value, int viewNum){
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (value == 0){
                    switch (viewNum){
                        case 0:
                            ((TextView)view).setText(questionList.get(quesNum).getQuestion());
                            break;
                        case 1:
                            ((Button)view).setText(questionList.get(quesNum).getOptionA());
                            break;
                        case 2:
                            ((Button)view).setText(questionList.get(quesNum).getOptionB());
                            break;
                        case 3:
                            ((Button)view).setText(questionList.get(quesNum).getOptionC());
                            break;
                        case 4:
                            ((Button)view).setText(questionList.get(quesNum).getOptionD());
                            break;

                    }
                    playAnim(view,1,viewNum);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }

}