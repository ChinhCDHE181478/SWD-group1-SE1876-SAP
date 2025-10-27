/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dal.DrivingLicenseDAO;
import java.util.List;
import model.DrivingLicense;
import model.User;
import utils.Common;

/**
 *
 * @author Chinh
 */
public class DrivingLicenseService {
    
    public Boolean checkDrivingLicense(User user, String requiredClass) {
        DrivingLicenseDAO d = new DrivingLicenseDAO();
        List<DrivingLicense> list = d.getLicensesByUser(user);
        boolean ok = Common.hasValidLicenseForCar(list, requiredClass);
        return ok;
    }
}
