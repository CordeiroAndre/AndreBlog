package com.spring.codeblog.service;

import com.spring.codeblog.model.Post;
import com.spring.codeblog.model.Work;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface WorkblogService {

    List<Work> findAll();

    Work findById(long Id);

    Work save(Work work);

    void deleteById(long Id);


}
