package M2IM.fr.java.verifier;

import java.util.List;

public interface Parser {
/**
  Impose des méthodes nécessaires pour le parsing
 */
    List<Piece> parse(String phrase);

    List<Piece> parseForAnswer(String phrase);

}
