package com.start.haven.post.service;

import com.start.haven.post.dao.PostDao;
import com.start.haven.post.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostService {

    public PostDao postDao;

    @Autowired
    public PostService(@Qualifier("postgres") PostDao postDao) {
        this.postDao = postDao;
    }

    public List<Post> getAllPosts() {
        return postDao.getAllPost();
    }

    public Optional<Post> getPostById(UUID id) {
        return postDao.getPostById(id);
    }

    public boolean createPost(Post newPost) {
        newPost.setId(UUID.randomUUID());
        return postDao.createPost(newPost);
    }

    public boolean deletePost(UUID id) {
        return postDao.deletePost(id);
    }

    public boolean updatePost(UUID id, Post post) {
        return postDao.updatePost(id, post);
    }
}
