package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import ru.job4j.cars.model.Engine;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
public class EngineRepository {

    private final CrudRepository crudRepository;

    public Engine create(Engine engine) {
        crudRepository.run(session -> session.persist(engine));
        return engine;
    }

    public void update(Engine engine) {
        crudRepository.run(session -> session.merge(engine));
    }

    public void delete(int engineId) {
        crudRepository.run(
                "delete from Engine where id = :fId",
                Map.of("fId", engineId)
        );
    }

    public List<Engine> findAllOrderById() {
        return crudRepository.query("from Engine order by id asc", Engine.class);
    }

    public Optional<Engine> findById(int engineId) {
        return crudRepository.optional(
                "from Engine where id = :fId", Engine.class,
                Map.of("fId", engineId)
        );
    }

    public List<Engine> findByLikeLogin(String key) {
        return crudRepository.query(
                "from Engine where login like :fKey", Engine.class,
                Map.of("fKey", "%" + key + "%")
        );
    }

    public Optional<Engine> findByLogin(String login) {
        return crudRepository.optional(
                "from Engine where login = :fLogin", Engine.class,
                Map.of("fLogin", login)
        );
    }
}
