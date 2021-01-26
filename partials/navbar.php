<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">Paczki</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="dodaj.php">Dodaj paczkę <span class="sr-only">(current)</span></a>
            </li>
        </ul>
        <?php if ($_SESSION['login']) { ?><a href="logout.php" class="btn btn-outline-success my-2 my-sm-0" type="submit">Wyloguj</a> <?php } ?>
    </div>
</nav>
