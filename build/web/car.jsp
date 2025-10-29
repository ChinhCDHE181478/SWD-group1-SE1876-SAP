<%-- 
    Document   : car
    Created on : Oct 27, 2025, 4:58:18 AM
    Author     : Chinh
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

        <!-- Header Start -->
        <div class="container-fluid bg-breadcrumb">
            <div class="container text-center py-5" style="max-width: 900px;">
                <h4 class="text-white display-4 mb-4 wow fadeInDown" data-wow-delay="0.1s">${car.model}</h4>
                <ol class="breadcrumb d-flex justify-content-center mb-0 wow fadeInDown" data-wow-delay="0.3s">
                    <li class="breadcrumb-item"><a href="index.html">Home</a></li>
                    <li class="breadcrumb-item"><a href="#">Pages</a></li>
                    <li class="breadcrumb-item active text-primary">Car</li>
                </ol>    
            </div>
        </div>
        <!-- Header End -->




        <!-- Banner Start -->
        <div class="container-fluid banner py-5 wow zoomInDown" data-wow-delay="0.1s">
            <div class="container py-5">
                <c:set var="car" value="${car}" />

                <div class="row g-5">
                    <!-- Hình ảnh (Carousel) -->
                    <div class="col-lg-6">
                        <c:choose>
                            <c:when test="${not empty car.images}">
                                <div id="carCarousel" class="carousel slide" data-bs-ride="carousel">
                                    <!-- Indicators -->
                                    <div class="carousel-indicators">
                                        <c:forEach var="img" items="${car.images}" varStatus="st">
                                            <button type="button"
                                                    data-bs-target="#carCarousel"
                                                    data-bs-slide-to="${st.index}"
                                                    class="${st.first ? 'active' : ''}"
                                                    aria-current="${st.first ? 'true' : 'false'}"
                                                    aria-label="Slide ${st.index + 1}"></button>
                                        </c:forEach>
                                    </div>

                                    <!-- Slides -->
                                    <div class="carousel-inner rounded-top">
                                        <c:forEach var="img" items="${car.images}" varStatus="st">
                                            <div class="carousel-item ${st.first ? 'active' : ''}">
                                                <img src="${img.image}" class="d-block w-100" alt="${car.model}">
                                            </div>
                                        </c:forEach>
                                    </div>

                                    <!-- Controls -->
                                    <button class="carousel-control-prev" type="button" data-bs-target="#carCarousel" data-bs-slide="prev">
                                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                        <span class="visually-hidden">Previous</span>
                                    </button>
                                    <button class="carousel-control-next" type="button" data-bs-target="#carCarousel" data-bs-slide="next">
                                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                        <span class="visually-hidden">Next</span>
                                    </button>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <img src="img/default-car.png" class="img-fluid w-100 rounded-top" alt="No Image">
                            </c:otherwise>
                        </c:choose>
                    </div>

                    <!-- Thông tin xe -->
                    <div class="col-lg-6">
                        <div class="categories-content rounded-bottom p-4">
                            <h2 class="mb-3">
                                <span class="text-primary">${car.brand.brandName} : </span>
                                ${car.model}
                            </h2>

                            <div class="mb-4">
                                <h4 class="bg-white text-primary rounded-pill py-2 px-4 mb-0">
                                    ${car.pricePerDay} VND/Day
                                </h4>
                            </div>

                            <div class="row gy-2 gx-0 text-center mb-4">
                                <div class="col-4 border-end border-white">
                                    <i class="fa fa-users text-dark"></i>
                                    <span class="text-body ms-1">${car.seat} Seat</span>
                                </div>
                                <div class="col-4 border-end border-white">
                                    <i class="fa fa-id-card text-dark"></i>
                                    <span class="text-body ms-1">${car.driverLicenseRequired}</span>
                                </div>
                                <div class="col-4">
                                    <i class="fa fa-info-circle text-dark"></i>
                                    <span class="text-body ms-1">${car.status}</span>
                                </div>
                            </div>

                            <!-- Chủ xe -->
                            <div class="mb-3">
                                <strong>Owner:</strong>
                                ${car.owner.name} (<a href="mailto:${car.owner.email}">${car.owner.email}</a>) - ${car.owner.phone}
                            </div>

                            <!-- Danh mục / Hãng -->
                            <div class="mb-3">
                                <strong>Brand:</strong> ${car.brand.brandName} |
                                <strong>Category:</strong> ${car.category.cateName}
                            </div>

                            <!-- Tính năng -->
                            <c:if test="${not empty car.features}">
                                <div class="mb-3">
                                    <strong>Features:</strong>
                                    <ul class="mb-0">
                                        <c:forEach var="f" items="${car.features}">
                                            <li>${f.definition.featureName}: ${f.featureValue}</li>
                                            </c:forEach>
                                    </ul>
                                </div>
                            </c:if>

                            <!-- BOOKED RANGES (Hiển thị cho người dùng biết) -->
                            <c:if test="${not empty car.bookedRanges}">
                                <div class="mb-3">
                                    <strong>Booked Ranges:</strong>
                                    <div class="d-flex flex-wrap gap-2">
                                        <c:forEach var="br" items="${car.bookedRanges}">
                                            <span class="badge text-bg-secondary" style="color: black">
                                                <fmt:formatDate value="${br.startDate}" pattern="yyyy-MM-dd" />
                                                →
                                                <fmt:formatDate value="${br.endDate}" pattern="yyyy-MM-dd" />
                                            </span>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>

                            <jsp:useBean id="now" class="java.util.Date" scope="page"/>
                            <fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="today" />

                            <form action="CreateBooking" method="post" class="mt-4" onsubmit="return validateDates()">
                                <input type="hidden" name="carId" value="${car.carId}"/>

                                <div class="row g-3 align-items-end">

                                    <div class="col-md-3">
                                        <label class="form-label">Start date</label>
                                        <input id="startDate" name="startDate" type="date" class="form-control" min="${today}" required>
                                    </div>

                                    <div class="col-md-3">
                                        <label class="form-label">End date</label>
                                        <input id="endDate" name="endDate" type="date" class="form-control" min="${today}" required>
                                    </div>

