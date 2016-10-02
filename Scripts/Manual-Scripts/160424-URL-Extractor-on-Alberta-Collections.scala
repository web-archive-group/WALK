/* now running on errorlogs branch of warcbase */
/* BROKEN WARC FILE IN GOVERNMENT_INFORMATION - logging enabled */

import org.warcbase.spark.matchbox._ 
import org.warcbase.spark.rdd.RecordRDD._ 

val government_information = 
  RecordLoader.loadArchives("/data/government_information/*.gz", sc) 
  .keepValidPages() 
  .map(r => (r.getCrawlMonth, ExtractDomain(r.getUrl))) 
  .countItems() 
  .saveAsTextFile("/data/derivatives/urls/government_information")

val hcf_alberta_online_encyclopedia = 
  RecordLoader.loadArchives("/data/hcf_alberta_online_encyclopedia/*.gz", sc) 
  .keepValidPages() 
  .map(r => (r.getCrawlMonth, ExtractDomain(r.getUrl))) 
  .countItems() 
  .saveAsTextFile("/data/derivatives/urls/hcf_alberta_online_encyclopedia")

val health_sciences_grey_literature = 
  RecordLoader.loadArchives("/data/health_sciences_grey_literature/*.gz", sc) 
  .keepValidPages() 
  .map(r => (r.getCrawlMonth, ExtractDomain(r.getUrl))) 
  .countItems() 
  .saveAsTextFile("/data/derivatives/urls/health_sciences_grey_literature")

val heritage_community_foundation = 
  RecordLoader.loadArchives("/data/heritage_community_foundation/*.gz", sc) 
  .keepValidPages() 
  .map(r => (r.getCrawlMonth, ExtractDomain(r.getUrl))) 
  .countItems() 
  .saveAsTextFile("/data/derivatives/urls/heritage_community_foundation")

val humanities_computing = 
  RecordLoader.loadArchives("/data/humanities_computing/*.gz", sc) 
  .keepValidPages() 
  .map(r => (r.getCrawlMonth, ExtractDomain(r.getUrl))) 
  .countItems() 
  .saveAsTextFile("/data/derivatives/urls/humanities_computing")

val idle_no_more = 
  RecordLoader.loadArchives("/data/idle_no_more/*.gz", sc) 
  .keepValidPages() 
  .map(r => (r.getCrawlMonth, ExtractDomain(r.getUrl))) 
  .countItems() 
  .saveAsTextFile("/data/derivatives/urls/idle_no_more")

val lfrancophonie_de_louest_canadien = 
  RecordLoader.loadArchives("/data/lfrancophonie_de_louest_canadien/*.gz", sc) 
  .keepValidPages() 
  .map(r => (r.getCrawlMonth, ExtractDomain(r.getUrl))) 
  .countItems() 
  .saveAsTextFile("/data/derivatives/urls/lfrancophonie_de_louest_canadien")

val ottawa_shooting_october_2014 = 
  RecordLoader.loadArchives("/data/ottawa_shooting_october_2014/*.gz", sc) 
  .keepValidPages() 
  .map(r => (r.getCrawlMonth, ExtractDomain(r.getUrl))) 
  .countItems() 
  .saveAsTextFile("/data/derivatives/urls/ottawa_shooting_october_2014")


val prarie_provinces = 
  RecordLoader.loadArchives("/data/prarie_provinces/*.gz", sc) 
  .keepValidPages() 
  .map(r => (r.getCrawlMonth, ExtractDomain(r.getUrl))) 
  .countItems() 
  .saveAsTextFile("/data/derivatives/urls/prarie_provinces")


val university_of_alberta_websites = 
  RecordLoader.loadArchives("/data/university_of_alberta_websites/*.gz", sc) 
  .keepValidPages() 
  .map(r => (r.getCrawlMonth, ExtractDomain(r.getUrl))) 
  .countItems() 
  .saveAsTextFile("/data/derivatives/urls/university_of_alberta_websites")


val web_archive_general = 
  RecordLoader.loadArchives("/data/web_archive_general/*.gz", sc) 
  .keepValidPages() 
  .map(r => (r.getCrawlMonth, ExtractDomain(r.getUrl))) 
  .countItems() 
  .saveAsTextFile("/data/derivatives/urls/web_archive_general")
