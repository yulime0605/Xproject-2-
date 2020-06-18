<?php


	if(isset($_GET['lock'])) {
    	$ret = shell_exec('/home/pi/servo_lock 2>&1');
    	echo $ret;
	}

	else if(isset($_GET['unlock'])) {
    	$ret = shell_exec('/home/pi/servo_unlock 2>&1');
    	echo $ret;
	}	

?>
