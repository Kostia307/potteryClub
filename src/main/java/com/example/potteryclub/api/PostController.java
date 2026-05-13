package com.example.potteryclub.api;

import com.example.potteryclub.service.PostService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.openapitools.model.Post;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Post", description = "Post API")
@RestController
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // GET /posts
    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getPosts() {
        return ResponseEntity.ok(postService.getPosts());
    }

    // POST /posts
    @PostMapping("/posts")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        Post created = postService.createPost(post);
        return ResponseEntity.status(201).body(created);
    }

    // PUT /posts/{id}
    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Integer id, @RequestBody Post post) {
        Post updated = postService.updatePost(id.longValue(), post);
        return ResponseEntity.ok(updated);
    }

    // DELETE /posts/{id}
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Integer id) {
        postService.deletePost(id.longValue());
        return ResponseEntity.noContent().build();
    }
}