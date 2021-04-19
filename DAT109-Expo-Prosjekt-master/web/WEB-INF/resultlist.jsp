<%--
  ~ Copyright (c) 2019 Grim Thomas Hammerstad (182722@stud.hvl.no)
  ~ Ørjan Enes (180337@stud.hvl.no)
  ~ Joakim Johesan (182719@stud.hvl.no)
  ~ Eirik Alvestav (180339@stud.hvl.no)
  ~ Adrian Holthe (182714@stud.hvl.no)
  ~ Kjetil Hunshammer (182718@stud.hvl.no)
  ~
  ~ All Rights Reserved.
  ~
  ~ Unauthorized copying of this file via any medium is strictly prohibited
  ~ Proprietary and confidential.
  ~ Distribution for testing purposes is only permitted within the
  ~ Kronstad campus of the Western Norway University of
  ~ Applied Sciences (Høgskulen på Vestlandet, HVL) in Bergen, Norway.
  ~
  --%>

<%-- Created by IntelliJ IDEA. --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Temporary template need to fix later When standServlet expos and votes work-->
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./style/materialize.min.css">
    <link rel="icon" type="image/png" href="./assets/favicon.png"/>
    <title>Resultatliste</title>
</head>
<body>
<nav>
    <div class="nav-wrapper blue-grey">
        <a href="#" class="brand-logo center">Expo</a>
        <ul id="nav-mobile" class="left hide-on-med-and-down">
            <li><a href="controlpanel">Control Panel</a></li>
            <li><a href="resultlist">Resultlist</a></li>
            <li><a href="scan">Scan</a></li>
        </ul>
    </div>
</nav>



<div class="row">
    <div class="col s12 m6 l4 xl4">
        <div class="card blue-grey darken-1">
            <div class="card-content white-text">
                <span class="card-title">Resultatliste</span>

                <form method="post">
                    <select name="ExpoListItem" class="white-text">
                        <c:forEach items="${expoList}" var="expo">
                            <c:choose>
                                <c:when test="${expo.expoName == selectedExpo}">
                                    <option value="${expo.expoName}" selected>${expo.expoName}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${expo.expoName}">${expo.expoName}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>

                    <table class="white-text">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>Stand</th>
                            <th>Votes</th>
                            <th>Score</th>
                        </tr>
                        <c:forEach items="${stands}" var="stands">
                            <tr>
                                <td>${stands.standId}</td>
                                <td>${stands.standName}</td>
                                <td>${stands.votes.size()}</td>
                                <td>${stands.getScore()}</td>
                            </tr>

                        </c:forEach>
                        </thead>
                    </table>

                    <div class="card-action">
                        <button class="btn" type="submit">Update</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


</body>
<script src="./scripts/materialize.min.js"></script>
<script type="text/javascript">
    M.AutoInit();
</script>
</html>