package M2IM.fr.java.verifier;

public class PieceChangeable extends Piece {

    public PieceChangeable(String part) {
        super(part);
    }

    public String pieceForTeacher() {
       /*
        Retourne morceau de la phrase qu'on modifie en String pour la version 'morceau pour professeur'
        */
        return "#" + part + "#";
    }
    public String pieceForStudent() {
        /*
        Retourne morceau de la phrase qu'on modifie (en l'occurence  ...)
         */
        return " ... ";
    }
    public String pieceForCorrectAnswer() {
        /*
        Retourne morceau de la phrase qu'on modifie en String pour la version 'morceau pour réponse correcte'
        */
        return part;
    }


}
