FROM node:lts-alpine as build-stage

# Change dir
RUN mkdir -p /app
WORKDIR /app

# Packages
COPY package*.json .
RUN npm install

# Copy source
COPY . .

# Build source
RUN npm run build

# Production
FROM nginx:stable-alpine as production-stage
COPY --from=build-stage /app/dist /usr/share/nginx/html

EXPOSE 80
CMD [ "nginx", "-g", "daemon off;" ]
