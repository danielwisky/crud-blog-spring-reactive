package br.com.danielwisky.blog.gateways.outputs.mongodb.documents;

import br.com.danielwisky.blog.domains.Post;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("posts")
public class PostDocument {

  @Id
  private String id;
  @Indexed
  private String title;
  private String description;
  @Indexed
  private List<String> tags;

  @CreatedDate
  private LocalDateTime createdDate;
  @LastModifiedDate
  private LocalDateTime lastModifiedDate;

  public PostDocument(final Post post) {
    this.id = post.getId();
    this.title = post.getTitle();
    this.description = post.getDescription();
    this.tags = post.getTags();
    this.createdDate = post.getCreatedDate();
    this.lastModifiedDate = post.getLastModifiedDate();
  }

  public Post toDomain() {
    return Post.builder()
        .id(this.id)
        .title(this.title)
        .description(this.description)
        .tags(this.tags)
        .createdDate(this.createdDate)
        .lastModifiedDate(this.lastModifiedDate)
        .build();
  }
}
