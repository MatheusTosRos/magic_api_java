package br.com.unicesumar.magic.repository;

import br.com.unicesumar.magic.entity.Card;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends MongoRepository<Card,String> {
}
