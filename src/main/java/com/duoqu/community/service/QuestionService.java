package com.duoqu.community.service;

import com.duoqu.community.mapper.QuestionMapper;
import com.duoqu.community.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    public int addQuestion(Question question) {
        return questionMapper.addQuestion(question);
    }
}
