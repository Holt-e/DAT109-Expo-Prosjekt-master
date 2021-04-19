/*
 * Copyright (c) 2019 Grim Thomas Hammerstad (182722@stud.hvl.no)
 * Ørjan Enes (180337@stud.hvl.no)
 * Joakim Johesan (182719@stud.hvl.no)
 * Eirik Alvestav (180339@stud.hvl.no)
 * Adrian Holte (182714@stud.hvl.no)
 * Kjetil Hunshammer (182718@stud.hvl.no)
 *
 * All Rights Reserved.
 *
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential.
 * Distribution for testing purposes is only permitted within the
 * Kronstad campus of the Western Norway University of
 * Applied Sciences (Høgskulen på Vestlandet, HVL) in Bergen, Norway.
 *
 */

function openQRCamera(node) {
    let reader = new FileReader();
    reader.onload = function () {
        qrcode.callback = res => {
            if (res instanceof Error) {
                alert("No QR code found. Please make sure the QR code is within the camera's frame and try again.");

            } else {
                updateForm(res);
            }
        };

        qrcode.decode(reader.result);
    };
    reader.readAsDataURL(node.files[0]);
}

function qrFromDataURL(data) {
    return qrcode.decode(data);
}

let video = document.getElementById("videoElement");

let form = document.getElementById("form");
let standField = document.getElementById("stand");
let expoField = document.getElementById("expo");
let scoreField = document.getElementById("score");

if (navigator.mediaDevices.getUserMedia) {
    navigator.mediaDevices.getUserMedia({video: true})
        .then(function (stream) {
            video.srcObject = stream;
        })
        .catch(function (err0r) {
            console.log("Falling back to image upload.");
            document.getElementById("file_upload").setAttribute("fallback", "true");
        });
}

function updateForm(data) {
    let parts = data.split(";");

    let expo = parts[0];
    let stand = parts[1];
    let extra = parts[2] || "";

    expoField.value = expo;
    standField.value = stand;
    scoreField.value = "5";
}

let videoFrame = document.getElementById("videoFrame");
let videoHandler = VideoFrame({
    id: "videoElement",
    frameRate: 29.97,
    callback: frame => {
        qrcode.callback = res => {
            if (res instanceof Error) {
                return;
            }

            updateForm(res);
        };

        // Act like we care about battery life
        if (frame % 15 === 0) {
            updateVideoFrame(video);

            let data = getVideoFrame(video);
            let qrData = qrFromDataURL(data);

            qrcode.decode(qrData);
        }
    }
});

function updateVideoFrame(video) {
    videoFrame.width = video.videoWidth;
    videoFrame.height = video.videoHeight;

    let ctx = videoFrame.getContext("2d");

    ctx.drawImage(video, 0, 0);
}

function getVideoFrame() {
    return videoFrame.toDataURL();
}

videoHandler.listen("frame");