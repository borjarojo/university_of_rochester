// This script depends on jQuery (jQuery must be installed before this runs)
// This script assumes you created some CSS to style the ".current" class

// Step 1: grab the current "location" (URL) of this webpage and put it in a variable
var currentPage=document.location.href;

// Step 2: check each anchor in the navigation element...
$("nav a").each(function() {

	// Step 2a: if there's a match...
	if (currentPage.indexOf(this.href) > -1) {

		// Step 2a-a: add the "current" class to the anchor
		$(this).addClass("current");
	}
});