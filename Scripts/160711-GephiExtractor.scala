import org.warcbase.spark.matchbox._ 
import org.warcbase.spark.rdd.RecordRDD._ 

val alberta_education_curriculum = 
  RecordLoader.loadArchives("/data/alberta_education_curriculum/*.gz", sc) 
  .keepValidPages()
  .map(r => (r.getCrawlDate, ExtractLinks(r.getUrl, r.getContentString)))
  .flatMap(r => r._2.map(f => (r._1, ExtractDomain(f._1).replaceAll("^\\s*www\\.", ""), ExtractDomain(f._2).replaceAll("^\\s*www\\.", ""))))
  .filter(r => r._2 != "" && r._3 != "")
  .countItems()
  .filter(r => r._2 > 5) 
  .map(r => (r.getCrawlMonth, r.getUrl)) 
  
WriteGDF(alberta_education_curriculum, "/data/derivatives/gephi/alberta_education_curriculum.gdf")

val alberta_floods_2013 = 
  RecordLoader.loadArchives("/data/alberta_floods_2013/*.gz", sc) 
  .keepValidPages()
  .map(r => (r.getCrawlDate, ExtractLinks(r.getUrl, r.getContentString)))
  .flatMap(r => r._2.map(f => (r._1, ExtractDomain(f._1).replaceAll("^\\s*www\\.", ""), ExtractDomain(f._2).replaceAll("^\\s*www\\.", ""))))
  .filter(r => r._2 != "" && r._3 != "")
  .countItems()
  .filter(r => r._2 > 5) 
  .map(r => (r.getCrawlMonth, r.getUrl)) 
  
WriteGDF(alberta_floods_2013, "/data/derivatives/gephi/alberta_floods_2013.gdf")

val alberta_oil_sands = 
  RecordLoader.loadArchives("/data/alberta_oil_sands/*.gz", sc) 
  .keepValidPages()
  .map(r => (r.getCrawlDate, ExtractLinks(r.getUrl, r.getContentString)))
  .flatMap(r => r._2.map(f => (r._1, ExtractDomain(f._1).replaceAll("^\\s*www\\.", ""), ExtractDomain(f._2).replaceAll("^\\s*www\\.", ""))))
  .filter(r => r._2 != "" && r._3 != "")
  .countItems()
  .filter(r => r._2 > 5) 
  .map(r => (r.getCrawlMonth, r.getUrl)) 
  
WriteGDF(alberta_oil_sands, "/data/derivatives/gephi/alberta_oil_sands.gdf")

val canadian_business_grey_literature = 
  RecordLoader.loadArchives("/data/canadian_business_grey_literature/*.gz", sc) 
  .keepValidPages()
  .map(r => (r.getCrawlDate, ExtractLinks(r.getUrl, r.getContentString)))
  .flatMap(r => r._2.map(f => (r._1, ExtractDomain(f._1).replaceAll("^\\s*www\\.", ""), ExtractDomain(f._2).replaceAll("^\\s*www\\.", ""))))
  .filter(r => r._2 != "" && r._3 != "")
  .countItems()
  .filter(r => r._2 > 5) 
  .map(r => (r.getCrawlMonth, r.getUrl)) 
  
WriteGDF(canadian_business_grey_literature, "/data/derivatives/gephi/canadian_business_grey_literature.gdf")

val elxn42 = 
  RecordLoader.loadArchives("/data/elxn42/*.gz", sc) 
  .keepValidPages()
  .map(r => (r.getCrawlDate, ExtractLinks(r.getUrl, r.getContentString)))
  .flatMap(r => r._2.map(f => (r._1, ExtractDomain(f._1).replaceAll("^\\s*www\\.", ""), ExtractDomain(f._2).replaceAll("^\\s*www\\.", ""))))
  .filter(r => r._2 != "" && r._3 != "")
  .countItems()
  .filter(r => r._2 > 5) 
  .map(r => (r.getCrawlMonth, r.getUrl)) 
  
WriteGDF(elxn42, "/data/derivatives/gephi/elxn42.gdf")

val energy_environment = 
  RecordLoader.loadArchives("/data/energy_environment/*.gz", sc) 
  .keepValidPages()
  .map(r => (r.getCrawlDate, ExtractLinks(r.getUrl, r.getContentString)))
  .flatMap(r => r._2.map(f => (r._1, ExtractDomain(f._1).replaceAll("^\\s*www\\.", ""), ExtractDomain(f._2).replaceAll("^\\s*www\\.", ""))))
  .filter(r => r._2 != "" && r._3 != "")
  .countItems()
  .filter(r => r._2 > 5) 
  .map(r => (r.getCrawlMonth, r.getUrl)) 
  
