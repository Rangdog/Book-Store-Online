<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<base href="${pageContext.servletContext.contextPath}/">
	<title>3T Bookshop</title>
	<link rel="shortcut icon" href="resources/image/LOGO.ico" type="image/x-icon">
	<link rel="stylesheet" href="resources/css/user/main.css">
	<!-- BOOTSTRAP -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
		integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
	</head>
<body>
	<span id="toaston" style="display: none">${toaston}</span>
	<security:authorize access="isAuthenticated()" var="isAuthen" />
	<security:authentication property="principal" var="account" />
	<!-- <header id="header">
        <img src="/image/HEADER_BANNER.jpg" alt="">
    </header> -->
	<nav>
		<div>
			<img src="resources/image/LOGO.png" alt="">
			<div id="search-bar">
				<label for="keyword"><ion-icon name="search"></ion-icon></label> <input
					type="search" name="keyword" id="keyword" autocomplete="off"
					placeholder="Bạn tìm gì hôm nay ?" />
			</div>
		</div>
		<c:choose>
			<c:when test="${isAuthen}">
				<div id="action-bar">
					<a class="vertical-link" href="user/cart.htm">
						<ion-icon name="cart-outline"></ion-icon>
						<div id="attention">
							<label>Giỏ hàng</label>
							<label>${soLuongChiTiet}</label>
						</div>
					</a>
					<div id="account-bar">
						<div id="account-action">
							<form action="user/taikhoan.htm">
								<ion-icon name="settings-outline"></ion-icon>
								<button name="thongtintaikhoan">Thông tin tài khoản</button>
							</form>
							<form action="user/myorder.htm">
								<ion-icon name="cube-outline"></ion-icon>
								<button>Đơn hàng của tôi</button>
							</form>
							<form action="address.htm">
		                    	<ion-icon name="location-outline"></ion-icon>
		                    	<button>Địa chỉ của tôi</button>
			                </form>
							<form action="user/taikhoan.htm">
								<ion-icon name="key-outline"></ion-icon>
								<button name="doimatkhau">Đổi mật khẩu</button>
							</form>
							<form action="logout.htm">
								<ion-icon name="log-out-outline"></ion-icon>
								<button>Đăng xuất</button>
							</form>
						</div>
						<a class="vertical-link" href="user/taikhoan.htm?thongtintaikhoan=">
							<ion-icon name="person-circle-outline"></ion-icon>
							<label>${acc.getHoTen()}</label>
						</a>
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<div id="action-bar-not-authen">
					<form action="login.htm">
						<button>Đăng nhập</button>
					</form>
					<form action="register.htm">
						<button>Đăng ký</button>
					</form>
				</div>
			</c:otherwise>
		</c:choose>
	</nav>
	<div id="toast"></div>
	<main>
		<section id="introduction">
			<div id="category">
				<label class="label-icon">
					<ion-icon name="grid-outline"></ion-icon>
					<span>DANH MỤC SẢN PHẨM</span>
				</label>
				<div class="list-group">
					<c:forEach var="e" items="${danhMucs}">
						<a href="danhmuc.htm?maDanhMuc=${e.maDanhMuc}" class="list-group-item list-group-item-action">${e.tenDanhMuc}</a>
					</c:forEach>
				</div>
			</div>
			<div id="banners" class="carousel slide" data-bs-ride="carousel">
				<div class="carousel-indicators">
					<button type="button" data-bs-target="#banners" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
					<button type="button" data-bs-target="#banners" data-bs-slide-to="1" aria-label="Slide 2"></button>
					<button type="button" data-bs-target="#banners" data-bs-slide-to="2" aria-label="Slide 3"></button>
				</div>
				<div class="carousel-inner">
					<div class="carousel-item active">
						<a href="chitietsanpham.htm?maSach=1"><img src="resources/image/BANNER_SLIDING/1.jpg" class="d-block w-100" alt=""></a>	
					</div>
					<div class="carousel-item">
						<a href="chitietsanpham.htm?maSach=10007"><img src="resources/image/BANNER_SLIDING/2.jpg" class="d-block w-100" alt=""></a>
					</div>
					<div class="carousel-item">
						<a href="chitietsanpham.htm?maSach=9"><img src="resources/image/BANNER_SLIDING/3.jpg" class="d-block w-100" alt=""></a>
					</div>
				</div>
				<button class="carousel-control-prev" type="button"
					data-bs-target="#banners" data-bs-slide="prev">
					<span class="carousel-control-prev-icon" aria-hidden="true"></span>
					<span class="visually-hidden">Previous</span>
				</button>
				<button class="carousel-control-next" type="button"
					data-bs-target="#banners" data-bs-slide="next">
					<span class="carousel-control-next-icon" aria-hidden="true"></span>
					<span class="visually-hidden">Next</span>
				</button>
			</div>
		</section>
		<section id="display">
			<div id="saling">
				<section id="outstanding">
					<label class="label-icon">
						<ion-icon name="star-outline"></ion-icon>
						<span>Bán chạy</span>
					</label>
					<ul class="item-list">
						<c:forEach var="e" items="${topSachBanChays}">
						<li class="item">
							<a href="chitietsanpham.htm?maSach=${e[0]}"><img src="${e[1]}" alt=""></a>
							<cite>${e[2]}</cite>
							<c:if test="${e[3] != -1}">
								<span class="new-price">${e[3]} đ</span>
								<span class="old-price">${e[4]} đ</span>
							</c:if>
							<c:if test="${e[3] == -1}">
								<span class="new-price">${e[4]} đ</span>
							</c:if>
							<a href="user/order.htm?muaNgay=${e[0]}" class="label-icon">
								<ion-icon name="cart-outline"></ion-icon>
								<span>Mua ngay</span>
							</a>
						</li>
						</c:forEach>
					</ul>
				</section>
				<section id="sale">
					<label class="label-icon">
						<ion-icon name="ticket-outline"></ion-icon>
						<span>Khuyến mãi</span>
					</label>
					<ul class="item-list">
						<c:forEach var="e" items="${topSachKhuyenMais}">
						<li class="item">
							<a href="chitietsanpham.htm?maSach=${e[0]}"><img src="${e[1]}" alt=""></a>
							<cite>${e[2]}</cite>
							<c:if test="${e[3] != -1}">
								<span class="new-price">${e[3]} đ</span>
								<span class="old-price">${e[4]} đ</span>
							</c:if>
							<c:if test="${e[3] == -1}">
								<span class="new-price">${e[4]} đ</span>
							</c:if>
							<a href="user/order.htm?muaNgay=${e[0]}" class="label-icon">
								<ion-icon name="cart-outline"></ion-icon>
								<span>Mua ngay</span>
							</a>
						</li>
						</c:forEach>
					</ul>
				</section>
				<section id="newest">
					<label class="label-icon">
						<ion-icon name="flag-outline"></ion-icon>
						<span>Sách mới</span>
					</label>
					<ul class="item-list">
					<c:forEach var="e" items="${topSachMois}">
						<li class="item">
							<a href="chitietsanpham.htm?maSach=${e[0]}"><img src="${e[1]}" alt=""></a>
							<cite>${e[2]}</cite>
							<c:if test="${e[3] != -1}">
								<span class="new-price">${e[3]} đ</span>
								<span class="old-price">${e[4]} đ</span>
							</c:if>
							<c:if test="${e[3] == -1}">
								<span class="new-price">${e[4]} đ</span>
							</c:if>
							<a href="user/order.htm?muaNgay=${e[0]}" class="label-icon">
								<ion-icon name="cart-outline"></ion-icon>
								<span>Mua ngay</span>
							</a>
						</li>
						</c:forEach>
					</ul>
				</section>
			</div>
		</section>
	</main>
	<footer>
		<div id="copyright">
			<img src="resources/image/LOGO.png" alt="">
			<div>
				<ion-icon name="finger-print-outline"></ion-icon>
				<em>2023 - Bản quyền của Nhà sách 3T</em>
			</div>
		</div>
		<div id="contacts">
			<strong>THÔNG TIN LIÊN LẠC</strong>
			<ul>
				<li>
					<ion-icon name="paper-plane"></ion-icon>
					<label>Nhà sách trực tuyến 3T</label>
				</li>
				<li>
					<ion-icon name="call"></ion-icon>
					<label>Hotline:</label>
					<a href="tel:0981183892">0981183892</a>
				</li>
				<li>
					<ion-icon name="mail"></ion-icon>
					<label>Email liên hệ:</label>
					<a href="mailto:3tbookstore@gmail.com">3tbookstore@gmail.com</a>
				</li>
				<li>
					<ion-icon name="location"></ion-icon>
					<label>Địa chỉ:</label>
					<a target="_blank" href="https://goo.gl/maps/iD2qqsHkUPLjkpvf7">97 Man Thiện, P. Hiệp Phú, TP. Thủ Đức, TP. Hồ Chí Minh</a>
				</li>
			</ul>
		</div>
		<div id="policies">
			<strong>CHÍNH SÁCH</strong>
			<ul>
				<li>
					<ion-icon name="shield-checkmark"></ion-icon>
					<a href="#">Chính sách bảo mật thông tin</a>
				</li>
				<li>
					<ion-icon name="shield-checkmark"></ion-icon>
					<a href="#">Chính sách đổi/trả và hoàn tiền</a>
				</li>
			</ul>
		</div>
	</footer>
	<!-- IMPORT IONICONS -->
	<script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
	<script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
	<!-- IMPORT BOOTSTRAP -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
	<script src="resources/js/user/main.js"></script>
	<!-- CONTROL THÔNG BÁO -->
	<script type="text/javascript">
		let toaston = document.getElementById('toaston').innerHTML;
		console.log(toaston);
		toast({
			title : "CHÀO MỪNG BẠN QUAY TRỞ LẠI VỚI 3T SHOP",
			message : "Đăng nhập thành công",
			type : "success",
			duration : 10000000000000000
		});

		if (toaston == "login") {
			toast({
				title : "CHÀO MỪNG BẠN QUAY TRỞ LẠI VỚI 3T SHOP",
				message : "Đăng nhập thành công",
				type : "success",
				duration : 5000
			});
		}
	</script>
</body>

</html>