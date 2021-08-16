<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1">
<title>Party Sign-up Sheet</title>
<link rel="shortcut icon" href="/addedResourceHandler/src/icons8-party-hat-96.png"/>
<link rel="stylesheet" type="text/css" href="/addedResourceHandler/styles/main.css"/>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Press+Start+2P&family=Sriracha&display=swap" rel="stylesheet">
</head>
<body>
  <header>
    <h4 class="title">TGIF! Let's party~</h4>
    <p>Please verify your attendee mobile phone number.</p>
    <section class="form">
      <form:form action="verifyAttendeeMobile" modelAttribute="attendee" method="POST">
        <label>Enter attendee mobile phone number: </label>
        <br>
        <form:input path="mobile"/>
        <br>
        <input type="submit" value="Verify">
        <p>&#8212; or &#8212;</p>
        <a class="update-btn" href="/">Go Back</a>
      </form:form>
    </section>
  </header>
  <footer>&#169; Zack Cheng 2021 &#124; &#9993; zacktcheng@gmail.com</footer>
</body>
</html>