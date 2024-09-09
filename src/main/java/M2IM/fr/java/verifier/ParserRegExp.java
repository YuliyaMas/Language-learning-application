package M2IM.fr.java.verifier;

import java.util.ArrayList;
import java.util.List;


public class ParserRegExp implements Parser {

    /**
     * Méthode parse permet de parser chaque phrase envoyée depuis l'exercice
     * Le mot à remplacer par ... est toujours entouré par #
     * @param phrase
     * @return une liste de morceaux (fixes et modifiables)
     */

    @Override
    public List<Piece> parse(String phrase) {
        /*
        Retourne après le parsing une liste de morceaux (fixes et modifiables)
         */
        List<Piece> listPiece = new ArrayList<>();
        String[] splittedPhrase = phrase.split("#");
        for (int i = 0; i < splittedPhrase.length; i++) {
            if (i % 2 == 0) {
                listPiece.add(new Piece(splittedPhrase[i].strip()));
            } else {
                listPiece.add(new PieceChangeable(splittedPhrase[i].strip()));
            }
        }

        return listPiece;
    }

    @Override
    public List<Piece> parseForAnswer(String phrase) {
          /*
        Retourne après le parsing une liste de morceaux modifiables pour en créer une liste à soumettre à l'étidiant
        comme choix de réponses possibles
         */
        List<Piece> listPiece = new ArrayList<>();
        List<Piece> listForAnswer = new ArrayList<>();
        String[] splittedPhrase = phrase.split("#");
        for (int i = 0; i < splittedPhrase.length; i++) {
            if (i % 2 == 0) {
                listPiece.add(new Piece(splittedPhrase[i].strip()));

            } else {
                listForAnswer.add(new PieceChangeable(splittedPhrase[i].strip()));
            }
        }

        return listForAnswer;
    }

}