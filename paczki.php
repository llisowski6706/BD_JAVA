<html>
<head><title>projekt</title></head>
<body>


<h1>Paczki</h1>

<table border="1px">
    <tr>
        <th>id</th>
        <th>imie</th>
        <th>nazwisko</th>
        <th>adres</th>
        <th>telefon</th>
        <th>data</th>
    </tr>
    <?php
    require 'dbConfig.php';

    try {
        $pdo = new PDO(DBConnection::CONNECTION_STRING, DBConnection::LOGIN, DBConnection::PASSWORD);
        $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

        $stmt = $pdo->query('SELECT * FROM Paczki');
        echo '<ul>';
        while ($row = $stmt->fetch()) {
            ?>
            <tr>
                <td><?php echo $row['id'] ?> </td>
                <td><?php echo $row['imie'] ?> </td>
                <td><?php echo $row['nazwisko'] ?></td>
                <td><?php echo $row['adres'] ?></td>
                <td><?php echo $row['telefon'] ?></td>
                <td><?php echo $row['data'] ?></td>
            </tr>
            <?
        }
        $stmt->closeCursor();
        echo '</ul>';
    } catch (PDOException $e) {
        echo 'Połączenie nie mogło zostać utworzone: ' . $e->getMessage();
    }
    ?>
</table>
</body>
</html>
