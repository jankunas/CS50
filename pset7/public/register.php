<?php

    // configuration
    require("../includes/config.php");

    // if user reached page via GET (as by clicking a link or via redirect)
    if ($_SERVER["REQUEST_METHOD"] == "GET"){
        // else render form
        render("register_form.php", ["title" => "Register"]);
    }

    // else if user reached page via POST (as by submitting a form via POST)
    else if ($_SERVER["REQUEST_METHOD"] == "POST"){
        if (empty($_POST["username"])){
            apologize("You must provide your username.");
        }
        else if (empty($_POST["password"])){
            apologize("You must provide your password.");
        }
        if (($_POST["confirmation"]) != ($_POST["password"])){
            apologize("You have retyped the password incorrectly, please try again");
        }
        
        //inserting a new user into database. 
        $result = CS50::query("INSERT IGNORE INTO users (username, hash, cash) VALUES(?, ?, 5000.0000)", $_POST["username"], password_hash($_POST["password"], PASSWORD_DEFAULT));
        if ($result == 0){
            apologize("Please try again, username already exists");
        }
        else {
            //selecting newly inserted user's id and storing it in $_SESSION variable
            $rows = CS50::query("SELECT LAST_INSERT_ID() AS id");
            $id = $rows[0]["id"];
            $_SESSION["id"] = $id;
            //redirecting the user to index.php
            redirect("/");
        }
    }
?>