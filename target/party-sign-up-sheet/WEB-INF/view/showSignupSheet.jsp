<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
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
    <p>Please sign-up for our upcoming party @ Finn Hill Gaming Club!</p>
    <section>
      <article>
        <p>Party Info:</p>
        <p>&#128198; Date: Every 2nd & 4th Friday of the month</p>
        <p>&#128342; Hours: 18:00pm ~ 23:00pm (PST)</p>
        <p>&#127968; Location: Finn Hill Gaming Club, 98034</p>
        <p>&#129333; Hosted by: Finn Hill Gaming Club</p>
      </article>
      <article>
        <p>Events:</p>
        <p>Potluck will start at 6pm! Soft drinks & Liquors.</p>
        <p>Nintendo Switch Mario Cart 8 competition.</p>
        <p>League of Legend team up pvp</p>
        <p>Pingpong & board games & more!</p>
      </article>
    </section>
  </header>
  <main>
    <section class="verification">
      <form action="/student-management/addAttendee">
        <input type="submit" value="Add New Attendee" class="add-button"/>
      </form>
      <c:set var="isAdmin" scope="session" value="${user.validInBinary}"/>
      <c:if test="${isAdmin == 0}">
        <form action="/student-management/adminSignin">
          <input type="submit" value="Sign-in as Admin to Delete Rows"/>
        </form>
      </c:if>
      <c:set var="isAdmin" scope="session" value="${user.validInBinary}"/>
      <c:if test="${isAdmin > 0}">
        <form action="/student-management/adminSignout">
          <input type="submit" value="Sign-out"/>
        </form>
      </c:if>
    </section>
    <section class="table">
      <div class="row">
        <span class="column-title">Attendee Name:</span>
        <span class="column-title">People in Total:</span>
        <span class="column-title">Stuff to Bring:</span>
        <span class="column-title">Phone Number:</span>
        <span class="column-title">Add'l. Note:</span>
      </div>
      <c:forEach var="attendee" items="${attendees}">
        <div class="row">
          <span class="column">${attendee.name}</span>
          <span class="column">${attendee.quantity}</span>
          <span class="column" style="line-height: 1;">${attendee.bringing}</span>
          <span class="column">${attendee.mobilePreview}</span>
          <span class="column" style="line-height: 1">${attendee.note}</span>
          <a class="update-btn" href="/student-management/verifyAttendee?AttendeeId=${attendee.id}&AttendeeMobile=${attendee.mobile}">
            Update
          </a>
          <c:set var="isAdmin" scope="session" value="${user.validInBinary}"/>
          <c:if test="${isAdmin > 0}">
            <a class="delete-btn" href="/student-management/deleteAttendee?AttendeeId=${attendee.id}" 
              onclick="if(!(confirm('Are you sure to delete this attendee?'))) return false"
              style="display: ${deleteHyperlinkDisplay};">Delete
            </a>
          </c:if>
        </div>
      </c:forEach>
    </section>
  </main>
  <footer>&#169; Zack Cheng 2021 &#124; zacktcheng@gmail.com</footer>
</body>
</html>