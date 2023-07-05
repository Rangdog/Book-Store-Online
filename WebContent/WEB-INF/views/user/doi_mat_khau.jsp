<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <base href="${pageContext.servletContext.contextPath}/">
    <title>Đổi mật khẩu mới</title>
    <link rel="stylesheet" href="resources/css/admin/button.css">
    <link rel="stylesheet" href="resources/css/share/display.css">
    <link rel="stylesheet" href="resources/css/user/doi_mat_khau.css">
    <style>
    	.error {
    		color: red;
    		border: none !important;
    	}
    </style>
</head>

<body>
    <main>
        <header>ĐỔI MẬT KHẨU</header>
        <form action="user/changepassword.htm" method="post">
            <div class="input-box">
                <label for="prs-password">Mật khẩu hiện tại</label>
                <div>
                    <input type="password" id="prs-password" placeholder="Mật khẩu hiện tại" name="matkhauhientai" 
                    	value="${matKhauHienTai}"/>
                    <ion-icon name="eye-off-outline" class="icon-eye"></ion-icon>
                </div>
                <div class="error">${erMatKhauHienTai}</div>
            </div>
            <div class="input-box">
                <label for="new-password">Mật khẩu mới</label>
                <div>
                    <input type="password" id="new-password" placeholder="Mật khẩu hiện mới" name="matkhaumoi" 
                    	value="${matKhauMoi}"/>
                    <ion-icon name="eye-off-outline" class="icon-eye"></ion-icon>
                </div>
                <div class="error">${erMatKhauMoi}</div>
            </div>
            <div class="input-box">
                <label for="prs-password">Mật khẩu xác nhận</label>
                <div>
                    <input type="password" id="confirm-password" placeholder="Nhập lại mật khẩu mới" name="matkhauxacnhan" 
                    	value="${matKhauXacNhan}"/>
                    <ion-icon name="eye-off-outline" class="icon-eye"></ion-icon>
                </div>
                <div class="error">${erMatKhauXacNhan}</div>
            </div>
            <div id="action">
                <button name="xacnhan" value="${id}" class="button text-button confirm">
                    <ion-icon name="checkmark-circle"></ion-icon>
                    <p>Xác nhận</p>
                </button>
                <button name="huybo" value="${id}" class="button text-button cancel">
                    <ion-icon name="close-circle"></ion-icon>
                    <p>Hủy bỏ</p>
                </button>
            </div>
        </form>
    </main>
    <!-- IMPORT JAVASCRIPT -->
    <script src="resources/js/user/password.js"></script>
    <!-- IMPORT IONICONS -->
    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</body>

</html>