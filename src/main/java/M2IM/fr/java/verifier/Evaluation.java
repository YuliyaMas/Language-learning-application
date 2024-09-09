package M2IM.fr.java.verifier;

import java.util.List;

public interface Evaluation {
    /**
     * Représente la méthode qu'on utilisera pour évaluer la réponse de l'étudiant
     * Correspond au Pattern Strategy
     */
    int doEvaluation(List<ElementForCorrection> correctors);
}
