#!/bin/bash

echo "Avviando mysql-geographic..."
docker-compose up -d mysql-geographic
sleep 60

# Avvia i container MySQL
echo "Avviando mysql-keycloak..."
docker-compose up -d mysql-keycloak
sleep 60

echo "Avviando mysql-artempact..."
docker-compose up -d mysql-artempact
sleep 60

# Avvia il frontend
echo "Avviando frontend-artempact..."
docker-compose up -d frontend-artempact
sleep 60

# Avvia Keycloak
echo "Avviando Keycloak..."
docker-compose up -d keycloak
sleep 60

# Avvia il backend
echo "Avviando backend-artempact..."
docker-compose up -d backend-artempact

echo "Tutti i servizi sono stati avviati."
