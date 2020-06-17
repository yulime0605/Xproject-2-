<?php
    $con = mysqli_connect("localhost", "xproject2", "password", "xproject1!");
    mysqli_query($con,'SET NAMES utf8');

    $bikeNumber = $_POST["bikeNumber"];
    
    $statement = mysqli_prepare($con, "SELECT * FROM USER WHERE bikeNumber = ?");
    mysqli_stmt_bind_param($statement, "s", $bikeNumber);
    mysqli_stmt_execute($statement);

    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $bikeNumber);

    $response = array();
    $response["success"] = false;
 
    while(mysqli_stmt_fetch($statement)) {
        $response["success"] = true;
        $response["bikeNumber"] = $bikeNumber;
    }

    echo json_encode($response);
?>