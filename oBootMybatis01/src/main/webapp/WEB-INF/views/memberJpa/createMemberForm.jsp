<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
	<h1>Mybatis JPA 회원등록</h1>
		<form action="${pageContext.request.contextPath }/memberJpa/save" method="post">
			ID : <input type="text" id="id" name="id" required="required"><p>
			이름 : <input type="text" id="name" name="name" placeholder="이름을 입력하세요"><p>
			PW : <input type="text" id="pw" name="password" placeholder="pw를 입력하세요">
				 <button type="submit">등록</button>
		</form>
		
		<a href="/members">JPA MEMBER LIST 조회</a>
	</div>
</body>
</html>