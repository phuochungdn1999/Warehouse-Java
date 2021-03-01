package com.example.warehouse.repository.elaticsearch;

import com.example.warehouse.model.elastic.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends ElasticsearchRepository<User, Long> {
    List<User> findByName(String name);
}
