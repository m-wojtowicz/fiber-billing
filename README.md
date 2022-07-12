# Wymagania

Node.js:

<https://nodejs.org/en/download/>

Docker Desktop:

<https://www.docker.com/products/docker-desktop/>

# Budowanie

```ps1
docker build --file=frontend/Dockerfile -t fiber-billing-frontend .
```

# Uruchamianie

```ps1
docker-compose -f docker-compose.yml up
```

Fontend domyślnie uruchamiany na porcie 3000.
<http://127.0.0.1:3000>