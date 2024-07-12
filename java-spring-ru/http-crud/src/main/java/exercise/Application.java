package exercise;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import exercise.model.Post;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @GetMapping("/posts")
    public List<Post> getPosts() {
        return posts;
    }

    @GetMapping ("posts/{id}")
    public Optional<Post> getPost(@PathVariable String id){
        return posts.stream().filter(post -> post.getId().equals(id)).findFirst();
    }

    @PostMapping("/posts")
    public Post addPost(@RequestBody Post post) {
        posts.add(post);
        return post;
    }

    @PutMapping("/posts/{id}")
    public Post updatePost(@PathVariable String id, @RequestBody Post post) {
        var mayBePost = posts.stream().filter(p -> p.getId().equals(id)).findFirst();
        if (mayBePost.isPresent()){
            var oldPost = mayBePost.get();
            oldPost.setId(id);
            oldPost.setTitle(post.getTitle());
            oldPost.setBody(post.getBody());}

        return post;
    }

    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable String id){
        var post = posts.stream().filter(p -> p.getId().equals(id)).findFirst();
        post.ifPresent(p -> posts.remove(post));
    }
    // END
}
