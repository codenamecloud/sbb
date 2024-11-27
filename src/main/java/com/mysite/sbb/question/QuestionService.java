package com.mysite.sbb.question;

import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.mysite.sbb.DataNotFoundException;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<Question> getList() {
        return this.questionRepository.findAll();
    }

    public Question getQuestion(Integer id) {
        Optional<Question> question = this.questionRepository.findById(id);
        if (question.isPresent()) {
            return question.get();
        } else {
            throw new DataNotFoundException("question not found");
        }
    }

    public void create(String subject, String content) {
        Question q = new Question();
        q.setSubject(subject);
        q.setContent(content);
        q.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q);
    }
}
