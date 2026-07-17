# amble-server-tests [![Build Status](https://github.com/bendavisnc/amble-server-tests/actions/workflows/runtests.yml/badge.svg)](https://github.com/bendavisnc/amble-server-tests/actions/workflows/runtests.yml)

Tests using clj-http to run tests against [amble-server](https://github.com/bendavisnc/amble-server)'s endpoints.

## Running

To run against a running server, run:

    SERVER_URL=http://localhost:3000 clj -X:test

