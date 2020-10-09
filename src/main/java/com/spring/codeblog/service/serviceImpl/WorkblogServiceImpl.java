package com.spring.codeblog.service.serviceImpl;

import com.spring.codeblog.model.Work;
import com.spring.codeblog.repository.WorkblogRepository;
import com.spring.codeblog.service.WorkblogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkblogServiceImpl implements WorkblogService {

    @Autowired
    WorkblogRepository workblogRepository;


    @Override
    public List<Work> findAll() {
        return workblogRepository.findAll();
    }

    @Override
    public Work findById(long id) {
        return workblogRepository.findById(id).get();
    }

    @Override
    public Work save(Work work) {
        return workblogRepository.save(work);
    }

    @Override
    public void deleteById(long id){ workblogRepository.deleteById(id); }

}
