package com.blocker.controller;


import com.blocker.payload.CommentDto;
import com.blocker.service.CommentService;
import com.blocker.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

  private CommentService commentservice;
  private PasswordEncoder passwordEncoder;

     public CommentController(CommentService commentservice, PasswordEncoder passwordEncoder) {
          this.commentservice = commentservice;
          this.passwordEncoder = passwordEncoder;
     }

     @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentDto> saveComment(@PathVariable Long postId, @RequestBody CommentDto commentDto) {
          String encode = passwordEncoder.encode(commentDto.getPassword());
          commentDto.setPassword(encode);


          CommentDto savecommentdto = commentservice.createComment(postId, commentDto);

        return  new ResponseEntity<>(savecommentdto, HttpStatus.CREATED);
    }
    @GetMapping
        public List<CommentDto> getAllComment(){

        return commentservice.getAllComment();
    }
}
