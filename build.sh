#!/bin/bash

mysql -uroot -psphic < database/init.sql  

gradle build 
