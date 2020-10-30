package cat.itb.m7_uf1_p14_geo_guesser;

public class QuestionModel {
    private String question, answer, hint;

    public QuestionModel(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

}
