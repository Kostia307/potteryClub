package com.example.potteryclub.service;

import com.example.potteryclub.model.PostEntity;
import com.example.potteryclub.model.UserEntity;
import com.example.potteryclub.repository.PostRepository;
import com.example.potteryclub.repository.UserRepository;
import org.openapitools.model.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository,
                       UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public List<Post> getPosts() {
        return postRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    public Post createPost(Post dto) {
        UserEntity user = userRepository.findById(dto.getUserId().longValue())
                .orElseThrow(() -> new RuntimeException("User not found"));

        PostEntity entity = new PostEntity();
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        entity.setUser(user);

        PostEntity saved = postRepository.save(entity);
        return toDto(saved); // return the saved entity with its generated id
    }

    // UPDATE POST
    public Post updatePost(Long id, Post dto) {
        PostEntity entity = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        if (dto.getTitle() != null) entity.setTitle(dto.getTitle());
        if (dto.getContent() != null) entity.setContent(dto.getContent());

        return toDto(postRepository.save(entity));
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    private Post toDto(PostEntity e) {
        Post dto = new Post();
        dto.setId(e.getId().intValue());
        dto.setTitle(e.getTitle());
        dto.setContent(e.getContent());
        dto.setUserId(e.getUser().getId().intValue());
        return dto;
    }
}