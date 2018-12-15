package pl.sdacademy.dbConnecton.service.repository;

import pl.sdacademy.dbConnecton.model.Location;

import java.util.Optional;

public interface LocationRepository extends BaseRepository<Location> {
    Optional<Location> find(String rackSymbol, String shelfSymbol, Long position);
}
