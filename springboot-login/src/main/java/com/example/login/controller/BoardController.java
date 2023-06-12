package com.example.login.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.login.mapper.BoardMapper;
import com.example.login.model.Board;
import com.example.login.model.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("board")
@RequiredArgsConstructor
@Controller
public class BoardController {

	private final BoardMapper boardMapper;

//	public BoardController(BoardMapper boardMapper) {
//		this.boardMapper = boardMapper;
//	}

	// 게시판 리스트 페이지 이동
	@GetMapping("list")
	public String boardList(@SessionAttribute(name = "loginMember", required = false) Member loginMember, Model model) {
		log.info("loginMember: {}", loginMember);

//		// 로그인 정보가 없을 경우, 로그인 폼을 리다이렉트
//		if (loginMember == null) {
//			return "redirect:/member/loginForm";
//		}

		List<Board> boards = boardMapper.findAllBoards();

		model.addAttribute("boards", boards);
		return "board/list";
	}

	// 게시글 등록 폼 이동
	@GetMapping("writeForm")
	public String writeForm() {
		return "board/writeForm";
	}

	// 게시글 등록
	@PostMapping("write")
	public String write(@SessionAttribute(name = "loginMember", required = false) Member loginMember,
			@ModelAttribute Board board) {
		log.info("board: {}", board);

		// 로그인 사용자의 아이디 정보를 추가
		board.setId(loginMember.getId());

		// 게시글 저장
		boardMapper.writeBoard(board);

		return "redirect:/board/list";
	}

	// 게시글 읽기 (아이디)
	@GetMapping("readForm/{board_id}")
	public String readForm(@PathVariable long board_id, Model model) {

		Board board = boardMapper.readForm(board_id);
		model.addAttribute("board", board);
		log.info("board: {}", board);
		return "board/readForm";
	}

	// 게시글 수정 폼 이동

	// 게시글 수정

	// 게시글 삭제

}
