package com.solr.web.util.solr;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;

public class SolrConnection {
	
	public static String url = "http://172.16.100.128:8983/solr/board_mgjang";
	public static SolrClient solr = new HttpSolrClient.Builder(url).build();

}
