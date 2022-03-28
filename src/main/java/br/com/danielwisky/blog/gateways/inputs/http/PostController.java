package br.com.danielwisky.blog.gateways.inputs.http;

import static org.springframework.data.domain.PageRequest.of;
import static org.springframework.data.domain.Sort.Direction.DESC;

import br.com.danielwisky.blog.domains.exceptions.ResourceNotFoundException;
import br.com.danielwisky.blog.gateways.PostDataGateway;
import br.com.danielwisky.blog.gateways.inputs.http.resources.request.PostFilterRequest;
import br.com.danielwisky.blog.gateways.inputs.http.resources.request.PostRequest;
import br.com.danielwisky.blog.gateways.inputs.http.resources.response.PageResponse;
import br.com.danielwisky.blog.gateways.inputs.http.resources.response.PostResponse;
import br.com.danielwisky.blog.usecases.UpdatePost;
import io.swagger.v3.oas.annotations.Operation;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/posts")
public record PostController(PostDataGateway postDataGateway, UpdatePost updatePost) {

  @PostMapping
  @ResponseStatus(HttpStatus.OK)
  @Operation(description = "Create post")
  public Mono<PostResponse> create(@RequestBody @Valid final PostRequest postRequest) {
    return postDataGateway.save(postRequest.toDomain()).map(PostResponse::new);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(description = "Update post by id")
  public Mono<PostResponse> update(
      @PathVariable final String id,
      @RequestBody @Valid final PostRequest postRequest) {
    return updatePost.execute(id, postRequest.toDomain()).map(PostResponse::new);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @Operation(description = "Delete post by id")
  public Mono<Void> delete(@PathVariable final String id) {
    return postDataGateway.deleteById(id);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(description = "Get post by id")
  public Mono<PostResponse> get(@PathVariable final String id) {
    return postDataGateway.findById(id)
        .switchIfEmpty(Mono.error(new ResourceNotFoundException()))
        .map(PostResponse::new);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  @Operation(description = "Search posts")
  public Mono<PageResponse<PostResponse>> search(
      final PostFilterRequest filterRequest,
      @RequestParam(defaultValue = "0") final Integer page,
      @RequestParam(defaultValue = "20") final Integer size
  ) {
    return postDataGateway.findByFilter(filterRequest.toDomain(), of(page, size, DESC, "id"))
        .map(result -> new PageResponse<>(result.map(PostResponse::new)));
  }
}