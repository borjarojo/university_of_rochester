<?php

// set some variables - use YOUR email address as the From and the To
$emailFrom = "dsaltz@u.rochester.edu";
$emailTo = "dsaltz@u.rochester.edu";
$subject = "Lab 7 - HTML forms";

// grab the data being passed from the method="post" in the HTML form
// and hold each in a variable

// use the NAMEs you used in each HTML form element below...
$name = Trim(stripslashes($_POST['name'])); 
$email = Trim(stripslashes($_POST['email'])); 
$phone = Trim(stripslashes($_POST['phone'])); 

// from the Checkboxes...
$firefox = $_POST['firefox']; 
$chrome = $_POST['chrome']; 
$safari = $_POST['safari']; 
$Ie = $_POST['Ie']; 

// from the Radio buttons...s
$browserSelect = $_POST['browserSelect']; 

// from the textarea box...
$comments = Trim(stripslashes($_POST['comments'])); 

// prepare email body text
$body = ""; //initialize the variable

$body .= "Name: ";
$body .= $name;
$body .= "\n";

$body .= "Email: ";
$body .= $email;
$body .= "\n";

$body .= "Phone: ";
$body .= $phone;
$body .= "\n\n";

$body .= "Browsers used: \n";
$body .= $firefox;
$body .= "\n";

$body .= $chrome;
$body .= "\n";

$body .= $safari;
$body .= "\n";

$body .= $Ie;
$body .= "\n\n";

$body .= "Favorite browser: ";
$body .= $browserSelect;
$body .= "\n\n";

$body .= "Message: \n";
$body .= $comments;
$body .= "\n";

// send email 
mail($emailTo, $subject, $body, "From: <$emailFrom>");

// send the user to the thank you webpage
header("Location: contact-thanks.html");

?>