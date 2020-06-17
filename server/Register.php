<?php 
    $con = mysqli_connect("localhost", "xproject2", "xproject1!", "xproject2");
    mysqli_query($con,'SET NAMES utf8');

    $userID = $_POST["userID"];
    $userPassword = $_POST["userPassword"];
    $userName = $_POST["userName"];
    $userNumber = $_POST["userNumber"];
    $userEmail = $_POST["userEmail"];

    $statement = mysqli_prepare($con, "INSERT INTO USER VALUES (?,?,?,?,?)");
    mysqli_stmt_bind_param($statement, "sssss", $userID, $userPassword, $userName, $userNumber, $userEmail);
    mysqli_stmt_execute($statement);


    $response = array();
    $response["success"] = true;
 
   
    echo json_encode($response);



?>