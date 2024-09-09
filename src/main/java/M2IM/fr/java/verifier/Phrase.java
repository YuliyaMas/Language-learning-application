package M2IM.fr.java.verifier;

import java.util.ArrayList;
import java.util.List;

public class Phrase {
     /**
        Représente la réponse de l'étudiant (string) et les morceaux obtenus après parsing de la phrase correspondante
        dans la version 'pour professeur' (avec #)
         */

    private List<Piece> fixeAndChangeablePieces = new ArrayList<>();
    private List<Piece> piecesTeacher;
    public Phrase(List<Piece> piecesTeacher) {

        this.piecesTeacher = piecesTeacher;
    }


    public List<Piece> analyseStudentAnswer(String studentPhrase) throws NoSearchFixePieceException {
        /*
        * Permet de comparer la réponse de l'étudiant avec les morceaux obtenus après parsing de la phrase correspondante
        * dans la version 'pour professeur' (avec #)
        * Retourne la réponse de l'étudiant sous forme de List de morceaux (retrouvés dans la liste de morceaux
        * de la phrase 'pour professeur')
        * Si un morceau fixe n'est pas correctement réécrit, à l'étape de correction, l'étudiant obtient un message
        * d'erreur
         */
        if (studentPhrase != null && studentPhrase.length() != 0) {
            int index = 0;
            int length = 0;
            for (int i = 0; i < piecesTeacher.size(); i++) {
                if (!(piecesTeacher.get(i) instanceof PieceChangeable)) {
                    index = studentPhrase.indexOf(piecesTeacher.get(i).pieceForCorrectAnswer());
                    if (index == -1)
                        throw new NoSearchFixePieceException("This part of phrase is not correct: ", piecesTeacher.get(i));
                    length = piecesTeacher.get(i).pieceForCorrectAnswer().length();
                    fixeAndChangeablePieces.add(new Piece(studentPhrase.substring(index, index + length).strip()));
                } else {
                    if (i < piecesTeacher.size() - 1) {
                        int indexNextPiece = studentPhrase.indexOf(piecesTeacher.get(i + 1).pieceForCorrectAnswer());
                        if (indexNextPiece == -1)
                            throw new NoSearchFixePieceException("This part of phrase is not correct: ", piecesTeacher.get(i + 1));
                        fixeAndChangeablePieces.add(new Piece(studentPhrase.substring(index + length, indexNextPiece).strip()));
                    } else {
                        fixeAndChangeablePieces.add(new Piece(studentPhrase.substring(index + length).strip()));
                    }
                }
            }
        }
        return fixeAndChangeablePieces;
    }
    public List<Piece> getPiecesTeacher() {

        return piecesTeacher;
    }

    public List<Piece> getFixeAndChangeablePieces() {

        return fixeAndChangeablePieces;
    }

    public Phrase setFixeAndChangeablePieces(List<Piece> fixeAndChangeablePieces) {
        this.fixeAndChangeablePieces = fixeAndChangeablePieces;
        return new Phrase(fixeAndChangeablePieces);
    }
}
