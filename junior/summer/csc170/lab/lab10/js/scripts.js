//	*******************************************************
//	Toggle element to hide/show
//	*******************************************************

// Step 1: what is the ID of the clickable element?
var toggler = "button";

// Step 1: what is the ID of the element to be toggled?
var toggled = "fig2";

// Step 3 and 4: Get a handle on the clickable element and create a variable to hold it
var toggle_button = document.getElementById(toggler);

// Step 5 and 6: Get a handle on the element to be toggled and create a variable to hold it
var toggle_element = document.getElementById(toggled);

// Step 7: Set the default state of the element to be toggled; either "inline" (showing) or "none" (hidden)
toggle_element.style.display = 'inline';

// Step 8: write the function to do the toggling (won't execute 'til called)
function toggleElement(id){ 

	// Step 8a: Create a variable and get a handle on the element to toggle (one step)
	var e = document.getElementById(id); 

	// Step 8b: if display:inline, it must be showing...
	if (e.style.display == 'inline') {

		// Step 8b-a: then hide it
		e.style.display = 'none'

	// Step 8c: else it must be hidden, so...
	} else {

		// Step 8c-a: then show it
		e.style.display = 'inline'
	}
}; 

// Step 9: Apply a click function to the clickable element
toggle_button.addEventListener("click", function(){

	// Step 9a: when the element is clicked, run the function
	toggleElement(toggled)

}, false);