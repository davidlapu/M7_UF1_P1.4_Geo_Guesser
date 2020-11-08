package cat.itb.m7_uf1_p14_geo_guesser;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class QuestionModelFactory implements ViewModelProvider.Factory {
    private final String[] capitals;
    private final String[] countries;
    private final int limitQuestions;

    public QuestionModelFactory(String[] capitals, String[] countries, int limitQuestions) {
        this.capitals = capitals;
        this.countries = countries;
        this.limitQuestions = limitQuestions;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new CapitalsQuestions(capitals,
                countries, limitQuestions);
    }
}
