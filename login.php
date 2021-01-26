<?php
session_start();

require 'dbConfig.php';

$login = $_POST['login'];
$password = $_POST['pass'];

if ($_SESSION['login']) {
    header('Location: /index.php');
}
if ($login && $password) {
    try {
        $pdo = new PDO(DBConnection::CONNECTION_STRING, DBConnection::LOGIN, DBConnection::PASSWORD);
        $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

        $stmt = $pdo->query('SELECT * FROM dane WHERE LOGIN = "' . $login . '" and haslo="' . $password . '"');

        $row = $stmt->fetch();

        if ($row) {
            $_SESSION['login'] = true;
            header('Location: /index.php');
        } else {
            header('Location: /loginFail.php');
        }
    } catch (Exception $e) {
        echo 'Połączenie nie mogło zostać utworzone: ' . $e->getMessage();
    }
}
