<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <base href="${pageContext.servletContext.contextPath}/">
    <title>Đơn hàng của tôi</title>
    <link rel="stylesheet" href="resources/css/share/display.css">
    <link rel="stylesheet" href="resources/css/share/list.css">
    <link rel="stylesheet" href="resources/css/user/don_hang.css">
    <link rel="stylesheet" href="resources/css/user/decorator.css">
</head>

<body>
    <main>
        <nav>
            <a href="user/myorder.htm" style='color: ${trangThaiLoc == 0 ? "red" : ""}'>CHỜ DUYỆT</a>
            <a href="user/myorder.htm?trangThai=1" style='color: ${trangThaiLoc == 1 ? "red" : ""}'>ĐÃ DUYỆT</a>
            <a href="user/myorder.htm?trangThai=3" style='color: ${trangThaiLoc == 3 ? "red" : ""}'>ĐANG VẬN CHUYỂN</a>
            <a href="user/myorder.htm?trangThai=4" style='color: ${trangThaiLoc == 4 ? "red" : ""}'>HOÀN THÀNH</a>
            <a href="user/myorder.htm?trangThai=5" style='color: ${trangThaiLoc == 5 ? "red" : ""}'>HOÀN TRẢ</a>
            <a href="user/myorder.htm?trangThai=2" style='color: ${trangThaiLoc == 2 ? "red" : ""}'>ĐÃ HỦY</a>
        </nav>
        <form action="user/readmore.htm" method="post">
            <div class="search-bar">
                <label for="search"><ion-icon name="search"></ion-icon></label>
                <input id="search" type="search" name="keyword"
                    placeholder="Tìm kiếm đơn hàng theo mã đơn hàng hoặc tên sản phẩm" />
            </div>
            <div class="list">
            	<c:forEach var="e" items="${donHangs}">
            	<div class="order">
                    <label>${e.getTenTrangThai()}</label>
                    <hr />
                    <div class="list">
                    	<c:forEach var="sanPham" items="${e.dsChiTietDonHang}">
	                    	<div class="product">
	                            <img src="${sanPham.sach.anhDaiDien}" alt="" />
	                            <div>
	                                <div>
	                                    <p>${sanPham.sach.tenSach}</p>
	                                    <p>${sanPham.soLuong}</p>
	                                </div>
	                                <p>${sanPham.giaTien} đ</p>
	                            </div>
	                        </div>
                        	<hr />
	                        <div class="total">
		                        <label class="bold">Tổng tiền:</label>
		                        <p>${sanPham.soLuong * sanPham.giaTien} đ</p>
		                    </div>
                    	</c:forEach>
                    	<div class="action">
		                        <button name="repurchase" value="${e.maDonHang}">MUA LẠI</button>
		                        <button name="readmore" value="${e.maDonHang}">XEM CHI TIẾT</button>
		                 </div>
                    </div>
                </div>
            	</c:forEach>
            </div>
        </form>
    </main>
    <footer>
		<div id="copyright">
			<a href="homepage.htm"><img src="resources/image/LOGO.png" alt=""></a>
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
					<span>0981183892</span>
				</li>
				<li>
					<ion-icon name="mail"></ion-icon>
					<label>Email liên hệ:</label>
					<span>3tbookstore@gmail.com</span>
				</li>
				<li>
					<ion-icon name="location"></ion-icon>
					<label>Địa chỉ:</label>
					<span>97 Man Thiện, P. Hiệp Phú, TP. Thủ Đức, TP. Hồ Chí Minh</span>
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
    <!-- IMPORT JAVASCRIPT -->
    <script src="resources/js/share/icon.js"></script>
    <!-- IMPORT IONICONS -->
    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</body>

</html>