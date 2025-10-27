/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import model.DrivingLicense;

/**
 *
 * @author Chinh
 */
public class Common {
    
    // Thứ bậc bằng lái ô tô ở Việt Nam (từ thấp -> cao)
    // (Các loại xe máy A1, A2, A3 không xét trong hệ thống này)
    private static final List<String> CAR_LICENSE_ORDER = Arrays.asList(
        "B1", "B2", "C", "D", "E", "F"
    );
    
    
    public static boolean hasValidLicenseForCar(List<DrivingLicense> licenses, String requiredClass) {
        if (licenses == null || licenses.isEmpty()) return false;

        // Nếu xe không yêu cầu bằng cụ thể => ai cũng thuê được
        if (requiredClass == null || requiredClass.trim().isEmpty()) return true;

        requiredClass = requiredClass.trim().toUpperCase();
        Timestamp now = new Timestamp(System.currentTimeMillis());

        for (DrivingLicense license : licenses) {
            String userClass = license.getLicenseClass();
            if (userClass == null) continue;

            userClass = userClass.trim().toUpperCase();

            // Bỏ qua nếu không thuộc loại ô tô
            if (!CAR_LICENSE_ORDER.contains(userClass)) continue;

            // Kiểm tra hạn sử dụng
            if (license.getExpiredDate() == null || license.getExpiredDate().before(now)) continue;

            // Nếu user có hạng >= required hạng
            if (isLicenseHigherOrEqual(userClass, requiredClass)) {
                return true;
            }
        }

        return false;
    }
    
    private static boolean isLicenseHigherOrEqual(String userClass, String requiredClass) {
        int userRank = CAR_LICENSE_ORDER.indexOf(userClass);
        int requiredRank = CAR_LICENSE_ORDER.indexOf(requiredClass);

        // Nếu bằng không hợp lệ (không nằm trong list) -> false
        if (userRank == -1 || requiredRank == -1) return false;

        // Hạng cao hơn = chỉ số lớn hơn
        return userRank >= requiredRank;
    }
}
