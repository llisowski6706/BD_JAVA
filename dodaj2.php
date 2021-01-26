
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Untitled Document</title>
</head>

<body>
<h3>DODAJ NOWĄ PACZKĘ</h3>

<!-- FORMULARZ HTML WPROWADZANIA DANYCH -->
<form name="form1" method="get" action="dodaj.php"></p>
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
<!-- KONIEC FORMULARZA -->

</br>
<a href="paczki.php">Lista paczek</a>
</body>



<?php
try{
            $db = new PDO('mysql:host=178.32.219.12;dbname=1206330_ek8163', '1206330_ek8163', 'mVCO8Y94m0YEsc',array(PDO::MYSQL_ATTR_INIT_COMMAND => 'SET NAMES utf8'));
        }catch (PDOException $e){
            print 'Błąd połączenia z bazą danych!: '. $e->getMessage().'';
            die();
?>

<?
	// tworzymy krotkie nazwy zmiennych otrzymanych z formularza
	$imie=$_GET['imie'];
	$naziwsko=$_GET['nazwisko'];
	$adres=$_GET['adres'];
	$telefon=$_GET['telefon'];
	$data=$_GET['data'];

	// przekazujemy wartosci zmiennych do odpowiednich pol w bazie danych
	// WSTAW DO tabeli test (kolumny w bazie) WARTOSCI (wartosci zmiennych)
	$zapytanie = "INSERT INTO Paczki (imie, nazwisko, adres, telefon, data) VALUES ('$imie','$nazwisko','$adres','$telefon','$data')" or die(mysql_error());
	// wykonaj zapytanie
	$wynik = mysql_query($zapytanie);
	// komunikat potwierdzający poprawne zapisanie danych w bazie
	if ($wynik) {
		echo 'Prawidłowo dodano do bazy danych';
	}

	mysql_close($db);
}
?>

</html>
