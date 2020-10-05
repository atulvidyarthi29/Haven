package com.start.haven.post.model;

import com.start.haven.users.model.User;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class VotesKey implements Serializable {

    @ManyToOne
    Post post;

    @ManyToOne
    User user;
}
