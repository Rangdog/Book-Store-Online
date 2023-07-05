<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "f" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">

<head>
	<base href="${pageContext.servletContext.contextPath}/">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chi tiết sản phẩm</title>
    <link rel="stylesheet" href="resources/css/admin/button.css">
    <link rel="stylesheet" href="resources/css/share/display.css">
    <link rel="stylesheet" href="resources/css/user/ct_sp.css">
    <link rel="stylesheet" href="resources/css/user/decorator.css">
    <!-- BOOTSTRAP CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>

<body>
    <main>
        <section id="introduction">
            <div id="product-image">
                <img src="${sach.anhDaiDien}" alt="">
            </div>
            <div id="product-information">
                <cite style="font-size: large;">${sach.tenSach}</cite>
                <div id="information">
                    <div class="labels">
                        <label>Tác Giả: </label>
                        <label>Nhà phát hành:</label>
                        <label>Nhà xuất bản:</label>
                        <label>Lượt mua:</label>
                    </div>
                    <div class="values">
                        <p><c:forEach items="${sach.dsTacGia }" var = "tacGia">${tacGia.tenTacGia}, </c:forEach></p>
                        <p>${sach.nhaPhatHanh.tenNPH}</p>
                        <p>${sach.nhaXuatBan.tenNXB}</p>
                        <p>${sach.luotMua}</p>
                    </div>
                </div>
                <div id="price-sale">
                    <div>
                    	<p style="width: 40%; text-indent: 5px;">${sach.getGiaSach()} đ</p>
                        <p class="original-price" style='display: ${sach.giaKhuyenMai == -1 ? "none" : ""}'>${sach.giaBan} đ</p>
                        <span class="sale">${sach.tinhKhuyenMaiThucTeMotNgay()} %</span>
                    </div>
                    <p style="font-style: italic;">Giao hàng miễn phí trong nội thành TP-HCM</p>
                </div>
                <div id="action">
                	<form action="user/chitietsanpham.htm">
                		<button name="add" value="${sach.maSach}">
                        	<ion-icon name="cart-outline"></ion-icon>
                        	<p>Thêm vào giỏ hàng</p>
                    	</button>
                	</form>
                    <form action="user/order.htm">
                    	 <button name="muaNgay" value="${sach.maSach}">Mua ngay</button>
                    </form>
                </div>
            </div>
        </section>
        <section id="description">
            <label>Giới thiệu</label>
            <cite>${sach.tenSach }</cite>
            <p>${sach.tomTat }</p>
        </section>
        <section id="product-detail">
            <label>Thông tin chi tiết</label>
            <table class="table table-striped table-bordered border-info">
                <tbody>
                    <tr>
                        <td>Tác giả</td>
                        <td><c:forEach items="${sach.dsTacGia }" var = "tacGia"> ${tacGia.tenTacGia}, </c:forEach></td>
                    </tr>
                    <tr>
                        <td>Nhà phát hành</td>
                        <td>${sach.nhaPhatHanh.tenNPH}</td>
                    </tr>
                    <tr>
                        <td>Nhà xuất bản</td>
                        <td>${sach.nhaXuatBan.tenNXB}</td>
                    </tr>
                    <tr>
                        <td>Kích thước</td>
                        <td>${sach.kichThuoc }</td>
                    </tr>
                    <tr>
                        <td>Trọng lượng</td>
                        <td>${sach.trongLuong}</td>
                    </tr>
                    <tr>
                        <td>Số trang</td>
                        <td>${sach.soTrang }</td>
                    </tr>
                    <tr>
                        <td>Hình thức</td>
                        <td>${sach.hinhThuc}</td>
                    </tr>
                    <tr>
                        <td>Thể loại</td>
                        <td><c:forEach items="${sach.dsTheLoai}" var = "theLoai">${theLoai.tenTheLoai}, </c:forEach></td>
                    </tr>
                </tbody>
            </table>
        </section>
        <section id="same-author">
            <label>Cùng tác giả</label>
            <div class="items">
            	<c:forEach var="e" items="${cungTacGia}">
						<div class="item">
							<a href="chitietsanpham.htm?maSach=${e[0]}"><img src="${e[1]}" alt=""></a>
							<cite>${e[2]}</cite>
							<c:if test="${e[3] != -1}">
								<div class="new-price">
			                        <p>${e[3]} đ</p>
			                    </div>
			                    <p class="old-price">${e[4]} đ</p>
							</c:if>
							<c:if test="${e[3] == -1}">
								<div class="new-price">
			                        <p>${e[4]} đ</p>
			                    </div>
							</c:if>
						</div>
				</c:forEach>
        </section>
        <section id="carable">
            <label>Có thể bạn quan tâm</label>
            <div class="items">
            	<c:forEach var="e" items="${cungTheLoai}">
						<div class="item">
							<a href="chitietsanpham.htm?maSach=${e[0]}"><img src="${e[1]}" alt=""></a>
							<cite>${e[2]}</cite>
							<c:if test="${e[3] != -1}">
								<div class="new-price">
			                        <p>${e[3]} đ</p>
			                    </div>
			                    <p class="old-price">${e[4]} đ</p>
							</c:if>
							<c:if test="${e[3] == -1}">
								<div class="new-price">
			                        <p>${e[4]} đ</p>
			                    </div>
							</c:if>
						</div>
				</c:forEach>
        </section> 	
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
    <!-- IMPORT BOOTSTRAP -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"></script>
</body>

</html>