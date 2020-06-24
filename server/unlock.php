<?php
    $con = mysqli_connect("localhost", "xproject2", "xproject1!", "xproject2");
    mysqli_query($con,'SET NAMES utf8');
    
    $userNumber = $_POST["userNumber"];
    //echo $userNumber;
    $statement = mysqli_prepare($con, "SELECT userNumber FROM USING_BIKE WHERE userNumber = ?");
    mysqli_stmt_bind_param($statement, "s", $userNumber);
    
    mysqli_stmt_execute($statement);


    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $userNumber);
    $response = array();
    $response["return_bike"] = false;
    //echo "test";
    //echo $statement;
    // $query = "SELECT * FROM USING_BIKE"; // 테이블에서 레코드갯수 카운트
    // $data = mysqli_query($con, $query);
    // $cnt1 = mysqli_num_rows($data);
    while(mysqli_stmt_fetch($statement)) {
        $userNumber = $_POST["userNumber"];
        $statement = mysqli_prepare($con, "DELETE FROM USING_BIKE WHERE userNumber = ?");
        mysqli_stmt_bind_param($statement, "s", strval($userNumber));
        mysqli_stmt_execute($statement);
        $response["return_bike"] = true;
        break;
    }

    echo json_encode($response);
?>