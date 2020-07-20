package com.example.unquote;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int currentQuestionIndex;
    int totalCorrect;
    int totalQuestions;
    ArrayList<Question> questions;


    ImageView questionImageView;
    TextView questionTextView;
    TextView questionsRemainingTextView;
    Button answer0Button;
    Button answer1Button;
    Button answer2Button;
    Button answer3Button;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_unquote_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setElevation(0);
        setContentView(R.layout.activity_main);

        questionImageView = findViewById(R.id.iv_main_question_image);
        questionTextView = findViewById(R.id.tv_main_question_title);
        questionsRemainingTextView = findViewById(R.id.tv_main_questions_remaining_count);
        answer0Button = findViewById(R.id.btn_main_answer_0);
        answer1Button = findViewById(R.id.btn_main_answer_1);
        answer2Button = findViewById(R.id.btn_main_answer_2);
        answer3Button = findViewById(R.id.btn_main_answer_3);
        submitButton = findViewById(R.id.btn_main_submit_answer);

        answer0Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAnswerSelected(0);
            }
        });

        answer1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAnswerSelected(1);
            }
        });

        answer2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAnswerSelected(2);
            }
        });

        answer3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAnswerSelected(3);
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAnswerSubmission();
            }
        });
        startNewGame();
    }


    public void displayQuestion(Question question) {
        questionImageView.setImageResource(question.imageId);
        questionTextView.setText(question.questionText);
        answer0Button.setText(question.answer0);
        answer1Button.setText(question.answer1);
        answer2Button.setText(question.answer2);
        answer3Button.setText(question.answer3);
    }

    public void displayQuestionsRemaining(int questionsRemaining) {
        questionsRemainingTextView.setText(String.valueOf(questionsRemaining));
    }

    public void onAnswerSelected(int answerSelected) {
        Question currentQuestion = getCurrentQuestion();
        currentQuestion.playerAnswer = answerSelected;
        answer0Button.setText(currentQuestion.answer0);
        answer1Button.setText(currentQuestion.answer1);
        answer2Button.setText(currentQuestion.answer2);
        answer3Button.setText(currentQuestion.answer3);


        switch(answerSelected) {
            case 0:
                answer0Button.setText("✔ " + currentQuestion.answer0);
                break;
            case 1:
                answer1Button.setText("✔ " + currentQuestion.answer1);
                break;
            case 2:
                answer2Button.setText("✔ " + currentQuestion.answer2);
                break;
            case 3:
                answer3Button.setText("✔ " + currentQuestion.answer3);
                break;
        }
    }
    public void onAnswerSubmission() {
        Question currentQuestion = getCurrentQuestion();
        if (currentQuestion.isCorrect()) {
            totalCorrect = totalCorrect + 1;
        }

        if(currentQuestion.playerAnswer == -1) {
            return;
        }

        questions.remove(currentQuestion);


        displayQuestionsRemaining(questions.size());

        if (questions.size() == 0) {
            String gameOverMessage = getGameOverMessage(totalCorrect, totalQuestions);


            AlertDialog.Builder gameOverDialogBuilder = new AlertDialog.Builder(MainActivity.this);
            gameOverDialogBuilder.setCancelable(false);
            gameOverDialogBuilder.setTitle("Game over!");
            gameOverDialogBuilder.setMessage(gameOverMessage);
            gameOverDialogBuilder.setPositiveButton("Play Again!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startNewGame();
                }
            });
            gameOverDialogBuilder.create().show();
        } else {
            chooseNewQuestion();


            displayQuestion(getCurrentQuestion());
        }
    }

    public void startNewGame() {
        questions = new ArrayList<>();


        Question question0 = new Question(R.drawable.img_quote_0, "Pretty good advice,\n" +
                "and perhaps a scientist\n" +
                "did say it… Who\n" +
                "actually did?", "Albert Einstein", "Issac Newton", "Rita Mae Brown", "Rosalind Franklin", 2);


        Question question1 = new Question(R.drawable.img_quote_1, "Was honest Abe\n" +
                "honestly quoted? Who\n" +
                "authored this pithy bit\n" +
                "of wisdom?", "Edward Stieglitz", "Maya Angelou", "Abraham Lincoln", "Ralph Waldo Emerson", 0);


        Question question2 = new Question(R.drawable.img_quote_2, "Easy advice to read,\n" +
                "difficult advice to\n" +
                "follow — who actually \n said it?", "Martin Luther King Jr.", "Mother Teresa", "Fred Rogers", "Oprah Winfrey", 1);

        Question question3 = new Question(R.drawable.img_quote_3, "Insanely inspiring,\n" +
                "insanely incorrect\n" +
                "(maybe). Who is the\n" +
                "true source of this\n" +
                "inspiration?", "Nelson Mandela", "Harriet Tubman", "Mahatma Gandhi", "Nicholas Klein", 3);

        Question question4 = new Question(R.drawable.img_quote_4, "A peace worth striving\n" +
                "for — who actually\n" +
                "reminded us of this?", "Malala Yousafzai", "Martin Luther King Jr.", "Liu Xiaobo", "Dalai Lama", 1);
        Question question5 = new Question(R.drawable.img_quote_5, "Unfortunately, true —\n" +
                "but did Marilyn Monroe\n" +
                "convey it or did\n" +
                "someone else?", "Laurel Thatcher Ulrich", "Eleanor Roosevelt", "Marilyn Monroe", "Queen Victoria", 0);

        Question question6  = new Question(R.drawable.img_quote_6, "Here’s the truth, Will Smith did\n" +
                "say this, but in which movie?", "Independence Day", "Bad Boys", "The men in black", "The pursuit of Happiness", 2);

        Question question7 = new Question(R.drawable.img_quote_7, "Which TV funny gal actually\n" +
                "quipped this 1-liner?", "Ellen Degeneres", "Amy Poehler", "Betty White", "Tina Fay", 3);

        Question question8 = new Question(R.drawable.img_quote_8, "This mayor won’t get my vote —\n" +
                "but did he actually give this\n" +
                "piece of advice? And if not, who\n" +
                "did?\n", "Forrest Gump, \n Forrest Gump", "Dorry, \n Finding Nemo", "Esther Williams", "The mayor, Jaws", 1);

        Question question9 = new Question(R.drawable.img_quote_9, "Her heart will go on, but whose\n" +
                "heart is it?", "Whitney Houston", "Diana Ross", "Celine Dion", "Mariah Carey", 0);

        Question question10 = new Question(R.drawable.img_quote_10, "He’s the king of something\n" +
                "alright — to whom does this\n" +
                "self-titling line belong to?", "Tony Montana,\n" +
                "Scarface", "Joker, The Dark\n" +
                "Knight", "Lex Luthor,\n" +
                "Batman v Superman", "Jack, Titanic", 3);

        Question question11 = new Question(R.drawable.img_quote_11, "Is “Grey” synonymous for\n" +
                "“wise”? If so, maybe Gandalf did\n" +
                "preach this advice. If not, who\n" +
                "did?", "Yoda, star wars", "Gandalf The Grey,\n" +
                "Lord of the Rings", "Dumbledore, Harry Potter", "Uncle Ben, Spiderman", 0);

        Question question12 = new Question(R.drawable.img_quote_12, "Houston, we have a problem\n" +
                "with this quote — which\n" +
                "space-traveler does this\n" +
                "catch-phrase actually belong to?", "Han solo, star wars", "Captain Kirk, Star \n trek", "Buzz Lightyear, Toy \n story", "Jim Lovell, Apollo 13", 2);
        questions.add(question0);
        questions.add(question1);
        questions.add(question2);
        questions.add(question3);
        questions.add(question4);
        questions.add(question5);
        questions.add(question6);
        questions.add(question7);
        questions.add(question8);
        questions.add(question9);
        questions.add(question10);
        questions.add(question11);
        questions.add(question12);

        totalCorrect = 0;
        totalQuestions = questions.size();

        while(questions.size() > 6) {
           int randomIndex =  generateRandomNumber(questions.size());
           questions.remove(randomIndex);
        }

        Question firstQuestion = chooseNewQuestion();


        displayQuestionsRemaining(questions.size());


        displayQuestion(firstQuestion);
    }

    Question chooseNewQuestion() {
        int newQuestionIndex = generateRandomNumber(questions.size());
        currentQuestionIndex = newQuestionIndex;
        return questions.get(currentQuestionIndex);
    }

    int generateRandomNumber(int max) {
        double randomNumber = Math.random();
        double result = max * randomNumber;
        return (int) result;
    }

    Question getCurrentQuestion() {
        Question currentQuestion = questions.get(currentQuestionIndex);
        return currentQuestion;
    }

    String getGameOverMessage(int totalCorrect, int totalQuestions) {
        if (totalCorrect == totalQuestions) {
            return "You got all " + totalQuestions + " right! You won!";
        } else {
            return "You got " + totalCorrect + " right out of " + totalQuestions + ". Better luck next time!";
        }
    }
}

