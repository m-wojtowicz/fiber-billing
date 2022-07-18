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

Keycloak dostępny na porcie 8080. Panel administratora dostępny na porcie 28080.

Backend dostępny na porcie 8000.

Fontend dostępny na porcie 4200.

<http://127.0.0.1:4200>

# Zatrzymywanie

Przy zamykaniu:

```
docker-compose down --rmi all
```

**Bez flagi `--rmi all` Docker nie będzie usuwał starych obrazów!**

# Problemy

Przy zmianach w skryptach bazy danych należy usunąć volume zawierający starą wersję bazy danych. Komenda:

```
docker volume rm fiber-billing_fiber-billing-data
```