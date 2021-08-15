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
<link rel="stylesheet" type="text/css" href="/party-sign-up-sheet/addedResourceHandler/styles/main.css"/>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Press+Start+2P&family=Sriracha&display=swap" rel="stylesheet">
</head>
<body>
  <header>
    <h4 class="title">TGIF! Let's party~&#127881;&#127867;&#127829;&#127918;</h4>
    <p>Please fill in the attendee information.</p>
    <section class="form">
      <form:form action="updateSignupSheet" modelAttribute="attendee" method="POST">
        <form:hidden path="id"/>
        <label>&#128947;Full name: </label>
        <form:input path="name"/>
        <br>
        <label>How Many People: </label>
        <form:input path="quantity"/>
        <br>
        <label>Stuff to Bring: </label>
        <form:input path="bringing"/>
        <br>
        <label>&#128947;Phone Number: </label>
        <form:input path="mobile"/>
        <br>
        <label>Email Address: </label>
        <form:input path="email"/>
        <br>
        <label>Additional Note: </label>
        <form:input path="note"/>
        <br>
        <input type="submit" value="Submit">
        <p>&#8212; or &#8212;</p>
        <a class="update-btn" href="/party-sign-up-sheet">Go Back</a>
      </form:form>
    </section>
  </header>
  <footer>&#169; Zack Cheng 2021 &#124; zacktcheng@gmail.com</footer>
</body>
</html>