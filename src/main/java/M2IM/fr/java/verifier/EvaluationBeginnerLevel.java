package M2IM.fr.java.verifier;

import java.util.List;

public class EvaluationBeginnerLevel implements Evaluation {

    /**
     * Représente la méthode qu'on utilisera pour évaluer la réponse de l'étudiant ayant le niveau Débutant
     */
    public EvaluationBeginnerLevel() {
    }

    @Override
    public int doEvaluation(List<ElementForCorrection> correctors) {
        int points = 0;
        for (int i = 0; i < correctors.size(); i++) {
            if (correctors.get(i).getAnswer() == (ANSWER.TRUE)) {
                points += 1;
            } else if (correctors.get(i).getAnswer() == ANSWER.NA) {
                points += -1;
            } else if (correctors.get(i).getAnswer() == ANSWER.FALSE) {
                points += 0;
            }
        }
        return points;
    }
}