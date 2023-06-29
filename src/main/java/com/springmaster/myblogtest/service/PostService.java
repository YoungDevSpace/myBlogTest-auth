package com.springmaster.myblogtest.service;

import com.springmaster.myblogtest.dto.PostRequestDto;
import com.springmaster.myblogtest.dto.PostResponseDto;
import com.springmaster.myblogtest.entity.Post;
import com.springmaster.myblogtest.repository.PostRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostResponseDto createPost(PostRequestDto requestDto) {
        // RequestDto -> Entity
        Post post = new Post(requestDto);

        // DB 저장
        Post savePost = postRepository.save(post);

        // Entity -> ResponseDto
        PostResponseDto postResponseDto = new PostResponseDto(post);

        return postResponseDto;
    }

    public List<PostResponseDto> getPosts() {
        // Stream 형태로 변환해서 리스트로 바로 만들어주기
        return postRepository.findAllByOrderByCreateAtDesc().stream() // DB 에서 조회한 List 를 Stream 으로 변환
                .map(PostResponseDto::new)  // Stream 처리를 통해 Post 를 PostResponseDto 로 변환
                .toList(); // Stream 을 List 로 다시 변환
    }
//    public List<PostResponseDto> getPosts() {
//        // DB 조회
//        return postRepository.findAll();
//    }

    public PostResponseDto getPost(Long id) {
        Post post = findPost(id);
        return new PostResponseDto(post);
    }

    public PostResponseDto updatePost(Long id, PostRequestDto requestDto) {
        Post post = findPost(id);
        // 비밀번호 체크
        post.checkPassword(requestDto.getPassword());

        // 필드 업데이트
        post.setTitle(requestDto.getTitle());
        post.setAuthor(requestDto.getAuthor());
        post.setContent(requestDto.getContent());

        return new PostResponseDto(post);
    }



    public void deletePost(Long id, String password) {
        // 해당 글이 DB에 있는지 확인
        Post post = findPost(id);
        post.checkPassword(password);
        // 해당 글 삭제하기
        postRepository.delete(post);
    }

    private Post findPost(Long id) {
        return postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
    }
}
