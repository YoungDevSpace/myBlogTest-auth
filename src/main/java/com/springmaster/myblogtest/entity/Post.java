package com.springmaster.myblogtest.entity;

import com.springmaster.myblogtest.dto.PostRequestDto;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Getter
//@Setter
//@NoArgsConstructor
//public class Post {
//    private Long id;
//    private String username;
//    private String contents;
//
//    public Post(PostRequestDto requestDto) {
//        this.username = requestDto.getUsername();
//        this.contents = requestDto.getContents();
//    }
//
//    public void update(PostRequestDto requestDto) {
//        this.username = requestDto.getUsername();
//        this.contents = requestDto.getContents();
//    }
//}
@Entity
@Getter
@Table(name = "post")
@NoArgsConstructor
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false, length = 500)
    private String content;

    @Column(nullable = false)
    private String password;

    public Post(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.author = postRequestDto.getAuthor();
        this.content = postRequestDto.getContent();
        this.password = postRequestDto.getPassword();
    }

    // Setter
    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // 비밀번호 체크
    public void checkPassword(String inputPassword) {
        if (!password.equals(inputPassword)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }
}