FROM node:lts-alpine

# Prerequisite
RUN npm install -g http-server

# Change dir
RUN mkdir -p /app
WORKDIR /app

# Packages
COPY frontend/package*.json ./
RUN npm install

# Copy source
COPY frontend/ .

# Build source
RUN npm run build

# $PORT is variable in docker compose
EXPOSE $PORT
ENV NUXT_HOST=0.0.0.0
ENV NUXT_PORT=$PORT
ENV PROXY_API=$PROXY_API
ENV PROXY_LOGIN=$PROXY_LOGIN
CMD [ "http-server", "dist" ]
