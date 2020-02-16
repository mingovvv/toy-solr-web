package com.solr.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;


import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.common.SolrDocumentList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.solr.entity.Board;
import com.solr.web.util.solr.SolrProcessor;



@Service
public class BoardServiceImpl implements BoardService {

	private SolrProcessor solrProcessor = new SolrProcessor();
	private JSONParser parse = new JSONParser();
	
	private JsonArray gsonArr = new JsonArray(); //gson
	private JSONArray simpleArr = new JSONArray(); //json
	private JSONObject obj = new JSONObject();
	
	@Override
	public List<Board> list() throws SolrServerException, IOException, ParseException {

		List<Board> list = new ArrayList<>();
		
		SolrQuery q = new SolrQuery();
		q.setQuery("*:*");
		q.setFields("row_num, idx, subject, date");
		q.setRows(100);
		q.setSort("date", ORDER.desc);
		q.setFilterQueries("is_deleted:false");

		gsonArr = solrProcessor.list(q);
		simpleArr = (JSONArray) parse.parse(gsonArr.toString());
		
		for(int i = 0; i<simpleArr.size();i++) {
			Board model = new Board();
			JSONObject obj = (JSONObject) simpleArr.get(i);
			
			if(obj.containsKey("row_num")) {
				model.setRowNum((String) obj.get("row_num"));
			}
			if(obj.containsKey("idx")) {
				JSONArray idx = (JSONArray) obj.get("idx");
				model.setIdx(idx.get(0).toString());
			}
			if(obj.containsKey("subject")) {
				JSONArray subject = (JSONArray) obj.get("subject");
				model.setSubject(subject.get(0).toString());
			}
			if(obj.containsKey("date")) {
				model.setDate((String) obj.get("date"));
			}
			list.add(model); 
		}
		return list;
	}

	@Override
	public void write(Board board) throws SolrServerException, IOException {
		
		//uuid 생성
		board.setIdx(UUID.randomUUID().toString());
		
		//날짜 포맷팅
		SimpleDateFormat sdf = 
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.KOREAN);
		Date date = new Date();
		String d = sdf.format(date);
		board.setDate(d);
		
		solrProcessor.addDoc(board);
		
	}

	@Override
	public Board read(String idx) throws SolrServerException, IOException, ParseException {

		SolrQuery q = new SolrQuery();
		q.setQuery("idx:"+idx);
		
		gsonArr = solrProcessor.read(q);
		simpleArr = (JSONArray) parse.parse(gsonArr.toString());
		
		Board model = new Board();
		
		for(int i = 0; i<1;i++) {
			
			JSONObject obj = (JSONObject) simpleArr.get(0);
		
			if(obj.containsKey("row_num")) {
				model.setRowNum((String) obj.get("row_num"));
			}
			if(obj.containsKey("idx")) {
				JSONArray idxP = (JSONArray) obj.get("idx");
				model.setIdx(idxP.get(0).toString());
			}
			if(obj.containsKey("subject")) {
				JSONArray subject = (JSONArray) obj.get("subject");
				model.setSubject(subject.get(0).toString());
			}
			if(obj.containsKey("content")) {
				JSONArray content = (JSONArray) obj.get("content");
				model.setContent(content.get(0).toString());
			}
			if(obj.containsKey("date")) {
				model.setDate((String) obj.get("date"));
			}
		}
		return model;
	}


	@Override
	public void edit(Board board) throws SolrServerException, IOException {
		solrProcessor.update(board);
	}

	@Override
	public void delete(Board board) throws SolrServerException, IOException {
		board.setDelete(true);
		solrProcessor.delete(board);
	}


	@Override
	public List<Board> search(String search, String pick) throws SolrServerException, IOException, ParseException {
		
		List<Board> list = new ArrayList<>();
		
		SolrQuery q = new SolrQuery();
		
		if(pick!=null) {
		
			//pick 분류
			try {
				String[] array = pick.split(",");
				q.setQuery(array[0]+":*"+search+"* "+array[1]+":*"+search+"*");
			}catch(Exception e) {
				q.setQuery(pick+":*"+search+"*");
			}
		
		}else{
			q.setQuery("*:*");
		}
			
		
		q.setSort("date", ORDER.desc);
		q.setRows(100);
		
		gsonArr = solrProcessor.search(q);
		simpleArr = (JSONArray) parse.parse(gsonArr.toString());
		
		for(int i = 0; i<simpleArr.size();i++) {
			Board model = new Board();
			JSONObject obj = (JSONObject) simpleArr.get(i);
			
			if(obj.containsKey("row_num")) {
				model.setRowNum((String) obj.get("row_num"));
			}
			if(obj.containsKey("idx")) {
				JSONArray idx = (JSONArray) obj.get("idx");
				model.setIdx(idx.get(0).toString());
			}
			if(obj.containsKey("subject")) {
				JSONArray subject = (JSONArray) obj.get("subject");
				model.setSubject(subject.get(0).toString());
			}
			if(obj.containsKey("date")) {
				model.setDate((String) obj.get("date"));
			}
			list.add(model); 
		}
		
		
		
		return list;
	}
}
