<table class="table table-hover">
    <thead>
        <tr>
            <th>Transaction type</th>
            <th>Symbol</th>
            <th>Quantity</th>
            <th>Price</th>
            <th>Time</th>
        </tr>
    </thead>
    <tbody>
        <?php foreach ($history as $transaction): ?>
        <tr>
            <td><?= $transaction["type"] ?></td>
            <td><?= $transaction["symbol"] ?></td>
            <td><?= $transaction["shares"] ?></td>
            <td>$ <?= htmlspecialchars(number_format($transaction["price"], 2)) ?></td>
            <td><?= $transaction["time"] ?></td>
        </tr>
        <?php endforeach ?>
    </tbody>
</table>