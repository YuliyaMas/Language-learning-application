package M2IM.fr.java.verifier;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Permet de lire (par ligne) et de passer en boucles pour parser toutes les phrases existantes dans le fichier txt
 * correspondant à l'exercice que l'étudiant choisit (un exercice par fichier, une phrase par ligne)
 */
public class ReadingExercise {
    String filename;
    String ligne;


    public ArrayList<List<Piece>> readFile(String filename) throws IOException {
        /*
         * Lecture de fichier et parsing de phrases (pour les soumettre à l'étudiant) pour réalisation :
         * prend en paramètre le nom du fichier (tous les fichiers
         * sont sauvegardés dans le package)
         * Retourne une liste de listes de morceaux : chaque liste de morceaux correspond à une phrase
         */
//        String path = "./src/main/java/M2IM/fr/java/verifier/";
        BufferedReader inputFile = new BufferedReader(new FileReader(filename));
        ParserRegExp parser = new ParserRegExp();
        ArrayList<List<Piece>> arrayOfPhrasesWithPieces = new ArrayList<>();
        ArrayList<List<Piece>> parsedWordsForAnswer = new ArrayList<>();
        List<Piece> parsingResult;

        //TODO reading file, return each phrase to add it to DB
        while ((ligne = inputFile.readLine()) != null) {
            parsingResult = parser.parse(ligne);
            List<Piece> wordForAnswer = parser.parseForAnswer(ligne);
            parsedWordsForAnswer.add(wordForAnswer);
            arrayOfPhrasesWithPieces.add(parsingResult);
        }
        List<Piece> newList = parsedWordsForAnswer.stream().flatMap(x -> x.stream()).sorted((y, z) ->
                y.toString().compareTo(z.toString())).collect(Collectors.toList());

        return arrayOfPhrasesWithPieces;
    }

}