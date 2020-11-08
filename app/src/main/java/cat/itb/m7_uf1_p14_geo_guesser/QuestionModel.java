package cat.itb.m7_uf1_p14_geo_guesser;

public class QuestionModel {
    private final String country;
    private final String[] possibleAnswers;
    private final String correctAnswer;

    public QuestionModel(String country, String[] possibleAnswers, String correctAnswer) {
        this.country = country;
        this.possibleAnswers = possibleAnswers;
        this.correctAnswer = correctAnswer;
    }

    public String getCountry() {
        return country;
    }

    public String[] getPossibleAnswers() {
        return possibleAnswers;
    }

    public boolean isCapital(String capital) {
            return capital.equals(correctAnswer);
    }

}
