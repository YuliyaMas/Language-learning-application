package M2IM.fr.java.verifier;

import java.util.ArrayList;
import java.util.List;


public class Correction {

    /**
     * @param phraseStudent la réponse donnée par étudiant, correspond à chaque phrase de l'exercice
     * @param phraseTeacher la phrase proposée par professeur, correspond à chaque phrase de l'exercice
     * @return une liste d'éléments appliqués pour correction @see ANSWER
     */
    public List<ElementForCorrection> verify(Phrase phraseStudent, Phrase phraseTeacher) {
        List<ElementForCorrection> correctors = new ArrayList<>();
        PieceChangeable pieceChangeable = new PieceChangeable("str"); // utilisé pour test
        if (phraseStudent.getFixeAndChangeablePieces() != null) {
            for (int i = 0; i < phraseStudent.getFixeAndChangeablePieces().size(); i++) {
                ElementForCorrection corrector = new ElementForCorrection();
                if (phraseStudent.getFixeAndChangeablePieces().get(i).pieceForCorrectAnswer().equals(pieceChangeable.pieceForStudent())) {
                    corrector.setCorrection(ANSWER.NA);
                    System.out.println(corrector.getAnswer());
                } else if (phraseStudent.getFixeAndChangeablePieces().get(i).pieceForCorrectAnswer().
                        equals(phraseTeacher.getPiecesTeacher().get(i).pieceForCorrectAnswer())) {
                    corrector.setCorrection(ANSWER.TRUE);
                    System.out.println(corrector.getAnswer());
                } else {
                    corrector.setCorrection(ANSWER.FALSE);
                    System.out.println(corrector.getAnswer());
                }
                correctors.add(corrector);
            }

        }
        return correctors;
    }
}

