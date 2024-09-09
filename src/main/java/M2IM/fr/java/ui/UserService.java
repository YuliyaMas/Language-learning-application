package M2IM.fr.java.ui;

import M2IM.fr.java.verifier.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    /**
     * La classe principale qui permet mettre en place le processus d'interaction de l'utilisateur avec des données
     * dans la base de données (ce pour accéder aux tables de BDD : user et exercise)
     */
    private final UserRepository userRepository;
    private final ExerciceRepository exerciceRepository;

    @Autowired
    public UserService(UserRepository userRepository, ExerciceRepository exerciceRepository) {
        this.userRepository = userRepository;
        this.exerciceRepository = exerciceRepository;
    }

    public List<Student> getStudents() {
    /*
     Permet d'obtenir une liste d'utilisateur existant dans la BDD
     */
        return userRepository.findAll();
    }

    public void addNewUser(Student student) {
    /*
     Permet d'ajouter un nouvel utilisateur dans la BDD
     */
        userRepository.save(student);
    }

    public void save(Student student) {
        /*
     Permet de sauvegarder un nouvel utilisateur dans la BDD si celui a été mis à jour par
     le professeur (modérateur de la BDD)
     */
        userRepository.save(student);
    }

    public Student getStudent(Long id) {
        /*
     Permet d'obtenir un utilisateur dans la BDD par son ID (numéro unique)
     */
        Student student = userRepository.getReferenceById(id);
        System.out.println(student.getId());
        return student;
    }

    public void deleteStudent(Long id) {
         /*
     Permet de supprimer un utilisateur de la BDD par son ID (numéro unique)
     */
        Student student = userRepository.getReferenceById(id);
        userRepository.delete(student);
    }

    public Student getStudentByEmail(String email) {
         /*
     Permet d'obtenir le mail de l'utilisateur enregistré dans la BDD
     */
        Optional<Student> userByEmail = userRepository.findUserByEmail(email);
        return userByEmail.get();
    }

    public Exercise getExercice(Long id) {
         /*
     Permet d'obtenir le numéro unique de l'exercice enregistré dans la BDD par son ID (numéro unique)
     */
        Exercise exercise = exerciceRepository.getReferenceById(id);
        return exercise;
    }

    public Optional<Integer> getResult(String email) {
         /*
     Permet d'obtenir le nombre total de points obtenus par l'étudiant à l'aide de son email
     */
        Optional<Integer> points = exerciceRepository.getPoints(email);
        return points;
    }

    public void savePoints(Integer points) {
         /*
     Permet de sauvegarder les points obtenus par email de l'étudiant
     */
    }

    public List<Exercise> getExercices() {
           /*
     Permet d'obtenir tous les exercices existant dans la BDD
     */
        return exerciceRepository.findAll();
    }

    public List<Piece> parsingWordsForAnswer(Exercise exercise) {
           /*
     Permet de parser chaque phrase dans l'exercice pour en récupérer les mots que l'étudiant devra remplir dans son
     exercice. Ses mots sont ensuite proposés à l'étudiant comme choix possible
     */
        ParserRegExp parser = new ParserRegExp();
        List<Piece> parsingWordsForAnswer = parser.parseForAnswer(exercise.getPhrase());
        return parsingWordsForAnswer;
    }

    public void verifyAnswer(Exercise exercise) {
           /*
     Permet de parser la réponse de l'étudiant et la comparer avec la bonne réponse
     Puis d'atribuer à l'étudiant un nombre de points par rapport à son niveau
     Critères de notation sont difinis de manière automatique au moment où l'étudiant indique son niveau
     lors de la création d'un compte
     */
        ParserRegExp parser = new ParserRegExp();
        List<Piece> parsingResult = parser.parse(exercise.getPhrase());
        Phrase phraseTeacher = new Phrase(parsingResult);
        List<Piece> studentPhrase = null;
        try {
            studentPhrase = phraseTeacher.analyseStudentAnswer(exercise.getAnswer());

        } catch (NoSearchFixePieceException e) {
            throw new RuntimeException(e);
        }
        Phrase studentPhrase_new = new Phrase(studentPhrase);
        studentPhrase_new.setFixeAndChangeablePieces(studentPhrase);
        Correction correction = new Correction();
        List<ElementForCorrection> resultCorrection = correction.verify(studentPhrase_new, phraseTeacher);
        LevelOfEvaluation chooseLevel = new LevelOfEvaluation();

        Student student = userRepository.getReferenceById(exercise.getStudentId());

        int points = chooseLevel.chooseEvaluation(resultCorrection, LEVEL.valueOf(student.getLevel().toUpperCase()));
        int p = exercise.getPoints() == null ? 0 : exercise.getPoints();
        p += points;
        exercise.setPoints(p);
        exerciceRepository.save(exercise);
        if (exercise.getStudentId() != null) {

            student.setPoints(student.getPoints() + p);
            userRepository.save(student);
        }

    }

    public Exercise generatePhraseForStudent(Exercise exercise) {
          /*
     Permet de parser chaque phrase dans l'exercice pour en récupérer les mots que l'étudiant devra remplir dans son
     exercice et le remplacer par "...".
     */
        ParserRegExp parser = new ParserRegExp();
        List<Piece> parsingResult = parser.parse(exercise.getPhrase());
        String phraseForStudent = " ";
        for (Piece p : parsingResult) {
            phraseForStudent += p.pieceForStudent();
        }
        exercise.setPhraseForStudent(phraseForStudent);
        return exercise;
    }
    public boolean uploadExercise(String path) throws IOException {
        ReadingExercise readingExercise = new ReadingExercise();
        readingExercise.readFile(path);
        Exercise exercise = new Exercise();
        //TODO to redo the method readFile
//        exercise.setPhrase();
        exerciceRepository.save(exercise);
        return false;
    }
}

//    public void generatePhraseForCorrectAnswer(ReadingExercise exercise) {
//          /*
//     Permet de parser chaque phrase dans l'exercice pour obtenir une bonne réponse qui sera affichée
//     après la réponse de l'étudiant
//     */
//        ParserRegExp parser = new ParserRegExp();
//        List<Piece> parsingResult = parser.parse(exercise.getPhrase());
//        String phraseForCorrectAnswer= " ";
//        for (Piece p : parsingResult) {
//            phraseForCorrectAnswer += p.pieceForCorrectAnswer();
//        }
//        exercise.setPhraseForStudent(phraseForCorrectAnswer);
//    }



