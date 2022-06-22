package br.com.danielwisky.blog.gateways.outputs.mongodb.repositories;

import static br.com.danielwisky.blog.utils.CriteriaUtils.addInIfNotEmpty;
import static br.com.danielwisky.blog.utils.CriteriaUtils.addRegexIfNotEmpty;
import static br.com.danielwisky.blog.utils.CriteriaUtils.reduceWithAndOperator;
import static org.springframework.data.mongodb.core.query.Query.query;

import br.com.danielwisky.blog.domains.PostFilter;
import br.com.danielwisky.blog.gateways.outputs.mongodb.documents.PostDocument;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PostCustomRepositoryImpl implements PostCustomRepository {

  private final ReactiveMongoTemplate reactiveMongoTemplate;

  @Override
  public Mono<Page<PostDocument>> findByFilter(final PostFilter filter, final Pageable pageable) {

    final List<Criteria> criteriaAsList = new ArrayList<>();
    addRegexIfNotEmpty(criteriaAsList, "title", filter.getTitle());
    addInIfNotEmpty(criteriaAsList, "tags", filter.getTags());
    final Criteria criteria = reduceWithAndOperator(criteriaAsList);

    final Mono<Long> totalPosts =
        reactiveMongoTemplate.count(query(criteria), PostDocument.class);
    final Flux<PostDocument> posts =
        reactiveMongoTemplate.find(query(criteria).with(pageable), PostDocument.class);

    return Mono.zip(posts.collectList(), totalPosts)
        .map(data -> new PageImpl<>(data.getT1(), pageable, data.getT2()));
  }
}