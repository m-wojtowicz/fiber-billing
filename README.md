# Wymagania

Node.js:

<https://nodejs.org/en/download/>

Docker Desktop:

<https://www.docker.com/products/docker-desktop/>

# Uruchamianie

W katalogu głównym projektu:

```
docker-compose up -d
```

Uruchamia bazę danych, backend i frontend.

Baza danych dostępna na porcie 5432. Połączenie kombinacją login:hasło `fiber-billing:fiber-billing`.

Keycloak dostępny na porcie 8080. Panel administratora dostępny na porcie 8080.

Backend dostępny na porcie 8000.

Fontend dostępny na porcie 4200.

<http://127.0.0.1:4200>

# Zatrzymywanie

Przy zamykaniu:

```
docker-compose down --rmi all
```

**Bez flagi `--rmi all` Docker nie będzie usuwał starych obrazów!**

# Backup

W pierwszej kolejności należy usunąć kontenery zapisujące do bazy danych:

```
docker-compose down
```

Aby utworzyć kopię zapasową należy uruchomić usługę `backup`:

```
docker-compose run --rm backup
```

Utworzy ona backup bazy danych: `backup/data.tar.bz2`.

Aby przywrócić backup z tej lokalizacji należy uruchomić usługę `restore`:

```
docker-compose run --rm restore
```

# Problemy

Przy zmianach w skryptach bazy danych należy usunąć volume zawierający starą wersję bazy danych. Komenda:

```
docker volume rm fiber-billing_fiber-billing-data
```