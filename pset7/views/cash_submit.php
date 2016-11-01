You currently have $ <?= htmlspecialchars(number_format($cash["cash"], 2))?> at your disposal.

<form action="addcash.php" method="post">
    <fieldset>
        <div class="form-group">
            Please enter the amount you would like to deposit to your wallet.
        </div>
        <div class="form-group">
            <input autocomplete="off" autofocus class="form-control" name="cash" placeholder="Amount" type="text"/><br>
        </div>
        <div class="form-group">
            <button class="btn btn-default" type="submit">
                <span aria-hidden="true" ></span>
                Deposit!
            </button>
        </div>
    </fieldset>
</form>