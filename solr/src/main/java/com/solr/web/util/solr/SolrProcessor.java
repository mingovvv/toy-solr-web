package com.solr.web.util.solr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.solr.entity.Board;

import antlr.collections.List;

public class SolrProcessor {
	
	private final static SolrConnection solrConnection = new SolrConnection();
	private JsonArray gsonArr = new JsonArray();
	private Gson gson = new Gson();
	
	public void addDoc(Board board) throws SolrServerException, IOException {
		
		SolrInputDocument solrDoc = new SolrInputDocument();
		

		//최신 글 row_num 가져오기
		SolrQuery query = new SolrQuery();
		
		query.setQuery("*:*");
		query.setFields("row_num");
		query.setSort("date", ORDER.desc);
		query.setRows(1);
		QueryResponse rsp = solrConnection.solr.query(query);
		SolrDocumentList docs=rsp.getResults();
		String num = docs.toString();
		 
		try {
			int s = num.indexOf("row_num=");   
			int e = num.indexOf("}"); 
			
			String w = num.substring(s+8,e);
			int g = Integer.parseInt(w);
			g++;  
			solrDoc.addField("row_num", g);
		}catch (Exception e) {
			solrDoc.addField("row_num", "1");
		}
			
		solrDoc.addField("id", board.getIdx());
		solrDoc.addField("idx", board.getIdx());
		solrDoc.addField("subject", board.getSubject());
		solrDoc.addField("content", board.getContent());
		solrDoc.addField("date", board.getDate());
		solrDoc.addField("is_deleted", board.isDelete());

		Collection<SolrInputDocument> solrDocs = new ArrayList<SolrInputDocument>();
		solrDocs.add(solrDoc);

		solrConnection.solr.add(solrDocs);
		solrConnection.solr.commit();

	}

	public void update(Board board) throws SolrServerException, IOException {
		
		//update
		SolrInputDocument solrDoc = new SolrInputDocument();

		Map<String,Object> subjectModifier = new HashMap<>();
		Map<String,Object> contentModifier = new HashMap<>();
		
		subjectModifier.put("set", board.getSubject());
		contentModifier.put("set", board.getContent());
		
		solrDoc.addField("id", board.getIdx());
		solrDoc.addField("subject", subjectModifier);
		solrDoc.addField("content", contentModifier);
		
		
		Collection<SolrInputDocument> solrDocs = new ArrayList<SolrInputDocument>();
		solrDocs.add(solrDoc);
		
		solrConnection.solr.add(solrDocs);
		solrConnection.solr.commit();
	}

	public void delete(Board board) throws SolrServerException, IOException {
		
		//delete
		SolrInputDocument solrDoc = new SolrInputDocument();
		
		Map<String,Object> isDeletedModifier = new HashMap<>();
		
		isDeletedModifier.put("set", board.isDelete());

		solrDoc.addField("id", board.getIdx());
		solrDoc.addField("is_deleted", isDeletedModifier);
		
		Collection<SolrInputDocument> solrDocs = new ArrayList<SolrInputDocument>();
		solrDocs.add(solrDoc);
		
		solrConnection.solr.add(solrDocs);
		solrConnection.solr.commit();
		
		//data 완전 삭제
		//solrConnection.solr.deleteById(idx);
		//solrConnection.solr.commit();
		
	}
	
	public JsonArray list(SolrQuery q) throws SolrServerException, IOException {
		
		QueryResponse rsp = solrConnection.solr.query(q);
        SolrDocumentList docs = rsp.getResults();
        
        System.out.println("SolrDocumentList 형식 :"+docs);
 
        //json -> gsonArray
        JsonElement elements = gson.toJsonTree(docs);
        System.out.println("JsonElement 형식 :"+docs);
        
        gsonArr = elements.getAsJsonArray();
        System.out.println("JSONArray 형식 :"+docs);
        
		return gsonArr;
	}

	public JsonArray read(SolrQuery q) throws SolrServerException, IOException {
		
		QueryResponse rsp = solrConnection.solr.query(q);
		SolrDocumentList docs = rsp.getResults();
		
		JsonElement elements = gson.toJsonTree(docs);
		gsonArr = elements.getAsJsonArray();
		return gsonArr;
	}

	public JsonArray search(SolrQuery q) throws SolrServerException, IOException {
		
		QueryResponse rsp = solrConnection.solr.query(q);
        SolrDocumentList docs = rsp.getResults();
        
        JsonElement elements = gson.toJsonTree(docs);
        gsonArr = elements.getAsJsonArray();
        
		return gsonArr;
	}
}