<!-- Province -->
<select id="provinceId" name="provinceId" class="form-select" required>
  <option value="">-- Select Province --</option>
  <c:forEach var="p" items="${provinces}">
    <option value="${p.provinceId}">${p.provinceName}</option>
  </c:forEach>
</select>

<!-- District: mỗi option gắn data-pid = provinceId -->
<select id="districtId" name="districtId" class="form-select" required>
  <option value="">-- Select District --</option>
  <c:forEach var="p" items="${provinces}">
    <c:forEach var="d" items="${p.districts}">
      <option value="${d.districtId}" data-pid="${p.provinceId}">${d.districtName}</option>
    </c:forEach>
  </c:forEach>
</select>

<!-- Ward: mỗi option gắn data-did = districtId, data-pid = provinceId -->
<select id="wardId" name="wardId" class="form-select" required>
  <option value="">-- Select Ward --</option>
  <c:forEach var="p" items="${provinces}">
    <c:forEach var="d" items="${p.districts}">
      <c:forEach var="w" items="${d.wards}">
        <option value="${w.wardId}" data-did="${d.districtId}" data-pid="${p.provinceId}">
          ${w.wardName}
        </option>
      </c:forEach>
    </c:forEach>
  </c:forEach>
</select>

<!-- Optional: backend muốn đọc provinceId “chắc chắn đúng” -->
<input type="hidden" id="selectedProvinceId" name="selectedProvinceId"/>

                                    <!-- House Number -->
                                    <div class="col-md-3">
                                        <label class="form-label">House Number</label>
                                        <input type="text" name="houseNumber" class="form-control" placeholder="e.g., 123A">
                                    </div>

                                    <!-- Address Detail -->
                                    <div class="col-md-9">
                                        <label class="form-label">Address Detail</label>
                                        <input type="text" name="addressDetail" class="form-control" placeholder="Street name or landmark">
                                    </div>

                                    <div class="col-md-12 mt-3">
                                        <button id="bookBtn" type="submit" class="btn btn-primary rounded-pill w-100 py-2">
                                            Book Now
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Banner End -->



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
        
        <script>
