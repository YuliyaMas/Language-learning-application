package M2IM.fr.java.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Controller
public class UserController {
    /**
     Permet de controller les données transmises depuis la BDD vers la partie frontend (pages html mises en forme par Thymeleaf)
     Permet de mapper les pages html et faire une redirection nécessaire en utilisant les annotations entre autres GET et POST
     Ces annotations permettent la connection de la partie frontend (des requests) de l'utilisateur au serveur (Spring Boot)
     Se référer à @see <a href="https://spring.io/guides/gs/spring-boot/">site du framework</a>
     */
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(Model model) {
        /*
         La page principale
         */
        model.addAttribute("student", new Student());
        return "index";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("student") Student student, RedirectAttributes redirAttrs) {
        /*
        Permet d'insaurer l'interaction lors de log in ou création de compte utilisateur
         */
        String redirect = "teacher";

        Student studentDB = userService.getStudentByEmail(student.getEmail());
        if(studentDB == null || !student.getPassword().equals(studentDB.getPassword())){
            redirect = "errorlogin";
        }

        if (Objects.equals(studentDB.getRole(), "student")) {
            redirect = "student";
        }
        redirAttrs.addFlashAttribute("studentId", studentDB.getId());
        return "redirect:/" + redirect;
    }

    @GetMapping("/student")
    public String getExercises(Model model) {
        /*
        Permet d'envoyer l'exercice depuis BDD vers le frontend
         */
        model.addAttribute("exercises", userService.getExercices());
        Long studentId = (Long) model.getAttribute("studentId");
        model.addAttribute("studentId", userService.getStudent(studentId));
        return "student";
    }

    @GetMapping("/student/{id}")
    public String getStudentId(@PathVariable(value = "id") Long id, Model model) {
        /*
        Permet d'obtenir un utilateur par son ID pour le transmettre vers frontend
         */
        Student studentId = userService.getStudent(id);
        model.addAttribute("studentId", studentId);
        model.addAttribute("exercises", userService.getExercices());
        return "student";
    }

    @GetMapping("/executeexercise/{id}")
    public String executeExercise (@PathVariable(value = "id") Long id, Model model) {
        /*
        Permet d'otenir l'exercice choisi par l'étudiant depuis la BDD et le transmettre vers le frontend
         */
        Exercise exercise = userService.getExercice(id);
        exercise = userService.generatePhraseForStudent(exercise);
        exercise.setStudentId(1L);
        model.addAttribute("exercise", exercise);
        return "executeexercise";
    }

    @GetMapping("/teacher")
    public String getStudents(Model model) {
        /*
        Permets d'afficher sur la page html correspondante la liste de tous les étudiants enregistrés dans la BDD
         */
        model.addAttribute("students", userService.getStudents());
        return "teacher";
    }

    @GetMapping("/addnew")
    public String addNewUser(Model model) {
        /*
        Permets de rediriger vers la page html où on remplit l'information nécessaire lors de création de compte utilisateur
         */
        Student student = new Student();
        model.addAttribute("student", student);
        return "adduser";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("student") Student student, Model model) {
        /*
        Permets de rediriger vers la page html où on obtient la réponse de bonne création de compte utilisateur
         */
        userService.save(student);
        model.addAttribute("student", student);
        return "saveuser";
    }

    @GetMapping("/updateuser/{id}")
    public String updateUser (@PathVariable(value = "id") Long id, Model model) {
        /*
        Permet d'ouvrir la forme à remplir lorsque le professeur souhaite modifier des données d'un utilisateur dans la BDD
        Accessible uniquement à l'utilisateur ayant le statut professeur
         */
        Student student = userService.getStudent(id);
        model.addAttribute("student", student);
        return "updateuser";
    }

    @GetMapping("/deletestudent/{id}")
    public String deleteStudent(@PathVariable(value = "id") Long id) {
        /*
        Permet de supprimer un utilisateur de la BDD
        Accessible uniquement à l'utilisateur ayant le statut professeur
         */
        userService.deleteStudent(id);
        return "redirect:/teacher";

    }
    @PostMapping("/update")
    public String updateUser(@ModelAttribute("student") Student student, Model model) {
        /*
        Permets de modifier les données que l'utilisateur a remplies lors de la création du compte
        Accessible uniquement à l'utilisateur ayant le statut professeur
         */
        userService.save(student);
        model.addAttribute("students", userService.getStudents());
        return "teacher";
    }

    @PostMapping("/answer")
    public String verifyAnswer(@ModelAttribute("exercise") Exercise exercise, Model model) {
        /*
        Permet d'envoyer la réponse donnée par l'élève vers la page où on l'affiche avec la bonne réponse à coté et le
        nombre de points obtenu pour l'exercice
         */
        userService.verifyAnswer(exercise);
//        userService.generatePhraseForCorrectAnswer(exercise);
        model.addAttribute("exercise", exercise);
        return "result";
    }

    @GetMapping("/exercisemanager")
    public String getExerciseManager(Model model) {
        /*
        Permet d'envoyer l'exercice depuis BDD vers le frontend
         */
        model.addAttribute("exercises", userService.getExercices());
        return "exercisemanager";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes attributes) {

        // check if file is empty
        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "Please select a file to upload.");
            return "redirect:/";
        }

        // normalize the file path
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        // save the file on the local file system
        try {
            Path path = Paths.get(fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // return success response
        attributes.addFlashAttribute("message", "You successfully uploaded " + fileName + '!');

        return "redirect:/";
    }

}