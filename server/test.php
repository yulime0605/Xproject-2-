<?php
    $con = mysqli_connect("localhost", "xproject2", "xproject1!", "xproject2");
    mysqli_query($con,'SET NAMES utf8');

    $userName = $_POST["userName"];
    $userNumber = $_POST["userNumber"];
    $bikeNumber = $_POST["bikeNumber"];

    // echo "$bikeNumber";
    // $response = array();
    // $response["bikeNumber"] = $bikeNumber;
    // $response["success"] = true;
    // echo json_encode($response);
    // if(empty($userName)) {
    //     $errMSG = "이름x";
    // }
    // if(empty($userNumber)) {
    //     $errMSG = "전화번호x";
    // }
    // if(empty($bikeNumber)) {
    //     $errMSG = "자전거번호x";
    // }

    // if(!$con) {
    //     $errMSG = "X";
    // }

    $statement = mysqli_prepare($con, "SELECT Bike_Number FROM USING_BIKE WHERE Bike_Number = ?");
    mysqli_stmt_bind_param($statement, "s", $bikeNumber);
    mysqli_stmt_execute($statement);
    

    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement);

    $response = array();
    $response["success"] = false;
 
    while(!(mysqli_stmt_fetch($statement))) {
        $statement = mysqli_prepare($con, "INSERT INTO USING_BIKE VALUES (?,?,?)");
        mysqli_stmt_bind_param($statement, "sss", $bikeNumber, $userName, $userNumber);
        mysqli_stmt_execute($statement);
        $response["success"] = true;
        break;
    }
    
   
        //$response["bikeNumber"] = $bikeNumber;
        // if(isset($_GET['lock'])) {
        //     $ret = shell_exec('/home/pi/servo_lock 2>&1');
        //     echo $ret;
        // }
        // else if(isset($_GET['unlock'])) {
        //     $ret = shell_exec('/home/pi/servo_unlock 2>&1');
        //     echo $ret;
        // }
    // }
    echo json_encode($response);
?>