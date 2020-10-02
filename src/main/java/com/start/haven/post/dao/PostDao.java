package com.start.haven.post.dao;

import com.start.haven.post.model.Post;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PostDao {
    List<Post> getAllPost();

    Optional<Post> getPostById(UUID id);

    boolean createPost(Post newPost);

    boolean deletePost(UUID id);

    boolean updatePost(UUID id, Post updatePost);

}
