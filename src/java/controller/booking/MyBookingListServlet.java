package controller.booking;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import model.Booking;
import model.User;
import service.BookingService;

@WebServlet(name="MyBookingListServlet", urlPatterns={"/MyBookings"})
public class MyBookingListServlet extends HttpServlet {

    private final BookingService bookingService = new BookingService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("userLogin");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        List<Booking> myBookings = bookingService.getBookingsByUserId(user.getUserId());
        request.setAttribute("myBookings", myBookings);
        request.getRequestDispatcher("my-bookings.jsp").forward(request, response);
    }
}
