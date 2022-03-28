package br.com.danielwisky.blog.templates;

import static br.com.danielwisky.blog.templates.FixtureCoreTemplates.VALID;

import br.com.danielwisky.blog.domains.Post;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import java.time.LocalDateTime;
import java.util.Arrays;

public class PostTemplateLoader implements TemplateLoader {

  @Override
  public void load() {
    Fixture.of(Post.class).addTemplate(VALID.name(), new Rule() {{
      add("id", "624201003a30791c019bcdd4");
      add("title", "Spring Boot Reactive");
      add("description", "Spring Boot Reactive Description");
      add("tags", Arrays.asList("java", "spring-reactive"));
      add("createdDate", LocalDateTime.now());
      add("lastModifiedDate", LocalDateTime.now());
    }});
  }
}