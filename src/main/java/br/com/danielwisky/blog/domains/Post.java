package br.com.danielwisky.blog.domains;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {

  private String id;
  private String title;
  private String description;
  private List<String> tags;

  private LocalDateTime createdDate;
  private LocalDateTime lastModifiedDate;
}