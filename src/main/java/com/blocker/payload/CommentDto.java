package com.blocker.payload;

import lombok.Data;

@Data
public class CommentDto {
    private long id;
    private  String body;
    private  String email;
    private String password;
    private String name;
}
