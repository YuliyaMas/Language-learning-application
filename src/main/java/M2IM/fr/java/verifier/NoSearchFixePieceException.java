package M2IM.fr.java.verifier;

public class NoSearchFixePieceException extends Exception{
    /**
     * Permet de jeter une exception lorsque l'étudiant a mal recopié un morceau fixe
     */
    Piece piece;

    public NoSearchFixePieceException(String message, Piece piece) {
        super(message + piece.pieceForCorrectAnswer());
        this.piece = piece;
    }
}
