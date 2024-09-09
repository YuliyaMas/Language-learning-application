package M2IM.fr.java.ui;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
public interface ExerciceRepository extends JpaRepository<Exercise, Long> {
    /**
     * Permet d'utiliser les méthodes existantes pour interagir avec la BDD (pour gérer la partie exercice)
     * Permet également d'en créer des customs : ici on en a une getPoints qui permet d'obtenir le nombre de points total
     * de l'utilisateur
     */
    @Query("SELECT points from Student s where s.email = ?1")
    Optional<Integer> getPoints(String email);
}