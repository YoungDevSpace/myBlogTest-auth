package com.springmaster.myblogtest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.springmaster.myblogtest.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostResponseDto {
    private Boolean success;
    private Long id;
    private String title;
    private String author;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.author = post.getAuthor();
        this.contents = post.getContent();
        this.createdAt = post.getCreateAt();
        this.modifiedAt = post.getModifiedAt();
    }

    public PostResponseDto(Boolean success) {
        this.success = success;
    }
}
