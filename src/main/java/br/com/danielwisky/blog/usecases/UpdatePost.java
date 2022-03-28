package br.com.danielwisky.blog.usecases;

import br.com.danielwisky.blog.domains.Post;
import br.com.danielwisky.blog.domains.exceptions.ResourceNotFoundException;
import br.com.danielwisky.blog.gateways.PostDataGateway;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public record UpdatePost(PostDataGateway postDataGateway) {

  public Mono<Post> execute(final String id, final Post post) {
    return postDataGateway.findById(id)
        .switchIfEmpty(Mono.error(new ResourceNotFoundException()))
        .flatMap(postOnDatabase -> update(post, postOnDatabase));
  }

  private Mono<Post> update(final Post post, final Post postOnDatabase) {
    post.setId(postOnDatabase.getId());
    post.setCreatedDate(postOnDatabase.getCreatedDate());
    return postDataGateway.save(post);
  }
}