package br.com.danielwisky.blog.gateways.inputs.http.resources.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PageResponse<T> {

  private List<T> content;
  private Long totalElements;
  private Integer totalPages;
  private Integer size;
  private Integer page;

  public PageResponse(final Page<T> page) {
    this.content = page.getContent();
    this.totalElements = page.getTotalElements();
    this.totalPages = page.getTotalPages();
    this.size = page.getSize();
    this.page = page.getNumber();
  }
}
