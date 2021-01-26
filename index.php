<?php  session_start(); ?>

<html>
<head><title>projekt</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<meta charset="utf-8">
</head>
<body>


<a href="index.php"><h1>Logowanie</h1></a>

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
    $password = $_POST['pass'];

    if ($login && $password) {
        try {
            $pdo = new PDO(DBConnection::CONNECTION_STRING, DBConnection::LOGIN, DBConnection::PASSWORD);
            $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

            $stmt = $pdo->query('SELECT * FROM dane WHERE LOGIN = "' . $login . '" and haslo="' . $password . '"' );

            $row = $stmt->fetch();

            if ($row) {
              ?>





<h1>Paczki</h1>

<table border="1px">
    <tr>
        <th>ID</th>
        <th>Imie</th>
        <th>Nazwisko</th>
        <th>Adres</th>
        <th>Telefon</th>
        <th>Data</th>
    </tr>
    <?php

    try {

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
<a href="dodaj.php">Dodaj paczkę</a>
