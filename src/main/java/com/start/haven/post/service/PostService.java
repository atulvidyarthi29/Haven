package com.start.haven.post.service;

import com.start.haven.post.dao.PostRepository;
import com.start.haven.post.model.Post;
import com.start.haven.users.dao.HavenUserRepository;
import com.start.haven.users.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final HavenUserRepository havenUserRepository;

    @Autowired
    public PostService(PostRepository postRepository, HavenUserRepository havenUserRepository) {
        this.postRepository = postRepository;
        this.havenUserRepository = havenUserRepository;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> getPostById(long id) {
        return postRepository.findById(id);
    }

    public void createPost(Post newPost) throws Exception {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        Optional<User> loggedInUser = havenUserRepository.findUserByUsername(username);
        loggedInUser.orElseThrow(() -> new Exception("User can't be authenticated"));
        newPost.setUserId(loggedInUser.get());
        postRepository.save(newPost);
    }

    public void deletePost(long id) throws Exception {
        Optional<Post> postToDelete = postRepository.findById(id);
        postToDelete.orElseThrow(() -> new Exception("Post not found"));
        postRepository.delete(postToDelete.get());
    }

    public void updatePost(long id, Post post) throws Exception {
        Post postToUpdate = postRepository.findById(id).get();
        postToUpdate.setTitle(post.getTitle());
        postToUpdate.setDescription(post.getDescription());
        postRepository.save(postToUpdate);
    }
}
