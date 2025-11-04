/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dal.PromotionDAO;
import java.util.List;
import model.Promotion;

/**
 *
 * @author Chinh
 */
public class PromotionService {
    
    public List<Promotion> getAllActivePromotions() {
        PromotionDAO promotionDAO = new PromotionDAO();
        return promotionDAO.getAllActivePromotions();
    }
    
}
