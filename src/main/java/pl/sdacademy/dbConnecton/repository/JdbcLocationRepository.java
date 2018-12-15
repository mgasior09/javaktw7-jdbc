package pl.sdacademy.dbConnecton.repository;

import pl.sdacademy.dbConnecton.model.Location;
import pl.sdacademy.dbConnecton.service.repository.LocationRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class JdbcLocationRepository implements LocationRepository {
    @Override
    public Optional<Location> find(String rackSymbol, String shelfSymbol, Long position) {
        Location foundLocation = null;
        try (Connection con = DataSourceProvider.getConnection()) {
            PreparedStatement statement = con.prepareStatement("SELECT id, rackSymbol, shelfSymbol, position FROM Location WHERE rackSymbol = ? AND shelfSymbol = ? AND position = ?");
            statement.setString(1, rackSymbol);
            statement.setString(2, shelfSymbol);
            statement.setLong(3, position);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Long foundPosition = rs.getLong("position");
                String foundRackSymbol = rs.getString("rackSymbol");
                String foundShelfSymbol = rs.getString("shelfSymbol");
                Long foundID = rs.getLong("id");
                foundLocation = new Location(foundID, foundRackSymbol, foundShelfSymbol, foundPosition);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(foundLocation);
    }

    @Override
    public Optional<Location> findById(Long id) {
        Location foundLocation = null;
        try (Connection con = DataSourceProvider.getConnection()) {
            PreparedStatement statement = con.prepareStatement("SELECT id, rackSymbol, shelfSymbol, position FROM Location WHERE id = ?");
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                long locationID = rs.getLong("id");
                String rackSymbol = rs.getString("rackSymbol");
                String shelfSymbol = rs.getString("shelfSymbol");
                Long position = rs.getLong("position");
                foundLocation = new Location(locationID, rackSymbol, shelfSymbol, position);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(foundLocation);
    }

    @Override
    public Location save(Location entity) {
        try (Connection con = DataSourceProvider.getConnection()) {
            PreparedStatement statement = con.prepareStatement("INSERT INTO Location ( rackSymbol, shelfSymbol, position) VALUES (?,?, ?)");
            statement.setString(1, entity.getRackSymbol());
            statement.setString(2, entity.getShelfSymbol());
            statement.setLong(3, entity.getPosition());
            statement.execute();
            ResultSet rs = statement.executeQuery("SELECT MAX(id) AS maxID FROM Location");
            if (rs.next()) {
                long locationID = rs.getLong("maxID");
                entity.setId(locationID);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public Location update(Location entity) {
        try (Connection con = DataSourceProvider.getConnection()) {
            PreparedStatement statement = con.prepareStatement("UPDATE Location set rackSymbol = ?, shelfSymbol =?, position = ? where id =?");
            statement.setString(1, entity.getRackSymbol());
            statement.setString(2, entity.getShelfSymbol());
            statement.setLong(3, entity.getPosition());
            statement.setLong(4, entity.getId());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }
}
