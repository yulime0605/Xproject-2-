<?php
    $con = mysqli_connect("localhost", "xproject2", "xproject1!", "xproject2");
    mysqli_query($con,'SET NAMES utf8');

    $userName = $_POST["userName"];
    $userNumber = $_POST["userNumber"];
    $bikeNumber = $_POST["bikeNumber"];

    $statement = mysqli_prepare($con, "SELECT Bike_Number FROM USING_BIKE WHERE Bike_Number = ?");
    mysqli_stmt_bind_param($statement, "s", $bikeNumber);
    mysqli_stmt_execute($statement);
    

    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $bikeNumber);
    $response = array();
    $response["success"] = false;
   
   
    while(!(mysqli_stmt_fetch($statement))) {
        $bikeNumber = $_POST["bikeNumber"];
        $statement = mysqli_prepare($con, "INSERT INTO USING_BIKE VALUES (?,?,?)");
        mysqli_stmt_bind_param($statement, "sss", strval($bikeNumber), $userName, strval($userNumber));
        mysqli_stmt_execute($statement);
        $response["success"] = true;
        $_SERVER['http://10.10.1.73/Boo.php?unlock']
        // if(isset($_GET['lock'])) {
        //     $ret = shell_exec('/home/pi/servo_lock 2>&1');
        //     echo $ret;
        // }
        // else if(isset($_GET['unlock'])) {
        //     $ret = shell_exec('/home/pi/servo_unlock 2>&1');
        //     echo $ret;
        break;
    }
    
   
        //$response["bikeNumber"] = $bikeNumber;
        // }
    // }
    echo json_encode($response);
?>