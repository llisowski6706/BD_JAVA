<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Add data to database</title>
</head>
<body>
  <form name="form1" method="POST" action="dodaj.php"></p>
    Imie: <input name="imie" type="text" size="20"required></p>
    Nazwisko: <input type="text" name="nazwisko" size="20"required></p>
    Adres: <input type="text" name="adres" size="20" required> </p>
    Telefon  <input type="tel" id="phone" name="telefon" value="123-456-789"
           pattern="[0-9]{3}-[0-9]{3}-[0-9]{3}"  required>

    <small>Format: 123-456-789</small></p>
    Data: <input type="date"  required id="start" name="data"
           value="2021-01-01"
           min="2011-01-01" max="2022-12-31"></p>
    <input value="Dodaj paczkę" type="submit">
  </form>
    <?php
        try{
            $db = new PDO('mysql:host=178.32.219.12;dbname=1206330_ek8163', '1206330_ek8163', 'mVCO8Y94m0YEsc',array(PDO::MYSQL_ATTR_INIT_COMMAND => 'SET NAMES utf8'));
        }catch (PDOException $e){
            print 'Błąd połączenia z bazą danych!: '. $e->getMessage().'';
            die();
        }
        if(isset($_POST['addData'])){
            $imie = trim($_POST['imie']);
            $nazwisko = trim($_POST['nazwisko']);
            $adres = trim($_POST['adres']);
            $telefon = trim($_POST['telefon']);
            $data = trim($_POST['data']);

            define('INFO_SUCCESS', 'This form has been sent');
            $sql = $db->prepare("INSERT INTO `Paczki` (`imie`, `nazwisko`, `adres`, `telefon`, `data`) VALUES (:imie, :nazwisko, :adres, :telefon, :data)");
            $sql->bindValue(":imie", $imie, PDO::PARAM_STR);
            $sql->bindValue(":nazwisko", $nazwisko, PDO::PARAM_STR);
            $sql->bindValue(":adres", $adres, PDO::PARAM_STR);
            $sql->bindValue(":telefon", $telefon, PDO::PARAM_STR);
            $sql->bindValue(":data", $data, PDO::PARAM_INT)

            echo '<p>'.INFO_SUCCESS.'</p>';
        }
    ?>
</body>
</html>
