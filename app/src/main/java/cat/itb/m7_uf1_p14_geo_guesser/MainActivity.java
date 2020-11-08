package cat.itb.m7_uf1_p14_geo_guesser;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CapitalsQuestions cq;
    private final Button[] buttons = new Button[4];
    private Button buttonHint;
    private TextView textQuestion, counterHint, textHint;
    private QuestionModel qm;
    private ProgressBar progressBarQuestions;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttons[0] = findViewById(R.id.button);
        buttons[1] = findViewById(R.id.button2);
        buttons[2] = findViewById(R.id.button3);
        buttons[3] = findViewById(R.id.button4);
        buttonHint = findViewById(R.id.buttonHint);
        counterHint = findViewById(R.id.textViewHintCounter);
        textHint = findViewById(R.id.textHint);
        for (Button b : buttons) {
            b.setOnClickListener(this);
        }
        textQuestion = findViewById(R.id.textViewQuestion);
        cq = new ViewModelProvider(this, new QuestionModelFactory(getResources().getStringArray(R.array.capitals),
                getResources().getStringArray(R.array.countries), 15)).get(CapitalsQuestions.class);
        progressBarQuestions = findViewById(R.id.progressBar);
        progressBarQuestions.setProgress(cq.getCounter() * 100 / cq.getLimitQuestions());

        buttonHint.setOnClickListener((v) -> giveHint());
        if (cq.isFirstStart()) {
            cq.setFirstStart(false);
            setUpNextQuestion();
        } else {
            rotationSetUp();
        }
        updateHint();


    }

    public void rotationSetUp() {
        qm = cq.getQuestion();
        String[] pa = qm.getPossibleAnswers();
        textQuestion.setText(getString(R.string.capital_question, qm.getCountry()));
        setUpButtons(pa);
        if (cq.getHintLeft() == 0) {
            buttonHint.setBackgroundTintList(AppCompatResources.getColorStateList(MainActivity.this, R.color.colorSecondaryText));
        }
        if (!cq.getLastHint().equals("")) {
            textHint.setText(cq.getLastHint());
            textHint.setVisibility(View.VISIBLE);
        }

    }

    public void setUpNextQuestion() {
        textHint.setVisibility(View.INVISIBLE);
        qm = cq.popQuestion();
        String[] pa = qm.getPossibleAnswers();
        textQuestion.setText(getString(R.string.capital_question, qm.getCountry()));
        setUpButtons(pa);
        cq.setLastHint("");
    }

    public void setUpButtons(String[] pa) {
        int i = 0;

        for (Button b : buttons) {
            b.setText(pa[i]);
            i++;
        }
    }

    public void sout(int s) {
        Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        Button b = (Button) v;

        if (qm.isCapital(b.getText().toString())) {
            sout(R.string.correct);
            cq.answerWasCorrect();
        } else {
            sout(R.string.incorrect);
        }

        if (cq.getCounter() < cq.getLimitQuestions()) {
            setUpNextQuestion();
            cq.answerWasFalse();
        } else {
            alertDialog = dialogBuilder();
            alertDialog.show();
        }

        progressBarQuestions.setProgress(cq.getCounter() * 100 / cq.getLimitQuestions());
    }

    public void giveHint() {
        if (cq.getHintLeft() > 0) {
            String hint = getString(R.string.hintText, cq.getHint());
            textHint.setText(hint);
            updateHint();
            cq.setLastHint(hint);
        } else {
            textHint.setText(R.string.no_more_hints);
        }

        if (cq.getHintLeft() == 0) {
            buttonHint.setBackgroundTintList(AppCompatResources.getColorStateList(MainActivity.this, R.color.colorSecondaryText));
        }

        textHint.setVisibility(View.VISIBLE);
    }

    public void updateHint() {
        counterHint.setText(String.valueOf(cq.getHintLeft()));
    }

    private AlertDialog dialogBuilder() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setTitle(R.string.dialog_title);
        builder.setMessage(getString(R.string.dialog_text, String.valueOf(cq.getScore())));
        builder.setPositiveButton(R.string.dialog_finish, (dialog, which) -> System.exit(0));

        return builder.create();
    }
}