(function () {
  const provinceEl = document.getElementById('provinceId');
  const districtEl = document.getElementById('districtId');
  const wardEl     = document.getElementById('wardId');
  const hiddenProv = document.getElementById('selectedProvinceId');

  const districtOpts = Array.from(districtEl.querySelectorAll('option[data-pid]'));
  const wardOpts     = Array.from(wardEl.querySelectorAll('option[data-did]'));

  // --- helpers ---
  const clearSelect = (sel) => { sel.value = ''; };
  const setHiddenProvince = (pid) => { if (hiddenProv) hiddenProv.value = pid || ''; };

  function filterDistrictsByProvince(pid) {
    let keep = 0;
    districtOpts.forEach(o => {
      const match = pid ? (o.dataset.pid === String(pid)) : true;
      o.hidden = !match;
      if (match) keep++;
    });
    // Nếu district đang chọn không thuộc province mới → clear
    const cur = districtEl.selectedOptions[0];
    if (cur && cur.dataset && cur.dataset.pid !== String(pid)) clearSelect(districtEl);
    return keep;
  }

  function filterWardsByProvince(pid) {
    wardOpts.forEach(o => {
      const match = pid ? (o.dataset.pid === String(pid)) : true;
      o.hidden = !match;
    });
    // Nếu ward đang chọn không thuộc province mới → clear
    const cur = wardEl.selectedOptions[0];
    if (cur && cur.dataset && cur.dataset.pid !== String(pid)) clearSelect(wardEl);
  }

  function filterWardsByDistrict(did) {
    wardOpts.forEach(o => {
      const match = did ? (o.dataset.did === String(did)) : true;
      o.hidden = !match;
    });
    // Nếu ward đang chọn không thuộc district mới → clear
    const cur = wardEl.selectedOptions[0];
    if (cur && cur.dataset && cur.dataset.did !== String(did)) clearSelect(wardEl);
  }

  // --- sync flows ---
  // 1) Province selected → lọc District/ Ward theo province
  provinceEl.addEventListener('change', () => {
    const pid = provinceEl.value;
    setHiddenProvince(pid);
    filterDistrictsByProvince(pid);
    // Ward theo province (anh muốn ward phải cùng province luôn)
    filterWardsByProvince(pid);
  });

  // 2) District selected (có thể trước khi chọn province)
  districtEl.addEventListener('change', () => {
    const dOpt = districtEl.selectedOptions[0];
    if (!dOpt) return;

    const pid = dOpt.dataset.pid;          // province của district
    // Nếu province select khác → tự sync lại
    if (pid && provinceEl.value !== String(pid)) {
      provinceEl.value = String(pid);
      setHiddenProvince(pid);
      filterDistrictsByProvince(pid);      // show đúng các district thuộc province
    }
    // Ward chỉ thuộc district được chọn
    const did = districtEl.value;
    filterWardsByDistrict(did);
  });

  // 3) Ward selected (có thể trước khi chọn district/province)
  wardEl.addEventListener('change', () => {
    const wOpt = wardEl.selectedOptions[0];
    if (!wOpt) return;

    const pid = wOpt.dataset.pid;          // province của ward
    const did = wOpt.dataset.did;          // district của ward

    // Sync province nếu khác
    if (pid && provinceEl.value !== String(pid)) {
      provinceEl.value = String(pid);
      setHiddenProvince(pid);
      filterDistrictsByProvince(pid);
    }
    // Sync district nếu khác
    if (did && districtEl.value !== String(did)) {
      districtEl.value = String(did);
    }
    // Ward bị lọc theo district
    filterWardsByDistrict(did);
  });

  // --- optional: khởi tạo nhẹ nhàng (không lọc gì cho tới khi có chọn) ---
  setHiddenProvince(provinceEl.value || '');
})();
</script>


        <script>
window.validateDates = function() {
  const s = document.getElementById("startDate").value;
  const e = document.getElementById("endDate").value;
  if (!s || !e) {
    alert("⚠️ Please select both start and end date!");
    return false;
  }
  if (new Date(s) >= new Date(e)) {
    alert("❌ End date must be after start date!");
    return false;
  }
  return true;
};
</script>





    </body>
</html>
