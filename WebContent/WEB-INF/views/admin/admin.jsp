<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%> 

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <base href="${pageContext.servletContext.contextPath}/">
    <title>Quản lý hệ thống</title>
    <link rel="shortcut icon" href="resources/image/LOGO.ico" type="image/x-icon">
    <link rel="stylesheet" href="resources/css/share/list.css">
    <link rel="stylesheet" href="resources/css/admin/admin.css">
    <link rel="stylesheet" href="resources/css/admin/display.css">
    <link rel="stylesheet" href="resources/css/admin/button.css">
    <link rel="stylesheet" href="resources/css/admin/modal.css">
    <link rel="stylesheet" href="resources/css/admin/statistic.css">
</head>
<body>
    <div id="toast"></div>
    <span id="toaston" style="display: none">${toaston}</span>
    <span id="title" style="display: none">${title}</span>
    <span id="message" style="display: none">${message}</span>
    <span id="type" style="display: none">${type}</span>
    <span id="duration" style="display: none">${duration}</span>
    <span id="flag" style="display: none">${page}</span>
    <span id="flag_modal" style="display: none">${modal}</span>
    <security:authorize access="isAuthenticated()" var="isAuthen" />
	<security:authentication property="principal" var="account"/>
	<!-- OVERALL DISPLAY -->
	<section id="overall">
	<nav>
		<div id="introduction">
			<c:if test="${acc.checkExistAnhDaiDien() == false}">
				<img src="resources/image/LOGO.png" alt="ảnh đại diện">
			</c:if>
			<c:if test="${acc.checkExistAnhDaiDien()}">
				<img src="${acc.anhDaiDien}" alt="ảnh đại diện">
			</c:if>
			<p>${acc.hoTen()}</p>
		</div>
        <div id="control-bar">
            <div class="control-button" id="control-staff">
                <ion-icon name="person-circle-outline"></ion-icon>
                <label>Quản lý nhân viên</label>
            </div>
            <div class="detail-control">
                <div class="control-button parent-control">
                    <ion-icon name="grid-outline"></ion-icon>
                    <label>Chi tiết sản phẩm</label>
                    <ion-icon name="caret-forward"></ion-icon>
                </div>
                <div class="detail-bar">
                    <div class="control-button" id="control-category"><ion-icon name="list-outline"></ion-icon><label>Danh mục</label></div>
                    <div class="control-button" id="control-genre"><ion-icon name="extension-puzzle-outline"></ion-icon><label>Thể loại</label></div>
                    <div class="control-button" id="control-author"><ion-icon name="finger-print-outline"></ion-icon><label>Tác giả</label></div>
                    <div class="control-button" id="control-publisher"><ion-icon name="print-outline"></ion-icon><label>Nhà xuất bản</label></div>
                    <div class="control-button" id="control-issuer"><ion-icon name="paper-plane-outline"></ion-icon><label>Nhà phát hành</label></div>
                </div>
            </div>
            <div class="control-button" id="control-import">
                <ion-icon name="cube-outline"></ion-icon>
                <label>Quản lý phiếu nhập</label>
            </div>
            <div class="control-button" id="control-product">
                <ion-icon name="book-outline"></ion-icon>
                <label>Quản lý sản phẩm</label>
            </div>
            <div class="control-button" id="control-order">
                <ion-icon name="cube-outline"></ion-icon>
                <label>Quản lý đơn hàng</label>
            </div>
            <div class="control-button" id="control-customer">
                <ion-icon name="people-outline"></ion-icon>
                <label>Quản lý khách hàng</label>
            </div>
            <div class="control-button" id="control-sale">
                <ion-icon name="ticket-outline"></ion-icon>
                <label>Quản lý khuyến mãi</label>
            </div>
            <div class="control-button" id="control-statistic">
                <ion-icon name="stats-chart-outline"></ion-icon>
                <label>Thống kê</label>
            </div>
    	</div>
    </nav>
    <main>
    	<header></header>
    	<section id="page-staff" class="page data-page">
	        <form action="">
	            <nav class="tool-bar">
	                <div>
	                    <div class="arrange-bar">
	                        <div class="arrange-control">
	                            <label>SẮP XẾP</label>
	                            <ion-icon name="caret-down-outline"></ion-icon>
	                        </div>
	                        <div class="arrange-choices">
	                            <button>Họ</button>
	                            <button>Tên</button>
	                        </div>
	                    </div>
	                    <div class="search-bar">
	                        <label for="search"><ion-icon name="search"></ion-icon></label>
	                        <input id="search" type="search" name="keyword" />
	                    </div>
	                </div>
	                <div>
	                    <button name="insert" class="modal-button">THÊM MỚI</button>
	                 	<button name = "addAccount">TẠO TÀI KHOẢN CHO NHÂN VIÊN</button>
	                    <button name = "rePassword" class = "modal-button">ĐỔI MẬT KHẨU</button>
	                </div>
	           	</nav>
	            <table>
	                <tr>
	                    <th>ID</th>
	                    <th>Họ</th>
	                    <th>Tên</th>
	                    <th>Giới tính</th>
	                    <th>Ngày sinh</th>
	                    <th>Số điện thoại</th>
	                    <th>Thao tác</th>
	                </tr>
	                <c:forEach var="e" items="${nhanViens}">
					<tr>
	                     <td>${e.maNhanVien}</td>
	                     <td>${e.ho}</td>
	                     <td>${e.ten}</td>
	                     <td>${e.gioiTinh ? "Nam" : "Nữ"}</td>
	                     <td>${e.ngaySinh}</td>
	                     <td>${e.sDT}</td>
	                     <td>
							 <button name="xemnhanvien" value="${e.maNhanVien}" class="button icon-button update">
	                             <ion-icon name="cloud-upload"></ion-icon>
	                         </button>
	                         <button name="xoanhanvien" value="${e.maNhanVien}" class="button icon-button delete">
	                             <ion-icon name="trash"></ion-icon>
	                         </button>
	                     </td>
	               	</tr>    
	                </c:forEach>    
	            </table>
	        </form>
	   	</section>
        <section id="page-category" class="page data-page product-detail">
	        <form action="admin.htm">
	            <nav class="tool-bar">
	                <div>
	                    <div class="arrange-bar">
	                        <div class="arrange-control">
	                            <label>SẮP XẾP</label>
	                            <ion-icon name="caret-down-outline"></ion-icon>
	                        </div>
	                        <div class="arrange-choices">
	                            <button>Tên danh mục</button>
	                        </div>
	                    </div>
	                    <div class="search-bar">
	                        <label for="search"><ion-icon name="search"></ion-icon></label>
	                        <input id="search" type="search" name="keyword" />
	                    </div>
	                </div>
	                <div>
	                    <button name="insert" class="modal-button">THÊM MỚI</button>
	                </div>
	            </nav>
	            <table>
	                <tr>
	                    <th>ID</th>
	                    <th>Tên danh mục</th>
	                    <th>Thao tác</th>
	                </tr>
	               	<c:forEach items="${danhMucs}" var = "danhMuc">
	               		<tr>
	                		<td>${danhMuc.maDanhMuc}</td>
	                		<td>${danhMuc.tenDanhMuc}</td>
	                		<td>
	                			<button name="xemdanhmuc" value="${danhMuc.maDanhMuc}" class="button icon-button update">
	                				<ion-icon name="cloud-upload"></ion-icon>
	                			</button>
	                			<button name="xoadanhmuc" value="${danhMuc.maDanhMuc}" class="button icon-button delete">
	                				<ion-icon name="trash"></ion-icon>
	                			</button>
	                    	</td>
	                   	</tr>
	               	</c:forEach>
	            </table>
	        </form>
        </section>
    	<section id="page-genre" class="page data-page product-detail">
	        <form action="admin.htm">
	            <nav class="tool-bar">
	                <div>
	                    <div class="arrange-bar">
	                        <div class="arrange-control">
	                            <label>SẮP XẾP</label>
	                            <ion-icon name="caret-down-outline"></ion-icon>
	                        </div>
	                        <div class="arrange-choices">
	                            <button>Tên thể loại</button>
	                        </div>
	                    </div>
	                    <div class="search-bar">
	                        <label for="search"><ion-icon name="search"></ion-icon></label>
	                        <input id="search" type="search" name="keyword" />
	                    </div>
	                </div>
	                <div>
	                    <button name="insert" class="modal-button">THÊM MỚI</button>
	                </div>
	            </nav>
	            <table>
	                <tr>
	                    <th>ID</th>
	                    <th>Tên thể loại</th>
	                    <th>Tên danh mục</th>
	                    <th>Thao tác</th>
	                </tr>
	                <c:forEach items="${theLoais}" var="theLoai">
	                <tr>
	                		<td>${theLoai.maTheLoai}</td>
	                		<td>${theLoai.tenTheLoai}</td>
	                		<td>${theLoai.danhMuc.tenDanhMuc}</td>
	                		<td>
	                			<button name="xemtheloai" value="${theLoai.maTheLoai}" class="button icon-button update">
	                				<ion-icon name="cloud-upload"></ion-icon>
	                			</button>
	                			<button name="xoatheloai" value="${theLoai.maTheLoai}" class="button icon-button delete">
	                				<ion-icon name="trash"></ion-icon>
	                			</button>
	                    	</td>
	                </tr>
	                </c:forEach>
	            </table>
	        </form>
        </section>
    	<section id="page-author" class="page data-page product-detail">
	       <form action="admin.htm">
	            <nav class="tool-bar">
	                <div>
	                    <div class="arrange-bar">
	                        <div class="arrange-control">
	                            <label>SẮP XẾP</label>
	                            <ion-icon name="caret-down-outline"></ion-icon>
	                        </div>
	                        <div class="arrange-choices">
	                            <button>Tên tác giả</button>
	                        </div>
	                    </div>
	                    <div class="search-bar">
	                        <label for="search"><ion-icon name="search"></ion-icon></label>
	                        <input id="search" type="search" name="keyword" />
	                    </div>
	                </div>
	                <div>
	                    <button name="insert" class="modal-button">THÊM MỚI</button>
	                </div>
	            </nav>
	            <table>
	                <tr>
	                    <th>ID</th>
	                    <th>Tên tác giả</th>
	                    <th>Thao tác</th>
	                </tr>
	                <c:forEach var="e" items="${tacGias}">
					<tr>
	                    <td>${e.maTacGia}</td>
	                    <td>${e.tenTacGia}</td>
	                    <td>
	                  		<button name="xemtacgia" value="${e.maTacGia}" class="button icon-button update">
	               				<ion-icon name="cloud-upload"></ion-icon>
	               			</button>
	               			<button name="xoatacgia" value="${e.maTacGia}" class="button icon-button delete">
	               				<ion-icon name="trash"></ion-icon>
	               			</button>
	                    </td>
	                </tr>
	                </c:forEach>
	            </table>
	        </form>
        </section>
    	<section id="page-publisher" class="page data-page product-detail">
	        <form action="admin.htm">
	            <nav class="tool-bar">
	                <div>
	                    <div class="arrange-bar">
	                        <div class="arrange-control">
	                            <label>SẮP XẾP</label>
	                            <ion-icon name="caret-down-outline"></ion-icon>
	                        </div>
	                        <div class="arrange-choices">
	                            <button>Trạng thái</button>
	                        </div>
	                    </div>
	                    <div class="search-bar">
	                        <label for="search"><ion-icon name="search"></ion-icon></label>
	                        <input id="search" type="search" name="keyword" />
	                    </div>
	                </div>
	                <div>
	                    <button name="insert" class="modal-button">THÊM MỚI</button>
	                </div>
	            </nav>
	            <table>
	                <tr>
	                    <th>ID</th>
	                    <th>Tên nhà xuất bản</th>
	                    <th>Thao tác</th>
	                </tr>
	                <c:forEach items="${nhaXuatBans}" var="nhaXuatBan">
	                <tr>
                		<td>${nhaXuatBan.maNXB}</td>
                		<td>${nhaXuatBan.tenNXB}</td>
                		<td>
                			<button name="xemnhaxuatban" value="${nhaXuatBan.maNXB}" class="button icon-button update">
                				<ion-icon name="cloud-upload"></ion-icon>
                			</button>
                			<button name="xoanhaxuatban" value="${nhaXuatBan.maNXB}" class="button icon-button delete">
                				<ion-icon name="trash"></ion-icon>
                			</button>
                    	</td>
	                </tr>
	                </c:forEach>
	            </table>
	        </form>
        </section>
    	<section id="page-issuer" class="page data-page product-detail">
	        <form action="admin.htm">
	            <nav class="tool-bar">
	                <div>
	                    <div class="arrange-bar">
	                        <div class="arrange-control">
	                            <label>SẮP XẾP</label>
	                            <ion-icon name="caret-down-outline"></ion-icon>
	                        </div>
	                        <div class="arrange-choices">
	                            <button>Trạng thái</button>
	                        </div>
	                    </div>
	                    <div class="search-bar">
	                        <label for="search"><ion-icon name="search"></ion-icon></label>
	                        <input id="search" type="search" name="keyword" />
	                    </div>
	                </div>
	                <div>
	                    <button name="insert" class="modal-button">THÊM MỚI</button>
	                </div>
	            </nav>
	            <table>
	                <tr>
	                    <th>ID</th>
	                    <th>Tên nhà phát hành</th>
	                    <th>Thao tác</th>
	                </tr>
	                <c:forEach items="${nhaPhatHanhs}" var="nhaPhatHanh">
	                <tr>
	                     <td>${nhaPhatHanh.maNPH}</td>
	                     <td>${nhaPhatHanh.tenNPH}</td>
	                     <td>
	                    	 <button name="xemnhaphathanh" value="${nhaPhatHanh.maNPH}" class="button icon-button update">
	               			 	<ion-icon name="cloud-upload"></ion-icon>
	               			 </button>
	               			 <button name="xoanhaphathanh" value="${nhaPhatHanh.maNPH}" class="button icon-button delete">
	               				<ion-icon name="trash"></ion-icon>
	               			 </button>
	                     </td>
	                </tr>
	                </c:forEach>
	            </table>
	        </form>
        </section>
    	<section id="page-customer" class="page data-page">
	        <form action="admin.htm">
	            <nav class="tool-bar">
	                <div>
	                    <div class="arrange-bar">
	                        <div class="arrange-control">
	                            <label>SẮP XẾP</label>
	                            <ion-icon name="caret-down-outline"></ion-icon>
	                        </div>
	                        <div class="arrange-choices">
	                            <button>Họ</button>
	                            <button>Tên</button>
	                        </div>
	                    </div>
	                    <div class="search-bar">
	                        <label for="search"><ion-icon name="search"></ion-icon></label>
	                        <input id="search" type="search" name="keyword" />
	                    </div>
	                </div>
	                <div>

	                </div>
	            </nav>
	            <table>
	                <tr>
	                    <th>ID</th>
	                    <th>Họ</th>
	                    <th>Tên</th>
	                    <th>Giới tính</th>
	                    <th>Ngày sinh</th>
	                    <th>Trạng thái</th>
	                    <th>Thao tác</th>
	                </tr>
	                <c:forEach var="e" items="${khachHangs}">
					<tr>
						<td>${e.maKhachHang}</td>
						<td>${e.ho}</td>
						<td>${e.ten}</td>
						<td>${e.gioiTinh == null ? "" : (e.gioiTinh ? "Nam" : "Nữ")}</td>
						<td>${e.ngaySinh}</td>
						<td>${e.taiKhoan.trangThai ? "Hoạt động" : "Đã khóa"}</td>
						<td>
	                        <button name="xemkhachhang" value="${e.maKhachHang}" class="button icon-button view">
	                            <ion-icon name="eye"></ion-icon>
	                        </button>
	                        <button name="khoakhachhang" value="${e.maKhachHang}" class="button icon-button block">
	                            <ion-icon name="lock-closed"></ion-icon>
	                        </button>
	                  	</td>
					  </tr>
	                  </c:forEach>
	            </table>
	        </form>
        </section>
        <section id="page-product" class="page data-page">
	        <form action="admin/sach.htm">
	            <nav class="tool-bar">
	                <div>
	                    <div class="arrange-bar">
	                        <div class="arrange-control">
	                            <label>SẮP XẾP</label>
	                            <ion-icon name="caret-down-outline"></ion-icon>
	                        </div>
	                        <div class="arrange-choices">
	                            <button>Tên sản phẩm</button>
	                        </div>
	                    </div>
	                    <div class="search-bar">
	                        <label for="search"><ion-icon name="search"></ion-icon></label>
	                        <input id="search" type="search" name="keyword" />
	                    </div>
	                </div>
	                <div>
	                    <button name="insert" class="modal-button">THÊM MỚI</button>
	                </div>
	            </nav>
	            <table>
	                <tr>
	                    <th>ID</th>
	                    <th>Hình ảnh</th>
	                    <th>Tên sách</th>
	                    <th>Giá bán</th>
	                    <th>Khuyến mãi</th>
	                    <th>Số lượng</th>
	                    <th>Tình trạng</th>
	                    <th>Thao tác</th>
	                </tr>
	                <c:forEach var="e" items="${Sachs}">
					<tr>
	                     <td>${e.maSach}</td>
	                     <td style="max-width: 100%;"><img class="image-product" src="${e.anhDaiDien}" alt=""></td>
	                     <td>${e.tenSach}</td>
	                     <td>${e.giaBan} đ</td>
	                     <td>${e.tinhKhuyenMaiThucTeMotNgay()}</td>
	                     <td>${e.soLuongTon}</td>
	                     <td>
							<c:if test="${e.trangThai == 0}">Hết hàng</c:if>
							<c:if test="${e.trangThai == 1}">Còn hàng</c:if>
							<c:if test="${e.trangThai == 2}">Ngưng bán</c:if>
	                     </td>
	                     <td>
	                    	<button name="xemsanpham" value="${e.maSach}" class="button icon-button view">
	                            <ion-icon name="eye"></ion-icon>
	                        </button>
	                        <button name="delete" value="${e.maSach}" class="button icon-button block">
	                            <ion-icon name="trash"></ion-icon>
	                        </button>
	                        <button name="theloaicuasach" value="${e.maSach}" class="button icon-button update">
	                        	<ion-icon name="attach"></ion-icon>
	                    	</button>
	                     	<button name="tacgiacuasach" value="${e.maSach}" class="button icon-button update">
	                        	<ion-icon name="finger-print"></ion-icon>
	                     	</button>
	                     </td>
	                </tr>
	                </c:forEach>
	            </table>
	        </form>
        </section>
		<section id="page-sale" class="page data-page">
	        <form action="admin/khuyenmai.htm">
	            <nav class="tool-bar">
	                <div>
	                    <div class="arrange-bar">
	                        <div class="arrange-control">
	                            <label>SẮP XẾP</label>
	                            <ion-icon name="caret-down-outline"></ion-icon>
	                        </div>
	                        <div class="arrange-choices">
	                            <button>Phần trăm &#37;</button>
	                        </div>
	                    </div>
	                    <div class="search-bar">
	                        <label for="search"><ion-icon name="search"></ion-icon></label>
	                        <input id="search" type="search" name="keyword" />
	                    </div>
	                </div>
	                <div>
	                    <button name="insert" class="modal-button">THÊM MỚI</button>
	                </div>
	            </nav>
	            <table>
	                <tr>
	                	<th>ID</th>
	                    <th>Ngày bắt đầu</th>
	                    <th>Ngày kết thúc</th>
	                    <th>Mức khuyến mãi</th>
	                    <th>Thao tác</th>
	                </tr>
	                <c:forEach var="e" items="${khuyenMais}">
					<tr>
	                     <td>${e.maKhuyenMai}</td>
	                     <td>${e.ngayBatDau}</td>
	                     <td>${e.ngayKetThuc}</td>
	                     <td>${e.phanTram} &#37;</td>
	                     <td>
	                         <button name="view" value="${e.maKhuyenMai}" class="button icon-button view auxiliary-button">
	                             <ion-icon name="eye"></ion-icon>
	                         </button>
	                         <button name="update" value="${e.maKhuyenMai}" class="button icon-button update">
	                              <ion-icon name="cloud-upload"></ion-icon>
	                         </button>
	                         <button name="deleteKhuyenMai" value="${e.maKhuyenMai}" class="button icon-button delete">
	                              <ion-icon name="trash"></ion-icon>
	                         </button>
	                     </td>
					</tr>
	                </c:forEach>
	            </table>
	        </form>
        </section>
    	<section id="page-view-sale" class="page data-page">
	        <form action="admin/khuyenmai.htm">
	            <div id="sale-information">
	                <div id="actions">
	                    <button name="back"><ion-icon name="log-out-outline"></ion-icon></button>
	                    <button name="listAdd" value="${khuyenMaiUpdate.maKhuyenMai}" class="button text-button insert">
	                        <ion-icon name="add-circle"></ion-icon>
	                        <p>Thêm mới</p>
	                    </button>
	                    <button name="delete" value="${khuyenMaiUpdate.maKhuyenMai}" class="button text-button delete">
	                        <ion-icon name="trash"></ion-icon>
	                        <p>Loại bỏ</p>
	                    </button>
	                </div>
	                <div id="detail-information">
	                    <div id="percentage-information">
	                        <div style="width: 30.5%;">
	                            <label for="">ID&#58;</label>
	                            <p>${khuyenMaiUpdate.maKhuyenMai}</p>
	                        </div>
	                        <div>
	                            <label for="">Mức khuyến mãi&#58;</label>
	                            <p>${khuyenMaiUpdate.phanTram}&#37;</p>
	                        </div>
	                    </div>
	                    <div id="date-information">
	                        <ion-icon name="calendar-sharp"></ion-icon>
	                        <p>${khuyenMaiUpdate.formatDate(khuyenMaiUpdate.ngayBatDau)}</p>
	                        <ion-icon name="play-sharp" style="width: 10%;"></ion-icon>
	                        <p>${khuyenMaiUpdate.formatDate(khuyenMaiUpdate.ngayKetThuc)}</p>
	                    </div>
	                </div>
	            </div>
	            <div id="applied-products">
	                <label class="bookmark">
	                    <ion-icon name="bookmark"></ion-icon>
	                    <span>Danh sách sản phẩm được áp dụng&#58;</span>
	                </label>
	                <table>
	                    <tr>
	                        <th><input type="checkbox" name="general" id="general"></th>
	                        <th>ID</th>
	                        <th>Tên sản phẩm</th>
	                    </tr>
	                   	<c:forEach var="e" items="${khuyenMaiUpdate.dsSach}">
	 				    <tr>
	                         <td><input type="checkbox" name="listID[]" value="${e.maSach}" id=""></td>
	                         <td>${e.maSach}</td>
	                         <td>${e.tenSach}</td>
	                   	</tr>
	                	</c:forEach>
	                </table>
	            </div>
	        </form>
        </section>
		<section id="page-view-genre" class="page data-page">
	        <form action="admin/sach.htm" method="POST">
	        <input value="${sachUpdate.maSach}" name="id" style="display: none;">
	            <div id="sale-information">
	                <div id="actions">
	                    <button class="sup-product"><ion-icon name="log-out-outline"></ion-icon></button>
	                    <button name="themtheloaivaosach" class="button text-button insert">
	                        <ion-icon name="add-circle"></ion-icon>
	                        <p>Thêm mới</p>
	                    </button>
	                    <button name="xoatheloaikhoisach" class="button text-button delete">
	                        <ion-icon name="trash"></ion-icon>
	                        <p>Loại bỏ</p>
	                    </button>
	                </div>
	                <div class="detail-sup-product">
	                   	<label class="bookmark">
		                    <ion-icon name="bookmark"></ion-icon>
		                    <span>Danh sách thể loại của sách ${sachUpdate.tenSach}&#58;</span>
	                	</label>
	                 	<table>
	                    <tr>
	                         <th>Thể loại hiện có</th>
	                         <th>
	                         	<c:forEach items="${sachUpdate.dsTheLoai}" var="e">
	                         		<p>${e.tenTheLoai}</p>
	                         	</c:forEach>
	                         </th>
	                    </tr>                                                     
	                 	</table>
	                </div>
	            </div>
	            <div id="applied-products">
	                <label class="bookmark">
	                    <ion-icon name="bookmark"></ion-icon>
	                    <span>Danh sách thể loại:&#58;</span>
	                </label>
	                <table>
	                    <tr>
	                        <th><input type="checkbox" name="general" id="general"></th>
	                        <th>ID</th>
	                        <th>Tên thể loại</th>
	                    </tr>
						<c:forEach items="${theLoais}" var="a">
						<tr>
							<c:choose>
								<c:when test="${sachUpdate.checkTheLoai(a)}"><td><input type="checkbox" name="theLoai[]" value="${a.maTheLoai}" id="general" checked></td></c:when>
								<c:otherwise><td><input type="checkbox" name="theLoai[]" value="${a.maTheLoai}" id="general"></td></c:otherwise>
							</c:choose>
							<td>${a.maTheLoai}</td>
							<td>${a.tenTheLoai}</td>
						</tr>
						</c:forEach>
	                </table>
	            </div>
	        </form>
        </section>
		<section id="page-view-author" class="page data-page">
	        <form action="admin/sach.htm" method="POST">
	        	<input name="id" value="${sachUpdate.maSach}" style="display: none;">
	            <div id="sale-information">
	                <div id="actions">
	                    <button class="sup-product"><ion-icon name="log-out-outline"></ion-icon></button>
	                    <button name="themtacgiavaosach" class="button text-button insert ">
	                        <ion-icon name=add-circle></ion-icon>
	                        <p>Thêm mới</p>
	                    </button>
	                    <button name="xoatacgiakhoisach" class="button text-button delete">
	                        <ion-icon name="trash"></ion-icon>
	                        <p>Loại bỏ</p>
	                    </button>
	                </div>
	                <div class="detail-sup-product">
	                   	<label class="bookmark">
		                     <ion-icon name="bookmark"></ion-icon>
		                     <span>Danh sách thể loại của sách ${sachUpdate.tenSach}&#58;</span>
	                	</label>
	                 <table>
	                     <tr>
	                         <th>Tác giả hiện có</th>
	                         <th>
	                         	<c:forEach items="${sachUpdate.dsTacGia}" var="e">
	                         		<p>${e.tenTacGia}</p>
	                         	</c:forEach>
	                         </th>
	                     </tr>                                                     
	                 </table>
	                </div>
	            </div>
	            <div id="applied-products">
	                <label class="bookmark">
	                    <ion-icon name="bookmark"></ion-icon>
	                    <span>Danh sách tác giả:&#58;</span>
	                </label>
	                <table>
	                    <tr>
	                        <th><input type="checkbox" name="general" id="general"></th>
	                        <th>ID</th>
	                        <th>Tên tác giả</th>
	                    </tr>
						<c:forEach items="${tacGias}" var="a">
						<tr>
							<c:choose>
								<c:when test="${sachUpdate.checkTacGia(a)}"><td><input type="checkbox" name="tacGia[]" value="${a.maTacGia}" id="general" checked></td></c:when>
								<c:otherwise><td><input type="checkbox" name="tacGia[]" value="${a.maTacGia}" id="general"></td></c:otherwise>
							</c:choose>
							<td>${a.maTacGia}</td>
							<td>${a.tenTacGia}</td>
						</tr>				
						</c:forEach>
	                </table>
	            </div>
	        </form>
        </section>
    	<section id="page-import" class="page data-page">
        	<form action="admin/phieunhap.htm">
                    <nav class="tool-bar">
                        <div>
                            <div class="arrange-bar">
                                <div class="arrange-control">
                                    <label>SẮP XẾP</label>
                                    <ion-icon name="caret-down-outline"></ion-icon>
                                </div>
                                <div class="arrange-choices">
                                    <button>Trạng thái</button>
                                </div>
                            </div>
                            <div class="search-bar">
                                <label for="search"><ion-icon name="search"></ion-icon></label>
                                <input id="search" type="search" name="keyword"
                                    placeholder="Tìm kiếm theo tên chính sách" />
                            </div>
                        </div>
                        <div>                 
                            <button name="insertpn" class="modal-button">THÊM MỚI</button>
                        </div>
                    </nav>
                    <table>
                        <tr>
                            <th>ID</th>
                            <th>Ngày</th>
                            <th>Nhà cung cấp</th>
                            <th>Nhân viên</th>
                            <th>Thao tác</th>
                        </tr>
                        <c:forEach items="${phieuNhaps}" var = "e">
                          <tr>
                            <td>${e.maPhieuNhap}</td>
                            <td>${e.ngay}</td>
                            <td>${e.nhaCungCap}</td>
                            <td>${e.nhanVien.hoTenNhanVien()}</td>
                            <td>
                                <div class="action-choices">
                                    <button name="view" value="${e.maPhieuNhap}" class="button icon-button view">
                                        <ion-icon name="eye"></ion-icon>
                                    </button>
                                    <button name="delete" value="${e.maPhieuNhap}" class="button icon-button delete">
                                        <ion-icon name="trash"></ion-icon>
                                    </button>
                                </div>
                            </td>
                          </tr>
                        </c:forEach>
                    </table>
                </form>
        </section>
        <section id="page-view-import" class="page data-page">
	        <form action="admin/phieunhap.htm">
	            <div id="sale-information">
	                <div id="actions">
	                    <button name="back"><ion-icon name="log-out-outline"></ion-icon></button>
	                    <button name="listAdd" value="${phieuNhapUpdate.maPhieuNhap}" class="button text-button insert">
	                        <ion-icon name="add-circle"></ion-icon>
	                        <p>Thêm mới</p>
	                    </button>
	                    <button name="deleteCTPN" value="${phieuNhapUpdate.maPhieuNhap}" class="button text-button delete">
	                        <ion-icon name="trash"></ion-icon>
	                        <p>Loại bỏ</p>
	                    </button>
	                </div>
	                <div id="detail-information">
	                    <div id="percentage-information">
	                        <div style="width: 30.5%;">
	                            <label for="">ID&#58;</label>
	                            <p>${phieuNhapUpdate.maPhieuNhap}</p>
	                        </div>
	                        <div>
	                            <label for="">Nhân Viên Lập&#58;</label>
	                            <p>${phieuNhapUpdate.nhanVien.hoTenNhanVien()}</p>
	                        </div>
	                    </div>
	                </div>
	            </div>
	            <div id="applied-products">
	                <label class="bookmark">
	                    <ion-icon name="bookmark"></ion-icon>
	                    <span>Danh sách sản phẩm được áp dụng&#58;</span>
	                </label>
	                <table>
	                    <tr>
	                        <th><input type="checkbox" name="general" id="general"></th>
	                        <th>ID</th>
	                        <th>Tên sản phẩm</th>
	                        <th>Số lượng</th>
	                        <th>Đơn Giá</th>
	                    </tr>
	                   	<c:forEach var="e" items="${phieuNhapUpdate.dsChiTietPhieuNhap}">
	 				    <tr>
	                         <td><input type="checkbox" name="listID[]" value="${e.sach.maSach}" id=""></td>
	                         <td>${e.sach.maSach}</td>
	                         <td>${e.sach.tenSach}</td>
	                         <td>${e.soLuong}</td>
	                         <td>${e.giaGoc}</td>
	                   	</tr>
	                	</c:forEach>
	                </table>
	            </div>
	        </form>
        </section>
    	<section id="page-order" class="page data-page">
	        <form action="admin.htm" method="POST" >
	            <nav class="tool-bar">
	                <div>
	                    <div class="arrange-bar">
	                        <div class="arrange-control">
	                            <label>SẮP XẾP</label>
	                            <ion-icon name="caret-down-outline"></ion-icon>
	                        </div>
	                        <div class="arrange-choices">
	                            <button>Ngày lập</button>
	                        </div>
	                    </div>
	                    <div class="search-bar">
	                        <label for="search"><ion-icon name="search"></ion-icon></label>
	                        <input id="search" type="search" name="keyword" />
	                    </div>
	                </div>
	                <div>
						
	                </div>
	            </nav>
	            <table>
	                <tr>
	                    <th>ID</th>
	                    <th>Nhân viên duyệt</th>
	                    <th>Ngày lập</th>
	                    <th>Nhân viên giao</th>
	                    <th>Trạng thái</th>
	                    <th>Thao tác</th>
	                </tr>
	                <c:forEach var="e" items="${donHangs}">
					<tr>
	                     <td>${e.maDonHang}</td>
	                     <td>${e.getNhanVienDuyet().hoTen()}</td>
	                     <td>${e.ngay}</td>
	                     <c:choose>
	                     	<c:when test="${e.trangThai != 0}"><td>${e.getNhanVienGiao().hoTen()}</td></c:when>
	                     	<c:otherwise><td></td></c:otherwise>
	                     </c:choose>
	                     <td>${e.getTenTrangThai()}</td>
	                     <td>
	                     	<div class="action-choices">
                                <button name="view" value="${e.maDonHang}" class="button icon-button view">
                                    <ion-icon name="eye"></ion-icon>
                                </button>
                                <button name="insert" value="${e.maDonHang}" class="button icon-button insert"
                                	${e.trangThai != 0 ? 'disabled' : ''}>
	                                    <ion-icon name="checkmark-circle"></ion-icon>
	                            </button>
                                <button class="button icon-button delete" name="huydonhang" value="${e.maDonHang}"
                                	${e.trangThai != 0 ? 'disabled' : ''}>
                                    <ion-icon name="ban" ></ion-icon>
                                </button>
	                     	</div>
	                     </td>
	                </tr>
	                </c:forEach>
	            </table>
	        </form>
        </section>
        <button name="log-out" class="button icon-button" onclick="location.href='logout.htm'">
        	<ion-icon name="power"></ion-icon>
       	</button>
    </main>
	</section>
	<!-- POP-UP MODAL -->
	<section id="modals">
	<div id="add-staff" class="modal modal-staff close">
	    <div class="modal-title">
	        <p>TẠO TÀI KHOẢN</p>
	        <button name="close" class="button">
	            <ion-icon name="close-sharp"></ion-icon>
	        </button>
	    </div>
        <div class="modal-content">
            <form action="admin/taotaikhoan.htm" method="post">
                <div class="filling-form">
                    <div class="labels">
                        <label>Nhân Viên&#58;</label>
                        <label>Tên Tài Khoản&#58;</label>
                        <label>Mật Khẩu&#58;</label>
                        <label>Xác Minh Mật Khẩu&#58;</label>
                        <label>Role&#58;</label>
                    </div>
                    <div class="values">
                        <select name="maNhanVien" id="">
		                	<c:forEach items="${nhanVienChuaCoTK}" var="e">
		                	 	<option value="${e.maNhanVien}">${e.hoTenNhanVien()}</option>
		                	</c:forEach>
	               		</select>
                        <input type="text" name="tenTaiKhoan" required />
                        <input type="password" name="matKhau" required />
                        <input type="password" name="xacminh" required />
                        <select name="role" id="">
								<option value="0">ADMIN</option>
								<option value="1">SHIPPER</option>
	               		</select>
                    </div>
                </div>
                <button name="save" class="button text-button update">
                    <ion-icon name="cloud-upload"></ion-icon>
                    <p>TẠO</p>
                </button>
            </form>
        </div>
    </div>
    <div id="rePassword-staff" class="modal modal-staff close">
	    <div class="modal-title">
	        <p>Đổi Mật Khẩu</p>
	        <p>Chỉ có Thể Đổi Mật Khẩu Của Mình</p>
	        <button name="close" class="button">
	            <ion-icon name="close-sharp"></ion-icon>
	        </button>
	    </div>
        <div class="modal-content">
            <form action="admin/doimk.htm" method="post">
                <div class="filling-form">
                    <div class="labels">               
                        <label>Mật Khẩu&#58;</label>
                        <label>Xác Minh Mật Khẩu&#58;</label>                    
                    </div>
                    <div class="values">
                        <input type="password" name="matKhau" required />
                        <input type="password" name="xacminh" required />
                    </div>
                </div>
                <button name="save" class="button text-button update">
                    <ion-icon name="cloud-upload"></ion-icon>
                    <p>TẠO</p>
                </button>
            </form>
        </div>
    </div>
	<div id="insert-staff" class="modal modal-staff close">
	    <div class="modal-title">
	        <p>THÊM NHÂN VIÊN</p>
	        <button name="close" class="button">
	            <ion-icon name="close-sharp"></ion-icon>
	        </button>
	    </div>
        <div class="modal-content">
            <form action="admin/themnhanvien.htm" method="post" enctype="multipart/form-data">
                <div class="upload" style="gap: 10px;">
                    <img src="resources/image/DEFAULT_IMAGE.png" alt="" />
                    <input type="file" name="anhDaiDien" class="upload-image" accept=".png, .jpg" />
                </div>
                <div class="filling-form">
                    <div class="labels">
                        <label>Họ&#58;</label>
                        <label>Tên&#58;</label>
                        <label>Giới tính&#58;</label>
                        <label>Ngày sinh&#58;</label>
                        <label>Địa chỉ&#58;</label>
                        <label>Số điện thoại&#58;</label>
                        <label>CMND&#58;</label>
                        <label>Lương&#58;</label>
                    </div>
                    <div class="values">
                        <input type="text" name="ho" required />
                        <input type="text" name="ten" required />
                        <div style="flex-direction: row; gap: 25px; padding: 0;">
                            <div>
                                <input type="radio" name="gioiTinh" value="True" checked/>
                                <label>Nam</label>
                            </div>
                            <div>
                                <input type="radio" name="gioiTinh" value="False"/>
                                <label>Nữ</label>
                            </div>
                        </div>
                        <input type="date" name="ngaySinh" />
                        <input type="text" name="diaChi" />
                        <input type="text" name="sDT" required />
                        <input type="text" name="cmnd" required />
                        <input type="number" name="luong" required />
                    </div>
                </div>
                <button name="save" class="button text-button update">
                    <ion-icon name="cloud-upload"></ion-icon>
                    <p>Lưu</p>
                </button>
            </form>
        </div>
    </div>
	<div id="update-staff" class="modal modal-staff close">
	    <div class="modal-title">
	        <p>SỬA NHÂN VIÊN</p>
	        <button name="close" class="button">
	            <ion-icon name="close-sharp"></ion-icon>
	        </button>
	    </div>
        <div class="modal-content">
            <form action="admin/suanhanvien.htm" method="post" enctype="multipart/form-data">
            	<input type="text" name="id" value="${nhanVien.maNhanVien}" style="display: none;" />
                <div class="upload" style="gap: 10px;">
                    <img src='${nhanVien.anhDaiDien == null ? "resources/image/DEFAULT_IMAGE.png" : nhanVien.anhDaiDien}' alt="" />
                    <input type="file" name="anhDaiDien" class="upload-image" accept=".png, .jpg" />
                </div>
                <div class="filling-form">
                    <div class="labels">
                        <label>Họ&#58;</label>
                        <label>Tên&#58;</label>
                        <label>Giới tính&#58;</label>
                        <label>Ngày sinh&#58;</label>
                        <label>Địa chỉ&#58;</label>
                        <label>Số điện thoại&#58;</label>
                        <label>CMND&#58;</label>
                        <label>Lương&#58;</label>
                        <label>Đã nghỉ&#58;</label>
                    </div>
                    <div class="values">
                        <input type="text" name="ho" value="${nhanVien.ho}" required />
                        <input type="text" name="ten" value="${nhanVien.ten}" required />
                        <div style="flex-direction: row; gap: 25px; padding: 0;">
                            <div>
                                <input type="radio" name="gioiTinh" value="True" ${nhanVien.gioiTinh ? 'checked' : ''} />
                                <label>Nam</label>
                            </div>
                            <div>
                                <input type="radio" name="gioiTinh" value="False" ${!nhanVien.gioiTinh ? 'checked' : ''} />
                                <label>Nữ</label>
                            </div>
                        </div>
                        <input type="date" name="ngaySinh" value="${nhanVien.ngaySinh}" />
                        <input type="text" name="diaChi" value="${nhanVien.diaChi}" />
                        <input type="text" name="sDT" value="${nhanVien.sDT}" required />
                        <input type="text" name="cmnd" value="${nhanVien.cmnd}" required />
                        <input type="number" name="luong" value="${nhanVien.luong}" required />
                        <select name="trangThaiXoa">
                        	<option value="False" ${!nhanVien.trangThaiXoa ? 'selected' : ''}>Hoạt động</option>
                        	<option value="True" ${nhanVien.trangThaiXoa ? 'selected' : ''}>Đã nghỉ</option>
                        </select>
                    </div>
                </div>
                <button name="save" class="button text-button update">
                    <ion-icon name="cloud-upload"></ion-icon>
                    <p>Lưu</p>
                </button>
            </form>
        </div>
    </div>
	<div class="modal-inner close" id="insert-category">
	    <div class="modal-header">
	        <h2>THÊM DANH MỤC</h2>
	        <button name="close" class="button">
	            <ion-icon name="close-sharp"></ion-icon>
	        </button>
	    </div>
	    <form action="admin/themdanhmuc.htm" method="PUT">
	        <div class="modal-body">
	            <div>
	                <p>Tên danh mục:</p>
	                <input name="tenDanhMuc" type="text">
	            </div>
	        </div>
	        <div class="modal-footer">
	            <button>Thêm</button>
	        </div>
	    </form>
    </div>
    <div class="modal-inner close" id="update-category">
	    <div class="modal-header">
	        <h2>SỬA DANH MỤC</h2>
	        <button name="close" class="button">
	            <ion-icon name="close-sharp"></ion-icon>
	        </button>
	    </div>
	    <form action="admin/suadanhmuc.htm" method="PUT">
	    	<input style="display: none" name="id" value="${danhMucUpdate.maDanhMuc}">
	        <div class="modal-body">
	            <div>
	                <p>Tên danh mục:</p>
	                <input name="tenDanhMuc" type="text" value="${danhMucUpdate.tenDanhMuc}">
	            </div>
	        </div>
	        <div class="modal-footer">
	            <button>Sửa</button>
	        </div>
	    </form>
    </div>
	<div class="modal-inner close" id="insert-genre">
	    <div class="modal-header">
	        <h2>THÊM THỂ LOẠI</h2>
	        <button name="close" class="button">
	            <ion-icon name="close-sharp"></ion-icon>
	        </button>
	    </div>
	    <form action="admin/themtheloai.htm" method="PUT">
	        <div class="modal-body">
	            <div>
	                <p>Tên thể loại:</p>
	                <input name = "tenTheLoai" type="text">
	            </div>
	            <div>
	            	<p>Danh mục:</p>
	                <select name="maDanhMuc" id="">
	                	<c:forEach items="${danhMucs}" var="danhMuc">
	                	 	<option value="${danhMuc.maDanhMuc}">${danhMuc.tenDanhMuc}</option>
	                	</c:forEach>
	                </select>
	            </div>
	        </div>
	        <div class="modal-footer">
	            <button>Thêm</button>
	        </div>
	    </form>
	</div>
	<div class="modal-inner close" id="update-genre">
	    <div class="modal-header">
	        <h2>SỬA THỂ LOẠI</h2>
	        <button name="close" class="button">
	            <ion-icon name="close-sharp"></ion-icon>
	        </button>
	    </div>
        <form action="admin/suatheloai.htm" method="PUT">
        	<input style="display: none" name = "id" value="${theLoaiUpdate.maTheLoai}">
            <div class="modal-body">
	            <div>
	                <p>Tên thể loại</p>
	                <input name ="tenTheLoai" type="text" value = "${theLoaiUpdate.tenTheLoai}">
	            </div>
                <div>
                    <p>Danh mục</p>
                    <select name="maDanhMuc" id="" value ="${theLoaiUpdate.danhMuc.tenDanhMuc}">
                    	<c:forEach items="${danhMucs}" var = "danhMuc">
                    		<c:if test="${danhMuc.maDanhMuc == theLoaiUpdate.danhMuc.maDanhMuc}">
                    			<option value="${danhMuc.maDanhMuc}" selected>${danhMuc.tenDanhMuc}</option>
                    		</c:if>
                    		<c:if test="${danhMuc.maDanhMuc != theLoaiUpdate.danhMuc.maDanhMuc}">
                    			<option value="${danhMuc.maDanhMuc}">${danhMuc.tenDanhMuc}</option>
                    		</c:if>
                    	</c:forEach>
                    </select>
                </div>
            </div>
            <div class="modal-footer">
                <button>Sửa</button>
            </div>
        </form>
    </div>
	<div class="modal-inner close" id="insert-author">
	    <div class="modal-header">
	        <h2>THÊM TÁC GIẢ</h2>
	        <button name="close" class="button">
	            <ion-icon name="close-sharp"></ion-icon>
	        </button>
	    </div>
	    <form action="admin/themtacgia.htm" method="POST">
	        <div class="modal-body">
	            <div>
	                <p>Tên tác giả:</p>
	                <input name="tenTacGia" type="text">
	            </div>
	        </div>
	        <div class="modal-footer">
	            <button>Thêm</button>
	        </div>
	    </form>
    </div>
	<div class="modal-inner close" id="update-author">
	    <div class="modal-header">
	         <h2>SỬA TÁC GIẢ</h2>
	         <button name="close" class="button">
	             <ion-icon name="close-sharp"></ion-icon>
	         </button>
	    </div>
	    <form action="admin/suatacgia.htm" method="POST">
	    	<input name="id" style="display: none" value="${tacgiaUpdate.maTacGia}">
	        <div class="modal-body">
	            <div>
	                <p>Tên tác giả:</p>
	                
	                <input name="tenTacGia" type="text" value="${tacgiaUpdate.tenTacGia}">
	            </div>
	        </div>
	        <div class="modal-footer">
	            <button>Sửa</button>
	        </div>
	    </form>
    </div>
	<div class="modal-inner close" id="insert-publisher">
	    <div class="modal-header">
	        <h2>THÊM NHÀ XUẤT BẢN</h2>
	        <button name="close" class="button">
	            <ion-icon name="close-sharp"></ion-icon>
	        </button>
	    </div>
	    <form action="admin/themnhaxuatban.htm" method="PUT">
	        <div class="modal-body">
	            <div>
	                <p>Nhà xuất bản:</p>
	                <input name="tenNhaXuatBan" type="text">
	            </div>
	        </div>
	        <div class="modal-footer">
	            <button>Thêm</button>
	        </div>
	    </form>
    </div>
    <div class="modal-inner close" id="update-publisher">
	    <div class="modal-header">
	        <h2>SỬA NHÀ XUẤT BẢN</h2>
	        <button name="close" class="button">
	            <ion-icon name="close-sharp"></ion-icon>
	        </button>
	    </div>
	    <form action="admin/suanhaxuatban.htm" method="PUT">
	    	<input style="display: none" name="id" value="${nhaXuatBanUpdate.maNXB}">
	        <div class="modal-body">
	            <div>
	                <p>Nhà xuất bản:</p>
	                <input name="tenNhaXuatBan" type="text" value="${nhaXuatBanUpdate.tenNXB}">
	            </div>
	        </div>
	        <div class="modal-footer">
	            <button>Sửa</button>
	        </div>
	    </form>
    </div>
	<div class="modal-inner close" id="insert-issuer">
	    <div class="modal-header">
	        <h2>THÊM NHÀ PHÁT HÀNH</h2>
	        <button name="close" class="button">
	            <ion-icon name="close-sharp"></ion-icon>
	        </button>
	    </div>
	    <form action="admin/themnhaphathanh.htm" method="POST">
	        <div class="modal-body">
	            <div>
	                <p>Nhà phát hành:</p>
	                <input name="tenNhaPhatHanh" type="text">
	            </div>
	        </div>
	        <div class="modal-footer">
	            <button>Thêm</button>
	        </div>
	    </form>
    </div>
    <div class="modal-inner close" id="update-issuer">
	    <div class="modal-header">
	        <h2>SỬA NHÀ PHÁT HÀNH</h2>
	        <button name="close" class="button">
	            <ion-icon name="close-sharp"></ion-icon>
	        </button>
	    </div>
	    <form action="admin/suanhaphathanh.htm" method="PUT">
	        <div class="modal-body">
	        	<input name="maNhaPhatHanh" style="display: none" value="${nhaPhatHanhUpdate.maNPH}">
	            <div>
	                <p>Nhà phát hành:</p>
	                <input name="tenNhaPhatHanh" type="text" value="${nhaPhatHanhUpdate.tenNPH}">
	            </div>
	        </div>
	        <div class="modal-footer">
	            <button>Sửa</button>
	        </div>
	    </form>
    </div>
	<div id="view-customer" class="modal close">
	    <div class="modal-title">
	        <p>THÔNG TIN KHÁCH HÀNG</p>
	        <button name="close" class="button">
	            <ion-icon name="close-sharp"></ion-icon>
	        </button>
	    </div>
	    <div class="modal-content">
	        <form action="">
		        <img src='${khachHang.anhDaiDien == null ? "resources/image/DEFAULT_IMAGE.png" : khachHang.anhDaiDien}' alt="" />
		        <div class="filling-form">
		            <div class="labels">
		                <label>Họ&#58;</label>
		                <label>Tên&#58;</label>
		                <label>Giới tính&#58;</label>
		                <label>Ngày sinh&#58;</label>
		                <label>Email&#58;</label>
		                <label>Trạng thái&#58;</label>
		            </div>
		            <div class="values">
		                <input type="text" name="ho" value="${khachHang.ho}" readonly>
		                <input type="text" name="ten" value="${khachHang.ten}" readonly>
		                <div style="flex-direction: row; gap: 25px; padding: 0;">
		                    <div>
		                        <input type="radio" name="sex" id="male" 
		                        	${khachHang.gioiTinh == true ? 'checked' : ''} disabled>
		                        <label for="male">Nam</label>
		                    </div>
		                    <div>
		                        <input type="radio" name="sex" id="female" 
		                        	${khachHang.gioiTinh == false ? 'checked' : ''} disabled>
		                        <label for="female">Nữ</label>
		                    </div>
		                </div>
		                <input type="date" name="date" value="${khachHang.ngaySinh}" readonly>
		                <input type="email" name="email" value="${khachHang.email}" 
		                	style='color: ${khachHang.trangThaiEmail ? "green" : "red"};' readonly>
		                <select name="status" id="status" style="width: 50%;" disabled>
		                    <option ${khachHang.taiKhoan.trangThai ? 'selected' : ''}>Hoạt động</option>
		                    <option ${!khachHang.taiKhoan.trangThai ? 'selected' : ''}>Đã khóa</option>
		                </select>
		            </div>
		        </div>
	        </form>
	    </div>
   	</div>
	<div id="insert-product" class="modal modal-product close">
	    <div class="modal-title">
	        <p>THÊM SẢN PHẨM MỚI</p>
	        <button name="close" class="button">
	            <ion-icon name="close-sharp"></ion-icon>
	        </button>
	    </div>
	    <div class="modal-content">
	        <form action="admin/sach.htm" method="post" enctype="multipart/form-data">
	            <div class="first-information">
	                <div>
	                    <img src="resources/image/DEFAULT_PRODUCT.png" alt="" />
	                    <input type="file" name="anhDaiDien" class="upload-image" accept=".png, .jpg" value=""/>
	                </div>
	                <div class="filling-form">
	                    <div class="labels">
	                        <label for="name">Tên sách</label>
	                        <label for="author">Tác giả</label>
	                        <label for="issuer">Nhà phát hành</label>
	                        <label for="publisher">Nhà xuất bản</label>
	                        <label for="genre">Thể loại</label>
	                    </div>
	                    <div class="values">
	                        <input type="text" name="tenSach" id="name" value="">
	                        <select name="tacGia" id="author">
	                        	<c:forEach items="${tacGias}" var="e">
	                        		<option value="${e.maTacGia}">${e.tenTacGia}</option>
	                        	</c:forEach>
	                        </select>
	                        <select name="nhaPhatHanh" id="issuer">
	                        	<c:forEach items="${nhaPhatHanhs}" var="e">
	                        		<option value="${e.maNPH}">${e.tenNPH}</option>
	                        	</c:forEach>
	                        </select>
	                        <select name="nhaXuatBan" id="publisher">
	                        	<c:forEach items="${nhaXuatBans}" var="e">
	                        		<option value="${e.maNXB}">${e.tenNXB}</option>
	                        	</c:forEach>
	                        </select>
	                        <select name="theLoai" id="genre">
	                        	<c:forEach items="${theLoais}" var="e">
	                        		<option value="${e.maTheLoai}">${e.tenTheLoai}</option>
	                        	</c:forEach>
	                        </select>
	                    </div>
	                </div>
	            </div>
	            <div class="second-information">
	                <div class="filling-form">
	                    <div class="labels">
	                        <label for="">Số trang</label>
	                        <label for="">Kích thước</label>
	                        <label for="">Trọng lượng</label>
	                    </div>
	                    <div class="values">
	                        <input type="number" name="soTrang" id="" value="">
	                        <select name="kichThuoc" id="" >
	                        	<option value="0">Nhỏ</option>
	                        	<option value="1">Vừa</option>
	                        	<option value="2">Lớn</option>
	                        </select>
	                        <input type="number" name="trongLuong" id="" value="">
	                    </div>
	                </div>
	                <div class="filling-form" style="align-items: flex-start;">
	                    <div class="labels">     
	                        <label for="">Ngôn ngữ</label>
	                        <label for="">Hình thức</label>
	                    </div>
	                    <div class="values">                                       
							<input type="text" name="ngonNgu" id="" value="">
							<input type="text" name="hinhThuc" id="" value="">
	                    </div>
	                </div>
	            </div>
	            <div class="third-information">
	                <label for="description">Tóm tắt</label>
	                <textarea name="tomTat" id="description" cols="30" rows="10"></textarea>
	            </div>
	            <button name="insert" class="button text-button update">
	                <ion-icon name="cloud-upload" style="font-size: x-large;"></ion-icon>
	                <p>Lưu</p>
	            </button>
	        </form>
	    </div>
    </div>
    <div id="view-product" class="modal modal-product close">
	    <div class="modal-title">
	        <p>THÔNG TIN SẢN PHẨM</p>
	        <button name="close" class="button">
	            <ion-icon name="close-sharp"></ion-icon>
	        </button>
	    </div>
	    <div class="modal-content">
	        <form action="admin/suasanpham.htm" method="post" enctype="multipart/form-data">
	        <input name="id" value="${sachUpdate.maSach}" style="display: none;" >
	            <div class="first-information">
	                <div>
	                    <img src="${sachUpdate.anhDaiDien}" alt="" />
	                    <input type="file" name="anhDaiDien"  class="upload-image" accept=".png, .jpg" />
	                </div>
	                <div class="filling-form">
	                    <div class="labels">
	                        <label for="name">Tên sách</label>
	                        <label for="issuer">Nhà phát hành</label>
	                        <label for="publisher">Nhà xuất bản</label>
	                    </div>
	                    <div class="values">
	                        <input type="text" name="tenSach" id="name" value="${sachUpdate.tenSach}">
	                        
	                        <select name="nhaPhatHanh" id="issuer">
	                        	<c:forEach items="${nhaPhatHanhs}" var="e">
	                         	<c:if test="${sachUpdate.nhaPhatHanh.maNPH == e.maNPH}">
	                         		<option value="${e.maNPH}" selected>${e.tenNPH}</option>
	                         	</c:if>
	                         	<c:if test="${sachUpdate.nhaPhatHanh.maNPH != e.maNPH}">
	                         		<option value="${e.maNPH}" >${e.tenNPH}</option>
	                         	</c:if>
	                         </c:forEach>
	                        </select>
	                        <select name="nhaXuatBan" id="publisher">
	                        	<c:forEach items="${nhaXuatBans}" var="e">
	                         	<c:if test="${sachUpdate.nhaXuatBan.maNXB == e.maNXB}">
	                         		<option value="${e.maNXB}" selected>${e.tenNXB}</option>
	                         	</c:if>
	                         	<c:if test="${sachUpdate.nhaXuatBan.maNXB != e.maNXB}">
	                         		<option value="${e.maNXB}">${e.tenNXB}</option>
	                         	</c:if>
	                         </c:forEach>
	                        </select>
	                    </div>
	                </div>
	            </div>
	            <div class="second-information">
	                <div class="filling-form">
	                    <div class="labels">
	                        <label for="">Số trang</label>
	                        <label for="">Kích thước</label>
	                        <label for="">Trọng lượng</label>
	                        <label for="">Hình thức</label>
	                        <label for="">Ngôn ngữ</label>
	                    </div>
	                    <div class="values">
	                        <input type="number" name="soTrang" value="${sachUpdate.soTrang}" id="" >
	                        <select name="kichThuoc" id="">
	                        	<c:if test="${sachUpdate.kichThuoc == 0}"><option value="0" selected>Nhỏ</option></c:if>
	                        	<c:if test="${sachUpdate.kichThuoc != 0}"><option value="0">Nhỏ</option></c:if>
	                        	
	                        	<c:if test="${sachUpdate.kichThuoc == 1}"><option value="1" selected>Vừa</option></c:if>
	                        	<c:if test="${sachUpdate.kichThuoc != 1}"><option value="1">Vừa</option></c:if>
	                        	
	                        	<c:if test="${sachUpdate.kichThuoc == 2}"><option value="2" selected>Lớn</option></c:if>
	                        	<c:if test="${sachUpdate.kichThuoc != 2}"><option value="2">Lớn</option></c:if>
	                        </select>
	                        <input type="number" name="trongLuong" id="" value="${sachUpdate.trongLuong }">
	                        <input type="text" name="hinhThuc" value="${sachUpdate.hinhThuc}">
	                        <input type="text" name="ngonNgu" value="${sachUpdate.ngonNgu }" id="">
	                    </div>
	                </div>
	                <div class="filling-form">
	                    <div class="labels">
	                        <label for="">Tình trạng</label>
	                        <label for="">Giá bán</label>
	                        <label for="">Giá khuyến mãi</label>
	                        <label for="">Số lượng</label>
	                    </div>
	                    <div class="values">
	                        <select name="trangThai" id="">
	                        	<c:if test="${sachUpdate.trangThai == 0}"><option value="0" selected>Hết hàng</option></c:if>
	                        	<c:if test="${sachUpdate.trangThai != 0}"><option value="0">Hết hàng</option></c:if>
	                        	
	                        	<c:if test="${sachUpdate.trangThai == 1}"><option value="1" selected>Còn hàng</option></c:if>
	                        	<c:if test="${sachUpdate.trangThai != 1}"><option value="1">Còn hàng</option></c:if>
	                        	
	                        	<c:if test="${sachUpdate.trangThai == 2}"><option value="2" selected>Ngưng bán</option></c:if>
	                        	<c:if test="${sachUpdate.trangThai	 != 2}"><option value="2">Ngưng bán</option></c:if>
	                        </select>
	                        <input type="number" name="giaBan" value="${sachUpdate.giaBan}" id="">
	                        <input type="number" name="giaKhuyenMai" value="${sachUpdate.giaKhuyenMai}" id="" readonly>
	                        <input type="number" name="soLuong" value="${ sachUpdate.soLuongTon}" id="" readonly>
	                    </div>
	                </div>
	            </div>
	            <div class="third-information">
	                <label for="description">Tóm tắt</label>
	                <textarea name="tomTat" id="description" cols="30" rows="10">${sachUpdate.tomTat }</textarea>
	            </div>
	            <button name="update" class="button text-button update">
	                <ion-icon name="cloud-upload" style="font-size: x-large;"></ion-icon>
	                <p>Lưu</p>
	            </button>
	        </form>
	    </div>
    </div>
	<div id="insert-sale" class="modal modal-sale close">
	    <div class="modal-title">
	        <p>THÊM KHUYẾN MÃI MỚI</p>
	        <button name="close" class="button">
	            <ion-icon name="close-sharp"></ion-icon>
	        </button>
	    </div>
	    <div class="modal-content">
	        <form action="admin/khuyenmai.htm">
	            <div class="filling-form" style="min-height: 20vh;">
	                <div class="labels">
	                    <label for="date-from">Ngày bắt đầu&#58;</label>
	                    <label for="date-to">Ngày kết thúc&#58;</label>
	                    <label for="number">Mức khuyến mãi &#40;&#37;&#41;&#58;</label>
	                </div>
	                <div class="values">
	                    <input type="date" name="ngayBatDau" id="date-from" />
	                    <input type="date" name="ngayKetThuc" id="date-to" />
	                    <input type="number" name="phanTram" id="percentage" />
	                </div>
	            </div>
	            <button name="themkhuyenmai" class="button text-button insert">
	                <ion-icon name="add"></ion-icon>
	                <p>Thêm mới</p>
	            </button>
	        </form>
	    </div>
	</div>
	<div id="update-sale" class="modal modal-sale close">
	    <div class="modal-title">
	        <p>THÔNG TIN KHUYẾN MÃI</p>
	        <button name="close" class="button">
	            <ion-icon name="close-sharp"></ion-icon>
	        </button>
	    </div>
	    <div class="modal-content">
	        <form action="admin/suakhuyenmai.htm" method="POST">
	        	<input name="id" style="display: none" value="${khuyenMaiUpdate.maKhuyenMai}">
	            <div class="filling-form" style="min-height: 20vh;">
	                <div class="labels">
	                    <label for="date-from">Ngày bắt đầu&#58;</label>
	                    <label for="date-to">Ngày kết thúc&#58;</label>
	                    <label for="number">Mức khuyến mãi &#40;&#37;&#41;&#58;</label>
	                </div>
	                <div class="values">
	                    <input type="date" name="ngayBatDau" value="${khuyenMaiUpdate.formatDate(khuyenMaiUpdate.ngayBatDau)}" required />
	                    <input type="date" name="ngayKetThuc" value="${khuyenMaiUpdate.formatDate(khuyenMaiUpdate.ngayKetThuc)}" required />
	                    <input type="number" name="phanTram" value="${khuyenMaiUpdate.phanTram}" required />
	                </div>
	            </div>
	            <button name="insert" class="button text-button update">
	                <ion-icon name="cloud-upload"></ion-icon>
	                <p>Lưu</p>
	            </button>
	        </form>
	    </div>
    </div>
	<div id="insert-view-sale" class="modal close">
	    <div class="modal-title">
	        <p>THÊM SẢN PHẨM KHUYẾN MÃI MỚI</p>
	        <button name="close" class="button">
	            <ion-icon name="close-sharp"></ion-icon>
	        </button>
	    </div>
	    <div class="modal-content">
	        <form action="admin/khuyenmai.htm">
	            <div style="width: 95%;">
	                <label class="bookmark">
	                    <ion-icon name="bookmark"></ion-icon>
	                    <span>Danh sách sản phẩm chưa được áp dụng&#58;</span>
	                </label>
	                <table>
	                    <tr>
	                        <th><input type="checkbox" name="general" id="general"></th>
	                        <th>ID</th>
	                        <th>Tên sản phẩm</th>
	                    </tr>
	                    <c:forEach items="${listSachKM}" var = "sach">
	                    	 <tr>
	                         <td><input type="checkbox" name="listID[]" value="${sach.maSach}" id=""></td>
	                         <td>${sach.maSach}</td>
	                         <td>${sach.tenSach}</td>
	                    	</tr>
	                    </c:forEach>
	                </table>
	            </div>
	            <button name="addSach" value="${maKhuyenMai}" class="button text-button update">
	                <ion-icon name="cloud-upload"></ion-icon>
	                <p>Lưu</p>
	            </button>
	        </form>
	    </div>
    </div>
	<div id="insertpn-import" class="modal modal-import close">
		<div class="modal-title">
			<p>THÊM MỚI PHIẾU NHẬP</p>
			<button name="close" class="button">
				<ion-icon name="close-sharp"></ion-icon>
			</button>
		</div>
		<div class="modal-content">
			<form action="admin/phieunhap.htm">
				<div class="horizontal-display">
					<label>Nhà cung cấp&#58;</label>
					<input type="text" name="nhaCungCap"/>
				</div>
				<button name="insertpn" class="button text-button insert" style="width: 15%; margin-top: 5vh;">
					<ion-icon name="add"></ion-icon>
					<p>Thêm mới</p>
				</button>
			</form>
		</div>
    </div>
	<div id="view-import" class="modal modal-import close">
	    <div class="modal-title">
	        <p>THÔNG TIN PHIẾU NHẬP</p>
	        <button name="close" class="button">
	            <ion-icon name="close-sharp"></ion-icon>
	        </button>
	    </div>
	    <div class="modal-content">
	        <form action="">
	            <div class="filling-form">
	                <div class="labels">
	                    <label>Mã phiếu nhập&#58;</label>
	                    <label>Tên sách&#58;</label>
	                    <label>Số lượng&#58;</label>
	                    <label>Giá Nhập&#58;</label>
	                </div>
	                <div class="values">
	                    <input type="text" name="maDonHang" value="${CTPNupdate.phieuNhap.maPhieuNhap}" id="name" readonly>
	                    <input type="text" name="name" id="name" value="${CTPNupdate.sach.tenSach}" readonly>
	                    <input type="text" name="soLuong" id="date" value="${CTPNupdate.soLuong}" readonly>
	                    <input type="text" name="giaNhap" id="date" value="${CTPNupdate.giaGoc}" readonly>
	                </div>
	            </div>
	        </form>
	    </div>
    </div>
	<div id="insert-view-import" class="modal modal-import close">
	    <div class="modal-title">
	        <p>THÊM SẢN PHẨM VÀO PHIẾU NHẬP ${phieuNhapUpdate.maPhieuNhap}</p>
	        <button name="close" class="button">
	            <ion-icon name="close-sharp"></ion-icon>
	        </button>
	    </div>
	    <div class="modal-content">
	        <form action="admin/phieunhap.htm">
	            <div style="width: 95%;">
	                <label class="bookmark">
	                    <ion-icon name="bookmark"></ion-icon>
	                    <span>Danh sách sản phẩm chưa có trong phiếu nhập&#58;</span>
	                </label>
	          		<div class="horizontal-display">
                        <label>Sách&#58;</label>
                        <select name="maSach" id="status" style="width: 80%;">
                        	<c:forEach items="${listSachImport}" var = "e">
                        		<option value="${e.maSach}">${e.tenSach}</option>
                        	</c:forEach>
                        </select>
                    </div>
                     <div class="horizontal-display">
                        <label>Số lượng&#58;</label>
                        <input type="text" name="soLuong" />
                    </div>
                    <div class="horizontal-display">
                        <label>Đơn giá&#58;</label>
                        <input type="text" name="donGia" />
                    </div>                 
	            </div>
	            <button name="addSach" value="${phieuNhapUpdate.maPhieuNhap}" class="button text-button update">
	                <ion-icon name="cloud-upload"></ion-icon>
	                <p>Lưu</p>
	            </button>
	        </form>
	    </div>
    </div>
	<div id="view-order" class="modal close">
	    <div class="modal-title">
	        <p>THÔNG TIN ĐƠN HÀNG</p>
	        <button name="close" class="button">
	            <ion-icon name="close-sharp"></ion-icon>
	        </button>
	    </div>
	    <div class="modal-content">
	        <div>
	            <div id="order-information">
	                <div class="label-value">
	                    <div>
	                        <label>Mã đơn hàng&#58;</label>
	                        <label>Ngày đặt hàng&#58;</label>
	                    </div>
	                    <div>
	                        <p>${donHang.maDonHang}</p>
	                        <p>${donHang.ngay}</p>
	                    </div>
	                </div>
	                <div>
	                    <label>Trạng thái đơn hàng&#58;</label>
	                    <p>${donHang.getTenTrangThai()}<p>	
	                </div>
	            </div>
	            <hr />
	            <div id="address-information">
	                <label>Địa chỉ giao hàng</label>
	                <div>
	                    <p>${donHang.diaChiGiaoHang.khachHang.getHoTen()} (${donHang.getDiaChiGiaoHang().sdtNN})</p>
	                    <p>${donHang.getDiaChiGiaoHang().diaChi}
	                    </p>
	                </div>
	            </div>
	            <hr />
	            <div class="list" style="margin: 5px 0;">
	                <c:forEach items="${donHang.dsChiTietDonHang}" var="e">
	                	<div class="product">
	                    <img src="${e.getSach().anhDaiDien}" alt="" />
	                    <div>
	                        <div>
	                            <p>${e.getSach().tenSach}</p>
	                            <p>${e.soLuong}</p>
	                        </div>
	                        <p>${e.giaTien}</p>
	                    </div>
	                </div>
	                </c:forEach>
	            </div>
	            <hr />
	            <div id="payment-information" class="label-value">
	                <div>
	                    <label>Tổng tiền hàng&#58;</label>
	                    <label>Phí vận chuyển&#58;</label>
	                    <label>Thành tiền&#58;</label>
	                    <label>Phương thức thành toán&#58;</label>
	                </div>
	                <div style="text-align: right;">
                    	<p>${donHang.getTongTienHang()}</p>
                    	<p>${donHang.phiVanChuyen}</p>
                    	<p>${donHang.getTongTienHang() + donHang.phiVanChuyen}</p>                          
	                    <p>Thanh toán khi nhận hàng</p>
	                </div>
	            </div>
	        </div>
	    </div>
    </div>
	<div id="insert-order" class="modal modal-order close ">
	    <div class="modal-title">
	        <p>CHỌN NHÂN VIÊN GIAO HÀNG</p>
	        <button name="close" class="button">
	            <ion-icon name="close-sharp"></ion-icon>
	        </button>
	    </div>
	    <div class="modal-content">
	        <form action="admin/duyetdonhang.htm" >
	        <input name="id" value="${donHangDuyet.maDonHang}" style="display: none;">
	            <div class="filling-form" style="min-height: 20vh;">
	               	<select name="maNhanVien" >
	               		<c:forEach items="${nhanVienGiao}" var="e">
	               			<option value="${e.maNhanVien}">${e.hoTen()}</option>
	               		</c:forEach>
	               	</select>
	            </div>
	            <button name="insert" class="button text-button insert">
	                <ion-icon name="checkmark-circle"></ion-icon>
	                <p>Duyệt</p>
	            </button>
	        </form>
	    </div>
    </div>
	
	<div id="delete" class="modal close modal-delete" style="text-align: center;">
		<div class="modal-title">
	        <p>CẢNH BÁO</p>
	        <button name="close" class="button">
	            <ion-icon name="close-sharp"></ion-icon>
	        </button>
        </div>
            <div class="modal-content">
                <form action="admin/${act}.htm" method="post">
      				<input name="id" value ="${idDelete}" type="text" style="display: none">
      				<p style="margin-bottom: 15px; color: red; font-weight: bold;">${delete}</p>
                    <button class="button text-button update" style="margin: auto; width: 30%;">
                        <ion-icon name="cloud-upload"></ion-icon>
                        <p>Xóa</p>
                    </button>
                </form>
            </div>
		</div>
	</section>
	<!-- IMPORT JAVASCRIPT -->
    <script src="resources/js/admin/admin.js"></script>
<!--     <script src="resources/js/admin/statistic.js"></script> -->
    <script src="resources/js/admin/modal.js"></script>
    <script src="resources/js/share/file_upload.js"></script>
    <script src="resources/js/share/icon.js"></script>
    <!-- IMPORT IONICONS -->
    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
    <!-- CONTROL PAGE-LOAD -->
    <script>
        let name = document.getElementById('flag').innerHTML
        let modal = document.getElementById('flag_modal').innerHTML
        let toaston = document.getElementById('toaston').innerHTML
        if (name == "") name = "page-staff"
        
        document.getElementById(name).classList.add('open')
        document.getElementById(name.replace('page', 'control')).click()
        
        if(!(modal == "")) {
        	overallElement.classList.toggle('inactive')
        	document.getElementById(modal).classList.remove('close')
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