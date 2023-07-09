package com.blocker.service;

import com.blocker.entities.Post;
import com.blocker.payload.PostDto;

import java.util.List;

public interface PostService {

    public PostDto createPost(PostDto postDto);

    List<PostDto> getallPost();

    PostDto getById(long id);

    PostDto updateById(PostDto postDto, long id);

    void deleteById(long id);
}
