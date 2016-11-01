<?php

    // configuration
    require("../includes/config.php"); 
    
    //set up the portfolio table
    require("../includes/toptable.php");

    // render portfolio
    render("portfolio.php", ["title" => "Portfolio", "holdings" => $holdings, "cash" => $cash]);
    
?>
