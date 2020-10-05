package com.start.haven.post.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class UpVoteDownVotes {

    @EmbeddedId
    VotesKey id;

    int upOrDown;
}
