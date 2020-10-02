package com.start.haven.post.dao;

import com.start.haven.post.model.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fake")
public class InMemoryDatabase implements PostDao {
    List<Post> allPosts = new ArrayList<>();

    @Override
    public List<Post> getAllPost() {
        return allPosts;
    }

    @Override
    public Optional<Post> getPostById(UUID id) {
        return allPosts.stream().filter(a -> a.getId().equals(id))
                .findFirst();
    }

    @Override
    public boolean createPost(Post newPost) {
        allPosts.add(newPost);
        return true;
    }

    @Override
    public boolean deletePost(UUID id) {
        Optional<Post> post = getPostById(id);
        if (post.isEmpty()) return false;
        allPosts.remove(post.get());
        return true;
    }

    @Override
    public boolean updatePost(UUID id, Post updatePost) {
        return getPostById(id).map(post -> {
            int index = allPosts.indexOf(post);
            if (index >= 0) {
                allPosts.set(index, updatePost);
                return true;
            }
            return false;
        }).orElse(false);
    }
}
