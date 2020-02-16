package com.solr.controller;



import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.solr.entity.Board;
import com.solr.service.BoardService;


@Controller
@RequestMapping("/board/*")
public class BoardController {

	@Autowired
	BoardService boardService; 
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String boardList(Model model) throws Exception {
		List<Board> list = boardService.list();	
		model.addAttribute("list", list);
		return "board/list";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public String boardSearch(Model model, @RequestParam("search") String search,
										   HttpServletRequest req) throws Exception {
		
		String pick = req.getParameter("pick");
	
		List<Board> list = boardService.search(search, pick);
		model.addAttribute("search", list);
		
		return "board/search";
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String boardWrite(Model model) throws Exception {
		return "board/write";
	}
	
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String Write(Board board) throws Exception {
		boardService.write(board);
		 
		return "redirect:/";
	}
	
	
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public String boardRead(@RequestParam("idx") String idx, Model model) throws Exception {
		Board read = boardService.read(idx);
		model.addAttribute("read", read);
		return "board/read";
	}
	
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String boardEdit(@RequestParam("idx") String idx, Model model) throws Exception {
		Board board = boardService.read(idx);
		model.addAttribute("edit", board);
		return "board/edit";
	}
	
	  
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String boardEdit(Board board) throws Exception {
		boardService.edit(board);
		return "redirect:/";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String boardDelete(Board board) throws Exception {
		boardService.delete(board);
		return "redirect:/";
	}
}   
