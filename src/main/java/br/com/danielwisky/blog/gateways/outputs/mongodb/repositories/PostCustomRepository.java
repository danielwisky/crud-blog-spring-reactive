package br.com.danielwisky.blog.gateways.outputs.mongodb.repositories;

import br.com.danielwisky.blog.domains.PostFilter;
import br.com.danielwisky.blog.gateways.outputs.mongodb.documents.PostDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

public interface PostCustomRepository {

  Mono<Page<PostDocument>> findByFilter(PostFilter filter, Pageable pageable);
}