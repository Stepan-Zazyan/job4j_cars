package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import ru.job4j.cars.model.Owner;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
public class OwnerRepository {
    private final CrudRepository crudRepository;

    public Owner create(Owner owner) {
        crudRepository.run(session -> session.persist(owner));
        return owner;
    }

    public void update(Owner owner) {
        crudRepository.run(session -> session.merge(owner));
    }

    public void delete(int ownerId) {
        crudRepository.run(
                "delete from Owner where id = :fId",
                Map.of("fId", ownerId)
        );
    }

    public List<Owner> findAllOrderById() {
        return crudRepository.query("from Owner order by id asc", Owner.class);
    }

    public Optional<Owner> findById(int ownerId) {
        return crudRepository.optional(
                "from Owner where id = :fId", Owner.class,
                Map.of("fId", ownerId)
        );
    }

    public List<Owner> findByLikeLogin(String key) {
        return crudRepository.query(
                "from Owner where login like :fKey", Owner.class,
                Map.of("fKey", "%" + key + "%")
        );
    }

    public Optional<Owner> findByLogin(String login) {
        return crudRepository.optional(
                "from Owner where login = :fLogin", Owner.class,
                Map.of("fLogin", login)
        );
    }
}
