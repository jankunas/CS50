<?php
    // configuration
    require("../includes/config.php"); 
    
    if ($_SERVER["REQUEST_METHOD"] == "GET"){
        require("../includes/toptable.php");
        render("sell_submit.php", ["title" => "Sell", "holdings" => $holdings, "cash" => $cash]);
    }
    
    else if ($_SERVER["REQUEST_METHOD"] == "POST"){
        if (empty($_POST["symbol"]))
            apologize("You must provide a symbol (i.e. FREE)");
        else if(empty($_POST["amount"]))
            apologize("You must provide quantity  (i.e. 2)");
            
        //obtaining necessary variables
        $amount = $_POST["amount"];
        $stock = lookup($_POST["symbol"]);   
        if ($stock == false)
            apologize("The symbol you have entered is invalid");
        $actuals = CS50::query("SELECT shares FROM portfolios WHERE user_id = ? AND symbol = ?", $_SESSION["id"], $_POST["symbol"]);
        if ($actuals == false)
            apologize("Unexpected error");
        else{
            $actual = 0;
            $actual = $actuals[0];
        }
            
        //validating user's input
        if (($amount < 0) || ($amount > $actual["shares"]))
            apologize("The amount you have enterered is invalid");
        else if ($amount == $actual){
            $soldall = CS50::query("DELETE FROM portfolios WHERE symbol = ? AND user_id = ?", $amount, $_SESSION["id"]);
            if ($soldall == false)
                apologize("There was a problem selling your stocks");
        }
        else if ($amount< $actual){
            $soldsome = CS50::query("UPDATE portfolios SET shares = shares - ? WHERE user_id = ?", $amount, $_SESSION["id"]);
            if ($soldsome == false)
                apologize("There was a problem selling your stocks");
        }
        //to set up user's credit after he/she sells some stocks
        $credit = $stock["price"] * $amount;
        $credits = CS50::query("UPDATE users SET cash = cash + ? WHERE id = ?", $credit, $_SESSION["id"]);
        if ($credits == false)
            apologize("Failed to credit your account");
            
        //update user's history in database
	    $update = CS50::query("INSERT INTO history (user_id, trans_type, symbol, shares, price) VALUES(?, ?, ?, ?, ?)", $_SESSION["id"], "SOLD", $stock["symbol"], $_POST["amount"], $stock["price"]);
	    if ($update == false)
	        apologize("There was a porblem updating your transaction history");
            
        //to render sell_show view
        require("../includes/toptable.php");
        render("sell_view.php", ["title" => "Summary", "holdings" => $holdings, "cash" => $cash, "amount" => $amount]);
        }
?>