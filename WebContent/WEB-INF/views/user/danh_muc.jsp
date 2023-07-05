<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <base href="${pageContext.servletContext.contextPath}/">
    <title>Danh mục sản phẩm</title>
    <link rel="stylesheet" href="resources/css/share/display.css">
    <link rel="stylesheet" href="resources/css/user/danh_muc.css">
    <link rel="stylesheet" href="resources/css/user/decorator.css">
</head>

<body>
	<span id = "cagetory" style="display: none;">${cagetory}</span>
	<span id = "price" style="display: none;">${price}</span>
	<span id = "genner" style="display: none;">${genner}</span>
	<span id = "NXB" style="display: none;">${NXB}</span>
	<span id = "huong" style="display: none;">${huong}</span>
	<span id = "tieuchi" style="display: none;">${sortby}</span>
    <main>
        <section id="filter-bar">
        	<form action="danhmuc.htm">
	        	<div>
	        		<button>Thiết lập bộ lọc</button>
	        	</div>
	            <div id="categories">
	                <label>Danh mục</label>
	                <ul class="isOverflow">      
	                	<c:forEach items="${danhMucs}" var = "danhMuc">
	                		<li>
	                			<input type="radio" id="danhmuc${danhMuc.maDanhMuc}" name = "maDanhMuc" value="${danhMuc.maDanhMuc}">
	                        	<label for="danhmuc${danhMuc.maDanhMuc}">${danhMuc.tenDanhMuc}</label>
	                        </li>
	                	</c:forEach>
	                	<li><a href="danhmuc.htm?maDanhMuc" id = "allDanhMuc">Tất Cả</a></li>
	                </ul>
	                <p class="overflowControl" style="display: none;">Xem tất cả</p>
	            </div>
	            <div id="prices">
	                <label>Giá bán</label>
	                <div id="ranges" class="isOverflow">
	                    <div>
	                        <input type="radio" id="0" name = "gia" value="0">
	                        <label for="0">&lt; 50.000 đ</label>
	                    </div>
	                    <div>
	                        <input type="radio" id="1" name = "gia" value="1">
	                        <label for="1">50.000 đ &sim; 200.000 đ</label>
	                    </div>
	                    <div>
	                        <input type="radio" id="2" name = "gia" value="2">
	                        <label for="2">200.000 đ &sim; 500.000 đ</label>
	                    </div>
	                    <div>
	                        <input type="radio" id="3"name = "gia" value="3">
	                        <label for="3">&gt; 500.000 đ</label>
	                    </div>
	                </div>
	                <p class="overflowControl" style="display: none;">Xem tất cả</p>
	            </div>
	            <div id="genres">
	                <label>Thể loại</label>
	                <ul class="isOverflow">
	                <c:if test="${theLoais.size() gt 0}">
	                	<c:forEach items="${theLoais}" var = "theLoai">
	                		<li>
	                			<input type="radio" id="theloai${theLoai.maTheLoai}" name = "tenTheLoai" value="${theLoai.maTheLoai}">
	                        	<label for="theloai${theLoai.maTheLoai}">${theLoai.tenTheLoai }</label>
	                        </li>
	                	</c:forEach>
	                </c:if>
	                </ul>
	                <p class="overflowControl" style="display: none;">Xem tất cả</p>
	            </div>
	            <div id="publishers">
	                <label>Nhà xuất bản</label>
	                <ul class="isOverflow">
	                	<c:forEach items="${nhaXuatBans}" var = "nhaXuatBan">
	                	<li>
	                		<input type="radio" id="nhaxuatban${nhaXuatBan.maNXB}" name = "nhaXuatBan" value="${nhaXuatBan.maNXB}">
	                        <label for="nhaxuatban${nhaXuatBan.maNXB}">${nhaXuatBan.tenNXB}</label>
	                	</li>
	                	</c:forEach>
	                </ul>
	                <p class="overflowControl" style="display: none;">Xem tất cả</p>
	            </div>
        	</form>
        </section>
        <section id="products">
            <div id="tool-bar">
                <div id="arrange-bar">
                	<form action="danhmuc.htm?maDanhMuc=${maDM}&gia=${mucgia}&tenTheLoai=${maTL}&nhaXuatBan=${maNXB}&page=${page}">
                		 	<label for="arrange-choices">Sắp xếp:</label>
		                    <select name="arrange" id="arrange-choices">
		                        <option value="0">Sắp xếp theo bảng chữ cái ↑</option>
		                        <option value="1">Lượt mua ↑</option>
		                    </select>
		                    <button>Sắp Xếp</button>
                	</form>
                </div>
                <div id="search-bar">
                	<form action="danhmuc.htm?maDanhMuc=${maDM}&gia=${mucgia}&tenTheLoai=${maTL}&nhaXuatBan=${maNXB}&page=${page}&arrange=${sortby}">
                		  	<label for="search"><ion-icon name="search"></ion-icon></label>
                   			<input type="search" name="timKiem" id="search" placeholder="Tìm kiếm sản phẩm theo tên sản phẩm hoặc tên tác giả hoặc trong tóm tắt" value = "${timKiemvalue}"/>
                   		<button>Tìm Kiếm</button>
                	</form>
                </div>
            </div>
            <hr />
            <div id="display">
            	<c:forEach items="${sachs}" var = "e">
            		<div class="product">
            			<a href="chitietsanpham.htm?maSach=${e[0]}"><img class="image" src="${e[1]}" alt=""></a>
		                    <div class="information">
		                        <p class="name">${e[2]}</p>
		                        <c:if test="${e[3] != -1}">
									<span class="new-price">${e[3]} đ</span>
									<span class="old-price">${e[4]} đ</span>
								</c:if>
								<c:if test="${e[3] == -1}">
									<span class="new-price">${e[4]} đ</span>
								</c:if>
		                    </div>
                		</div>
            	</c:forEach>
            </div>
            <div id="pagination">
            	<form action="danhmuc.htm?maDanhMuc=${maDM}&gia=${mucgia}&tenTheLoai=${maTL}&nhaXuatBan=${maNXB}&arrange=${sortby}">
	                <a href="danhmuc.htm?maDanhMuc=${maDM}&gia=${mucgia}&tenTheLoai=${maTL}&nhaXuatBan=${maNXB}&page=${page-1}&arrange=${sortby}" id = "left"><ion-icon name="chevron-back"></ion-icon></a>
		                <input type="number" name="page" id="page-number" value="1" min="1" max="${pageSize}" />
		                <p>/ ${pageSize}</p>
		            <a href="danhmuc.htm?maDanhMuc=${maDM}&gia=${mucgia}&tenTheLoai=${maTL}&nhaXuatBan=${maNXB}&page=${page+1}&arrange=${sortby}" id = "right"><ion-icon name="chevron-forward"></ion-icon></a>
	                <button>go</button>            	
            	</form>
            </div>
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
    <script src="resources/js/user/category.js"></script>
    <script src="resources/js/share/icon.js"></script>
    <!-- IMPORT IONICONS -->
    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
    <script type="text/javascript">
    	let danhmuc = document.getElementById('cagetory').innerHTML;
    	let gia = document.getElementById('price').innerHTML;
    	let theloai = document.getElementById('genner').innerHTML;
    	let nxb = document.getElementById('NXB').innerHTML;
    	let tieuchi = document.getElementById('tieuchi').innerHTML;
    	if(danhmuc != ""){
    		document.getElementById(danhmuc).checked = "true";
    	}
    	if(gia != ""){
    		document.getElementById(gia).checked = "true";
    	}
    	if(theloai != ""){
    		document.getElementById(theloai).checked = "true";
    	}
    	if(nxb != ""){
    		document.getElementById(nxb).checked = "true";
    	}
    	let huong = document.getElementById('huong').innerHTML;
    	if(huong != ""){
    		if(huong == "leftright"){
    			document.getElementById('left').style.display = "none";  
    			document.getElementById('right').style.display = "none";   
    		}
    		else{
    			document.getElementById(huong).style.display = "none";    			
    		}
    	}
    	if(tieuchi != ""){
    		document.getElementById('arrange-choices').selectedIndex  = tieuchi;   
    	}
    </script>
</body>

</html>