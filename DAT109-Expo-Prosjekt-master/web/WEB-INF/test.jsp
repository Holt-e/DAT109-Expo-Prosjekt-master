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

<%--
  Created by IntelliJ IDEA.
  User: JoakimJohesan
  Date: 07/03/2019
  Time: 09:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Test</title>
    <link rel="stylesheet" href="style/style.css" type="text/css">
    <link rel="icon" type="image/png" href="./assets/favicon.png"/>
</head>

<body>
    <%-- Scanner --%>
    <input type=text class=qrcode-text/>
    <label class=qrcode-text-btn>
        <input type="file"
               accept="image/*"
               capture= "camera"
               onchange="openQRCamera(this);"
               tabindex=-1/>
    </label>

    <div id="container">
        <video class="videoFeed" autoplay="true" muted="true" id="videoElement"></video>
        <canvas class="videoFrame" id="videoFrame"></canvas>
    </div>


    <%-- Generator --%>
    <input id="text" type="text" value="" style="width:80%" /><br/>
    <div id="qrcode"></div>

    <script src="./scripts/VideoFrame.min.js"></script>
    <script src="./scripts/qr_packed.js"></script>
    <script src="./scripts/qrScan.js"></script>

    <script src="./scripts/qrcode.min.js"></script>
    <script src="./scripts/qrGenerator.js"></script>
    </body>
</html>