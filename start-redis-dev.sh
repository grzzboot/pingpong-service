#!/usr/bin/env bash
docker run --name dev-redis -p 6379:6379 -d redis:5.0.7-alpine