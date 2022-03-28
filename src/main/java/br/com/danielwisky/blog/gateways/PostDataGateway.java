package br.com.danielwisky.blog.gateways;

import br.com.danielwisky.blog.domains.Post;
import br.com.danielwisky.blog.domains.PostFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

public interface PostDataGateway {

  Mono<Post> save(Post post);

  Mono<Void> deleteById(String id);

  Mono<Post> findById(String id);

  Mono<Page<Post>> findByFilter(PostFilter filter, Pageable pageable);
}
