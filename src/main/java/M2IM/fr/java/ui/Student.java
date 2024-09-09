package M2IM.fr.java.ui;

import javax.persistence.*;


@Entity
@Table
public class Student {
    /** Permet de créer et de gérer la base de données comportant des étudiants et des professeurs (des utilisateurs)
     * utilisant l'application*/
    @Id
    @SequenceGenerator(name="user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;
    /**
     Correspond au numéro unique attribué à chaque utilisateur quand celui est enregistré dans la base de données
     */
    private String name;
    /**
     Correspond au nom de l'utilisateur qu'il enregistre lors de la création de son compte personnel (cabinet)
     */
    private String email;
    /**
     Correspond au mail de l'utilisateur qu'il enregistre lors de la création de son compte personnel (cabinet)
     Permet d'entrer au cabinet
     */
    private int points;
    /**
     Nombre de points que l'étudiant totalise en réalisant des exercices
     */
    private String level;
    /**
     Niveau de l'étudiant qu'il enregistre en créant son compte personnel
     */
    private String role;
    /**
     Role que l'utilisateur choisit (étudiant ou professeur)
     */
    private String password;

    /**
     Mot de pass de l'utilisateur, est choisi lors de la création de son compte personnel
     Necessaire pour entrer dans son compte (cabinet)
     */
    public Student() {

    }

    public Student(String name, String email, int points, String level, String role, String password) {
        this.name = name;
        this.email = email;
        this.points = points;
        this.level = level;
        this.role = role;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", points=" + points +
                ", level='" + level + '\'' +
                ", role='" + role + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
