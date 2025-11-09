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

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Lato:ital,wght@0,400;0,700;0,900;1,400;1,700;1,900&family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet"> 

        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

        <link href="${pageContext.request.contextPath}/lib/animate/animate.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">

        <style>
            body.modal-open {
                overflow: auto !important;
                padding-right: 0 !important;
            }
            .modal-backdrop {
                display: none !important;
            }

            /* --- Custom Styles for Enhanced Booking Page --- */
            .booking-header {
                background-image: linear-gradient(135deg, #FF6B6B, #FFB8B8); /* Màu gradient tươi sáng */
                color: white;
                padding: 2.5rem 0; /* Tăng padding */
                border-radius: 15px 15px 0 0; /* Bo tròn góc trên */
                box-shadow: 0 8px 20px rgba(0, 0, 0, 0.15); /* Thêm bóng đổ mềm mại */
                position: relative;
                overflow: hidden;
            }
            .booking-header::before { /* Thêm hiệu ứng gợn sóng */
                content: '';
                position: absolute;
                bottom: -50px;
                left: -50px;
                width: 200px;
                height: 200px;
                background-color: rgba(255, 255, 255, 0.1);
                border-radius: 50%;
                animation: pulse 4s infinite alternate;
            }
            .booking-header::after { /* Thêm hiệu ứng gợn sóng thứ hai */
                content: '';
                position: absolute;
                top: -30px;
                right: -30px;
                width: 150px;
                height: 150px;
                background-color: rgba(255, 255, 255, 0.1);
                border-radius: 50%;
                animation: pulse 5s infinite reverse alternate;
            }
            @keyframes pulse {
                0% { transform: scale(1); opacity: 0.8; }
                100% { transform: scale(1.2); opacity: 1; }
            }

            .card-booking {
                border: none; /* Bỏ border mặc định */
                border-radius: 15px; /* Bo tròn card */
                box-shadow: 0 10px 30px rgba(0, 0, 0, 0.08); /* Bóng đổ mạnh hơn một chút */
                background-color: #ffffff;
                overflow: hidden;
            }

            .card-booking .card-body {
                padding: 3rem; /* Tăng padding bên trong */
            }

            .section-title {
                font-size: 1.8rem;
                color: #333; /* Màu chữ đậm hơn */
                margin-bottom: 1.5rem;
                position: relative;
                padding-left: 30px; /* Khoảng trống cho icon */
                font-weight: 700;
            }
            .section-title i {
                position: absolute;
                left: 0;
                top: 50%;
                transform: translateY(-50%);
                color: #FF6B6B; /* Màu icon */
                font-size: 1.5rem;
            }

            .detail-item p {
                margin-bottom: 0.7rem;
                font-size: 1.05rem;
                color: #555;
            }
            .detail-item p strong {
                color: #333;
                min-width: 120px; /* Cố định chiều rộng cho label để căn chỉnh */
                display: inline-block;
            }

            .price-summary {
                background-color: #fcfcfc; /* Nền xám rất nhẹ cho khu vực thanh toán */
                border: 1px dashed #ddd;
                border-radius: 10px;
                padding: 2rem;
            }
            .price-summary p {
                font-size: 1.1rem;
                margin-bottom: 0.8rem;
            }
            .price-summary p strong {
                color: #333;
                min-width: 150px;
                display: inline-block;
            }
            #finalPriceText {
                font-size: 1.8rem; /* To hơn */
                font-weight: 900;
                color: #dc3545; /* Màu đỏ nổi bật cho tổng tiền */
                letter-spacing: 0.5px;
            }
            #originalPrice, #depositText {
                font-size: 1.1rem;
                color: #6c757d; /* Màu xám cho giá gốc và đặt cọc */
            }
            #selectedPromoText {
                color: #28a745;
                font-weight: bold;
                margin-right: 5px;
            }
            .btn-action {
                border-radius: 50px; /* Nút tròn hơn */
                padding: 12px 30px; /* Tăng kích thước nút */
                font-size: 1.1rem;
                font-weight: 600;
                transition: all 0.3s ease;
                box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            }
            .btn-action.btn-success {
                background-image: linear-gradient(45deg, #28a745, #218838); /* Gradient cho nút Confirm */
                border: none;
            }
            .btn-action.btn-success:hover {
                transform: translateY(-2px);
                box-shadow: 0 6px 15px rgba(40, 167, 69, 0.4);
            }
            .btn-action.btn-outline-danger {
                color: #dc3545;
                border: 2px solid #dc3545; /* Border đậm hơn */
                background-color: white;
            }
            .btn-action.btn-outline-danger:hover {
                background-color: #dc3545;
                color: white;
                transform: translateY(-2px);
                box-shadow: 0 6px 15px rgba(220, 53, 69, 0.3);
            }

            .modal-header.bg-primary {
                background-image: linear-gradient(45deg, #1abc9c, #16a085) !important; /* Màu gradient xanh cho modal header */
                color: white;
                border-radius: 10px 10px 0 0;
            }
            .table thead th {
                background-color: #f8f9fa; /* Nền nhẹ cho header bảng */
                border-bottom: 2px solid #dee2e6;
            }
            .table tbody tr:hover {
                background-color: #f1f1f1; /* Hiệu ứng hover cho bảng */
            }
            .btn-outline-primary.btn-sm.ms-2 {
                color: #6f42c1; /* Màu tím nhẹ cho nút Select Promotion */
                border-color: #6f42c1;
                font-weight: 600;
                transition: all 0.2s ease;
            }
            .btn-outline-primary.btn-sm.ms-2:hover {
                background-color: #6f42c1;
                color: white;
            }
            .selectPromoBtn {
                background-color: #28a745;
                border-color: #28a745;
                font-weight: 500;
                color: white;
            }
            .selectPromoBtn:hover {
                background-color: #218838;
                border-color: #1e7e34;
            }

            hr {
                border-top: 1px solid rgba(0,0,0,.08); /* Đường phân cách mỏng hơn */
                margin: 2.5rem 0; /* Tăng khoảng cách */
            }
            /* End Custom Styles */
        </style>
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
                    <a href="${pageContext.request.contextPath}/HomeServlet" class="navbar-brand p-0">
                        <h1 class="display-6 text-primary"><i class="fas fa-car-alt me-3"></i>Cental</h1>
                        </a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                        <span class="fa fa-bars"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarCollapse">
                        <div class="navbar-nav mx-auto py-0">
                            <a href="${pageContext.request.contextPath}/HomeServlet" class="nav-item nav-link">Home</a>
                            <a href="${pageContext.request.contextPath}/about.html" class="nav-item nav-link">About</a>
                            <a href="${pageContext.request.contextPath}/service.html" class="nav-item nav-link">Service</a>
                            <a href="${pageContext.request.contextPath}/blog.html" class="nav-item nav-link">Blog</a>

                            <div class="nav-item dropdown">
                                <a href="#" class="nav-link dropdown-toggle active" data-bs-toggle="dropdown">Pages</a>
                                <div class="dropdown-menu m-0">
                                    <a href="${pageContext.request.contextPath}/feature.html" class="dropdown-item active">Our Feature</a>
                                    <a href="${pageContext.request.contextPath}/cars.html" class="dropdown-item">Our Cars</a>
                                    <a href="${pageContext.request.contextPath}/team.html" class="dropdown-item">Our Team</a>
                                    <a href="${pageContext.request.contextPath}/testimonial.html" class="dropdown-item">Testimonial</a>
                                    <a href="${pageContext.request.contextPath}/404.html" class="dropdown-item">404 Page</a>
                                </div>
                            </div>
                            <a href="${pageContext.request.contextPath}/contact.html" class="nav-item nav-link">Contact</a>

                            <c:if test="${not empty sessionScope.userLogin}">
                                <a href="${pageContext.request.contextPath}/profile" class="nav-item nav-link">Profile</a>
                            </c:if>
                        </div>
                        <c:choose>
                            <c:when test="${not empty sessionScope.userLogin}">
                                <span class="me-2 text-black">
                                    👋 Welcome, ${sessionScope.userLogin.name != null ? sessionScope.userLogin.name : sessionScope.userLogin.email}
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
        <div class="container py-5">
            <div class="card card-booking">
                <div class="card-header booking-header text-center">
                    <h3 class="mb-0 display-5 fw-bold">Confirm Your Booking</h3>
                    <p class="lead mt-2">Please review your information before completing</p>
                </div>

                <div class="card-body">
                    <c:set var="b" value="${booking}" />
                    
                    <div class="row mb-4">
                        <div class="col-lg-6 detail-item">
                            <h5 class="section-title"><i class="fas fa-user-circle"></i>Customer Information</h5>
                            <p><strong>Full Name:</strong> ${b.customer.name}</p>
                            <p><strong>Phone:</strong> ${b.customer.phone}</p>
                            <p><strong>Email:</strong> ${b.customer.email}</p>
                        </div>

                        <div class="col-lg-6 detail-item">
                            <h5 class="section-title"><i class="fas fa-map-marked-alt"></i>Pick-up Location</h5>
                            <p><strong>Address:</strong> 
                                ${b.address.houseNumber}, 
                                ${b.address.addressDetail},
                                ${b.address.ward.wardName}, 
                                ${b.address.district.districtName}, 
                                ${b.address.province.provinceName}</p>
                        </div>
                    </div>

                    <hr/>

                    <div class="row mb-4">
                        <div class="col-lg-6 detail-item">
                            <h5 class="section-title"><i class="fas fa-car-side"></i>Car Information</h5>
                            <p><strong>Brand:</strong> ${b.car.brand.brandName}</p>
                            <p><strong>Model:</strong> ${b.car.model}</p>
                            <p><strong>Seats:</strong> ${b.car.seat}</p>
                            <p><strong>License Required:</strong> <span class="highlight-text">${b.car.driverLicenseRequired}</span></p>
                            <p><strong>Price / day:</strong> 
                                <fmt:formatNumber value="${b.car.pricePerDay}" type="currency" currencySymbol="₫"/>
                            </p>
                        </div>

                        <div class="col-lg-6 detail-item">
                            <h5 class="section-title"><i class="fas fa-clock"></i>Booking Duration</h5>
                            <p><strong>From:</strong> ${b.startDate}</p>
                            <p><strong>To:</strong> ${b.endDate}</p>
                            <p><strong>Total duration:</strong> <span class="highlight-text">${hours} hours</span></p>
                        </div>
                    </div>

                    <hr/>

                    <div class="row justify-content-center">
                        <div class="col-lg-8 price-summary">
                            <h5 class="section-title text-center" style="padding-left: 0;"><i class="fas fa-wallet"></i>Payment Summary</h5>
                            <p><strong>Original Price:</strong> 
                                <span id="originalPrice">
                                    <fmt:formatNumber value="${totalPrice}" type="currency" currencySymbol="₫"/>
                                </span>
                            </p>
                            <p><strong>Deposit:</strong> 
                                <span id="depositText">
                                    <fmt:formatNumber value="${b.car.deposit != null ? b.car.deposit : 0}" type="currency" currencySymbol="₫"/>
                                </span>
                            </p>
                             <p><strong>Promotion:</strong> 
                                <span id="selectedPromoText">None applied</span>
                                <button type="button" class="btn btn-outline-primary btn-sm ms-2" data-bs-toggle="modal" data-bs-target="#promoModal">
                                    🎁 Select Promotion
                                </button>
                            </p>
                            <hr style="margin: 1.5rem 0;" />
                            <p class="text-center" style="margin-bottom: 0.5rem;"><strong>GRAND TOTAL (Deposit included):</strong></p>
                            <h4 class="text-center">
                                <span id="finalPriceText" class="fw-bold text-danger">
                                    <fmt:formatNumber value="${totalPrice}" type="currency" currencySymbol="₫"/>
                                </span>
                            </h4>
                        </div>
                    </div>

                    <form id="confirmForm" action="${pageContext.request.contextPath}/CreateBooking" method="post">
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
                        <input type="hidden" id="depositValue" value="${b.car.deposit != null ? b.car.deposit : 0}">

                        <div class="text-center mt-5">
                            <button type="submit" class="btn btn-success btn-action me-3">
                                <i class="fas fa-check-circle me-2"></i> Confirm & Book
                            </button>
                            <a href="${pageContext.request.contextPath}/CarDetailServlet?carId=${b.car.carId}" class="btn btn-outline-danger btn-action">
                                <i class="fas fa-times me-2"></i> Cancel
                            </a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="modal fade" id="promoModal" tabindex="-1" aria-labelledby="promoModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content" style="border-radius: 10px;">
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
                                        <td class="fw-bold">${p.code}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${p.discountPercent > 0}">
                                                    ${p.discountPercent}% Off
                                                </c:when>
                                                <c:otherwise>
                                                    Save <fmt:formatNumber value="${p.discountAmount}" type="currency" currencySymbol="₫"/>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <button type="button" class="btn btn-sm btn-success selectPromoBtn"
                                                    data-id="${p.promotionId}"
                                                    data-code="${p.code}"
                                                    data-percent="${p.discountPercent}"
                                                    data-amount="${p.discountAmount}">
                                                Apply
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
        <script src="${pageContext.request.contextPath}/lib/wow/wow.min.js"></script>
        <script src="${pageContext.request.contextPath}/lib/easing/easing.min.js"></script>
        <script src="${pageContext.request.contextPath}/lib/waypoints/waypoints.min.js"></script>
        <script src="${pageContext.request.contextPath}/lib/counterup/counterup.min.js"></script>
        <script src="${pageContext.request.contextPath}/lib/owlcarousel/owl.carousel.min.js"></script>

        <script src="${pageContext.request.contextPath}/js/main.js"></script>

        <script>
            document.addEventListener("DOMContentLoaded", function () {
                // Tổng tiền thuê (từ server)
                const baseTotal = parseFloat(document.getElementById("finalTotal").value) || 0;
                // Tiền đặt cọc
                const deposit = parseFloat(document.getElementById("depositValue").value) || 0;

                // 👉 Cộng deposit vào tổng ban đầu
                let totalWithDeposit = baseTotal + deposit;
                document.getElementById("finalTotal").value = totalWithDeposit.toFixed(2);
                document.getElementById("finalPriceText").textContent =
                        totalWithDeposit.toLocaleString('vi-VN', {style: 'currency', currency: 'VND'});

                // Khi chọn promotion
                document.addEventListener("click", function (e) {
                    if (e.target.classList.contains("selectPromoBtn")) {
                        const btn = e.target;
                        const id = btn.dataset.id;
                        const code = btn.dataset.code;
                        const percent = parseFloat(btn.dataset.percent) || 0;
                        const amount = parseFloat(btn.dataset.amount) || 0;

                        // Tính lại tổng sau giảm giá
                        let discounted = baseTotal;
                        if (percent > 0)
                            discounted -= baseTotal * (percent / 100);
                        else if (amount > 0)
                            discounted -= amount;
                        if (discounted < 0)
                            discounted = 0;

                        // 🧮 Cộng thêm deposit
                        const finalPrice = discounted + deposit;

                        // Cập nhật lại giao diện
                        document.getElementById("promotionId").value = id;
                        document.getElementById("selectedPromoText").textContent = code; // Cập nhật text mã
                        document.getElementById("finalTotal").value = finalPrice.toFixed(2);
                        document.getElementById("finalPriceText").textContent =
                                finalPrice.toLocaleString('vi-VN', {style: 'currency', currency: 'VND'});

                        // Đóng modal Bootstrap đúng cách
                        const promoModalEl = document.getElementById('promoModal');
                        const modalInstance = bootstrap.Modal.getInstance(promoModalEl);
                        if (modalInstance)
                            modalInstance.hide();

                        setTimeout(() => {
                            document.querySelectorAll('.modal-backdrop').forEach(el => el.remove());
                            document.body.classList.remove('modal-open');
                            document.body.style.removeProperty('padding-right');
                        }, 400);
                    }
                });
            });
        </script>
    </body>
</html>