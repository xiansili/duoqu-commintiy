package com.duoqu.community.mapper;

import com.duoqu.community.model.Question;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {
    int addQuestion(Question question);
}
