package com.start.haven.post.dao;

import com.start.haven.post.model.UpVoteDownVotes;
import com.start.haven.post.model.VotesKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UpVotesDownVotesRepository extends JpaRepository<UpVoteDownVotes, VotesKey> {
}
