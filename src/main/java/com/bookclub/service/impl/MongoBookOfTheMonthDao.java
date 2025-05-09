package com.bookclub.service.impl;

import com.bookclub.model.BookOfTheMonth;
import com.bookclub.service.dao.BookOfTheMonthDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("bookOfTheMonthDao")
public class MongoBookOfTheMonthDao implements BookOfTheMonthDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void add(BookOfTheMonth entity) {
        mongoTemplate.save(entity);
    }

    @Override
    public void update(BookOfTheMonth entity) {
        BookOfTheMonth bookOfTheMonth = mongoTemplate.findById(entity.getId(), BookOfTheMonth.class);
        if (bookOfTheMonth != null) {
            bookOfTheMonth.setMonth(entity.getMonth());
            bookOfTheMonth.setIsbn(entity.getIsbn());
            mongoTemplate.save(bookOfTheMonth);
        }
    }

    @Override
    public boolean remove(String key) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(key));
        mongoTemplate.remove(query, BookOfTheMonth.class);
        return true;
    }

    @Override
    public List<BookOfTheMonth> list(String key) {
        if (key.equals("999")) {
            return mongoTemplate.findAll(BookOfTheMonth.class);
        } else {
            Query query = new Query();
            query.addCriteria(Criteria.where("month").is(Integer.parseInt(key)));
            return mongoTemplate.find(query, BookOfTheMonth.class);
        }
    }

    @Override
    public BookOfTheMonth find(String key) {
        return mongoTemplate.findById(key, BookOfTheMonth.class);
    }
}
