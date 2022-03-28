package br.com.danielwisky.blog.gateways.outputs.mongodb;

import br.com.danielwisky.blog.domains.Post;
import br.com.danielwisky.blog.domains.PostFilter;
import br.com.danielwisky.blog.gateways.PostDataGateway;
import br.com.danielwisky.blog.gateways.outputs.mongodb.documents.PostDocument;
import br.com.danielwisky.blog.gateways.outputs.mongodb.repositories.PostCustomRepository;
import br.com.danielwisky.blog.gateways.outputs.mongodb.repositories.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public record PostDataGatewayImpl(
    PostRepository postRepository,
    PostCustomRepository postCustomRepository) implements PostDataGateway {

  @Override
  public Mono<Post> save(final Post post) {
    return postRepository.save(new PostDocument(post)).map(PostDocument::toDomain);
  }

  @Override
  public Mono<Void> deleteById(final String id) {
    return postRepository.deleteById(id);
  }

  @Override
  public Mono<Post> findById(final String id) {
    return postRepository.findById(id).map(PostDocument::toDomain);
  }

  @Override
  public Mono<Page<Post>> findByFilter(final PostFilter filter, final Pageable pageable) {
    return postCustomRepository.findByFilter(filter, pageable)
        .map(result -> result.map(PostDocument::toDomain));
  }
}