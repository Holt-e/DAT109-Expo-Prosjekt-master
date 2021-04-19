<%--
  ~ Copyright (c) 2019 Grim Thomas Hammerstad (182722@stud.hvl.no)
  ~ Ørjan Enes (180337@stud.hvl.no)
  ~ Joakim Johesan (182719@stud.hvl.no)
  ~ Eirik Alvestad (180339@stud.hvl.no)
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
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./style/materialize.min.css">

    <meta charset="UTF-8">
    <link rel="icon" type="image/png" href="./assets/favicon.png"/>
    <title>Controlpanel</title>
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
    <%-- ADD EXPO --%>
    <div class="col s12 m6 l3">
        <form method="post">
            <div class="card blue-grey darken-1">
                <div class="card-content white-text">
                    <span class="card-title">Add New Expo</span>


                    <div class="input-field">
                        <input type="hidden" name="type" value="add_expo">

                        <input id="expoText1" type="text" name="expo" value="">
                        <label for="expoText1">Expo Name</label>
                    </div>

                    <div class="input-field">
                        <input id="startDate" type="date" name="startDate" value="">
                        <label for="startDate" class="active">Start date</label>
                    </div>
                    <div class="input-field">
                        <input id="startTime" type="time" name="startTime" value="">
                        <label for="startTime" class="active">Start Time</label>
                    </div>
                    <div class="input-field">
                        <input id="endDate"  type="date" name="endDate" value="">
                        <label for="endDate" class="active">End date</label>
                    </div>
                    <div class="input-field">
                        <input id="endTime" type="time" name="endTime" value="">
                        <label for="endTime" class="active">End time</label>
                    </div>
                </div>
                <div class="errorTexst">
                    <p>${aexpoErrorMessage}</p>
                </div>
                <div class="card-action">
                    <button id="AddExpo" class="btn" type="submit">Add</button>
                </div>
            </div>
        </form>
    </div>
    <%-- DELETE EXPO --%>
    <div class="col s12 m6 l3">
        <form method="post">
            <div class="card blue-grey darken-1">
                <div class="card-content white-text">
                    <span class="card-title">Delete Expo</span>
                    <input type="hidden" name="type" value="delete_expo">
                    <select name="deleteExpoName">
                        <c:forEach items="${expos}" var="expo">
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
                </div>
                <div class="errorTexst">
                    <p>${expoErrorMessage}</p>
                </div>
                <div class="card-action">
                    <button id="DeleteExpo" class="btn" type="submit">Delete</button>
                </div>
            </div>
        </form>
    </div>
    <%-- ADD STAND --%>
    <div class="col s12 m6 l3">
        <div class="card blue-grey darken-1">
            <div class="card-content white-text">
                <span class="card-title">Add Stand to Expo</span>
                <form method="post">

                    <input type="hidden" name="type" value="add_stand">
                    <select name="expoName">
                        <c:forEach items="${expos}" var="expo">
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

                    <div class="input-field">
                        <input id="standText" type="text" name="standServlet" value="">
                        <label for="standText">Stand Name</label>
                    </div>
                    <div class="card-action">
                        <button id="AddStand" class="btn" type="submit">Add</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <%-- ADD JURY --%>
    <div class="col s12 m6 l3">
        <div class="card blue-grey darken-1">
            <div class="card-content white-text">
                <span class="card-title">Add Jury Member</span>
                <form method="post">
                    <div class="input-field">
                        <input type="hidden" name="type" value="add_jury">
                        <input id="JPnumber" type="tel" name="JPnumber" value="">
                        <label for="JPnumber">Phone numer</label>
                    </div>
                    <div class="errorTexst">
                        <p>${juryErrorMessage}</p>
                    </div>
                    <div class="card-action">
                        <button id="AddJury" class="btn" type="submit">Add</button>

                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


<script src="./scripts/materialize.min.js"></script>
<script type="text/javascript">
    M.AutoInit();

</script>

</body>
</html>