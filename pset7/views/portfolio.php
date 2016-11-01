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
