package com.solr.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.json.simple.parser.ParseException;

import com.solr.entity.Board;
import com.solr.service.BoardServiceImpl;

public class ListTest {

	public static void main(String[] args) {
		List<Board> list = new ArrayList<>();

		Board board = new Board();
		for (int i = 0; i < 3; i++) {
			board.setRowNum("row" + i );
			board.setContent("cont" + i);
			
			System.out.println(board.toString());
			list.add(board);
			System.out.println("2" + board.toString());
			System.out.println(list.toString());
		}
		
		System.out.println(list.toString());
	}
	

}
