#!/bin/bash
python3 skeleton_parser.py ebay_data/items-*.json

sort Bidders.dat | uniq > temp.dat && mv temp.dat Bidders.dat
sort Users.dat | uniq > temp.dat && mv temp.dat Users.dat 
sort Sellers.dat | uniq > temp.dat && mv temp.dat Sellers.dat
sort Categories.dat | uniq > temp.dat && mv temp.dat Categories.dat

