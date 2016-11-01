<div class="left">
    <table class="table table-hover">
        <thead>
            <tr>
                <th>Symbol</th>
                <th>Name</th>
                <th>Shares</th>
                <th>Price</th>
            </tr>
        </thead>
        <tbody>
            <?php foreach ($holdings as $holding): ?>
            <tr>
                <td><?= htmlspecialchars($holding["symbol"]) ?></td>
                <td><?= htmlspecialchars($holding["name"]) ?></td>
                <td><?= htmlspecialchars($holding["shares"]) ?></td>
                <td>$<?= htmlspecialchars($holding["price"]) ?></td>
            </tr>
            <?php endforeach ?>
        </tbody>
        <tbody>
            <tr>
                <td colspan="3"> TOTAL CASH LEFT </td>
                <td>$<?= htmlspecialchars(number_format($cash["cash"], 2)) ?></td>
            </tr>
        </tbody>
    </table>
</div>

<form action="sell.php" method="post">
    <fieldset>
        <div class="form-group">
            Please enter the symbol of the stock you would like to sell and the quantity below
        </div>
        <div class="form-group">
            <input autocomplete="off" autofocus class="form-control" name="symbol" placeholder="Symbol" type="text"/><br>
            <input autocomplete="off" class="form-control" name="amount" placeholder="Quantity" type="text"/>
        </div>
        <div class="form-group">
            <button class="btn btn-default" type="submit">
                <span aria-hidden="true" ></span>
                Sell!
            </button>
        </div>
    </fieldset>
</form>