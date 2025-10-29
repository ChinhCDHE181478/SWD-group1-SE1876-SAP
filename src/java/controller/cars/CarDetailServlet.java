/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.cars;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.*;
import service.*;

/**
 *
 * @author Chinh
 */
@WebServlet(name="CarDetailServlet", urlPatterns={"/CarDetailServlet"})
public class CarDetailServlet extends HttpServlet {
    private final CarService carService = new CarService();
    private final AddressService addressService = new AddressService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        List<Province> provinces = addressService.getAllProvincesWithDistrictsAndWards();
        Long carId = Long.valueOf(request.getParameter("carId"));
        Car c = carService.getCarById(carId);
        System.out.println(c);
        request.setAttribute("provinces", provinces);
        request.setAttribute("car", c);
        request.getRequestDispatcher("/car.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    }

}
