package M2IM.fr.java.verifier;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test extends ReadingExercise {
    /**
     * Permet de tester et executer le programme et faire les exercices proposés via la terminale
     */

public static void main(String[] args) throws NoSearchFixePieceException, IOException {
Test test = new Test();
String path = "./src/main/java/M2IM/fr/java/verifier/";
ArrayList<List<Piece>> resultParser = test.readFile(path +"exercice1");

int total = 0;
for (List<Piece> parsedPhrase : resultParser) {
    System.out.println("Teacher's phrase contains pieces: " + parsedPhrase);
    Phrase phraseTeacher = new Phrase(parsedPhrase);
    List<Piece> studentPhrase = null;
    Scanner input = new Scanner(System.in);
    try {
        System.out.println("Please write below your answer ");
        String answer = input.nextLine();
        studentPhrase = phraseTeacher.analyseStudentAnswer(answer);
    } catch (NoSearchFixePieceException e) {
        throw new RuntimeException(e);
    }
    Phrase studentPhrase_new = new Phrase(studentPhrase);
    studentPhrase_new.setFixeAndChangeablePieces(studentPhrase);
    Correction correction = new Correction();
    List<ElementForCorrection> resultCorrection = correction.verify(studentPhrase_new, phraseTeacher);
    LevelOfEvaluation level = new LevelOfEvaluation();
    int result = level.chooseEvaluation(resultCorrection, LEVEL.INTERMEDIATE);
    System.out.println(result);
    total  += result;

}
System.out.println(total);
    }
}