package M2IM.fr.java.verifier;

public class Piece {
    /**
     Represente un morceau qu'on obtient en parsant chaque phrase de l'exercice
     */
    String part;
    /*
    Le morceau en version String
     */

    public Piece(String part) {
        this.part = part;
    }

    public String pieceForTeacher() {
        /*
        Retourne morceau de la phrase qu'on ne modifie pas en String pour la versin 'morceau pour professeur'
         */
        return part;
    }

    public String pieceForStudent() {
        /*
        Retourne morceau de la phrase qu'on ne modifie pas en String pour la version 'morceau pour étudiant'
         */
        return part;
    }

    public String pieceForCorrectAnswer() {
        /*
        Retourne morceau de la phrase qu'on ne modifie pas en String pour la version 'morceau pour correct phrase'
         */
        return part;
    }

    @Override
    public String toString() {
        return part ;
    }
}

