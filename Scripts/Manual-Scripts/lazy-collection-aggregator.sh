cd alberta_education_curriculum/
cat part* > alberta_education_curriculum-all.txt
cd ..
cd alberta_floods_2013/
cat part* > alberta_floods_2013-all.txt
cd ..
cd alberta_oil_sands/
cat part* > alberta_oil_sands-all.txt
cd ..
cd canadian_business_grey_literature/
cat part* > canadian_business_grey_literature-all.txt
cd ..
cd elxn42/
cat part* > elxn42-all.txt
cd ..
cd energy_environment/
cat part* > energy_environment-all.txt
cd ..
cd hcf_alberta_online_encyclopedia/
cat part* > hcf_alberta_online_encyclopedia-all.txt
cd ..
cd health_sciences_grey_literature/
cat part* > health_sciences_grey_literature-all.txt
cd ..
cd heritage_community_foundation/
cat part* > heritage_community_foundation-all.txt
cd ..
cd humanities_computing/
cat part* > humanities_computing-all.txt
cd ..
cd idle_no_more/
cat part* > idle_no_more-all.txt
cd ..
cd lfrancophonie_de_louest_canadien/
cat part* > lfrancophonie_de_louest_canadien-all.txt
cd ..
cd ottawa_shooting_october_2014/
cat part* > ottawa_shooting_october_2014-all.txt
cd ..
cd prarie_provinces/
cat part* > prarie_provinces-all.txt
cd ..
cd web_archive_general/
cat part* > web_archive_general-all.txt
cd ..
mkdir assembled
find -iname "*.txt" -exec cp {} ./assembled \;
tar -zcvf urls-assembled.tar.gz assembled/

