<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Cental - Car Rent Website</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Lato:ital,wght@0,400;0,700;0,900;1,400;1,700;1,900&family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet"> 

        <!-- Icon Font Stylesheet -->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="lib/animate/animate.min.css" rel="stylesheet">
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">


        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Template Stylesheet -->
        <link href="css/style.css" rel="stylesheet">
    </head>
    <body>


        <!-- Spinner Start -->
        <div id="spinner" class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
            <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
                <span class="sr-only">Loading...</span>
            </div>
        </div>
        <!-- Spinner End -->

        <!-- Topbar Start -->
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
        <!-- Topbar End -->

        <!-- Navbar & Hero Start -->
        <div class="container-fluid nav-bar sticky-top px-0 px-lg-4 py-2 py-lg-0">
            <div class="container">
                <nav class="navbar navbar-expand-lg navbar-light">
                    <a href="" class="navbar-brand p-0">
                        <h1 class="display-6 text-primary"><i class="fas fa-car-alt me-3"></i></i>Cental</h1>
                        <!-- <img src="img/logo.png" alt="Logo"> -->
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

                            <!-- Nếu đã đăng nhập -->
                            <c:if test="${not empty sessionScope.userLogin}">
                                <a href="${pageContext.request.contextPath}/profile" class="nav-item nav-link">Profile</a>
                            </c:if>
                        </div>
                        <c:choose>
                            <c:when test="${not empty sessionScope.userLogin}">
                                <!-- Khi user đã đăng nhập -->
                                <span class="me-2 text-black">
                                    👋 Xin chào, ${sessionScope.userLogin.name != null ? sessionScope.userLogin.name : sessionScope.userLogin.email}
                                </span>
                                <a href="${pageContext.request.contextPath}/logout" 
                                   class="btn btn-danger rounded-pill py-2 px-4">
                                    Logout
                                </a>
                            </c:when>

                            <c:otherwise>
                                <!-- Khi user chưa đăng nhập -->
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
        <!-- Navbar & Hero End -->

        <div class="container py-5">
            <div class="card shadow-lg border-0 rounded-4">
                <div class="card-header bg-primary text-white text-center">
                    <h3 class="mb-0">🔒 Confirm Your Booking</h3>
                </div>

                <div class="card-body p-4">
                    <c:set var="b" value="${booking}" />
                    <div class="row mb-4">
                        <div class="col-lg-6">
                            <h5 class="text-primary fw-bold mb-3">👤 Customer Information</h5>
                            <p><strong>Full Name:</strong> ${b.customer.name}</p>
                            <p><strong>Phone:</strong> ${b.customer.phone}</p>
                            <p><strong>Email:</strong> ${b.customer.email}</p>
                        </div>

                        <div class="col-lg-6">
                            <h5 class="text-primary fw-bold mb-3">📍 Address Information</h5>
                            <p><strong>Pick-up Address:</strong> 
                                ${b.address.houseNumber}, 
                                ${b.address.addressDetail},
                                ${b.address.ward.wardName}, 
                                ${b.address.district.districtName}, 
                                ${b.address.province.provinceName}</p>
                        </div>
                    </div>

                    <hr class="my-4"/>

                    <!-- Xe được thuê -->
                    <div class="row mb-4">
                        <div class="col-md-4">
                            <img src="${b.car.images[0].image}" class="img-fluid rounded shadow-sm" alt="${b.car.model}">
                        </div>
                        <div class="col-md-8">
                            <h5 class="text-primary fw-bold mb-3">🚗 Car Information</h5>
                            <p><strong>Brand:</strong> ${b.car.brand.brandName}</p>
                            <p><strong>Model:</strong> ${b.car.model}</p>
                            <p><strong>Seats:</strong> ${b.car.seat}</p>
                            <p><strong>License Required:</strong> ${b.car.driverLicenseRequired}</p>
                            <p><strong>Price per day:</strong> 
                                <fmt:formatNumber value="${b.car.pricePerDay}" type="currency" currencySymbol="₫"/>
                            </p>
                        </div>
                    </div>

                    <hr class="my-4"/>

                    <!-- Thông tin thời gian -->
                    <div class="row mb-4">
                        <div class="col-md-6">
                            <h5 class="text-primary fw-bold mb-3">⏰ Booking Duration</h5>
                            <p><strong>Start Date:</strong> <fmt:formatDate value="${b.startDate}" pattern="yyyy-MM-dd"/></p>
                            <p><strong>End Date:</strong> <fmt:formatDate value="${b.endDate}" pattern="yyyy-MM-dd"/></p>
                            <p><strong>Total Days:</strong> ${days}</p>
                        </div>

                        <div class="col-md-6">
                            <h5 class="text-primary fw-bold mb-3">💰 Payment Summary</h5>
                            <p><strong>Original Price:</strong> 
                                <span id="originalPrice">
                                    <fmt:formatNumber value="${totalPrice}" type="currency" currencySymbol="₫"/>
                                </span>
                            </p>
                            <p><strong>Promotion:</strong> 
                                <span id="selectedPromoText" class="text-success">None</span>
                                <button type="button" class="btn btn-outline-primary btn-sm ms-2" data-bs-toggle="modal" data-bs-target="#promoModal">
                                    🎁 Select Promotion
                                </button>
                            </p>
                            <p><strong>Total after discount:</strong>
                                <span id="finalPriceText" class="fw-bold text-danger">
                                    <fmt:formatNumber value="${totalPrice}" type="currency" currencySymbol="₫"/>
                                </span>
                            </p>
                        </div>
                    </div>

                    <form id="confirmForm" action="CreateBooking" method="post">
                        <!-- Hidden fields -->
                        <input type="hidden" name="carId" value="${b.car.carId}">
                        <input type="hidden" name="provinceId" value="${b.address.province.provinceId}">
                        <input type="hidden" name="districtId" value="${b.address.district.districtId}">
                        <input type="hidden" name="wardId" value="${b.address.ward.wardId}">
                        <input type="hidden" name="startDate" value="${b.startDate}">
                        <input type="hidden" name="endDate" value="${b.endDate}">
                        <input type="hidden" name="houseNumber" value="${b.address.houseNumber}">
                        <input type="hidden" name="addressDetail" value="${b.address.addressDetail}">
                        <input type="hidden" id="promotionId" name="promotionId" value="">
                        <input type="hidden" id="finalTotal" name="finalTotal" value="${totalPrice}">

                        <div class="text-center mt-4">
                            <button type="submit" class="btn btn-success rounded-pill px-4 py-2 me-3">
                                ✅ Confirm Booking
                            </button>
                            <a href="CarDetailServlet?carId=${b.car.carId}" class="btn btn-outline-danger rounded-pill px-4 py-2">
                                ❌ Cancel
                            </a>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- Modal chọn Promotion -->
        <div class="modal fade" id="promoModal" tabindex="-1" aria-labelledby="promoModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header bg-primary text-white">
                        <h5 class="modal-title" id="promoModalLabel">🎁 Select Promotion</h5>
                        <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal"></button>
                    </div>
                    <div class="modal-body">
                        <table class="table table-hover align-middle text-center">
                            <thead class="table-primary">
                                <tr><th>Code</th><th>Discount</th><th></th></tr>
                            </thead>
                            <tbody>
                                <c:forEach var="p" items="${promotions}">
                                    <tr>
                                        <td>${p.code}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${p.discountPercent > 0}">
                                                    ${p.discountPercent}% off
                                                </c:when>
                                                <c:otherwise>
                                                    <fmt:formatNumber value="${p.discountAmount}" type="currency" currencySymbol="₫"/> off
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <button type="button" class="btn btn-sm btn-success selectPromoBtn"
                                                    data-id="${p.promotionId}"
                                                    data-code="${p.code}"
                                                    data-percent="${p.discountPercent}"
                                                    data-amount="${p.discountAmount}">
                                                Select
                                            </button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>


        <!-- Footer Start -->
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
        <!-- Footer End -->

        <!-- Copyright Start -->
        <div class="container-fluid copyright py-4">
            <div class="container">
                <div class="row g-4 align-items-center">
                    <div class="col-md-6 text-center text-md-start mb-md-0">
                        <span class="text-body"><a href="#" class="border-bottom text-white"><i class="fas fa-copyright text-light me-2"></i>Your Site Name</a>, All right reserved.</span>
                    </div>
                    <div class="col-md-6 text-center text-md-end text-body">
                        <!--/*** This template is free as long as you keep the below author’s credit link/attribution link/backlink. ***/-->
                        <!--/*** If you'd like to use the template without the below author’s credit link/attribution link/backlink, ***/-->
                        <!--/*** you can purchase the Credit Removal License from "https://htmlcodex.com/credit-removal". ***/-->
                        Designed By <a class="border-bottom text-white" href="https://htmlcodex.com">HTML Codex</a>
                    </div>
                </div>
            </div>
        </div>
        <!-- Copyright End -->


        <!-- Back to Top -->
        <a href="#" class="btn btn-secondary btn-lg-square rounded-circle back-to-top"><i class="fa fa-arrow-up"></i></a>   

        <!-- JavaScript Libraries -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="lib/wow/wow.min.js"></script>
        <script src="lib/easing/easing.min.js"></script>
        <script src="lib/waypoints/waypoints.min.js"></script>
        <script src="lib/counterup/counterup.min.js"></script>
        <script src="lib/owlcarousel/owl.carousel.min.js"></script>


        <!-- Template Javascript -->
        <script src="js/main.js"></script>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
        <script>
            $(function () {
                const baseTotal = ${totalPrice};

                $(".selectPromoBtn").click(function () {
                    const id = $(this).data("id");
                    const code = $(this).data("code");
                    const percent = parseFloat($(this).data("percent"));
                    const amount = parseFloat($(this).data("amount"));

                    $("#promotionId").val(id);
                    $("#selectedPromoText").text(code);

                    let finalPrice = baseTotal;
                    if (percent > 0)
                        finalPrice -= baseTotal * (percent / 100);
                    else if (amount > 0)
                        finalPrice -= amount;

                    if (finalPrice < 0)
                        finalPrice = 0;

                    $("#finalTotal").val(finalPrice.toFixed(2));
                    $("#finalPriceText").text(finalPrice.toLocaleString('vi-VN', {style: 'currency', currency: 'VND'}));

                    $("#promoModal").modal("hide");
                });
            });
        </script>

    </body></html>
