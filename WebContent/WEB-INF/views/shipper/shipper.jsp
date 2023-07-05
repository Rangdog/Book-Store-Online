<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" href="resources/image/LOGO.ico" type="image/x-icon">
<base href="${pageContext.servletContext.contextPath}/">
<title>Shipper #${shipper.maNhanVien}</title>
<link rel="stylesheet" href="resources/css/shipper/shipper.css">
<link rel="stylesheet" href="resources/css/shipper/modal.css">
<link rel="stylesheet" href="resources/css/admin/button.css">
</head>

<body>
	<input id="openModal" value="${openModal}" style="display: none;" />
	<header>
		<div id="staff">
			<img src='${shipper.anhDaiDien == null ? "resources/image/DEFAULT_IMAGE.png" : shipper.anhDaiDien}' alt="" />
			<label>${shipper.ho} ${shipper.ten}</label>
		</div>
		<div id="title"><label>DANH SÁCH ĐƠN HÀNG</label></div>
		<button name="log-out" class="button icon-button" onclick="location.href='logout.htm'">
        	<ion-icon name="power"></ion-icon>
       	</button>
	</header>
	<main>
		<form action="shipper.htm" method="post">
			<table>
				<thead>
					<th>ĐƠN HÀNG</th>
					<th>KHÁCH HÀNG</th>
					<th>ĐỊA CHỈ</th>
					<th>TRẠNG THÁI</th>
					<th>THAO TÁC</th>
				</thead>
				<c:forEach var="e" items="${donHangs}">
				<tr>
					<td>${e.maDonHang}</td>
					<td>
						${e.diaChiGiaoHang.tenNN}<br/>
						(${e.diaChiGiaoHang.sdtNN})
					</td>
					<td>${e.diaChiGiaoHang.diaChi}</td>
					<td>${e.getTenTrangThai()}</td>
					<td>
						<button name="view" value="${e.maDonHang}"><ion-icon name="eye"></ion-icon></button>
						<button name="deliver" value="${e.maDonHang}" ${e.trangThai != 1 ? 'disabled' : ''}>
							<ion-icon name="paper-plane"></ion-icon>
						</button>
						<button name="complete" value="${e.maDonHang}" ${e.trangThai != 3 ? 'disabled' : ''}>
							<ion-icon name="receipt"></ion-icon>
						</button>
						<button name="refund" value="${e.maDonHang}" ${e.trangThai != 3 ? 'disabled' : ''}>
							<ion-icon name="close-circle"></ion-icon>
						</button>
					</td>
				</tr>
				</c:forEach>
			</table>
		</form>
	</main>
	<section>
		<div id="modal-view" class="modal close">
			<div id="modal-title">
				<p>THÔNG TIN ĐƠN HÀNG #${donHang.maDonHang}</p>
				<button name="close"><ion-icon name="close-sharp"></ion-icon></button>
			</div>
			<div id="modal-content">
				<div id="information">
					<div class="format">
						<div>
							<label>Ngày đặt:</label>
							<label>Phí vận chuyển:</label>
							<label>Thành tiền:</label>
						</div>
						<div>
							<p>${donHang.ngay}</p>
							<p>${donHang.phiVanChuyen} đ</p>
							<p>${donHang.getTongTienHang()} đ</p>
						</div>
					</div>
					<hr />
					<div>
						<label>Họ tên: <span>${donHang.diaChiGiaoHang.tenNN} (${donHang.diaChiGiaoHang.sdtNN})</span></label>
						<label>Địa chỉ: <span>${donHang.diaChiGiaoHang.diaChi}</span></label>
					</div>
				</div>
				<hr />
				<div id="products">
					<c:forEach var="e" items="${donHang.dsChiTietDonHang}">
					<div class="product">
						<img src="${e.sach.anhDaiDien}" alt="" />
						<div>
							<cite>${e.sach.tenSach}</cite>
							<p>${e.soLuong}</p>
							<p>${e.giaTien} đ</p>
						</div>
					</div>
					</c:forEach>
				</div>
			</div>
		</div>
		<div id="modal-refund" class="modal close" style="width: 40%;">
            <div id="modal-title">
                <p>HOÀN TRẢ ĐƠN HÀNG #${maDonHang}</p>
                <button name="close"><ion-icon name="close-sharp"></ion-icon></button>
            </div>
            <div id="modal-content">
                <form action="refund.htm" style="padding: 10px; text-align: center;">
                    <div>
                        <label style="margin-bottom: 10px; text-decoration: underline; text-align: left;">LÝ DO HOÀN TRẢ:</label>
                        <textarea name="lyDo" required rows="3" style="width: 98%; resize: none; padding: 5px;"></textarea>
                    </div>
                    <button name="confirm" value="${maDonHang}">XÁC NHẬN</button>
                </form>
            </div>
        </div>
	</section>
	<!-- IMPORT JAVASCRIPT -->
	<script>
    	document.querySelectorAll('ion-icon').forEach(icon => icon.style.pointerEvents = 'none')
        document.querySelectorAll('button[name="close"]').forEach(button => {
        	button.onclick = function (e) {
                document.querySelectorAll('.modal').forEach(modal => modal.classList.add('close'))
            }
        })
    	var openModal = document.getElementById("openModal")
    	if (openModal.value != null) document.getElementById(openModal.value).classList.remove('close')
    </script>
	<!-- IMPORT IONICONS -->
	<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
	<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</body>

</html>