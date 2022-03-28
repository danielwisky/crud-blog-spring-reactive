package br.com.danielwisky.blog.gateways.inputs.http.resources.request;

import br.com.danielwisky.blog.domains.Post;
import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostRequest {

  @NotBlank
  private String title;
  @NotBlank
  private String description;
  private List<String> tags;

  public Post toDomain() {
    return Post.builder()
        .title(this.title)
        .description(this.description)
        .tags(this.tags)
        .build();
  }
}