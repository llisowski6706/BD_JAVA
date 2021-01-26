<?php

if($_POST){

    if(isset($_POST['insert'])){

        insert();

    }elseif(isset($_POST['select'])){

        select();

    }

}



    function select()

    {

       echo "The select function is called.";

    }

    function insert()

    {

       echo "The insert function is called.";

    }



?>.


<!-- <input type="submit" class="button" name="insert" value="insert" />
<input type="submit" class="button" name="select" value="select" /> -->





<?php

 if($_GET['button1']){fun1();}



 function fun1()

 {

   // Prevent script from timing out
   set_time_limit(0);

   // Table to be duplicated
   $table = 'Paczki';

   // Source server of the table to be duplicated
   $sourceHost = '178.32.219.12';
   $sourceUser = '1206330_ek8163';
   $sourcePassword = 'mVCO8Y94m0YEsc';
   $sourceDatabase = '1206330_ek8163';

   // Destination server to duplicate the table
   $destinationHost = '178.32.219.12';
   $destinationUser = '1073312_GqG';
   $destinationPassword = 'HtHWLU19ocEMOA';
   $destinationDatabase = '1073312_GqG';


   // Connect to source server
   $source = mysql_connect($sourceHost, $sourceUser, $sourcePassword);
   mysql_select_db($sourceDatabase, $source);

   // Connect to destination server
   $destination = mysql_connect($destinationHost, $destinationUser, $destinationPassword); // connect server 2
   mysql_select_db($destinationDatabase, $destination); // select database 2

   // Get the table structure from the source and create it on destination server
   $tableInfo = mysql_fetch_array(mysql_query("SHOW CREATE TABLE $table  ", $source)); // get structure from table on server 1
   mysql_query(" $tableInfo[1] ", $destination); // use found structure to make table on server 2

   // Copy data from source to destination
   $result = mysql_query("SELECT * FROM $table  ", $source); // select all content

   while ($row = mysql_fetch_array($result, MYSQL_ASSOC)) {
       mysql_query("INSERT INTO $table (" . implode(", ", array_keys($row)) . ") VALUES ('" . implode("', '", array_values($row)) . "')", $destination);
   }

   // Close connections
   mysql_close($source);
   mysql_close($destination);





 }

 function fun2()

 {

   //This function will update some column in database to 2

 }

?>

<!DOCTYPE html>
<html lang="en">
  <head>
    <title>Zaktualizuj bazę danych</title>
    <meta charset="utf-8">
  </head>
  <body>
    Kliknij, aby zaktualizować bazy danych. =><button id="btnfun1" name="btnfun1" onClick='location.href="?button1=1"'>Aktualizuj</button>
  </body>
</html>
