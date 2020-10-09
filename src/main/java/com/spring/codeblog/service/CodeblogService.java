package com.spring.codeblog.service;

import com.spring.codeblog.model.Post;

import java.util.List;

public interface CodeblogService {

    List<Post> findAll();

    Post findById(long Id);

    Post save(Post post);

    void deleteById(long Id);
}
