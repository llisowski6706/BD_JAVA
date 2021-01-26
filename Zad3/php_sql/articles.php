<html>
<head><title>projekt</title></head>
<body>


<h1>Artykuły</h1>

<table border="1px">
    <tr>
        <th>szerokosc</th>
        <th>dlugosc</th>
        <th>kolor</th>
        <th>firma</th>
        <th>rodzaj</th>
        <th>cena</th>
    </tr>
    <?php
    require 'dbConfig.php';

    try {
        $pdo = new PDO(DBConnection::CONNECTION_STRING, DBConnection::LOGIN, DBConnection::PASSWORD);
        $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

        $stmt = $pdo->query('SELECT * FROM artykuly');
        echo '<ul>';
        while ($row = $stmt->fetch()) {
            ?>
            <tr>
                <td><?php echo $row['szerokosc'] ?> </td>
                <td><?php echo $row['dlugosc'] ?> </td>
                <td><?php echo $row['kolor'] ?></td>
                <td><?php echo $row['firma'] ?></td>
                <td><?php echo $row['rodzaj'] ?></td>
                <td><?php echo $row['cena'] ?></td>
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

