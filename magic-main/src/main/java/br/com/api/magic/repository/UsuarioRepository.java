package br.com.api.magic.repository;

import br.com.api.magic.entity.Usuario;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, ObjectId> {

    Optional<Usuario> findByLogin(String login);
    Optional<Usuario> findById(ObjectId id);
}