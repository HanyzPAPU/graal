#!/bin/bash

# Runs the `./run-jazzer.sh` script, but prepends a timestamp to each line of output (stdout and stderr)
./run-jazzer.sh $@ 2>&1 | while IFS= read -r line; do printf '[%s] %s\n' "$(date '+%Y-%m-%d %H:%M:%S')" "$line"; done
