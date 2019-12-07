#!/usr/bin/env bash
docker run --name dev-postgres -p 5432:5432 -d postgres:9.6-alpine