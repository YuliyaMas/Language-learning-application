package M2IM.fr.java.ui;

import javax.persistence.*;

@Entity
@Table
public class Exercise {
    /** Permet de créer et de gérer la base de données comportant des exercices pour les étudiants
     * utilisant l'application*/
    @Id
    @SequenceGenerator(name = "exercice_sequence",
            sequenceName = "exercice_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "exercice_sequence"
    )
    private Long id;
    /**
     Correspond au numéro unique attribué à chaque exercice quand celui est enregistré dans la base de données
     */
    private String name;
    /**
     Correspond au nom de l'exercice qui apparait dans l'Interface graphique (GUI)
     */
    private String phrase;
    /**
     Correspond à l'exercice (à cette étape c\'est une seule phrase)
     */
    private String phraseForStudent;
    /**
     Correspond à l'exercice qui apparait pour l'étudiant dans GUI (avec ... à la place du mot à trouver comme réponse)
     */
    private String wordForAnswer;
    /**
     Correspond au mot à trouver comme réponse
     */
    private String phraseForCorrectAnswer;
    /**
     La bonne réponse
     */
    private String answer;
    /**
     La réponse de l'étudiant
     */
    private Integer points;
    /**
     Le nombre de point que l'étudiant a obtenu pour son exercice
     */
    private Long studentId;
    /**
     Correspond au numéro unique attribué à chaque étudiant quand celui est enregistré dans la base de données
     */
    public Exercise() {
    }

    public Exercise(String phrase) {
        this.phrase = phrase;
    }

    public String getWordForAnswer() {
        return wordForAnswer;
    }

    public void setWordForAnswer(String wordForAnswer) {
        this.wordForAnswer = wordForAnswer;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getPhraseForStudent() {
        return phraseForStudent;
    }

    public void setPhraseForStudent(String phraseForStudent) {
        this.phraseForStudent = phraseForStudent;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public String getPhraseForCorrectAnswer() {
        return phraseForCorrectAnswer;
    }

    public void setPhraseForCorrectAnswer(String phraseForCorrectAnswer) {
        this.phraseForCorrectAnswer = phraseForCorrectAnswer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }


    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ReadingExercise{" +
                "id=" + id +
                ", phrase='" + phrase + '\'' +
                '}';
    }
}

