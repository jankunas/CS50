<form action="buy.php" method="post">
    <fieldset>
        <div class="form-group">
            Please enter the symbol of the stock you would like to buy and the quantity below
        </div>
        <div class="form-group">
            <input autocomplete="off" autofocus class="form-control" name="symbol" placeholder="Symbol" type="text"/><br>
            <input autocomplete="off" class="form-control" name="amount" placeholder="Quantity" type="text"/>
        </div>
        <div class="form-group">
            <button class="btn btn-default" type="submit">
                <span aria-hidden="true" ></span>
                Buy!
            </button>
        </div>
    </fieldset>
</form>