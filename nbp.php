<?php
    $curl = curl_init();
    curl_setopt($curl, CURLOPT_URL, 'https://www.nbp.pl/kursy/xml/a006z210112.xml');
    curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);
    $dane = curl_exec($curl);
    curl_close($curl);

    $tabela_kursow = new SimpleXMLElement($dane);
    // wybranie daty publikacji tabeli w NBP
    $data = $tabela_kursow->data_publikacji;
    echo $data;
    echo "<br/>\n";

    //wybranie poszczególnych walut
    $czk = $tabela_kursow->pozycja[2];
    echo" PLN  1,00  = " .$czk->kod_waluty;
    echo " ".$czk->kurs_sredni;
    echo "<br/>\n\n\n";

    ?>
