package com.start.haven.post.controller;

import com.start.haven.post.model.Post;
import com.start.haven.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public void createNewPost(@RequestBody Post newPost) throws Exception {
        postService.createPost(newPost);
    }

    @GetMapping("{id}")
    public Post getAllPostById(@PathVariable("id") long id) {
        return postService.getPostById(id).orElse(null);
    }

    @DeleteMapping("delete/{id}")
    public void deletePostById(@PathVariable("id") long id) throws Exception {
        postService.deletePost(id);
    }

    @PutMapping("update/{id}")
    public void updatePostById(@PathVariable("id") long id, @RequestBody Post post) throws Exception {
        postService.updatePost(id, post);
    }

}
