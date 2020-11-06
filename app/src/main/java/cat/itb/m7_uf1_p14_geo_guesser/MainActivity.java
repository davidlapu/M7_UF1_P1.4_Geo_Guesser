package cat.itb.m7_uf1_p14_geo_guesser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private CapitalsQuestions capitalsQuestions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        capitalsQuestions = new CapitalsQuestions(getResources().getStringArray(R.array.capitals),
                getResources().getStringArray(R.array.countries));

        QuestionModel as = capitalsQuestions.getQuestion();

        Toast.makeText(this, as.getCountry(),Toast.LENGTH_LONG).show();
        Toast.makeText(this, as.getPossibleAnswers()[0],Toast.LENGTH_LONG).show();
        Toast.makeText(this, as.getPossibleAnswers()[1],Toast.LENGTH_LONG).show();
        Toast.makeText(this, as.getPossibleAnswers()[2],Toast.LENGTH_LONG).show();
        Toast.makeText(this,  as.getPossibleAnswers()[3],Toast.LENGTH_LONG).show();


    }
}