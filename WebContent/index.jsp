<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <base href="${pageContext.servletContext.contextPath}/">
    <title>Đăng nhập</title>
    <link rel="shortcut icon" href="resources/image/LOGO.ico" type="image/x-icon">
    <link rel="stylesheet" href="resources/css/share/display.css">
    <link rel="stylesheet" href="resources/css/user/dang_nhap.css">
    <!-- BOOTSTRAP CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
        integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<style>
    	.error {
    		color: red;
    		border: none !important;
    	}
    </style>
</head>

<body>
	<span id = "toaston" style="display: none">${error}</span>
	<span id = "flag" style="display: none">${flag}</span>
    <!-- BACKGROUND SLIDING -->
    <div id="toast"></div>
    <div id="carousel" class="carousel slide" data-ride="carousel" data-pause="false">
        <div class="carousel-inner">
            <div class="carousel-item active" data-interval="5000">
                <img src="resources/image/LOGIN_SLIDING/LOGIN_1.png" alt="">
            </div>
            <div class="carousel-item" data-interval="5000">
                <img src="resources/image/LOGIN_SLIDING/LOGIN_2.png" alt="">
            </div>
            <div class="carousel-item" data-interval="5000">
                <img src="resources/image/LOGIN_SLIDING/LOGIN_3.png" alt="">
            </div>
        </div>
    </div>
    <!-- OVERALL DISPLAY -->
    <main>
        <section id="login-form" class="hide">
            <form action="login.htm" method="post">
                <header>ĐĂNG NHẬP</header>
                <div class="user">
                    <label for="username">Tài khoản</label>
                    <div class="input-box">
                        <input id="username" name="tentaikhoan" type="text" required autocomplete="off"
                            placeholder="Nhập tên tài khoản">
                    </div>
                </div>
                <div class="user">
                    <label for="password">Mật khẩu</label>
                    <div class="input-box">
                        <input id="password" name="matkhau" type="password" required class="password"
                            placeholder="Nhập mật khẩu">
                        <ion-icon name="eye-off-outline" class="icon-eye"></ion-icon>
                    </div>
                </div>
                <div class="login__flex login make-flex separate-row">
                    <button class="button-85">Đăng nhập</button>
                </div>
                <div class="make-flex separate-row choices">
                    <a id="foget-password">Quên mật khẩu?</a>
                    <a id="btn-register">Chưa có tài khoản?</a>
                </div>
                <div class="nav-flex">
                    <a href=""><ion-icon name="logo-facebook"></ion-icon>
                        <p>Facebook</p>
                    </a>
                    <a href=""><ion-icon name="logo-google"></ion-icon>
                        <p>Google</p>
                    </a>
                </div>
            </form>
        </section>
        <section id="register-form" class = "hide">
            <form action="process_register.htm" method="post">
                <header>ĐĂNG KÝ</header>
                <div id="register-body">
                    <div>
                        <label for="sign-username">Tên đăng nhập</label>
                        <div class="input-box">
                            <input type="text" name="tenTaiKhoan" id="sign-username" required autocomplete="off"
                                placeholder="Tên đăng nhập" value="${tenTaiKhoan}">
                        </div>
                    </div>
                    <div>
                        <label for="email">Email</label>
                        <div class="input-box">
                            <input type="email" name="email" id="email" required autocomplete="off"
                                placeholder="@gmail.com" value="${email}">
                        </div>
                    </div>
                    <div>
                        <label for="sign-password">Mật khẩu</label>
                        <div class="input-box">
                            <input type="password" name="matKhau" id="sign-password" required class="password"
                                placeholder="Mật khẩu" value="${matKhau}">
                            <ion-icon name="eye-off-outline" class="icon-eye"></ion-icon>
                        </div>
                    </div>
                    <div>
                        <label for="confirm-password">Xác nhận mật khẩu</label>
                        <div class="input-box">
                            <input type="password" name="matKhauXacNhan" id="confirm-password" required
                                class="password" placeholder="Mật khẩu xác nhận" value="${matKhauXacNhan}">
                            <ion-icon name="eye-off-outline" class="icon-eye"></ion-icon>
                        </div>
                    </div>
                    <div>
                        <label for="policy">Điều khoản</label>
                        <div id="register-policy">
                            <input type="checkbox" name="" id="policy" checked required>
                            <p>Tôi chấp nhận với <a href="">điều khoản</a></p>
                        </div>
                    </div>
                    <div class="error">${erDangKy}</div>
                    <div id="register-button">
                        <button class="button-85 separate-row">Đăng kí</button>
                    </div>
                </div>
                <div id="register-login">
                    <a>Bạn đã có tài khoản?</a>
                </div>
            </form>
        </section>
        <section id="restore-form" class="hide">
            <form action="restore.htm" method="post">
                <header class="separate-row">KHÔI PHỤC MẬT KHẨU</header>
                <label for="confirm-email" class="separate-row">Email</label>
                <div class="separate-row input-box" id="restore-input">
                    <input type="email" id="confirm-email" name="confirm-email" required autocomplete="off" 
                    	placeholder="Nhập email">
                </div>
                <div id="restore-form-btn">
                    <div class="separate-row"><button class="button-85" id="btn-confirm">XÁC NHẬN</button></div>
                    <div class="separate-row"><button class="button-85" id="btn-back">TRỞ VỀ</button></div>
                </div>
            </form>
        </section>
    </main>
    <!-- IMPORT JAVASCRIPT -->
    <script src="resources/js/user/login.js"></script>
    <script src="resources/js/user/password.js"></script>
    <!-- IMPORT IONICONS -->
    <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
    <!-- IMPORT BOOTSTRAP -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
</body>
	<script type="text/javascript">
	 let toaston = document.getElementById('toaston').innerHTML;
	 let modal = document.getElementById('flag').innerHTML;
	 if(modal == ""){
		 modal = "login-form";
	 }
	 document.getElementById(modal).classList.toggle('hide');
	 document.getElementById(modal).classList.toggle('active');
	 if(toaston == "failed"){
     	toast({
     		 title: "SAI TÀI KHOẢN HOẶC MẬT KHẨU",
     	      message: "Có lỗi xảy ra, vui lòng kiểm tra lại tài khoản mật khẩu.",
     	      type: "error",
     	      duration: 5000
       	});
     }
	 else if(toaston == "deny"){
		 toast({
     		 title: "BẠN KHÔNG CÓ QUYỀN ĐỂ TRUY CẬP ĐỊA CHỈ NÀY",
     	      message: "Có lỗi xảy ra, vui lòng liên hệ quản trị viên.",
     	      type: "error",
     	      duration: 5000
       	});
	 }
	 else if(toaston == "logout"){
		 toast({
     		 title: "ĐĂNG XUẤT THÀNH CÔNG",
     	      message: "Mong rằng bạn sẽ quay trở lại",
     	      type: "success",
     	      duration: 5000
       	});
	 }
	</script>
</html>