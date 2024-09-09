package M2IM.fr.java.ui;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 UserConfig permet d'introduire dans la base de données des données pour
 tester l'application depuis la partie backend lors du développement
 */
@Configuration
public class UserConfig {
    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository, ExerciceRepository exerciceRepository) {
        /*
         * Enregistre dans la base de données des données de la partie Utilisateur nécessaire pour tester l'application
         */
        return args -> {
            Student student1 = new Student("Johnny Depp", "johnny.depp@gmail.com", 0, "intermediate", "student", "root");
            Student student2 = new Student("Jennifer Aniston", "jennifer.anniston@inalco.com", 0, "beginner", "student", "root");
            Student student3 = new Student("Natalie Portman", "natalie.portmann@inalco.com", 8, "intermediate", "student", "root");
            Student teacher1 = new Student("Bruce Lee", "bruce.lee@inalco.com", 0, null, "teacher", "root");
            Student teacher2 = new Student("Angelina Jolie", "angelina.jolie@inalco.com", 0, null, "teacher", "root");
            List<Student> usersList = new ArrayList<>();
            usersList.add(student1);
            usersList.add(student2);
            usersList.add(student3);
            usersList.add(teacher1);
            usersList.add(teacher1);
            usersList.add(teacher2);
            repository.saveAll(usersList);
        };
    }
}
