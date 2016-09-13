#!/usr/bin/env bash
# USAGE: sh warcbase-process.sh COLLECTIONNAME
# i.e. sh warcbase-process.sh WAHR_ymmfire

# create the new scala script to run
sed -e "s/\${COLLECTION}/$1/g" template.scala > $1.scala

# execute in Spark Shell
/home/ubuntu/project/spark-1.6.1-bin-hadoop2.6/bin/spark-shell -i /home/ubuntu/test/$1.scala --driver-memory 55G --jars /home/ubuntu/project/warcbase/warcbase-core/target/warcbase-core-0.1.0-SNAPSHOT-fatjar.jar

# combine part files, move to directory
cat /data/derivatives/fullurls/$1/part* > $1-fullurls.txt
mv $1-fullurls.txt /data/derivatives/fullurls
#rm -rf /data/derivatives/fullurls/$1/

cat /data/derivatives/urls/$1/part* > $1-urls.txt
mv $1-urls.txt /data/derivatives/urls
#rm -rf /data/derivatives/urls/$1/

cat /data/derivatives/links/$1/part* > $1-links.txt
mv $1-links.txt /data/derivatives/links
#rm -rf /data/derivatives/links/$1/

cat /data/derivatives/text/$1/part* > $1-text.txt
mv $1-text.txt /data/derivatives/text
#rm -rf /data/derivatives/text/$1/

# let's remove part files manually, on second thought
