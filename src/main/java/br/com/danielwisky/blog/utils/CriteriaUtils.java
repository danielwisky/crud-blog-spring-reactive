package br.com.danielwisky.blog.utils;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;

public class CriteriaUtils {

  public static void addRegexIfNotEmpty(
      final List<Criteria> criteria, final String key, final String value) {
    if (StringUtils.isNotBlank(value)) {
      criteria.add(where(key).regex(value));
    }
  }

  public static void addInIfNotEmpty(
      final List<Criteria> criteria, final String key, final List<String> values) {
    if (CollectionUtils.isNotEmpty(values)) {
      criteria.add(where(key).in(values));
    }
  }

  public static Criteria reduceWithAndOperator(final List<Criteria> criterias) {
    return CollectionUtils.isEmpty(criterias)
        ? new Criteria()
        : new Criteria().andOperator(criterias.toArray(new Criteria[0]));
  }
}