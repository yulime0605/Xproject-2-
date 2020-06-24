<?php
    $con = mysqli_connect("localhost", "xproject2", "xproject1!", "xproject2");
    mysqli_query($con,'SET NAMES utf8');

    $managerID = $_POST["managerID"];
    $managerPassword = $_POST["managerPassword"];
    
    $statement = mysqli_prepare($con, "SELECT * FROM MANAGER WHERE managerID = ? AND managerPassword = ?");
    mysqli_stmt_bind_param($statement, "ss", $managerID, $managerPassword);
    mysqli_stmt_execute($statement);


    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $managerID, $managerPassword, $managerName, $managerNumber, $managerEmail);

    $response = array();
    $response["success"] = false;
 
    while(mysqli_stmt_fetch($statement)) {
        $response["success"] = true;
        $response["managerID"] = $managerID;
        $response["managerPassword"] = $managerPassword;
        $response["managerName"] = $managerName;
        $response["managerNumber"] = $managerNumber;  
        $response["managerEmail"] = $managerEmail;        
    }

    echo json_encode($response);



?>