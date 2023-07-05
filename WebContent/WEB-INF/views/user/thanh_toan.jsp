<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <base href="${pageContext.servletContext.contextPath}/">
    <title>Thanh toán đơn hàng</title>
    <link rel="stylesheet" href="resources/css/admin/button.css">
    <link rel="stylesheet" href="resources/css/share/display.css">
    <link rel="stylesheet" href="resources/css/user/thanh_toan.css">
    <link rel="stylesheet" href="resources/css/user/decorator.css">
</head>

<body>
    <main>
        <h2>THÔNG TIN THANH TOÁN</h2>
        <form action="user/payment.htm" method="post">
        	<input type="text" name="phiVanChuyen" value="${phiVanChuyen}" style="display: none;" />
            <section id="payment-address">
                <h3>Địa chỉ nhận hàng</h3>
                <input type="text" name="id" id="id" value="${diaChi.maDiaChi}">
                <div id="address-information">
                    <p><span id="used-receiver">${diaChi.tenNN}</span><br><span id="used-phone">${diaChi.sdtNN}</span></p>
                    <p id="used-address">${diaChi.diaChi}</p>
                    <button name="change-address" class="button text-button">
                        <ion-icon name="reload" style="font-size: 25px;"></ion-icon>
                        <p>Thay đổi</p>
                    </button>
                </div>
            </section>
            <section id="payment-product">
                <div id="payment-product-title">
                    <label style="width: 40%;">Sản phẩm</label>
                    <label style="width: 25%;">Đơn giá</label>
                    <label style="width: 25%;">Số lượng</label>
                    <label style="width: 10%;">Thành tiền</label>
                </div>
                <div id="payment-product-content">
                    <div id="product-list">
                    	<c:forEach var="e" items="${sanPhams}">
                    	<input type="text" name="sanPham[]" value="${e.sach.maSach}" style="display: none;" />
                    	<input type="text" name="soLuong[]" value="${e.soLuong}" style="display: none;" />
                    	<input type="text" name="giaTien[]" value="${e.sach.getGiaSach()}" style="display: none;" />
                    	<div class="product">
                            <div class="product-image">
                                <img src="${e.sach.anhDaiDien}" alt="" />
                                <p>${e.sach.tenSach}</p>
                            </div>
                            <p class="product-price">${e.sach.getGiaSach()} đ</p>
                            <p class="product-quantity">${e.soLuong}</p>
                            <p class="product-total">
                            	${e.soLuong * e.sach.getGiaSach()} đ
                            </p>
                        </div>
                    	</c:forEach>
                    </div>
                </div>
            </section>
            <section id="payment-method">
                <div class="payment-method-title">
                    <h3>Phương thức thanh toán</h3>
                    <p>Thanh toán khi nhận hàng</p>
                </div>
                <div class="payment-method-content">
                    <div class="labels">
                        <label>Tổng tiền hàng:</label>
                        <label>Phí vận chuyển:</label>
                        <label>Tổng thanh toán:</label>
                    </div>
                    <div class="values">
                        <p>${tongTien} đ</p>
                        <p>${phiVanChuyen} đ</p>
                        <p>${tongTien + phiVanChuyen} đ</p>
                    </div>
                </div>
                <div id="payment-confirm">
                    <p>Nhấn "Đặt hàng" đồng nghĩa với việc bạn tuân theo <a href="">Chính sách của 3T Shop</a></p>
                    <button name="confirm-order" class="button text-button">
                        <ion-icon name="card"></ion-icon>
                        <p>Thanh toán</p>
                    </button>
                </div>
            </section>
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
    <section id="modals">
        <div id="modal-address" class="modal close">
            <div class="modal-title">
                <p>SỔ ĐỊA CHỈ</p>
                <button name="close" class="button">
                    <ion-icon name="close-sharp"></ion-icon>
                </button>
            </div>
            <div class="modal-content">
                <div id="address-list">
                	<c:forEach var="e" items="${diaChis}">
                	<div class="address-item">
                        <input type="text" name="id" value="${e.maDiaChi}">
                        <div>
                            <div class="item-information">
                                <ion-icon name="home"></ion-icon>
                                <input type="text" name="receiver" value="${e.tenNN}" disabled>
                                <ion-icon name="call"></ion-icon>
                                <input type="text" name="phone" value="${e.sdtNN}" disabled>
                            </div>
                            <div class="item-information">
                                <ion-icon name="location"></ion-icon>
                                <textarea name="address" cols="30" rows="10" disabled>${e.diaChi}</textarea>
                            </div>
                        </div>
                        <button name="target">Giao đến địa chỉ này</button>
                    </div>
                	</c:forEach>
                </div>
            </div>
        </div>
    </section>
    <!-- IMPORT JAVASCRIPT -->
    <script src="resources/js/user/payment.js"></script>
    <script src="resources/js/share/icon.js"></script>
    <!-- IMPORT IONICONS -->
    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</body>

</html>