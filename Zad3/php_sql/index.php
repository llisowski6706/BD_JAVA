<?php  session_start(); ?>

<html>
<head><title>projekt</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
</head>
<body>


<h1>Logowanie</h1>

<form method="post" class="form-inline">
<div class="form-group">
    <label for="login">Login</label>
    <input type="text" class="form-control" id="login" aria-describedby="login" placeholder="Login" name="login">
    <small id="loginHelp" class="form-text text-muted">Proszę podać login.</small>
  </div>
<div class="form-group">
    <label for="login">Hasło</label>
    <input type="password" class="form-control" id="pass" aria-describedby="pass" placeholder="Hasło" name="pass">
    <small id="passHelp" class="form-text text-muted">Proszę podać hasło.</small>
  </div>
    <input type="submit"  class="btn btn-primary">
</form>
</body>
</html>


<?php

require 'dbConfig.php';

    $login = $_POST['login'];
    $password = $_POST['haslo'];

    if ($login && $password) {
        try {
            $pdo = new PDO(DBConnection::CONNECTION_STRING, DBConnection::LOGIN, DBConnection::PASSWORD);
            $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

            $stmt = $pdo->query('SELECT * FROM dane WHERE LOGIN = "' . $login . '" and haslo="' . $password . '"' );

            $row = $stmt->fetch();

            if ($row) {
              ?>
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

    try {

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
            <?php
        }
        $stmt->closeCursor();
        echo '</ul>';
    } catch (PDOException $e) {
        echo 'Połączenie nie mogło zostać utworzone: ' . $e->getMessage();
    }

            } else {
                echo "<div class=\"alert alert-danger\" role=\"alert\">
  Logowanie nie udane
</div>";
            }
        } catch (PDOException $e) {
            echo 'Połączenie nie mogło zostać utworzone: ' . $e->getMessage();
        }

}
?>
