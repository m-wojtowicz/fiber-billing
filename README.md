# Wymagania

Node.js:

<https://nodejs.org/en/download/>

Docker Desktop:

<https://www.docker.com/products/docker-desktop/>

# Uruchamianie

W Katalogu głównym projektu:

```
docker-compose up -d
```

Przy zamykaniu:

```
docker-compose down --rmi all
```

**Bez flagi `--rmi all` Docker nie będzie usuwał starych obrazów!**

Uruchamia bazę danych, backend i frontend.

Baza danych dostępna na porcie 5432.

Backend dostępny na porcie 8080.

Fontend dostępny na porcie 4200.

<http://127.0.0.1:4200>