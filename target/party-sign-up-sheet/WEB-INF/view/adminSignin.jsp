<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1">
<title>Party Sign-up Sheet</title>
<link rel="stylesheet" type="text/css" href="/part-sign-up-sheet/addedResourceHandler/styles/main.css"/>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Press+Start+2P&family=Sriracha&display=swap" rel="stylesheet">
</head>
<body>
  <header>
    <h4 class="title">TGIF! Let's party~&#127881;&#127867;&#127829;&#127918;</h4>
    <p>Please enter the password to sign in.</p>
    <section class="form">
      <form:form action="validateAdminSignin" modelAttribute="user" method="POST">
        <label>Enter Admin Password: </label>
        <br>
        <form:input path="password"/>
        <br>
        <input type="submit" value="Submit">
        <p style="color: #ccccc6">&#8212; or &#8212;</p>
        <a class="update-btn" href="/student-management">Continue as Attendee</a>
      </form:form>
    </section>  
  </header>
  <footer>&#169; Zack Cheng 2021 &#124; zacktcheng@gmail.com</footer>
</body>
</html>