WriteGDF(energy_environment, "/data/derivatives/gephi/energy_environment.gdf")

val government_information = 
  RecordLoader.loadArchives("/data/government_information/*.gz", sc) 
  .keepValidPages()
  .map(r => (r.getCrawlDate, ExtractLinks(r.getUrl, r.getContentString)))
  .flatMap(r => r._2.map(f => (r._1, ExtractDomain(f._1).replaceAll("^\\s*www\\.", ""), ExtractDomain(f._2).replaceAll("^\\s*www\\.", ""))))
  .filter(r => r._2 != "" && r._3 != "")
  .countItems()
  .filter(r => r._2 > 5) 
  .map(r => (r.getCrawlMonth, r.getUrl)) 
  
WriteGDF(government_information, "/data/derivatives/gephi/government_information.gdf")

val hcf_alberta_online_encyclopedia = 
  RecordLoader.loadArchives("/data/hcf_alberta_online_encyclopedia/*.gz", sc) 
  .keepValidPages()
  .map(r => (r.getCrawlDate, ExtractLinks(r.getUrl, r.getContentString)))
  .flatMap(r => r._2.map(f => (r._1, ExtractDomain(f._1).replaceAll("^\\s*www\\.", ""), ExtractDomain(f._2).replaceAll("^\\s*www\\.", ""))))
  .filter(r => r._2 != "" && r._3 != "")
  .countItems()
  .filter(r => r._2 > 5) 
  .map(r => (r.getCrawlMonth, r.getUrl)) 
  
WriteGDF(hcf_alberta_online_encyclopedia, "/data/derivatives/gephi/hcf_alberta_online_encyclopedia.gdf")

val health_sciences_grey_literature = 
  RecordLoader.loadArchives("/data/health_sciences_grey_literature/*.gz", sc) 
  .keepValidPages()
  .map(r => (r.getCrawlDate, ExtractLinks(r.getUrl, r.getContentString)))
  .flatMap(r => r._2.map(f => (r._1, ExtractDomain(f._1).replaceAll("^\\s*www\\.", ""), ExtractDomain(f._2).replaceAll("^\\s*www\\.", ""))))
  .filter(r => r._2 != "" && r._3 != "")
  .countItems()
  .filter(r => r._2 > 5) 
  .map(r => (r.getCrawlMonth, r.getUrl)) 
  
WriteGDF(health_sciences_grey_literature, "/data/derivatives/gephi/health_sciences_grey_literature.gdf")

val heritage_community_foundation = 
  RecordLoader.loadArchives("/data/heritage_community_foundation/*.gz", sc) 
  .keepValidPages()
  .map(r => (r.getCrawlDate, ExtractLinks(r.getUrl, r.getContentString)))
  .flatMap(r => r._2.map(f => (r._1, ExtractDomain(f._1).replaceAll("^\\s*www\\.", ""), ExtractDomain(f._2).replaceAll("^\\s*www\\.", ""))))
  .filter(r => r._2 != "" && r._3 != "")
  .countItems()
  .filter(r => r._2 > 5) 
  .map(r => (r.getCrawlMonth, r.getUrl)) 
  
WriteGDF(heritage_community_foundation, "/data/derivatives/gephi/heritage_community_foundation.gdf")

val humanities_computing = 
  RecordLoader.loadArchives("/data/humanities_computing/*.gz", sc) 
  .keepValidPages()
  .map(r => (r.getCrawlDate, ExtractLinks(r.getUrl, r.getContentString)))
  .flatMap(r => r._2.map(f => (r._1, ExtractDomain(f._1).replaceAll("^\\s*www\\.", ""), ExtractDomain(f._2).replaceAll("^\\s*www\\.", ""))))
  .filter(r => r._2 != "" && r._3 != "")
  .countItems()
  .filter(r => r._2 > 5) 
  .map(r => (r.getCrawlMonth, r.getUrl)) 
  
WriteGDF(humanities_computing, "/data/derivatives/gephi/humanities_computing.gdf")

