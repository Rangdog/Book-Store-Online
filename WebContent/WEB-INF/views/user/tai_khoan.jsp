<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <base href="${pageContext.servletContext.contextPath}/">
    <title>Thông tin tài khoản</title>
    <link rel="stylesheet" href="resources/css/admin/button.css">
    <link rel="stylesheet" href="resources/css/share/display.css">
    <link rel="stylesheet" href="resources/css/user/tai_khoan.css">
    <link rel="stylesheet" href="resources/css/user/decorator.css">
</head>

<body>
    <main>
        <div id="account-title">
            <h2>THÔNG TIN TÀI KHOẢN</h2>
            <p>Quản lý thông tin hồ sơ để bảo mật tài khoản của bạn</p>
        </div>
        <form action="user/taikhoan.htm" method="post" enctype="multipart/form-data">
            <div id="account-content">
                <div id="account-information">
                    <div class="form-group">
                        <label for="firstname">Họ:</label>
                        <input type="text" name="ho" id="firstname" autocomplete="off" value="${khachHang.ho}">
                    </div>
                    <div class="form-group">
                        <label for="lastname">Tên:</label>
                        <input type="text" name="ten" id="lastname" autocomplete="off" value="${khachHang.ten}">
                    </div>
                    <div class="form-group">
                        <label for="email">Email:</label>
                        <input type="email" id="email" readonly value="${khachHang.email}" 
                        	style='color: ${khachHang.trangThaiEmail ? "green" : "red"};'>
                        <a href="user/changeemail.htm?thaydoiemail=${khachHang.maKhachHang}">Thay đổi</a>
                    </div>
                    <div class="form-group">
                        <label>Giới tính:</label>
                        <input type="radio" name="gioiTinh" id="male" value="True" 
                        	${khachHang.gioiTinh == true ? 'checked' : ''}>
                        <label for="male">Nam</label>
                        <input type="radio" name="gioiTinh" id="female" value="False" 
                        	${khachHang.gioiTinh == false ? 'checked' : ''}>
                        <label for="female">Nữ</label>
                    </div>
                    <div class="form-group">
                        <label for="date">Ngày sinh:</label>
                        <input type="date" name="ngaySinh" id="date" value="${khachHang.ngaySinh}">
                    </div>
                    <div style="width: 100%; display: flex; justify-content: center; gap: 20px;">
                        <button name="xacthucemail" value="${khachHang.maKhachHang}" class="button text-button confirm"
                        	${khachHang.trangThaiEmail ? 'disabled' : ''}>
                        	<ion-icon name="finger-print"></ion-icon>
                        	<p>Xác thực</p>
                    	</button>
	                    <button name="capnhatthongtin" value="${khachHang.maKhachHang}" class="button text-button update">
	                        <ion-icon name="cloud-upload"></ion-icon>
	                        <p>Cập nhật</p>
	                    </button>
                    </div>
                </div>
                <div id="account-image">
                    <img src='${khachHang.anhDaiDien == null ? "resources/image/DEFAULT_IMAGE.png" : khachHang.anhDaiDien}'  
                    	alt="" />
                    <input type="file" name="anhDaiDien" class="upload-image" accept=".png, .jpg" />
                </div>
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
    <script src="resources/js/share/file_upload.js"></script>
    <script src="resources/js/share/icon.js"></script>
    <!-- IMPORT IONICONS -->
    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</body>

</html>