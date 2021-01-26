
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
