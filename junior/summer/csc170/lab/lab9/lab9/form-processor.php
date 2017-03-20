<?php

// set some variables - use YOUR email address as the From and the To
$emailFrom = "borja.rojo@gmail.com";
$emailTo = "borja.rojo@gmail.com";
$subject = "Lab 9 - HTML forms";

// grab the data being passed from the method="post" in the HTML form
// and hold each in a variable

// use the NAMEs you used in each HTML form element below...
$name = Trim(stripslashes($_POST['name'])); 
$email = Trim(stripslashes($_POST['email'])); 
$phone = Trim(stripslashes($_POST['tele'])); 

// from the Checkboxes...
$checkFirefox = $_POST['checkFirefox']; 
$checkChrome = $_POST['checkChrome']; 
$checkSafari = $_POST['checkSafari']; 
$checkIe = $_POST['checkIe']; 

// from the Radio buttons...
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
$body .= $tele;
$body .= "\n\n";

$body .= "Browsers used: \n";
$body .= $checkFirefox;
$body .= "\n";

$body .= $checkChrome;
$body .= "\n";

$body .= $checkSafari;
$body .= "\n";

$body .= $checkIe;
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