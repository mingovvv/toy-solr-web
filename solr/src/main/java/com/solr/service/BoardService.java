package com.solr.service;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.json.simple.parser.ParseException;

import com.solr.entity.Board;

public interface BoardService {

	List<Board> list() throws SolrServerException, IOException, ParseException;

	void write(Board board) throws SolrServerException, IOException, ParseException;

	Board read(String idx) throws SolrServerException, IOException, ParseException;

	void edit(Board board) throws SolrServerException, IOException;

	void delete(Board board) throws SolrServerException, IOException;

	List<Board> search(String search, String pick) throws SolrServerException, IOException, ParseException;
}
