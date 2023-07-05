<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <base href="${pageContext.servletContext.contextPath}/">
    <title>Giỏ hàng của tôi</title>
    <link rel="stylesheet" href="resources/css/admin/button.css">
    <link rel="stylesheet" href="resources/css/share/display.css">
    <link rel="stylesheet" href="resources/css/user/gio_hang.css">
</head>

<body>
    <main>
    	<c:if test="${gioHang.isEmpty()}">
	        <div id="nothing-cart">
	            <img src="resources/image/DEFAULT_CART.png" alt="">
	            <button onclick="location.href='homepage.htm'">Mua hàng ngay</button>
	        </div>
        </c:if>
        <c:if test="${!gioHang.isEmpty()}">
	        <div id="having-cart" style="display: flex;">
	            <div class="labels">
	                <label>Sản phẩm</label>
	                <label>Đơn giá</label>
	                <label>Số lượng</label>
	                <label>Thành tiền</label>
	            </div>
	            <hr />
	            <form action="user/cart.htm">
	                <div class="products">
	                	<c:forEach var="e" items="${gioHang}">
	                	<div class="product">
	                        <div class="product-information">
	                            <img src="${e.sach.anhDaiDien}" alt="">
	                            <p>${e.sach.tenSach}</p>
	                        </div>
	                        <p class="price">${e.sach.getGiaSach()} đ</p>
	                        <div class="quantity">
	                            <button name="giamSoLuong" value="${e.khachHang.maKhachHang}_${e.sach.maSach}">
	                                <ion-icon name="remove-outline"></ion-icon>
	                            </button>
	                            <p>${e.soLuong}</p>
	                            <button name="tangSoLuong" value="${e.khachHang.maKhachHang}_${e.sach.maSach}">
	                                <ion-icon name="add-outline"></ion-icon>
	                            </button>
	                        </div>
	                        <p class="total">${e.soLuong * e.sach.getGiaSach()}</p>
	                        <!-- DÙNG <a> THAY VÌ <button> -->
	                        <button name="xoaChiTietGioHang" value="${e.khachHang.maKhachHang}_${e.sach.maSach}">
	                            <ion-icon name="trash"></ion-icon>
	                        </button>
	                    </div>
	                	</c:forEach>
	                </div>
	                <hr />
	                <div id="order">
	                    <div>
	                        <label>Tổng tiền</label>
	                        <p>${tongTien} đ</p>
	                    </div>
	                    <div>
	                        <button name="datHang" class="button text-button">
	                            <ion-icon name="cube"></ion-icon>
	                            <p>Đặt hàng</p>
	                        </button>
	                    </div>
	                </div>
	            </form>
	        </div>
        </c:if>
    </main>
    <!-- IMPORT JAVASCRIPT -->
    <script src="resources/js/share/icon.js"></script>
    <!-- IMPORT IONICONS -->
    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</body>

</html>