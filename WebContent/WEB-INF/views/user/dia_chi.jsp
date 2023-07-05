<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "f" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.servletContext.contextPath}/">
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sổ địa chỉ</title>
    <link rel="stylesheet" href="resources/css/admin/button.css">
    <link rel="stylesheet" href="resources/css/share/display.css">
    <link rel="stylesheet" href="resources/css/share/list.css">
    <link rel="stylesheet" href="resources/css/user/dia_chi.css">
    <link rel="stylesheet" href="resources/css/user/decorator.css">
</head>
<body>
	<div id="toast"></div>
    <span id="toaston" style="display: none">${toaston}</span>
    <span id="title" style="display: none">${title}</span>
    <span id="message" style="display: none">${message}</span>
    <span id="type" style="display: none">${type}</span>
    <span id="duration" style="display: none">${duration}</span>
    <main>
    	<span id="button" style="display: none;">${modal}</span>
        <header class="make-flex">
            <label>ĐỊA CHỈ CỦA TÔI</label>
            <button id="add-address" class="button text-button add">
                <ion-icon name="add-outline"></ion-icon>
                <p>Thêm địa chỉ mới</p>
            </button>
        </header>
        <section class="list">       	
          	<c:forEach items="${diaChis}" var="a">
         		<div class="address">
	          		<div class="make-flex">
	                    <div class="information">
	                        <div class="make-flex">
	                            <label>${a.tenNN}</label>
	                            <p><ion-icon name="call"></ion-icon> ${a.sdtNN}</p>
	                        </div>
	                        <p>${a.diaChi}</p>
	                    </div>
	                    <div class="operation">
	                        <div class="make-flex">
	                            <form action="xemdiachi.htm" method="POST">
	                            	<input name="id" value="${a.maDiaChi}" style="display: none;">
	                            	<button class="btn-update" name="">Cập nhật</button>
	                            </form>
	                            <form action="deletediachi.htm" method="POST" style="width: 48.8%">
	                            	<input name="id" value="${a.maDiaChi}" style="display: none;">
	                            	<button class="delete">Xóa</button>
	                            </form>
	                        </div>
	                        <form action="thietlapmacdinh.htm">
	                        	<input name="id" value="${a.maDiaChi}" style="display: none;">
	                        	<button name="" class="default">Thiết lập mặc định</button>
	                        </form>
	                    </div>
	                </div>
	                <c:choose>
	                	<c:when test="${a.trangThai == 1}"><span class="address-default">Mặc định</span></c:when>
	                	<c:otherwise></c:otherwise>
	                </c:choose>     
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
    <!-- POP-UP MODAL -->
    <div id="modal" class="hide">
        <div class ="modal-inner">
            <form action="themdiachi.htm" method="POST">
            	<input type="text" name="id" style="display: none;">
                <div class="modal-hearder">
                    <h3 class="modal-title">THÊM MỚI</h3>
                    <ion-icon class="modal-close" name="close-outline"></ion-icon>
                </div>
                <div class="modal-body">
                    <div class="name-of-customer">
                        <p>Họ và tên:</p>
                        <input name="tenNN" type="text" required>
                    </div>
                    <div class="number-of-customer">
                        <p>Số điện thoại:</p>
                        <input name="sdtNN" type="tel" required>
                    </div>
                    <div class="address-of-customer">
                        <p>Địa chỉ:</p>
                        <textarea name="diaChi" id="" cols="30" rows="10"></textarea>
                    </div>
              
                    <div class="default-customer">
                        <input name="trangThai" type="checkbox" id="Default" value="1">
                        <label for="Default">Đặt làm địa chỉ mặc định</label>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn-action">THÊM</button>
                </div>
            </form>
        </div>    
    </div>
    
    <div id="modal-update" class="hide">
        <div class ="modal-inner">
            <form action="suadiachi.htm" method="POST">
            	<input name="id" value="${diaChiUpdate.maDiaChi}" style="display: none;">
                <div class="modal-hearder">
                    <h3 class="modal-title">CẬP NHẬT</h3>
                    <ion-icon class="modal-close" name="close-outline"></ion-icon>
                </div>
                <div class="modal-body">
                    <div class="name-of-customer">
                        <p>Họ và tên:</p>
                        <input name="tenNN" type="text" required value="${diaChiUpdate.tenNN}">
                    </div>
                    <div class="number-of-customer">
                        <p>Số điện thoại:</p>
                        <input name="sdtNN" type="tel" required value="${diaChiUpdate.sdtNN}">
                    </div>
                    <div class="address-of-customer">
                        <p>Địa chỉ:</p>
                        <textarea name="diaChi" id="" cols="30" rows="10">${diaChiUpdate.diaChi}</textarea>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn-action">CẬP NHẬT</button>
                </div>
            </form>
        </div>    
    </div>
    <div id="delete" class="hide">
		<div class ="modal-inner">
            <form action="${act}.htm" method="POST">
            <input name="id" value ="${idDelete}" type="text" style="display: none">
                <div class="modal-hearder">
                    <h5 class="modal-title">${delete}</h5>
                    <ion-icon id = "button-close"  class="modal-close" name="close-outline"></ion-icon>
                </div>
                <div class="modal-footer">
                    <button class="btn-action">XÓA</button>
                </div>
            </form>
        </div>    
	</div>
    <!-- IMPORT JAVASCRIPT -->
    <script src="resources/js/user/address.js"></script>
    <script src="resources/js/admin/modal.js"></script>
    
    <!-- IMPORT IONICONS -->
    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
    <script type="text/javascript">
    	let flag = document.querySelector('#button').innerHTML;
    	let toaston = document.getElementById('toaston').innerHTML
    	console.log(flag)
    	if(flag != "") {
    		if(flag == "add-address"){
    			document.getElementById(flag).click();
    		}else if (flag=="delete"){
    			document.getElementById(flag).classList.toggle('hide');
    		}else{
    			document.getElementById(flag).click();
    		}
    		
    	}
    	if(toaston == "1") {
	    	let title = document.getElementById('title').innerHTML
	    	let message = document.getElementById('message').innerHTML
	    	let type = document.getElementById('type').innerHTML
	    	let duration = Number(document.getElementById('duration').innerHTML)
	    	toast({ title: title, message: message, type: type, duration: duration });
	    }
    	
    </script>
</body>

</html>