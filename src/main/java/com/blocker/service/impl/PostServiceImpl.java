package com.blocker.service.impl;

import com.blocker.entities.Post;

import com.blocker.exception.ResourceNotFoundException;
import com.blocker.payload.PostDto;
import com.blocker.repository.PostRepository;
import com.blocker.service.PostService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

 private   PostRepository postRepository;


     public PostServiceImpl(PostRepository postRepository) {
          this.postRepository = postRepository;

     }

     @Override
    public PostDto createPost(PostDto postDto) {

        Post post = mapToEntity(postDto);


        Post savedpost = postRepository.save(post);
        PostDto postDto1 = mapToDto(savedpost);

        return postDto1;
    }

    @Override
    public List<PostDto> getallPost() {
        List<Post> list = postRepository.findAll();
        return list.stream().map(l1->mapToDto(l1)).collect(Collectors.toList());
    }

    @Override
    public PostDto getById(long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post","ID",id)

        );
        return mapToDto(post);
    }

    @Override
    public PostDto updateById(PostDto postDto, long id) {
        Post saveupdate = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post","ID",id)
        );
        saveupdate.setTitle(postDto.getTitle());
        saveupdate.setDescription(postDto.getDescription());
        saveupdate.setContent(postDto.getContent());
        Post post = postRepository.save(saveupdate);
        return mapToDto(post);
    }

    @Override
    public void deleteById(long id) {
        postRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Post","ID",id)
        );
        postRepository.deleteById(id);
    }

    Post mapToEntity(PostDto postDto){

        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
 post.setDescription(postDto.getDescription());
        return post;
    }
    PostDto mapToDto (Post post){
        PostDto dto = new PostDto();
        dto.setId(dto.getId());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        dto.setDescription(post.getDescription());

        return  dto;
    }


}
