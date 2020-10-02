package com.start.haven.post.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;


public class Post {

    // TODO: 02/10/2020 JPA required 

    UUID id;
    String title;
    String description;
    long upVotes;
    long downVotes;
    UUID userId;

    public Post(@JsonProperty("id") UUID id,
                @JsonProperty("title") String title,
                @JsonProperty("description") String description) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.upVotes = 0L;
        this.downVotes = 0L;
        this.userId = null;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public long getUpVotes() {
        return upVotes;
    }

    public long getDownVotes() {
        return downVotes;
    }

    public UUID getUserId() {
        return userId;
    }
}
