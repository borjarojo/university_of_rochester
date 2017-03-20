//	****************************************************
// Function example: get price
//	****************************************************
function getPrice() {
	var price;
	price = prompt("Enter the price");
	price = price * 1;
	alert("You entered $ " + price.toFixed(2) );
}


//	****************************************************
//	Function example: change source of an image
//	****************************************************
//	Create a variable to hold information "outside" of
//	the function; this is done once
var origSrc = "";

function toggleDoc(x) {
	var e = document.getElementById(x);
	e.setAttribute("src","doctor.jpg");
}