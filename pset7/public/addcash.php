<?php
    // configuration
    require("../includes/config.php"); 
    
    //error checking
    if ($_SERVER["REQUEST_METHOD"] == "GET"){
        require("../includes/toptable.php");
        render("cash_submit.php", ["title" => "Add Funds", "cash" => $cash]);
    }
    else if ($_SERVER["REQUEST_METHOD"] == "POST"){
        if (empty($_POST["cash"]))
            apologize("You must enter the amount (i.e. 25)");
        if ($_POST["cash"] < 0)
            apologize("The amount you can deposit must be a positive number");
        
        //depositing funds    
        $addcash = CS50::query("UPDATE users SET cash = cash + ? WHERE id = ?" ,$_POST["cash"], $_SESSION["id"]);
        if ($addcash == false)
            apologize("Unexpected error");
        else 
            render("cash_view.php", ["title" => "Summary", "cash" => $_POST["cash"]]);
    }
?>