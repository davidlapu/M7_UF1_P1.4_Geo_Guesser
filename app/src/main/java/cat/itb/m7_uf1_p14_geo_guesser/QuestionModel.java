package cat.itb.m7_uf1_p14_geo_guesser;

public class QuestionModel {
    private String country;
    private String[] possibleAnswers = new String[4];

    public QuestionModel() {
    }

    public QuestionModel(String country, String[] possibleAnswers) {
        this.country = country;
        this.possibleAnswers = possibleAnswers;
    }

    public String getCountry() {
        return country;
    }

    public String[] getPossibleAnswers() {
        //todo make random
        return possibleAnswers;
    }

    public boolean isCapital(String capital) {
            return capital.equals(possibleAnswers[0]);
    }

}
