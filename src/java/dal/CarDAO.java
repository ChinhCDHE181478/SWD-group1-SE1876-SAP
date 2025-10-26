/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import java.util.List;
import model.Brand;
import model.Car;
import model.CarCategory;
import model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import model.CarFeature;
import model.CarImage;
import model.FeatureDefinition;
import model.Role;

/**
 *
 * @author Chinh
 */
public class CarDAO extends DBContext<Car>{
    
    public List<Car> filterCars(
            String model, String seat, Double minPrice, Double maxPrice,
            String status, Long categoryId,
            int currentPage, int numberPerPage) {

        List<Car> list = new ArrayList<>();
        Map<Long, Car> carMap = new LinkedHashMap<>(); // giữ thứ tự sắp xếp

        StringBuilder sql = new StringBuilder("""
            SELECT 
                c.car_id, c.model, c.license_plate, c.seat,
                c.price_per_day, c.status, c.created_at,
                c.deposit, c.update_at, c.admin_fee_percent,
                b.brand_id, b.brand_name,
                cat.cate_id, cat.cate_name,
                u.user_id, u.name, u.email, u.phone,
                img.image_id, img.image
            FROM Car c
            LEFT JOIN Brand b ON c.brand_id = b.brand_id
            LEFT JOIN CarCategory cat ON c.category_id = cat.cate_id
            LEFT JOIN [User] u ON c.owner_id = u.user_id
            LEFT JOIN Car_Image img ON c.car_id = img.car_id
            WHERE 1=1
        """);

        // Build dynamic filters
        if (model != null && !model.trim().isEmpty()) {
            sql.append(" AND c.model LIKE ? ");
        }
        if (seat != null && !seat.trim().isEmpty()) {
            sql.append(" AND c.seat = ? ");
        }
        if (minPrice != null) {
            sql.append(" AND TRY_CAST(c.price_per_day AS FLOAT) >= ? ");
        }
        if (maxPrice != null) {
            sql.append(" AND TRY_CAST(c.price_per_day AS FLOAT) <= ? ");
        }
        if (status != null && !status.trim().isEmpty()) {
            sql.append(" AND c.status = ? ");
        }
        if (categoryId != null) {
            sql.append(" AND c.category_id = ? ");
        }

        // Order + Pagination (SQL Server)
        sql.append("""
            ORDER BY c.created_at DESC
            OFFSET ? ROWS FETCH NEXT ? ROWS ONLY
        """);

        try (PreparedStatement ps = connection.prepareStatement(sql.toString())) {
            int index = 1;

            if (model != null && !model.trim().isEmpty()) {
                ps.setString(index++, "%" + model + "%");
            }
            if (seat != null && !seat.trim().isEmpty()) {
                ps.setString(index++, seat);
            }
            if (minPrice != null) {
                ps.setDouble(index++, minPrice);
            }
            if (maxPrice != null) {
                ps.setDouble(index++, maxPrice);
            }
            if (status != null && !status.trim().isEmpty()) {
                ps.setString(index++, status);
            }
            if (categoryId != null) {
                ps.setLong(index++, categoryId);
            }

            // Pagination values
            int offset = (currentPage - 1) * numberPerPage;
            ps.setInt(index++, offset);
            ps.setInt(index++, numberPerPage);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("car_id");
                Car car = carMap.get(id);

                if (car == null) {
                    car = new Car();
                    car.setCarId(id);
                    car.setModel(rs.getString("model"));
                    car.setLicensePlate(rs.getString("license_plate"));
                    car.setSeat(rs.getString("seat"));

                    try {
                        car.setPricePerDay(Double.parseDouble(rs.getString("price_per_day")));
                    } catch (Exception e) {
                        car.setPricePerDay(0);
                    }

                    car.setStatus(rs.getString("status"));
                    car.setCreatedAt(rs.getTimestamp("created_at"));
                    car.setDeposit(rs.getDouble("deposit"));
                    car.setUpdateAt(rs.getTimestamp("update_at"));
                    car.setAdminFeePercent(rs.getDouble("admin_fee_percent"));

                    // Brand
                    Brand brand = new Brand();
                    brand.setBrandId(rs.getLong("brand_id"));
                    brand.setBrandName(rs.getString("brand_name"));
                    car.setBrand(brand);

                    // Category
                    CarCategory cat = new CarCategory();
                    cat.setCateId(rs.getLong("cate_id"));
                    cat.setCateName(rs.getString("cate_name"));
                    car.setCategory(cat);

                    // Owner
                    User owner = new User();
                    owner.setUserId(rs.getLong("user_id"));
                    owner.setName(rs.getString("name"));
                    owner.setEmail(rs.getString("email"));
                    owner.setPhone(rs.getString("phone"));
                    car.setOwner(owner);

                    // init image list
                    car.setImages(new ArrayList<>());
                    carMap.put(id, car);
                }

                // Add image if exists
                long imgId = rs.getLong("image_id");
                String imgUrl = rs.getString("image");
                if (imgId > 0 && imgUrl != null) {
                    boolean exists = car.getImages().stream()
                            .anyMatch(i -> i.getImageId() == imgId);
                    if (!exists) {
                        CarImage img = new CarImage();
                        img.setImageId(imgId);
                        img.setImage(imgUrl);
                        car.getImages().add(img);
                    }
                }
            }

            rs.close();

            list.addAll(carMap.values());

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return list;
    }

