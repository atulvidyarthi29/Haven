package com.start.haven.post.service;

import com.start.haven.post.dao.PostRepository;
import com.start.haven.post.model.Post;
import com.start.haven.security.UserUtil;
import com.start.haven.users.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserUtil userUtil;

    @Autowired
    public PostService(PostRepository postRepository, UserUtil userUtil) {
        this.postRepository = postRepository;
        this.userUtil = userUtil;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> getPostById(long id) {
        return postRepository.findById(id);
    }

    public void createPost(Post newPost) throws Exception {
        Optional<User> loggedInUser = userUtil.loggedInUser();
        loggedInUser.orElseThrow(() -> new Exception("User can't be authenticated"));
        newPost.setUser(loggedInUser.get());
        postRepository.save(newPost);
    }

    public void deletePost(long id) throws Exception {
        Optional<Post> postToDelete = postRepository.findById(id);
        postToDelete.orElseThrow(() -> new Exception("Post not found"));
        if (postToDelete.get().getUser().getId() != userUtil.loggedInUser().get().getId())
            throw new Exception("Authentication Failed");
        postRepository.delete(postToDelete.get());
    }

    public void updatePost(long id, Post post) throws Exception {
        Optional<Post> optionalPost = postRepository.findById(id);
        optionalPost.orElseThrow(() -> new Exception("Cannot retrieve the post."));
        Post postToUpdate = optionalPost.get();
        if (postToUpdate.getUser().getId() != userUtil.loggedInUser().get().getId())
            throw new Exception("Authentication Failed");
        postToUpdate.setTitle(post.getTitle());
        postToUpdate.setDescription(post.getDescription());
        postRepository.save(postToUpdate);
    }
}
