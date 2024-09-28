package br.com.unicesumar.magic.repository;

import br.com.unicesumar.magic.entity.Usuario;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, ObjectId> {

    Optional<Usuario> findByLogin(String login);
    Optional<Usuario> findById(ObjectId id);
}