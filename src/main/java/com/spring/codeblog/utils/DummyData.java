package com.spring.codeblog.utils;

import com.spring.codeblog.model.Work;
import com.spring.codeblog.repository.WorkblogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DummyData {

        @Autowired
        WorkblogRepository workblogRepository;

        //@PostConstruct
        public void savePosts(){

            List<Work> WorkList = new ArrayList<>();
            Work work = new Work();
            work.setTexto("trabalho chato");
            work.setTitulo("BRF");
            work.setIcon("fastfood");

            Work work1 = new Work();
            work1.setTitulo("UDESC");
            work1.setTexto("trabalho chatinho");
            work1.setIcon("book");


            WorkList.add(work);
            WorkList.add(work1);

            for(Work work2: WorkList){

                    Work workSaved = workblogRepository.save(work2);
                    System.out.println(workSaved.getId());
            }
        }
}