    // Đếm tổng số bản ghi để tính totalPages
    public int countFilteredCars(String model, String seat, Double minPrice, Double maxPrice,
                                 String status, Long categoryId) {
        int total = 0;

        StringBuilder sql = new StringBuilder("""
            SELECT COUNT(DISTINCT c.car_id) AS total
            FROM Car c
            WHERE 1=1
        """);

        if (model != null && !model.trim().isEmpty()) {
            sql.append(" AND c.model LIKE ? ");
        }
        if (seat != null && !seat.trim().isEmpty()) {
            sql.append(" AND c.seat = ? ");
        }
        if (minPrice != null) {
            sql.append(" AND TRY_CAST(c.price_per_day AS FLOAT) >= ? ");
        }
        if (maxPrice != null) {
            sql.append(" AND TRY_CAST(c.price_per_day AS FLOAT) <= ? ");
        }
        if (status != null && !status.trim().isEmpty()) {
            sql.append(" AND c.status = ? ");
        }
        if (categoryId != null) {
            sql.append(" AND c.category_id = ? ");
        }

        try (PreparedStatement ps = connection.prepareStatement(sql.toString())) {
            int index = 1;
            if (model != null && !model.trim().isEmpty()) {
                ps.setString(index++, "%" + model + "%");
            }
            if (seat != null && !seat.trim().isEmpty()) {
                ps.setString(index++, seat);
            }
            if (minPrice != null) {
                ps.setDouble(index++, minPrice);
            }
            if (maxPrice != null) {
                ps.setDouble(index++, maxPrice);
            }
            if (status != null && !status.trim().isEmpty()) {
                ps.setString(index++, status);
            }
            if (categoryId != null) {
                ps.setLong(index++, categoryId);
            }

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt("total");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return total;
    }
    
    
    public Car getCarById(long carId) {
        Car car = null;

        String sql = """
            SELECT 
                c.car_id, c.model, c.license_plate, c.seat,
                c.price_per_day, c.status, c.created_at,
                c.deposit, c.update_at, c.admin_fee_percent,

                b.brand_id, b.brand_name,
                cat.cate_id, cat.cate_name,

                u.user_id, u.name AS owner_name, u.email AS owner_email, u.phone AS owner_phone,
                r.role_id, r.role_name, r.description AS role_desc,

                img.image_id, img.image,

                f.feature_id, f.feature_value,
                d.feature_def_id, d.feature_name, d.description AS feature_desc

            FROM Car c
            LEFT JOIN Brand b ON c.brand_id = b.brand_id
            LEFT JOIN CarCategory cat ON c.category_id = cat.cate_id
            LEFT JOIN [User] u ON c.owner_id = u.user_id
            LEFT JOIN [Role] r ON u.role_id = r.role_id
            LEFT JOIN Car_Image img ON c.car_id = img.car_id
            LEFT JOIN Car_Feature f ON c.car_id = f.car_id
            LEFT JOIN Feature_Definition d ON f.feature_def_id = d.feature_def_id
            WHERE c.car_id = ?
            ORDER BY img.image_id, f.feature_id
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, carId);
            ResultSet rs = ps.executeQuery();

            Map<Long, Car> carMap = new HashMap<>();

            while (rs.next()) {
                long id = rs.getLong("car_id");
                car = carMap.get(id);

                if (car == null) {
                    car = new Car();
                    car.setCarId(id);
                    car.setModel(rs.getString("model"));
                    car.setLicensePlate(rs.getString("license_plate"));
                    car.setSeat(rs.getString("seat"));

                    try {
                        car.setPricePerDay(Double.parseDouble(rs.getString("price_per_day")));
                    } catch (Exception e) {
                        car.setPricePerDay(0);
                    }

                    car.setStatus(rs.getString("status"));
                    car.setCreatedAt(rs.getTimestamp("created_at"));
                    car.setDeposit(rs.getDouble("deposit"));
                    car.setUpdateAt(rs.getTimestamp("update_at"));
                    car.setAdminFeePercent(rs.getDouble("admin_fee_percent"));

                    // Brand
                    Brand brand = new Brand();
                    brand.setBrandId(rs.getLong("brand_id"));
                    brand.setBrandName(rs.getString("brand_name"));
                    car.setBrand(brand);

                    // Category
                    CarCategory cat = new CarCategory();
                    cat.setCateId(rs.getLong("cate_id"));
                    cat.setCateName(rs.getString("cate_name"));
                    car.setCategory(cat);

                    // Owner + Role
                    Role role = new Role();
                    role.setRoleId(rs.getLong("role_id"));
                    role.setRoleName(rs.getString("role_name"));
                    role.setDescription(rs.getString("role_desc"));

                    User owner = new User();
                    owner.setUserId(rs.getLong("user_id"));
                    owner.setName(rs.getString("owner_name"));
                    owner.setEmail(rs.getString("owner_email"));
                    owner.setPhone(rs.getString("owner_phone"));
                    owner.setRole(role);

                    car.setOwner(owner);

                    // Initialize collections
                    car.setImages(new ArrayList<>());
                    car.setFeatures(new ArrayList<>());

                    carMap.put(id, car);
                }

                // Add image (avoid duplicates)
                long imgId = rs.getLong("image_id");
                String imgUrl = rs.getString("image");
                if (imgId > 0 && imgUrl != null) {
                    boolean exists = car.getImages().stream()
                            .anyMatch(i -> i.getImageId() == imgId);
                    if (!exists) {
                        CarImage img = new CarImage();
                        img.setImageId(imgId);
                        img.setImage(imgUrl);
                        car.getImages().add(img);
                    }
                }

                // Add feature (avoid duplicates)
                long featureId = rs.getLong("feature_id");
                String featureValue = rs.getString("feature_value");
                if (featureId > 0 && featureValue != null) {
                    boolean exists = car.getFeatures().stream()
                            .anyMatch(f -> f.getFeatureId() == featureId);
                    if (!exists) {
                        CarFeature feature = new CarFeature();
                        feature.setFeatureId(featureId);
                        feature.setFeatureValue(featureValue);

                        FeatureDefinition def = new FeatureDefinition();
                        def.setFeatureDefId(rs.getLong("feature_def_id"));
                        def.setFeatureName(rs.getString("feature_name"));
                        def.setDescription(rs.getString("feature_desc"));
                        feature.setDefinition(def);

                        car.getFeatures().add(feature);
                    }
                }
            }

            rs.close();
            if (!carMap.isEmpty()) {
                car = carMap.values().iterator().next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return car;
    }
    
    
    public static void main(String[] args) {
        CarDAO c = new CarDAO();
        List<Car> c1 = c.filterCars(null, null, null, null, null, null, 1, 10);
        for(Car c2 : c1) {
            System.out.println(c2.getCarId());
        }
    }
}
