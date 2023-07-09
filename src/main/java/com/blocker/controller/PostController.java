package com.blocker.controller;

import com.blocker.payload.PostDto;
import com.blocker.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")

public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

@PostMapping

    public ResponseEntity<PostDto> createPost( @Valid @RequestBody PostDto postDto){

        PostDto post = postService.createPost(postDto);

        return  new ResponseEntity<>(post, HttpStatus.CREATED);
    }
    @GetMapping
    public List<PostDto> getAllPost(){

        return postService.getallPost();
    }
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getById(@PathVariable ("id") long id){
        PostDto dto = postService.getById(id);

        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updateById(@RequestBody PostDto postDto,@PathVariable("id") long id){
        PostDto update = postService.updateById(postDto, id);

        return ResponseEntity.ok(update);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") long id){

        postService.deleteById(id);
        return  new ResponseEntity<>("Delete id!!!",HttpStatus.OK);
    }
}
