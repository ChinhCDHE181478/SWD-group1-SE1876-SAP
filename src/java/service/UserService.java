/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dal.DrivingLicenseDAO;
import dal.UserDAO;
import java.util.List;
import model.DrivingLicense;
import model.User;

/**
 *
 * @author Chinh
 */
public class UserService {
    
    public boolean updateProfile(User user) {
        UserDAO userDAO = new UserDAO();
        return userDAO.updateUser(user);
    }
}
