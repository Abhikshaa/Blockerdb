package com.blocker.service.impl;

import com.blocker.entities.Comment;
import com.blocker.entities.Post;

import com.blocker.exception.ResourceNotFoundException;
import com.blocker.payload.CommentDto;
import com.blocker.repository.CommentRepository;
import com.blocker.repository.PostRepository;
import com.blocker.service.CommentService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private PasswordEncoder passwordEncoder;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, PasswordEncoder passwordEncoder) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Post post = postRepository.findById((long)postId).orElseThrow(


                () -> new ResourceNotFoundException("Post","ID",postId)


        );


        Comment comment = mapToEntity(commentDto);//many
           comment.setPost(post);//one
        String encode = passwordEncoder.encode(comment.getPassword());
        commentDto.setPassword(encode);
        Comment saved = commentRepository.save(comment);
        CommentDto dto = mapToDto(saved);
        return dto;
    }

    @Override
    public List<CommentDto> getAllComment() {
        List<Comment> list = commentRepository.findAll();
        return list.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    Comment mapToEntity(CommentDto commentDto){
        Comment com = new Comment();
        com.setName(commentDto.getName());
        com.setEmail(commentDto.getEmail());
        com.setBody(commentDto.getBody());
        return  com;
    }
    CommentDto mapToDto(Comment comment){
        CommentDto dto = new CommentDto();
        dto.setId(comment.getId());
        dto.setName(comment.getName());
        dto.setBody(comment.getBody());
        dto.setEmail(comment.getEmail());
        return dto;

    }

}
