<?php
    // configuration
    require("../includes/config.php"); 
    
    //handling submit+quote.php and view_quote.php files
    if ($_SERVER["REQUEST_METHOD"] == "GET")
        render("quote_submit.php", ["title" => "Quote"]);
    else if ($_SERVER["REQUEST_METHOD"] == "POST"){
        if (empty($_POST["symbol"]))
            apologize("You must provide a symbol.");
        $stock = lookup($_POST["symbol"]);
        if ($stock == false)
            apologize("The symbol you have enterered is invalid, please provide a valid symbol");
        else 
            render("quote_view.php", ["title" => "Quote", "symbol" => $stock["symbol"], "price" => number_format($stock["price"], 2), "name" => $stock["name"]]);
    }
?>