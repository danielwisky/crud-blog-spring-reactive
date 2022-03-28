package br.com.danielwisky.blog.usecases;

import static br.com.danielwisky.blog.templates.FixtureCoreTemplates.VALID;
import static br.com.six2six.fixturefactory.Fixture.from;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static reactor.core.publisher.Mono.just;

import br.com.danielwisky.blog.UnitTest;
import br.com.danielwisky.blog.domains.Post;
import br.com.danielwisky.blog.domains.exceptions.ResourceNotFoundException;
import br.com.danielwisky.blog.gateways.PostDataGateway;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class UpdatePostTest extends UnitTest {

  @InjectMocks
  private UpdatePost updatePost;

  @Mock
  private PostDataGateway postDataGateway;

  @Captor
  private ArgumentCaptor<Post> postArgumentCaptor;

  @Test
  public void shouldExecute() {

    final Post postOnDatabase = from(Post.class).gimme(VALID.name());
    final Post post = Post.builder().id(postOnDatabase.getId()).build();

    when(postDataGateway.findById(post.getId())).thenReturn(just(postOnDatabase));
    when(postDataGateway.save(post)).thenReturn(Mono.empty());

    final Mono<Post> result = updatePost.execute(post.getId(), post);

    StepVerifier.create(result)
        .then(() -> verify(postDataGateway).findById(post.getId()))
        .then(() -> verify(postDataGateway).save(postArgumentCaptor.capture()))
        .then(() -> {
          final Post postCaptured = postArgumentCaptor.getValue();
          assertEquals(post.getId(), postCaptured.getId());
          assertEquals(post.getCreatedDate(), postCaptured.getCreatedDate());
        })
        .expectComplete()
        .verify();
  }

  @Test
  public void shouldThrowResourceNotFoundExceptionWhenNotFound() {

    final Post post = Post.builder().id("624200f83a30791c019bcdd3").build();
    when(postDataGateway.findById(post.getId())).thenReturn(Mono.empty());

    final Mono<Post> result = updatePost.execute(post.getId(), post);

    StepVerifier.create(result)
        .then(() -> verify(postDataGateway).findById(post.getId()))
        .expectError(ResourceNotFoundException.class)
        .verify();
  }
}