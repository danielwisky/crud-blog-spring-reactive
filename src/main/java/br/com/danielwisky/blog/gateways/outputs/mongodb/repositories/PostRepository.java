package br.com.danielwisky.blog.gateways.outputs.mongodb.repositories;

import br.com.danielwisky.blog.gateways.outputs.mongodb.documents.PostDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PostRepository extends
    ReactiveMongoRepository<PostDocument, String>, PostCustomRepository {

}