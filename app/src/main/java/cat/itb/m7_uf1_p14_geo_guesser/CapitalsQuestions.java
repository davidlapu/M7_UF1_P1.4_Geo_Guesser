package cat.itb.m7_uf1_p14_geo_guesser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class CapitalsQuestions {
    private HashMap<String, String> countriesData;
    private String[] capitals;
    private Random random = new Random();

    public CapitalsQuestions(String[] capitals, String[] countries) {
        countriesData = new HashMap<>(capitals.length);
        this.capitals = capitals;
        for (int i = 0; i < capitals.length; i++) {
            countriesData.put(countries[i], capitals[i]);
        }

    }

    public QuestionModel getQuestion() {
        String country = getRandomCountry();
        String trueCapital = countriesData.get(country);
        ArrayList<String> randCapitals = get3randIncorretCapitals(trueCapital);
        String[] possibleAnswers = {trueCapital, randCapitals.get(0), randCapitals.get(1), randCapitals.get(2)};

        QuestionModel questionModel = new QuestionModel(country, possibleAnswers);
        countriesData.remove(country);
        return questionModel;
    }

    private String getRandomCountry(){
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
            }while (capital.equals(trueCapital) || randCapitals.contains(capital));
            randCapitals.add(capital);
        }
        return randCapitals;

    }
}
