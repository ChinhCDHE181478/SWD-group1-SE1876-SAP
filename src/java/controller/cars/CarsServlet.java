/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.cars;

import dal.CarDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Car;

/**
 *
 * @author Chinh
 */
@WebServlet(name="CarsServlet", urlPatterns={"/CarsServlet"})
public class CarsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
//        int currentPage = Integer.parseInt(request.getParameter("page") == null ? "1" : request.getParameter("page"));
        int currentPage = 1;
        int numberPerPage = 6; // số xe / trang

        CarDAO dao = new CarDAO();
        List<Car> cars = dao.filterCars(null, null, null, null, null, null, currentPage, numberPerPage);
        int totalCars = dao.countFilteredCars(null, null, null, null, null, null);

        int totalPages = (int) Math.ceil((double) totalCars / numberPerPage);

        request.setAttribute("cars", cars);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("numberPerPage", numberPerPage);
        request.setAttribute("totalCars", totalCars);
        request.setAttribute("totalPages", totalPages);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    }

}
