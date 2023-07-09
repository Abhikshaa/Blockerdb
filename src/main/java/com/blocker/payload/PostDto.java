package com.blocker.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
@Data

public class PostDto {
    private long id;
    @NotEmpty
    @Size(min=4,max=16,message = "maximum charater should be 16")
    private  String content;
    @NotEmpty
    @Size(min=7 )
    private  String description;
    @NotEmpty(message = "should not empty")
    private String title;

    private List<CommentDto> comments;
}
