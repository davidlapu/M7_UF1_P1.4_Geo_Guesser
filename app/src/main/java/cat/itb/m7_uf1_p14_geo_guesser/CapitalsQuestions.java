package cat.itb.m7_uf1_p14_geo_guesser;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class CapitalsQuestions extends ViewModel {
    private final HashMap<String, String> countriesData;
    private final String[] capitals;
    private final Random random = new Random();
    private int questionCounter = 0;
    private final int limitQuestions;
    private int hintLeft = 3;
    private double score = 0;
    private boolean hintWasUsed = false;
    private String hint;
    private boolean isFirstStart = true;
    private QuestionModel questionModel;
    private String lastHint = "";

    public CapitalsQuestions(String[] capitals, String[] countries, int limitQuestions) {
        countriesData = new HashMap<>(capitals.length);
        this.capitals = capitals;
        this.limitQuestions = limitQuestions;
        if (countriesData.isEmpty()) {
            for (int i = 0; i < capitals.length; i++) {
                countriesData.put(countries[i], capitals[i]);
            }
        }
    }

    public QuestionModel popQuestion() {
        hintWasUsed = false;
        String country = getRandomCountry();
        String trueCapital = countriesData.get(country);
        ArrayList<String> capitals = new ArrayList<>(4);

        capitals.addAll(get3randIncorretCapitals(trueCapital));
        capitals.add(trueCapital);
        Collections.shuffle(capitals);

        QuestionModel questionModel = new QuestionModel(country, capitals.toArray(new String[4]), trueCapital);
        countriesData.remove(country);
        questionCounter++;
        hint = Character.toString(trueCapital.charAt(0));
        this.questionModel = questionModel;
        return questionModel;
    }

    public QuestionModel getQuestion() {
        return questionModel;
    }

    private String getRandomCountry() {
        Object[] values = countriesData.keySet().toArray();
        Object randomValue = values[random.nextInt(values.length)];
        return (String) randomValue;
    }

    private ArrayList<String> get3randIncorretCapitals(String trueCapital) {
        ArrayList<String> randCapitals = new ArrayList<>(3);
        String capital;

        for (int i = 0; i < 3; i++) {
            do {
                capital = capitals[random.nextInt(capitals.length)];
            } while (capital.equals(trueCapital) || randCapitals.contains(capital));
            randCapitals.add(capital);
        }
        return randCapitals;

    }

    public int getCounter() {
        return questionCounter;
    }

    public void resetCounter() {
        questionCounter = 0;
    }

    public int getLimitQuestions() {
        return limitQuestions;
    }

    public int getHintLeft() {
        return hintLeft;
    }

    public String getHint() {
        hintLeft -= 1;
        hintWasUsed = true;
        return hint;
    }

    public void answerWasCorrect() {
        if (!hintWasUsed) {
            score += 1;
        }
    }

    public void answerWasFalse() {
        score -= 0.5;
    }

    public int getScore() {
        double decimalScore = (double) score * 100 / (limitQuestions - 1);
        return (int) decimalScore;
    }

    public boolean isFirstStart() {
        return isFirstStart;
    }

    public void setFirstStart(boolean firstStart) {
        this.isFirstStart = firstStart;
    }

    public String getLastHint() {
        return lastHint;
    }

    public void setLastHint(String lastHint) {
        this.lastHint = lastHint;
    }
}
