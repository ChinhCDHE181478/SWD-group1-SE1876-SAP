<%-- 
    Document   : payment-result
    Created on : Nov 4, 2025, 1:26:23 PM
    Author     : Chinh
    REVISED    : Gemini (Nov 10, 2025)
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Payment Result - Cental</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Lato:ital,wght@0,400;0,700;0,900;1,400;1,700;1,900&family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet"> 

        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

        <link href="lib/animate/animate.min.css" rel="stylesheet">
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

        <link href="css/bootstrap.min.css" rel="stylesheet">

        <link href="css/style.css" rel="stylesheet">
    </head>
    <body>
        <div id="spinner" class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
            <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
                <span class="sr-only">Loading...</span>
            </div>
        </div>
        <div class="container-fluid topbar bg-secondary d-none d-xl-block w-100">
            <div class="container">
                <div class="row gx-0 align-items-center" style="height: 45px;">
                    <div class="col-lg-6 text-center text-lg-start mb-lg-0">
                        <div class="d-flex flex-wrap">
                            <a href="#" class="text-muted me-4"><i class="fas fa-map-marker-alt text-primary me-2"></i>Find A Location</a>
                            <a href="tel:+01234567890" class="text-muted me-4"><i class="fas fa-phone-alt text-primary me-2"></i>+01234567890</a>
                            <a href="mailto:example@gmail.com" class="text-muted me-0"><i class="fas fa-envelope text-primary me-2"></i>Example@gmail.com</a>
                        </div>
                    </div>
                    <div class="col-lg-6 text-center text-lg-end">
                        <div class="d-flex align-items-center justify-content-end">
                            <a href="#" class="btn btn-light btn-sm-square rounded-circle me-3"><i class="fab fa-facebook-f"></i></a>
                            <a href="#" class="btn btn-light btn-sm-square rounded-circle me-3"><i class="fab fa-twitter"></i></a>
                            <a href="#" class="btn btn-light btn-sm-square rounded-circle me-3"><i class="fab fa-instagram"></i></a>
                            <a href="#" class="btn btn-light btn-sm-square rounded-circle me-0"><i class="fab fa-linkedin-in"></i></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="container-fluid nav-bar sticky-top px-0 px-lg-4 py-2 py-lg-0">
            <div class="container">
                <nav class="navbar navbar-expand-lg navbar-light">
                    <a href="" class="navbar-brand p-0">
                        <h1 class="display-6 text-primary"><i class="fas fa-car-alt me-3"></i></i>Cental</h1>
                        </a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                        <span class="fa fa-bars"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarCollapse">
                        <div class="navbar-nav mx-auto py-0">
                            <a href="HomeServlet" class="nav-item nav-link">Home</a>
                            <a href="about.html" class="nav-item nav-link">About</a>
                            <a href="service.html" class="nav-item nav-link">Service</a>
                            <a href="blog.html" class="nav-item nav-link">Blog</a>

                            <div class="nav-item dropdown">
                                <a href="#" class="nav-link dropdown-toggle active" data-bs-toggle="dropdown">Pages</a>
                                <div class="dropdown-menu m-0">
                                    <a href="feature.html" class="dropdown-item active">Our Feature</a>
                                    <a href="cars.html" class="dropdown-item">Our Cars</a>
                                    <a href="team.html" class="dropdown-item">Our Team</a>
                                    <a href="testimonial.html" class="dropdown-item">Testimonial</a>
                                    <a href="404.html" class="dropdown-item">404 Page</a>
                                </div>
                            </div>
                            <a href="contact.html" class="nav-item nav-link">Contact</a>

                            <c:if test="${not empty sessionScope.userLogin}">
                                <a href="${pageContext.request.contextPath}/profile" class="nav-item nav-link">Profile</a>
                            </c:if>
                                
                                <c:if test="${not empty sessionScope.userLogin}">
                                <a href="${pageContext.request.contextPath}/MyBookings" class="nav-item nav-link">My Booking</a>
                            </c:if>
                        </div>
                        <c:choose>
                            <c:when test="${not empty sessionScope.userLogin}">
                                <span class="me-2 text-black">
                                    👋 Xin chào, ${sessionScope.userLogin.name != null ? sessionScope.userLogin.name : sessionScope.userLogin.email}
                                </span>
                                <a href="${pageContext.request.contextPath}/logout" 
                                   class="btn btn-danger rounded-pill py-2 px-4">
                                    Logout
                                </a>
                            </c:when>

                            <c:otherwise>
                                <a href="${pageContext.request.contextPath}/login" 
                                   class="btn btn-primary rounded-pill py-2 px-4">
                                    Login
                                </a>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </nav>
            </div>
        </div>
        <div class="container-fluid py-5" style="background-color: #f8f9fa;">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-8 col-md-10">

                        <c:choose>
                            <%-- ================= TRƯỜNG HỢP THANH TOÁN THÀNH CÔNG ================= --%>
                            <c:when test="${not empty payment && payment.status == 'SUCCESS'}">
                                <div class="card shadow-lg border-0 rounded-3 overflow-hidden wow fadeInUp" data-wow-delay="0.1s">
                                    <div class="card-header bg-success text-white text-center p-4">
                                        <i class="fas fa-check-circle fa-5x"></i>
                                        <h1 class="display-4 mt-3 mb-0 text-white">Payment Successful!</h1>
                                        <p class="lead mb-0">Your booking is confirmed.</p>
                                    </div>
                                    <div class="card-body p-4 p-md-5">
                                        <h3 class="mb-3">Hello, ${sessionScope.userLogin.name}!</h3>
                                        <p class="lead mb-4">
                                            Thank you for your payment. Your car rental booking is complete. 
                                            We've sent a confirmation email to <strong>${sessionScope.userLogin.email}</strong> with all the details.
                                        </p>

                                        <h4 class="mb-3 text-primary">Transaction Summary</h4>
                                        <ul class="list-group list-group-flush mb-4">
                                            <li class="list-group-item d-flex justify-content-between align-items-center">
                                                <span><i class="fas fa-hashtag me-2 text-muted"></i>Booking ID</span>
                                                <strong class="text-dark">#${bookingId}</strong>
                                            </li>
                                            <li class="list-group-item d-flex justify-content-between align-items-center">
                                                <span><i class="fas fa-receipt me-2 text-muted"></i>Transaction Reference</span>
                                                <span class="text-dark" style="word-break: break-all;">${payment.transactionRef}</span>
                                            </li>
                                            <li class="list-group-item d-flex justify-content-between align-items-center">
                                                <span><i class="fas fa-credit-card me-2 text-muted"></i>Payment Method</span>
                                                <span class="badge bg-primary fs-6">${payment.method}</span>
                                            </li>
                                            <li class="list-group-item d-flex justify-content-between align-items-center">
                                                <span><i class="fas fa-file-invoice-dollar me-2 text-muted"></i>Total Amount Paid</span>
                                                <strong class="fs-4 text-success">
                                                    <fmt:formatNumber value="${payment.amount}" type="number" minFractionDigits="0"/> VND
                                                </strong>
                                            </li>
                                        </ul>

                                        <h4 class="mb-3 text-primary">What's Next?</h4>
                                        <p>Please check your email for the full booking confirmation and instructions on picking up your vehicle. You can also view and manage all your bookings from your profile.</p>
                                    </div>
                                    <div class="card-footer text-center p-4 bg-light">
                                        <a href="MyBookings" class="btn btn-primary btn-lg rounded-pill py-3 px-5 me-2">
                                            <i class="fas fa-list-alt me-2"></i> View My Bookings
                                        </a>
                                        <a href="HomeServlet" class="btn btn-outline-secondary rounded-pill py-3 px-5">
                                            <i class="fas fa-home me-2"></i> Back to Home
                                        </a>
                                    </div>
                                </div>
                            </c:when>

                            <%-- ================= TRƯỜNG HỢP THANH TOÁN THẤT BẠI ================= --%>
                            <c:when test="${not empty payment && payment.status == 'FAILED'}">
                                <div class="card shadow-lg border-0 rounded-3 overflow-hidden wow fadeInUp" data-wow-delay="0.1s">
                                    <div class="card-header bg-danger text-white text-center p-4">
                                        <i class="fas fa-times-circle fa-5x"></i>
                                        <h1 class="display-4 mt-3 mb-0 text-white">Payment Failed</h1>
                                        <p class="lead mb-0">Your transaction could not be completed.</p>
                                    </div>
                                    <div class="card-body p-4 p-md-5">
                                        <h3 class="mb-3">Hello, ${sessionScope.userLogin.name}.</h3>
                                        <p class="lead mb-4">
                                            Unfortunately, there was an issue processing your payment, or the transaction was cancelled.
                                            Your booking (<strong>#${bookingId}</strong>) has not been confirmed.
                                        </p>

                                        <h4 class="mb-3 text-danger">Transaction Details</h4>
                                        <ul class="list-group list-group-flush mb-4">
                                            <li class="list-group-item d-flex justify-content-between align-items-center">
                                                <span><i class="fas fa-hashtag me-2 text-muted"></i>Booking ID</span>
                                                <strong class="text-dark">#${bookingId}</strong>
                                            </li>
                                            <li class="list-group-item d-flex justify-content-between align-items-center">
                                                <span><i class="fas fa-receipt me-2 text-muted"></i>Transaction Reference</span>
                                                <span class="text-dark" style="word-break: break-all;">${payment.transactionRef}</span>
                                            </li>
                                            <li class="list-group-item d-flex justify-content-between align-items-center">
                                                <span><i class="fas fa-exclamation-triangle me-2 text-muted"></i>Payment Status</span>
                                                <span class="badge bg-danger fs-6">${payment.status}</span>
                                            </li>
                                            <li class="list-group-item d-flex justify-content-between align-items-center">
                                                <span><i class="fas fa-file-invoice-dollar me-2 text-muted"></i>Attempted Amount</span>
                                                <strong class="fs-4 text-dark">
                                                    <fmt:formatNumber value="${payment.amount}" type="number" minFractionDigits="0"/> VND
                                                </strong>
                                            </li>
                                        </ul>

                                        <h4 class="mb-3 text-danger">What To Do?</h4>
                                        <p>No funds have been withdrawn from your account. You can return to our car listing to create a new booking. If this problem persists, please contact our support team.</p>
                                        <p class="text-center text-danger fw-bold fs-5">${message}</p>
                                    </div>
                                    <div class="card-footer text-center p-4 bg-light">
                                        <a href="HomeServlet" class="btn btn-warning btn-lg rounded-pill py-3 px-5 me-2">
                                            <i class="fas fa-search me-2"></i> Find Another Car
                                        </a>
                                        <a href="HomeServlet" class="btn btn-outline-secondary rounded-pill py-3 px-5">
                                            <i class="fas fa-home me-2"></i> Back to Home
                                        </a>
                                    </div>
                                </div>
                            </c:when>

                            <%-- ================= TRƯỜNG HỢP LỖI KHÔNG XÁC ĐỊNH ================= --%>
                            <c:otherwise>
                                <div class="card shadow-lg border-warning rounded-3 wow fadeInUp" data-wow-delay="0.1s">
                                    <div class="card-header bg-warning text-dark text-center p-4">
                                        <i class="fas fa-exclamation-triangle fa-5x"></i>
                                        <h1 class="display-4 mt-3 mb-0">Invalid Request</h1>
                                    </div>
                                    <div class="card-body p-4 p-md-5 text-center">
                                        <h3 class="mb-3">Oops! Something went wrong.</h3>
                                        <p class="lead mb-4">
                                            We couldn't find the transaction details you're looking for. This might be due to an expired session or an invalid link.
                                        </p>
                                        <p class="text-danger fw-bold fs-5">${error}</p>
                                    </div>
                                    <div class="card-footer text-center p-4 bg-light">
                                        <a href="HomeServlet" class="btn btn-primary btn-lg rounded-pill py-3 px-5">
                                            <i class="fas fa-home me-2"></i> Return to Homepage
                                        </a>
                                    </div>
                                </div>
                            </c:otherwise>
                        </c:choose>

                    </div>
                </div>
            </div>
        </div>
        <div class="container-fluid footer py-5 wow fadeIn" data-wow-delay="0.2s">
            <div class="container py-5">
                <div class="row g-5">
                    <div class="col-md-6 col-lg-6 col-xl-3">
                        <div class="footer-item d-flex flex-column">
                            <div class="footer-item">
                                <h4 class="text-white mb-4">About Us</h4>
                                <p class="mb-3">Dolor amet sit justo amet elitr clita ipsum elitr est.Lorem ipsum dolor sit amet, consectetur adipiscing elit consectetur adipiscing elit.</p>
                            </div>
                            <div class="position-relative">
                                <input class="form-control rounded-pill w-100 py-3 ps-4 pe-5" type="text" placeholder="Enter your email">
                                <button type="button" class="btn btn-secondary rounded-pill position-absolute top-0 end-0 py-2 mt-2 me-2">Subscribe</button>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-6 col-xl-3">
                        <div class="footer-item d-flex flex-column">
                            <h4 class="text-white mb-4">Quick Links</h4>
                            <a href="#"><i class="fas fa-angle-right me-2"></i> About</a>
                            <a href="#"><i class="fas fa-angle-right me-2"></i> Cars</a>
                            <a href="#"><i class="fas fa-angle-right me-2"></i> Car Types</a>
                            <a href="#"><i class="fas fa-angle-right me-2"></i> Team</a>
                            <a href="#"><i class="fas fa-angle-right me-2"></i> Contact us</a>
                            <a href="#"><i class="fas fa-angle-right me-2"></i> Terms & Conditions</a>
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-6 col-xl-3">
                        <div class="footer-item d-flex flex-column">
                            <h4 class="text-white mb-4">Business Hours</h4>
                            <div class="mb-3">
                                <h6 class="text-muted mb-0">Mon - Friday:</h6>
                                <p class="text-white mb-0">09.00 am to 07.00 pm</p>
                            </div>
                            <div class="mb-3">
                                <h6 class="text-muted mb-0">Saturday:</h6>
                                <p class="text-white mb-0">10.00 am to 05.00 pm</p>
                            </div>
                            <div class="mb-3">
                                <h6 class="text-muted mb-0">Vacation:</h6>
                                <p class="text-white mb-0">All Sunday is our vacation</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-6 col-xl-3">
                        <div class="footer-item d-flex flex-column">
                            <h4 class="text-white mb-4">Contact Info</h4>
                            <a href="#"><i class="fa fa-map-marker-alt me-2"></i> 123 Street, New York, USA</a>
                            <a href="mailto:info@example.com"><i class="fas fa-envelope me-2"></i> info@example.com</a>
                            <a href="tel:+012 345 67890"><i class="fas fa-phone me-2"></i> +012 345 67890</a>
                            <a href="tel:+012 345 67890" class="mb-3"><i class="fas fa-print me-2"></i> +012 345 67890</a>
                            <div class="d-flex">
                                <a class="btn btn-secondary btn-md-square rounded-circle me-3" href=""><i class="fab fa-facebook-f text-white"></i></a>
                                <a class="btn btn-secondary btn-md-square rounded-circle me-3" href=""><i class="fab fa-twitter text-white"></i></a>
                                <a class="btn btn-secondary btn-md-square rounded-circle me-3" href=""><i class="fab fa-instagram text-white"></i></a>
                                <a class="btn btn-secondary btn-md-square rounded-circle me-0" href=""><i class="fab fa-linkedin-in text-white"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="container-fluid copyright py-4">
            <div class="container">
                <div class="row g-4 align-items-center">
                    <div class="col-md-6 text-center text-md-start mb-md-0">
                        <span class="text-body"><a href="#" class="border-bottom text-white"><i class="fas fa-copyright text-light me-2"></i>Your Site Name</a>, All right reserved.</span>
                    </div>
                    <div class="col-md-6 text-center text-md-end text-body">
                        Designed By <a class="border-bottom text-white" href="https://htmlcodex.com">HTML Codex</a>
                    </div>
                </div>
            </div>
        </div>
        <a href="#" class="btn btn-secondary btn-lg-square rounded-circle back-to-top"><i class="fa fa-arrow-up"></i></a> 

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="lib/wow/wow.min.js"></script>
        <script src="lib/easing/easing.min.js"></script>
        <script src="lib/waypoints/waypoints.min.js"></script>
        <script src="lib/counterup/counterup.min.js"></script>
        <script src="lib/owlcarousel/owl.carousel.min.js"></script>


        <script src="js/main.js"></script>

    </body>
</html>