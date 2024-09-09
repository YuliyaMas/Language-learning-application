package M2IM.fr.java.verifier;

import java.util.List;

public class LevelOfEvaluation {
    /**
     * Permet de définir l'évaluation par rapport au niveau de l'étudiant qu'il choisit lors de création d'un compte
     */
    private Evaluation evaluation;

    public void setEvaluation(Evaluation evaluation) {

        this.evaluation = evaluation;
    }

    public Integer chooseEvaluation(List<ElementForCorrection> correctors, LEVEL level) {
        /*
        L'évaluation définie par rapport au niveau de l'étudiant qu'il choisit lors de création d'un compte
         */
        if (level == LEVEL.BEGINNER) {
            setEvaluation(new EvaluationBeginnerLevel());

        } else if (level == LEVEL.INTERMEDIATE) {
            setEvaluation(new EvaluationIntermediateLevel());
        }
        else if (level == LEVEL.ADVANCED) {
            setEvaluation(new EvaluationAdvancedLevel());
        }

        return evaluation.doEvaluation(correctors);
    }

}
