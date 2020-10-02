package com.start.haven.post.dao;

import com.start.haven.post.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class PostgresDao implements PostDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PostgresDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Post> getAllPost() {
        String query = "SELECT * FROM Post";
        return jdbcTemplate.query(query, ((resultSet, i) ->
                new Post(
                        UUID.fromString(resultSet.getString("id")),
                        resultSet.getString("title"),
                        resultSet.getString("description")
                )));

    }

    @Override
    public Optional<Post> getPostById(UUID id) {
        String query = "SELECT * FROM Post WHERE id= ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(query, new Object[]{id}, ((resultSet, i) -> new Post(
                    UUID.fromString(resultSet.getString("id")),
                    resultSet.getString("title"),
                    resultSet.getString("description")
            ))));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean createPost(Post newPost) {
        String query = "INSERT INTO post values (?, ?, ?, ?, ?, ?)";
        int result = jdbcTemplate.update(query, newPost.getId(), newPost.getTitle(), newPost.getDescription(),
                newPost.getUpVotes(), newPost.getDownVotes(), newPost.getUserId());
        return result > 0;
    }

    @Override
    public boolean deletePost(UUID id) {
        String query = "DELETE FROM post where id = ?";
        int result = jdbcTemplate.update(query, id);
        return result > 0;
    }

    @Override
    public boolean updatePost(UUID id, Post updatePost) {
        String query = "UPDATE post SET title = ?, description = ? WHERE id = ?";
        int result = jdbcTemplate.update(query, updatePost.getTitle(), updatePost.getDescription(), updatePost.getId());
        return result > 0;
    }
}
