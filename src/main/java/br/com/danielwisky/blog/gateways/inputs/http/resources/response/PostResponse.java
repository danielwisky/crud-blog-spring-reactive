package br.com.danielwisky.blog.gateways.inputs.http.resources.response;

import br.com.danielwisky.blog.domains.Post;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PostResponse {

  private String id;
  private String title;
  private String description;
  private List<String> tags;
  private LocalDateTime createdDate;
  private LocalDateTime lastModifiedDate;

  public PostResponse(final Post post) {
    this.id = post.getId();
    this.title = post.getTitle();
    this.description = post.getDescription();
    this.tags = post.getTags();
    this.createdDate = post.getCreatedDate();
    this.lastModifiedDate = post.getLastModifiedDate();
  }
}