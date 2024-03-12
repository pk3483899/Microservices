package com.microservice.post.service;

import com.microservice.post.entity.Post;
import com.microservice.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post savePost(Post post){
        String postId = UUID.randomUUID().toString();//it rendomly generate one id for the given post
        post.setId(postId);
        Post savePost = postRepository.save(post);
        return savePost;
    }

    public Post findPostById(String postId){
        return postRepository.findById(postId).get();
    }


}
