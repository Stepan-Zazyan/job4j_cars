package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import ru.job4j.cars.model.Car;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
public class CarRepository {

    private final CrudRepository crudRepository;

    public Car create(Car car) {
        crudRepository.run(session -> session.persist(car));
        return car;
    }

    public void update(Car car) {
        crudRepository.run(session -> session.merge(car));
    }

    public void delete(int carId) {
        crudRepository.run(
                "delete from Car where id = :fId",
                Map.of("fId", carId)
        );
    }

    public List<Car> findAllOrderById() {
        return crudRepository.query("from Car order by id asc", Car.class);
    }

    public Optional<Car> findById(int carId) {
        return crudRepository.optional(
                "from Car where id = :fId", Car.class,
                Map.of("fId", carId)
        );
    }

    public List<Car> findByLikeLogin(String key) {
        return crudRepository.query(
                "from Car where login like :fKey", Car.class,
                Map.of("fKey", "%" + key + "%")
        );
    }

    public Optional<Car> findByLogin(String login) {
        return crudRepository.optional(
                "from Car where login = :fLogin", Car.class,
                Map.of("fLogin", login)
        );
    }
}
