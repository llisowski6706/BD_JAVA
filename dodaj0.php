<?php  session_start(); ?>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Add data to database</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
</head>
<body>

<?php
  if (!$_SESSION['login']) {
      header('Location: /index.php');
  }
  ?>
<?php require 'partials/navbar.php'; ?>
  <form name="form1" method="POST" action="dodaj.php"></p>
    Imie: <input name="imie" type="text" size="20"required></p>
    Nazwisko: <input type="text" name="nazwisko" size="20"required></p>
    Adres: <input type="text" name="adres" size="20" required> </p>
    Telefon  <input type="tel" id="phone" name="telefon" value="123-456-789"
           pattern="[0-9]{3}-[0-9]{3}-[0-9]{3}"  required>

    <small>Format: 123-456-789</small></p>
    Data nadania: <input type="date"  required id="start" name="data"
           value="2021-01-01"
           min="2011-01-01" max="2022-12-31"></p>
    <input value="Dodaj paczkę" type="submit">
  </form>
    <?php
        require "dbConfig.php";

        try{
            if ( $_SERVER['REQUEST_METHOD'] == 'POST' ) {
                $db = new PDO(DBConnection::CONNECTION_STRING, DBConnection::LOGIN, DBConnection::PASSWORD, array(PDO::MYSQL_ATTR_INIT_COMMAND => 'SET NAMES utf8'));

                $imie = trim($_POST['imie']);
                $nazwisko = trim($_POST['nazwisko']);
                $adres = trim($_POST['adres']);
                $telefon = trim($_POST['telefon']);
                $data = trim($_POST['data']);

                $sql = $db->prepare("INSERT INTO `Paczki` (`imie`, `nazwisko`, `adres`, `telefon`, `data`) VALUES (:imie, :nazwisko, :adres, :telefon, :data)");
                $sql->bindValue(":imie", $imie, PDO::PARAM_STR);
                $sql->bindValue(":nazwisko", $nazwisko, PDO::PARAM_STR);
                $sql->bindValue(":adres", $adres, PDO::PARAM_STR);
                $sql->bindValue(":telefon", $telefon, PDO::PARAM_STR);
                $sql->bindValue(":data", $data, PDO::PARAM_STR);
                $sql->execute();

                header('Location: /index.php');

            }
        }catch (PDOException $e){
            print 'Błąd połączenia z bazą danych!: '. $e->getMessage().'';
            die();
        }
    ?>
</body>
</html>
