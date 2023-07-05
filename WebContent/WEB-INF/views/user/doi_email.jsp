<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <base href="${pageContext.servletContext.contextPath}/">
    <title>Đổi email mới</title>
    <link rel="stylesheet" href="resources/css/admin/button.css">
    <link rel="stylesheet" href="resources/css/share/display.css">
    <link rel="stylesheet" href="resources/css/user/doi_email.css">
    <style>
    	.error {
    		color: red;
    		border: none !important;
    	}
    </style>
</head>

<body>
    <main>
        <header>THAY ĐỔI EMAIL</header>
        <form action="user/changeemail.htm" method="post">
            <div id="email-confirmation">
                <label for="email">Nhập email mới:</label>
                <input type="email" name="email" id="email" autofocus autocomplete="off" value="${email}" />
                <div class="error" style="position: absolute; left: 710px; top: 365px;">${erEmail}</div>
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
    <script src="resources/js/share/icon.js"></script>
    <!-- IMPORT IONICONS -->
    <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
</body>

</html>