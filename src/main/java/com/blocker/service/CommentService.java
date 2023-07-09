package com.blocker.service;

import com.blocker.payload.CommentDto;

import java.util.List;

public interface CommentService {

    public CommentDto createComment(long postId,CommentDto commentDto);

     List<CommentDto> getAllComment();
}
