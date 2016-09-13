import org.warcbase.spark.matchbox._ 
import org.warcbase.spark.rdd.RecordRDD._ 

val alberta_education_curriculum = 
  RecordLoader.loadArchives("/data/alberta_education_curriculum/*.gz", sc) 
  .keepValidPages() 
  .map(r => (r.getCrawlMonth, r.getUrl)) 
  .saveAsTextFile("/data/derivatives/fullurls/alberta_education_curriculum")

val alberta_floods_2013 = 
  RecordLoader.loadArchives("/data/alberta_floods_2013/*.gz", sc) 
  .keepValidPages() 
  .map(r => (r.getCrawlMonth, r.getUrl)) 
  .saveAsTextFile("/data/derivatives/fullurls/alberta_floods_2013")

val alberta_oil_sands = 
  RecordLoader.loadArchives("/data/alberta_oil_sands/*.gz", sc) 
  .keepValidPages() 
  .map(r => (r.getCrawlMonth, r.getUrl)) 
  .saveAsTextFile("/data/derivatives/fullurls/alberta_oil_sands")

val canadian_business_grey_literature = 
  RecordLoader.loadArchives("/data/canadian_business_grey_literature/*.gz", sc) 
  .keepValidPages() 
  .map(r => (r.getCrawlMonth, r.getUrl)) 
  .saveAsTextFile("/data/derivatives/fullurls/canadian_business_grey_literature")

val elxn42 = 
  RecordLoader.loadArchives("/data/elxn42/*.gz", sc) 
  .keepValidPages() 
  .map(r => (r.getCrawlMonth, r.getUrl)) 
  .saveAsTextFile("/data/derivatives/fullurls/elxn42")

val energy_environment = 
  RecordLoader.loadArchives("/data/energy_environment/*.gz", sc) 
  .keepValidPages() 
  .map(r => (r.getCrawlMonth, r.getUrl)) 
  .saveAsTextFile("/data/derivatives/fullurls/energy_environment")

val government_information = 
  RecordLoader.loadArchives("/data/government_information/*.gz", sc) 
  .keepValidPages() 
  .map(r => (r.getCrawlMonth, r.getUrl)) 
  .saveAsTextFile("/data/derivatives/fullurls/government_information")

val hcf_alberta_online_encyclopedia = 
  RecordLoader.loadArchives("/data/hcf_alberta_online_encyclopedia/*.gz", sc) 
  .keepValidPages() 
  .map(r => (r.getCrawlMonth, r.getUrl)) 
  .saveAsTextFile("/data/derivatives/fullurls/hcf_alberta_online_encyclopedia")

val health_sciences_grey_literature = 
  RecordLoader.loadArchives("/data/health_sciences_grey_literature/*.gz", sc) 
  .keepValidPages() 
  .map(r => (r.getCrawlMonth, r.getUrl)) 
  .saveAsTextFile("/data/derivatives/fullurls/health_sciences_grey_literature")

val heritage_community_foundation = 
  RecordLoader.loadArchives("/data/heritage_community_foundation/*.gz", sc) 
  .keepValidPages() 
  .map(r => (r.getCrawlMonth, r.getUrl)) 
  .saveAsTextFile("/data/derivatives/fullurls/heritage_community_foundation")

val humanities_computing = 
  RecordLoader.loadArchives("/data/humanities_computing/*.gz", sc) 
  .keepValidPages() 
  .map(r => (r.getCrawlMonth, r.getUrl)) 
  .saveAsTextFile("/data/derivatives/fullurls/humanities_computing")

val idle_no_more = 
  RecordLoader.loadArchives("/data/idle_no_more/*.gz", sc) 
  .keepValidPages() 
  .map(r => (r.getCrawlMonth, r.getUrl)) 
  .saveAsTextFile("/data/derivatives/fullurls/idle_no_more")

val lfrancophonie_de_louest_canadien = 
  RecordLoader.loadArchives("/data/lfrancophonie_de_louest_canadien/*.gz", sc) 
  .keepValidPages() 
  .map(r => (r.getCrawlMonth, r.getUrl)) 
  .saveAsTextFile("/data/derivatives/fullurls/lfrancophonie_de_louest_canadien")

val ottawa_shooting_october_2014 = 
  RecordLoader.loadArchives("/data/ottawa_shooting_october_2014/*.gz", sc) 
  .keepValidPages() 
  .map(r => (r.getCrawlMonth, r.getUrl)) 
  .saveAsTextFile("/data/derivatives/fullurls/ottawa_shooting_october_2014")

val prarie_provinces = 
  RecordLoader.loadArchives("/data/prarie_provinces/*.gz", sc) 
  .keepValidPages() 
  .map(r => (r.getCrawlMonth, r.getUrl)) 
  .saveAsTextFile("/data/derivatives/fullurls/prarie_provinces")

val university_of_alberta_websites = 
  RecordLoader.loadArchives("/data/university_of_alberta_websites/*.gz", sc) 
  .keepValidPages() 
  .map(r => (r.getCrawlMonth, r.getUrl)) 
  .saveAsTextFile("/data/derivatives/fullurls/university_of_alberta_websites")

val web_archive_general = 
  RecordLoader.loadArchives("/data/web_archive_general/*.gz", sc) 
  .keepValidPages() 
  .map(r => (r.getCrawlMonth, r.getUrl)) 
  .saveAsTextFile("/data/derivatives/fullurls/web_archive_general")