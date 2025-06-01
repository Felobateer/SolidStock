FROM node:18 AS build
WORKDIR /src/app
COPY . .
RUN npm install && npm run build

FROM nginx:alpine
COPY --from=build /app/dist/your-app-name /usr/share/nginx/html
EXPOSE 80
