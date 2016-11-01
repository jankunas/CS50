<?php
    // configuration
    require("../includes/config.php"); 
    
    if ($_SERVER["REQUEST_METHOD"] == "GET")
        render("buy_submit.php", ["title" => "Buy"]);
    else if ($_SERVER["REQUEST_METHOD"] == "POST"){
        if (empty($_POST["symbol"]))
            apologize("You must provide a symbol.");
        else if(empty($_POST["amount"]))
        apologize("You must provide quantity  (i.e. 2)");
        
        if (preg_match("/^\d+$/", $_POST["amount"]) == false)
            apologize("Please provide a positive whole number as quantity!");
            
        //obtaining necessary variables
        $stock = lookup($_POST["symbol"]);  
        if ($stock == false)
            apologize("The symbol you have entered is invalid");
            
        //to calculate how much it will cost to buy the specified number of shares
        $price = $stock["price"] * $_POST["amount"];
        
        //obtain user's cash
        $casharr = CS50::query("SELECT cash FROM users WHERE id =?", $_SESSION ["id"]);
        if ($casharr == false)
            apologize("Unexpected error!");
        $casharr = 0;
        $cash = $casharr[0]["casharr"];
        
        //check whether user has sufficient cash to buy the stock
        if ($price > $cash)
            apologize("Unfortunately, you cannot afford it!");
        else {
            //to upper letters of the entered symbol
            $stock["symbol"] = mb_strtoupper($stock["symbol"]);
            
            //to insert bought shares into user's portfolio
		    $buy = CS50::query("INSERT INTO portfolios (symbol, user_id, shares) VALUES (?,?,?) ON DUPLICATE KEY UPDATE shares = shares + VALUES(shares)",  $stock["symbol"], $_SESSION["id"], $_POST["amount"]);
		    if ($buy == false)
		        apologize("Unexpected error");
		        
		    //to calculate user's balance after the stocks were bought
	    	$calculate = CS50::query("UPDATE users SET cash=cash - ? WHERE id= ? ", $price, $_SESSION["id"]); 
	    	if ($calculate == false)
	    	    apologize("Unexpected error");
	    	    
	    	//update user's history in database
	    	$update = CS50::query("INSERT INTO history (user_id, trans_type, symbol, shares, price) VALUES(?, ?, ?, ?, ?)", $_SESSION["id"], "BOUGHT", $stock["symbol"], $_POST["amount"], $stock["price"]);
	    	if ($update == false)
	    	    apologize("There was a porblem updating your transaction history");
        }
        render("buy_view.php", ["price" => $price, "amount" => $_POST["amount"]]);
    }
?>