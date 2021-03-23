package com.example.warehouse.service.elasticsearch;

import com.example.warehouse.model.elastic.User;
import com.example.warehouse.repository.WarehouseRepository;
import com.example.warehouse.repository.elaticsearch.UserRepo;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSearchService {
    private static final String INDEX = "java";

    private UserRepo userRepo;
    private ElasticsearchOperations elasticsearchOperations;
    @Autowired
    public UserSearchService (UserRepo warehouseRepository,ElasticsearchOperations elasticsearchOperations){
        this.userRepo = warehouseRepository;
        this.elasticsearchOperations = elasticsearchOperations;
    }

    public  void createUserIndexBulk(List<User> user) {
        userRepo.saveAll(user);
    }

    public  void createUserIndex(User user){
        userRepo.save(user);
    }

    public SearchHits<User> findByBrand(String brandName){
        QueryBuilder queryBuilder = QueryBuilders.matchQuery("manufacturer",brandName);

        Query searchQuery = new NativeSearchQueryBuilder().withQuery(queryBuilder).build();

        SearchHits<User> userSearchHits = elasticsearchOperations.search(searchQuery,User.class, IndexCoordinates.of(INDEX));
        return userSearchHits;
    }

    public SearchHits<User> findByName(String name){
        Query searchQuery = new StringQuery("{\"match\":{\"name\":{\"query\":\""+ name + "\"}}}\"");
        SearchHits<User> userSearchHits =elasticsearchOperations.search(searchQuery,User.class,IndexCoordinates.of(INDEX));
        return userSearchHits;
    }

}
