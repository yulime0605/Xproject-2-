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
    $response["rent_success"] = false;
    $query = "SELECT * FROM USING_BIKE"; // 테이블에서 레코드갯수 카운트
    $data = mysqli_query($con, $query);
    $cnt1 = mysqli_num_rows($data);
    if(!(mysqli_stmt_fetch($statement))) {
        $bikeNumber = $_POST["bikeNumber"];

        $statement = mysqli_prepare($con, "INSERT INTO USING_BIKE VALUES (?,?,?)");
        mysqli_stmt_bind_param($statement, "sss", strval($bikeNumber), $userName, strval($userNumber));
        mysqli_stmt_execute($statement);
        $response["rent_success"] = true;
        //echo $cnt1;
        //echo $cnt2;
    }
    

    
    $response["count"] = $cnt1;
    echo json_encode($response); 

?>