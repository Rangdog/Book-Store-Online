<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <base href="${pageContext.servletContext.contextPath}/">
    <title>Chi tiết đơn hàng</title>
    <link rel="stylesheet" href="resources/css/share/display.css">
    <link rel="stylesheet" href="resources/css/share/list.css">
    <link rel="stylesheet" href="resources/css/user/ct_dh.css">
    <link rel="stylesheet" href="resources/css/user/decorator.css">
</head>

<body>
    <main>
        <div id="order-information">
            <div class="label-value">
                <div>
                    <label>Mã đơn hàng:</label>
                    <label>Ngày đặt hàng:</label>
                </div>
                <div>
                    <p>${donHang.maDonHang}</p>
                    <p>${donHang.ngay}</p>
                </div>
            </div>
            <div>
                <label>Trạng thái đơn hàng:</label>
                <p>${donHang.getTenTrangThai()}</p>
            </div>
        </div>
        <hr />
        <div id="address-information">
            <label>Địa chỉ giao hàng</label>
            <div>
                <p>${donHang.diaChiGiaoHang.tenNN} (${donHang.diaChiGiaoHang.sdtNN})</p>
                <p>${donHang.diaChiGiaoHang.diaChi}</p>
            </div>
        </div>
        <hr />
        <div class="list">
        	<c:forEach var="e" items="${donHang.dsChiTietDonHang}">
        	<div class="product">
                <img src="${e.sach.anhDaiDien}" alt="" />
                <div>
                    <div>
                        <p>${e.sach.tenSach}</p>
                        <p>${e.soLuong}</p>
                    </div>
                    <p>${e.giaTien} đ</p>
                </div>
            </div>
        	</c:forEach>
        </div>
        <hr />
        <div id="payment-information" class="label-value">
            <div>
                <label>Tổng tiền hàng:</label>
                <label>Phí vận chuyển:</label>
                <label>Thành tiền:</label>
                <label>Phương thức thành toán:</label>
            </div>
            <div style="text-align: right;">
                <p>${donHang.getTongTienHang()} đ</p>
                <p>${donHang.phiVanChuyen} đ</p>
                <p>${donHang.getTongTienHang() + donHang.phiVanChuyen} đ</p>
                <p>Thanh toán khi nhận hàng</p>
            </div>
        </div>
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
	<script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
	<script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
</body>

</html>