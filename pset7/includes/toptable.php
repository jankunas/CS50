<?php 
    /*a php file to render top of the table for the buy/sell phps*/
    $holdings = [];
    $rows = CS50::query("SELECT symbol, shares FROM portfolios WHERE user_id=?", $_SESSION["id"]);
    $casharr = CS50::query("SELECT cash FROM users WHERE id=?", $_SESSION["id"]);
    $cash = 0;
    $cash = $casharr[0];
    foreach ($rows as $row){
        $stock = lookup($row["symbol"]);
        if ($stock !== false){
            $holdings[] = [
                "name" => $stock["name"],
                "price" => number_format($stock["price"], 2),
                "shares" => $row["shares"],
                "symbol" => $row["symbol"]
            ];
        }
    }
?>