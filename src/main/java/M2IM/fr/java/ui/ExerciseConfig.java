package M2IM.fr.java.ui;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 ExerciseConfig permet d'introduire dans la base de données des données pour
 tester l'application depuis la partie backend lors du développement
 */
@Configuration
public class ExerciseConfig {
    /*
     * Enregistre dans la base de données des données de la partie Exercice nécessaire pour tester l'application
     */
    @Bean
    CommandLineRunner commandLineRunner2(ExerciceRepository exerciceRepository) {

        return args -> {
            Exercise phrase1 = new Exercise("This is a #test# phrase");
            Exercise phrase2 = new Exercise("It is a #correct# answer");
            Exercise phrase3 = new Exercise("You are a #perfect# student!");
            phrase1.setName("ReadingExercise 1");
            phrase1.setPhraseForCorrectAnswer("This is a test phrase");
            phrase2.setName("ReadingExercise 2");
            phrase2.setPhraseForCorrectAnswer("It is a correct answer");
            phrase3.setName("ReadingExercise 3");
            phrase3.setPhraseForCorrectAnswer("You are a perfect student!");
            List<Exercise> phrasesList = new ArrayList<>();

            phrasesList.add(phrase1);
            phrasesList.add(phrase2);
            phrasesList.add(phrase3);
            exerciceRepository.saveAll(phrasesList);
        };
    }
}