val idle_no_more = 
  RecordLoader.loadArchives("/data/idle_no_more/*.gz", sc) 
  .keepValidPages()
  .map(r => (r.getCrawlDate, ExtractLinks(r.getUrl, r.getContentString)))
  .flatMap(r => r._2.map(f => (r._1, ExtractDomain(f._1).replaceAll("^\\s*www\\.", ""), ExtractDomain(f._2).replaceAll("^\\s*www\\.", ""))))
  .filter(r => r._2 != "" && r._3 != "")
  .countItems()
  .filter(r => r._2 > 5) 
  .map(r => (r.getCrawlMonth, r.getUrl)) 
  
WriteGDF(idle_no_more, "/data/derivatives/gephi/idle_no_more.gdf")

val lfrancophonie_de_louest_canadien = 
  RecordLoader.loadArchives("/data/lfrancophonie_de_louest_canadien/*.gz", sc) 
  .keepValidPages()
  .map(r => (r.getCrawlDate, ExtractLinks(r.getUrl, r.getContentString)))
  .flatMap(r => r._2.map(f => (r._1, ExtractDomain(f._1).replaceAll("^\\s*www\\.", ""), ExtractDomain(f._2).replaceAll("^\\s*www\\.", ""))))
  .filter(r => r._2 != "" && r._3 != "")
  .countItems()
  .filter(r => r._2 > 5) 
  .map(r => (r.getCrawlMonth, r.getUrl)) 
  
WriteGDF(lfrancophonie_de_louest_canadien, "/data/derivatives/gephi/lfrancophonie_de_louest_canadien.gdf")

val ottawa_shooting_october_2014 = 
  RecordLoader.loadArchives("/data/ottawa_shooting_october_2014/*.gz", sc) 
  .keepValidPages()
  .map(r => (r.getCrawlDate, ExtractLinks(r.getUrl, r.getContentString)))
  .flatMap(r => r._2.map(f => (r._1, ExtractDomain(f._1).replaceAll("^\\s*www\\.", ""), ExtractDomain(f._2).replaceAll("^\\s*www\\.", ""))))
  .filter(r => r._2 != "" && r._3 != "")
  .countItems()
  .filter(r => r._2 > 5) 
  .map(r => (r.getCrawlMonth, r.getUrl)) 
  
WriteGDF(ottawa_shooting_october_2014, "/data/derivatives/gephi/ottawa_shooting_october_2014.gdf")

val prarie_provinces = 
  RecordLoader.loadArchives("/data/prarie_provinces/*.gz", sc) 
  .keepValidPages()
  .map(r => (r.getCrawlDate, ExtractLinks(r.getUrl, r.getContentString)))
  .flatMap(r => r._2.map(f => (r._1, ExtractDomain(f._1).replaceAll("^\\s*www\\.", ""), ExtractDomain(f._2).replaceAll("^\\s*www\\.", ""))))
  .filter(r => r._2 != "" && r._3 != "")
  .countItems()
  .filter(r => r._2 > 5) 
  .map(r => (r.getCrawlMonth, r.getUrl)) 
  
WriteGDF(prarie_provinces, "/data/derivatives/gephi/prarie_provinces.gdf")

val university_of_alberta_websites = 
  RecordLoader.loadArchives("/data/university_of_alberta_websites/*.gz", sc) 
  .keepValidPages()
  .map(r => (r.getCrawlDate, ExtractLinks(r.getUrl, r.getContentString)))
  .flatMap(r => r._2.map(f => (r._1, ExtractDomain(f._1).replaceAll("^\\s*www\\.", ""), ExtractDomain(f._2).replaceAll("^\\s*www\\.", ""))))
  .filter(r => r._2 != "" && r._3 != "")
  .countItems()
  .filter(r => r._2 > 5) 
  .map(r => (r.getCrawlMonth, r.getUrl)) 
  
WriteGDF(university_of_alberta_websites, "/data/derivatives/gephi/university_of_alberta_websites.gdf")

val web_archive_general = 
  RecordLoader.loadArchives("/data/web_archive_general/*.gz", sc) 
  .keepValidPages()
  .map(r => (r.getCrawlDate, ExtractLinks(r.getUrl, r.getContentString)))
  .flatMap(r => r._2.map(f => (r._1, ExtractDomain(f._1).replaceAll("^\\s*www\\.", ""), ExtractDomain(f._2).replaceAll("^\\s*www\\.", ""))))
  .filter(r => r._2 != "" && r._3 != "")
  .countItems()
  .filter(r => r._2 > 5) 
  .map(r => (r.getCrawlMonth, r.getUrl)) 
  
WriteGDF(web_archive_general, "/data/derivatives/gephi/web_archive_general.gdf")