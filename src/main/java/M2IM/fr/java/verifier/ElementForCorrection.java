package M2IM.fr.java.verifier;

public class ElementForCorrection {
    private ANSWER answer;
    /*
     * La notation attribuée lors de comparaison de la réponse de l'étudiant avec la bonne réponse
     * Est attribuée pour chaque morceau
     * Utilisée pour définir des critères d'évaluation de la réponse de l'étudiant
     */

    private Piece answerStudent;
    /*
    Le morceau obtenu en parsant la réponse de l'étudiant
     */

    private Piece correctAnswer;
    /*
    Le morceau obtenu en parsant la bonne réponse pour comparer avec la réponse de l'étudiant
    Pour l'usage ultérieur (pour le moment la correcte phrase est enregistrée directement dans la BDD de GUI
     */

    public ANSWER getAnswer() {

        return answer;
    }

    public void setCorrection(ANSWER answer) {
        this.answer = answer;
    }

    public Piece getAnswerStudent() {
        return answerStudent;
    }

    public Piece getCorrectAnswer() {
        return correctAnswer;
    }
}
