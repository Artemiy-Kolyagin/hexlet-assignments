package exercise.controller.users;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import exercise.model.Post;
import exercise.Data;

// BEGIN
@RestController
public class PostsController {
    List<Post> posts = new ArrayList<>();

    @GetMapping("/api/users/{id}/posts")
    public ResponseEntity<List<Post>> getPosts(@PathVariable int id){
        List<Post> usersPosts = posts.stream().filter(post -> post.getUserId() == id).toList();
        return ResponseEntity.ok().body(usersPosts);
    }

    @PostMapping("/api/users/{id}/posts")
    public ResponseEntity<Post> createPost(@PathVariable int id, @RequestBody Post post){
        Post newPost = new Post(id, post.getSlug(), post.getTitle(), post.getBody());
        posts.add(newPost);
        return ResponseEntity.status(201).body(newPost);
    }
}
// END
