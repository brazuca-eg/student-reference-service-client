<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!doctype html>

<head>
    <title>Student Reference Service</title>

    <jsp:include page="style.jsp"/>
</head>

<body>
<!-- Spinner Start -->
<div id="spinner"
     class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
    <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
        <span class="sr-only">Loading...</span>
    </div>
</div>
<!-- Spinner End -->


<!-- Navbar Start -->
<nav class="navbar navbar-expand-lg bg-white navbar-light shadow sticky-top p-0">
    <a href="index.html" class="navbar-brand d-flex align-items-center px-4 px-lg-5">
        <h2 class="m-0 text-primary"><i class="fa fa-book me-3"></i>Student Reference Service</h2>
    </a>
    <button type="button" class="navbar-toggler me-4" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarCollapse">
        <div class="navbar-nav ms-auto p-4 p-lg-0">
            <a href="index.html" class="nav-item nav-link active">Home</a>
            <a href="about.html" class="nav-item nav-link">About</a>
            <a href="courses.html" class="nav-item nav-link">Courses</a>
            <div class="nav-item dropdown">
                <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">Pages</a>
                <div class="dropdown-menu fade-down m-0">
                    <a href="team.html" class="dropdown-item">Our Team</a>
                    <a href="testimonial.html" class="dropdown-item">Testimonial</a>
                    <a href="404.html" class="dropdown-item">404 Page</a>
                </div>
            </div>
            <a href="contact.html" class="nav-item nav-link">Contact</a>
        </div>
    </div>
</nav>
<!-- Navbar End -->


<!-- About Start -->
<div class="container-xxl py-5">
    <div class="container">
        <div class="row g-5">
            <div class="col-lg-6 wow fadeInUp" data-wow-delay="0.1s" style="min-height: 400px;">
                <div class="position-relative h-100">
                    <img class="img-fluid position-absolute w-100 h-100" src="img/about.jpg" alt=""
                         style="object-fit: cover;">
                </div>
            </div>
            <div class="col-lg-6 wow fadeInUp" data-wow-delay="0.3s">
                <h1 class="mb-4">Реєстрація у системі</h1>
                <div class="row gy-2 gx-4 mb-4">
                    <form action="#" method="post">
                        Email
                        <input type="text" id="email" name="email" class="form-control border-3 w-75 p-3"
                               placeholder="Введіть вашу пошту">
                        Пароль
                        <input type="password" id="password" name="password" class="form-control border-3 w-75 p-3"
                               placeholder="Введіть пароль">
                        Повторіть пароль
                        <input type="password" id="password2" name="password2" class="form-control border-3 w-75 p-3"
                               placeholder="Повторіть пароль">
                        Ім'я
                        <input type="text" id="name" name="name" class="form-control border-3 w-75 p-3"
                               placeholder="Введіть ім'я">
                        Прізвище
                        <input type="text" id="surname" name="surname" class="form-control border-3 w-75 p-3"
                               placeholder="Введіть прізвище">
                        Ім'я по батькові
                        <input type="text" id="fatherhood" name="fatherhood" class="form-control border-3 w-75 p-3"
                               placeholder="Введіть ім'я по батькові">
                        <p>Оберіть вашу стать</p>
                        <input type="radio" id="gender_m" name="gender" value="Male">
                        <label for="gender_m">Чоловік</label><br>
                        <input type="radio" id="gender_f" name="gender" value="Female">
                        <label for="gender_f">Жінка</label><br>
                        <p></p>
                        <p>Оберіть вашу роль у системі</p>
                        <input type="radio" id="role_student" name="role" value="Student">
                        <label for="role_student">Я студент</label><br>
                        <input type="radio" id="role_worker" name="role" value="Worker">
                        <label for="role_worker">Я працівник деканату</label><br>


                        <button type="submit" value="submit" name="submitButton" class="btn btn-primary py-3 px-5 mt-2">
                            Зареєструватися
                        </button>

                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- About End -->


<jsp:include page="footer.jsp"/>


<!-- Back to Top -->
<a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>


<!-- JavaScript Libraries -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="lib/wow/wow.min.js"></script>
<script src="lib/easing/easing.min.js"></script>
<script src="lib/waypoints/waypoints.min.js"></script>
<script src="lib/owlcarousel/owl.carousel.min.js"></script>

<!-- Template Javascript -->
<script src="js/main.js"></script>
</body>

</html>