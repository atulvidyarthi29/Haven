package com.start.haven.post.controller;

import com.start.haven.post.model.Post;
import com.start.haven.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("posts")
public class PostController {

    public PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("")
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @PostMapping("create")
    public void createNewPost(@RequestBody Post newPost) {
        postService.createPost(newPost);
    }

    @GetMapping("{id}")
    public Post getAllPostById(@PathVariable("id") UUID id) {
        return postService.getPostById(id).orElse(null);
    }

    @DeleteMapping("delete/{id}")
    public void deletePostById(@PathVariable("id") UUID id) {
        postService.deletePost(id);
    }

    @PutMapping("update/{id}")
    public void updatePostById(@PathVariable("id") UUID id, @RequestBody Post post) {
        post.setId(id);
        postService.updatePost(id, post);
    }

}
