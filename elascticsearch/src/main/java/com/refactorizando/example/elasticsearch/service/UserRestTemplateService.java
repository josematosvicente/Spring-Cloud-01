package com.refactorizando.example.elasticsearch.service;

import com.refactorizando.example.elasticsearch.entity.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserRestTemplateService {

    private static final String INDEX = "user";

    private final  ElasticsearchOperations elasticsearchOperations;

    public List<String> createUserIndexBulk(final List<User> users) {

        List<IndexQuery> queries = users.stream()
                .map(user->
                        new IndexQueryBuilder()
                                .withId(user.getId().toString())
                                .withObject(user).build())
                .collect(Collectors.toList());;

        return elasticsearchOperations.bulkIndex(queries, IndexCoordinates.of(INDEX))
                .stream()
                .map(object->object.getId())
                .collect(Collectors.toList());
    }

    public String createUserIndex(User user) {

        IndexQuery indexQuery = new IndexQueryBuilder()
                .withId(user.getId().toString())
                .withObject(user).build();

        String documentId = elasticsearchOperations.index(indexQuery, IndexCoordinates.of(INDEX));

        return documentId;
    }

    public List<User> findUserByName(final String name) {

        QueryBuilder queryBuilder = QueryBuilders.matchQuery("name", name);

        Query searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .build();

        SearchHits<User> hits = elasticsearchOperations.search(searchQuery, User.class, IndexCoordinates.of(INDEX));

         return hits.stream()
                 .map(hit->hit.getContent())
                 .collect(Collectors.toList());

    }

    public List<User> findBySurname(final String surname) {
        Query searchQuery = new StringQuery(
                "{\"match\":{\"surname\":{\"query\":\""+ surname + "\"}}}\"");

        SearchHits<User> users = elasticsearchOperations.search(
                searchQuery,
                User.class,
                IndexCoordinates.of(INDEX));

        return users.stream()
                .map(hit->hit.getContent())
                .collect(Collectors.toList());

    }

    public List<User> findByUser(final String user) {
        Criteria criteria = new Criteria("name")
                .greaterThan(10.0)
                .lessThan(100.0);

        Query searchQuery = new CriteriaQuery(criteria);

        SearchHits<User> users = elasticsearchOperations
                .search(searchQuery,
                        User.class,
                        IndexCoordinates.of(INDEX));

        return users.stream()
                .map(hit->hit.getContent())
                .collect(Collectors.toList());
    }

    public List<User> processSearch(final String query) {
        log.info("Search with query {}", query);

        QueryBuilder queryBuilder =
                QueryBuilders
                        .multiMatchQuery(query, "name", "surname")
                        .fuzziness(Fuzziness.AUTO); //crea todas las posibles variaciones

        Query searchQuery = new NativeSearchQueryBuilder()
                .withFilter(queryBuilder)
                .build();

        SearchHits<User> users =
                elasticsearchOperations
                        .search(searchQuery, User.class,
                                IndexCoordinates.of(INDEX));

        return users.stream()
                .map(hit->hit.getContent())
                .collect(Collectors.toList());
    }
}
