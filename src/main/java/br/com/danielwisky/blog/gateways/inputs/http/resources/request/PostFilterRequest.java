package br.com.danielwisky.blog.gateways.inputs.http.resources.request;

import br.com.danielwisky.blog.domains.PostFilter;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostFilterRequest {

  private String title;
  private List<String> tags;

  public PostFilter toDomain() {
    return PostFilter.builder()
        .title(this.title)
        .tags(this.tags)
        .build();
  }
}