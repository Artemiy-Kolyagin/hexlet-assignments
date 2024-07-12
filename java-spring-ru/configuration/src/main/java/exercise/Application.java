package exercise;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import  org.springframework.beans.factory.annotation.Autowired;

import exercise.model.User;
import exercise.component.UserProperties;

@SpringBootApplication
@RestController
public class Application {

    // Все пользователи
    private List<User> users = Data.getUsers();

    // BEGIN
    @Autowired
    UserProperties usersInfo;
    // END

    @GetMapping("/users")
    public List<User> index() {
        return users;
    }

    @GetMapping("/users/{id}")
    public Optional<User> show(@PathVariable Long id) {
        var user = users.stream()
            .filter(u -> u.getId() == id)
            .findFirst();
        return user;
    }

    @GetMapping("/admins")
    public List<String> getAdmins(){

        List<String> adminEmails = usersInfo.getAdmins();

        return users.stream()
                .filter(u -> adminEmails.contains(u.getEmail()))
                .map(u -> u.getName())
                .sorted()
                .toList();

//        List<User> adminsList =  users.stream()
//                .filter(user -> userProperties.getAdmins().contains(user.getEmail()))
//                .sorted(Comparator.comparing(User::getName))
//                .toList();
//
//        return ResponseEntity.ok().body(adminsList);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
