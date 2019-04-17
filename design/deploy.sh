#!/bin/sh

pandoc -f markdown -t html -o www/index.html ../README.md
cp washington_hhh_lineage.png www/design
cp washington_hhh_lineage.png www
aws s3 sync www/ s3://wh3.nullgeodesic.com
