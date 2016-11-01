<?php
    // configuration
    require("../includes/config.php"); 
    
    //to obtain values from history table
    $rows = CS50::query("SELECT trans_type, time, symbol, shares, price FROM history WHERE user_id = ?", $_SESSION["id"]);
    
    //to initialize history array 
    $history = [];
    foreach ($rows as $row){
        $history[] = [
            "type" => $row["trans_type"],
            "time" => $row["time"],
            "symbol" => $row["symbol"],
            "shares" => $row["shares"],
            "price" => $row["price"]
            ];
    }
    render("history_view.php", ["title" => "History", "history" => $history]);

?>