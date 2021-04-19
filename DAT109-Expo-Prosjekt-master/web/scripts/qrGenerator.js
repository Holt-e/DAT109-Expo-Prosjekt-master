let qrcodegen = new QRCode("qrcode");

let elText = document.getElementById("text");

function makeCode () {
    qrcodegen.makeCode(elText.value);
}

elText.addEventListener("keydown", e => {
	if (e.keyCode === 13) {
		makeCode()
	}
});

   
