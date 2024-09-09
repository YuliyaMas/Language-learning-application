package M2IM.fr.java.ui;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Student, Long> {
    /**
     *
     * Permet d'utiliser les méthodes existantes pour interagir avec la BDD (pour gérer la partie utilisateur)
     * Permet également d'en créer des customs : ici on en a une findUserByEmail qui permet de vérifier
     * lors de création de compte si l'utilisateur (son email et mot de pass) existe déja dans la BDD
     *
     */
    @Query("SELECT s from Student s where s.email = ?1")
    Optional<Student> findUserByEmail(String email);
}
