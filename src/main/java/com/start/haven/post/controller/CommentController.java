package com.start.haven.post.controller;

import com.start.haven.post.model.Comment;
import com.start.haven.post.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    public final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("post/{postId}/comments")
    public List<Comment> getCommentsByPost(@PathVariable("postId") int postId) {
        return commentService.getAllCommentsByPost(postId);
    }

    @GetMapping("user/comments")
    public List<Comment> getCommentsByUser() {
        return commentService.getAllCommentsByUser();
    }

    @PostMapping("post/{postId}/newComment")
    public void addNewComment(@PathVariable("postId") long postId, @RequestBody Comment comment) {
        commentService.addComment(postId, comment);
    }

    @DeleteMapping("post/{postId}/comment/{commentId}")
    public void deleteComment(@PathVariable("postId") long postId,
                              @PathVariable("commentId") long commentId) throws Exception {
        commentService.deleteComment(commentId);
    }

    @PutMapping("post/{postId}/comment/{commentId}")
    public void updateComment(@PathVariable("postId") long postId,
                              @PathVariable("commentId") long commentId, @RequestBody Comment comment) throws Exception {
        commentService.updateComment(commentId, comment);
    }

}
