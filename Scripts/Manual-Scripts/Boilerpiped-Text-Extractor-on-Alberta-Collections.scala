import org.warcbase.spark.matchbox.{RemoveHTML, RecordLoader, ExtractBoilerpipeText}
import org.warcbase.spark.rdd.RecordRDD._

RecordLoader.loadArchives("/data/alberta_education_curriculum/*.gz", sc)
  .keepValidPages()
  .map(r => (r.getCrawlMonth, r.getDomain, r.getUrl, ExtractBoilerpipeText(r.getContentString)))
  .saveAsTextFile("/data/derivatives/text/alberta_education_curriculum")

RecordLoader.loadArchives("/data/alberta_floods_2013/*.gz", sc)
  .keepValidPages()
  .map(r => (r.getCrawlMonth, r.getDomain, r.getUrl, ExtractBoilerpipeText(r.getContentString)))
  .saveAsTextFile("/data/derivatives/text/alberta_floods_2013")

RecordLoader.loadArchives("/data/alberta_oil_sands/*.gz", sc)
  .keepValidPages()
  .map(r => (r.getCrawlMonth, r.getDomain, r.getUrl, ExtractBoilerpipeText(r.getContentString)))
  .saveAsTextFile("/data/derivatives/text/alberta_oil_sands")

RecordLoader.loadArchives("/data/canadian_business_grey_literature/*.gz", sc)
  .keepValidPages()
  .map(r => (r.getCrawlMonth, r.getDomain, r.getUrl, ExtractBoilerpipeText(r.getContentString)))
  .saveAsTextFile("/data/derivatives/text/canadian_business_grey_literature")

RecordLoader.loadArchives("/data/elxn42/*.gz", sc)
  .keepValidPages()
  .map(r => (r.getCrawlMonth, r.getDomain, r.getUrl, ExtractBoilerpipeText(r.getContentString)))
  .saveAsTextFile("/data/derivatives/text/elxn42")

RecordLoader.loadArchives("/data/energy_environment/*.gz", sc)
  .keepValidPages()
  .map(r => (r.getCrawlMonth, r.getDomain, r.getUrl, ExtractBoilerpipeText(r.getContentString)))
  .saveAsTextFile("/data/derivatives/text/energy_environment")

RecordLoader.loadArchives("/data/hcf_alberta_online_encyclopedia/*.gz", sc)
  .keepValidPages()
  .map(r => (r.getCrawlMonth, r.getDomain, r.getUrl, ExtractBoilerpipeText(r.getContentString)))
  .saveAsTextFile("/data/derivatives/text/hcf_alberta_online_encyclopedia")

RecordLoader.loadArchives("/data/health_sciences_grey_literature/*.gz", sc)
  .keepValidPages()
  .map(r => (r.getCrawlMonth, r.getDomain, r.getUrl, ExtractBoilerpipeText(r.getContentString)))
  .saveAsTextFile("/data/derivatives/text/health_sciences_grey_literature")

RecordLoader.loadArchives("/data/heritage_community_foundation/*.gz", sc)
  .keepValidPages()
  .map(r => (r.getCrawlMonth, r.getDomain, r.getUrl, ExtractBoilerpipeText(r.getContentString)))
  .saveAsTextFile("/data/derivatives/text/heritage_community_foundation")

RecordLoader.loadArchives("/data/humanities_computing/*.gz", sc)
  .keepValidPages()
  .map(r => (r.getCrawlMonth, r.getDomain, r.getUrl, ExtractBoilerpipeText(r.getContentString)))
  .saveAsTextFile("/data/derivatives/text/humanities_computing")

RecordLoader.loadArchives("/data/lfrancophonie_de_louest_canadien/*.gz", sc)
  .keepValidPages()
  .map(r => (r.getCrawlMonth, r.getDomain, r.getUrl, ExtractBoilerpipeText(r.getContentString)))
  .saveAsTextFile("/data/derivatives/text/lfrancophonie_de_louest_canadien")

RecordLoader.loadArchives("/data/ottawa_shooting_october_2014/*.gz", sc)
  .keepValidPages()
  .map(r => (r.getCrawlMonth, r.getDomain, r.getUrl, ExtractBoilerpipeText(r.getContentString)))
  .saveAsTextFile("/data/derivatives/text/ottawa_shooting_october_2014")

RecordLoader.loadArchives("/data/prarie_provinces/*.gz", sc)
  .keepValidPages()
  .map(r => (r.getCrawlMonth, r.getDomain, r.getUrl, ExtractBoilerpipeText(r.getContentString)))
  .saveAsTextFile("/data/derivatives/text/prarie_provinces")

RecordLoader.loadArchives("/data/web_archive_general/*.gz", sc)
  .keepValidPages()
  .map(r => (r.getCrawlMonth, r.getDomain, r.getUrl, ExtractBoilerpipeText(r.getContentString)))
  .saveAsTextFile("/data/derivatives/text/web_archive_general")