package com.microservice.comment.service;

import com.microservice.comment.config.RestTemplateConfig;
import com.microservice.comment.entity.Comment;
import com.microservice.comment.payload.Post;
import com.microservice.comment.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private RestTemplateConfig restTemplate;

    public Comment saveComment(Comment comment){

        Post post=restTemplate.getRestTemplate().getForObject("http://localhost:8081/api/posts/"+comment.getPostId(), Post.class);//post.class will store the postId object comming from the post/other project
                                                                                                                                      //we also need to create a Post class in comment project in the payload package.
        if(post!=null){
            String commentId = UUID.randomUUID().toString();
            comment.setCommetnId(commentId);
            Comment savedComment = commentRepository.save(comment);
            return savedComment;
        }else {
            return null;
        }




    }
}
