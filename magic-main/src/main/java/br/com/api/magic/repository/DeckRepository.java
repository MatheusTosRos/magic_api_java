package br.com.api.magic.repository;

import br.com.api.magic.entity.Deck;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeckRepository extends MongoRepository<Deck, String> {

    List<Deck> findAll();
    List<Deck> findByUser(String user);
}
