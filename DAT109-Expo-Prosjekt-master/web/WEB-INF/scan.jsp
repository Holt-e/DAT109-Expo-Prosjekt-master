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
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./style/materialize.min.css">
    <link rel="stylesheet" href="./style/style.css" type="text/css">
    <link rel="icon" type="image/png" href="./assets/favicon.png"/>
    <title>Vote</title>
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
    <div class="col s12 m6">
        <div class="card blue-grey darken-1 s12">
            <div class="card-content white-text">
                <span class="card-title">Scanner</span>
                <form action="#">
                    <div class="file-field input-field">
                        <div class="btn">
                            <span>Upload QR-Code Image</span>
                            <input type="file" id="file_upload" accept="image/*" capture="camera"
                                   onchange="openQRCamera(this);" tabindex=-1 fallback="false"/>
                        </div>
                        <div class="file-path-wrapper">
                            <input class="file-path validate" type="text">
                        </div>
                    </div>
                </form>

                <div id="container">
                    <video class="videoFeed" autoplay="true" muted="true" id="videoElement" width="100%"></video>
                    <canvas class="videoFrame" id="videoFrame"></canvas>
                </div>
                <form id="form" method="post">
                    <div class="input-field">
                        <input id="expo" type="text" name="expoName" readonly="readonly"/>
                        <label for="expo">Expo</label>
                    </div>
                    <div class="input-field">
                        <input id="stand" type="text" name="standName" readonly="readonly"/>
                        <label for="stand">Stand</label>
                    </div>
                    <span class="red-text">${loginMessage}</span>
                    <br>
                    <br>
                    <div class="rating">
                        <input type="radio" id="star5" name="rating" value="5"/><label for="star5"></label>
                        <input type="radio" id="star4" name="rating" value="4"/><label for="star4"></label>
                        <input type="radio" id="star3" name="rating" value="3"/><label for="star3"></label>
                        <input type="radio" id="star2" name="rating" value="2"/><label for="star2"></label>
                        <input type="radio" id="star1" name="rating" value="1"/><label for="star1"></label>
                    </div>
                    <br>
                    <div class="card-action">
                        <button id="Vote" class="waves-effect waves-light btn s12" type="submit">
                            Vote
                        </button>

                    </div>
                </form>
            </div>
        </div>
    </div>

</div>


<script src="./scripts/VideoFrame.min.js"></script>
<script src="./scripts/qr_packed.js"></script>
<script src="./scripts/qrScan.js"></script>
<script src="./scripts/qrcode.min.js"></script>
<script src="./scripts/materialize.min.js"></script>
<script type="text/javascript">
    M.AutoInit();
</script>
</body>
</html>