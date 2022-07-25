package com.restboardapi.board.controller;

import com.restboardapi.board.dto.BoardDto;
import com.restboardapi.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestBoardApiController {

    @Autowired
    private BoardService boardService;

    //리스트 조회
    @RequestMapping(value="/api/board",method = RequestMethod.GET)
    public List<BoardDto> openBoardList()throws Exception{
        return boardService.selectBoardList();
    }
    //글 쓰기 파입첨부 제외
    @RequestMapping(value = "/api/board/write",method = RequestMethod.POST)
    public void insertBoard(@RequestBody BoardDto board) throws Exception {
        boardService.insertBoard(board,null);
    }

    //글상세
    @RequestMapping(value = "/api/board/{boardIdx}",method = RequestMethod.GET)
    public BoardDto openBoardDetail(@PathVariable("boardIdx") int boardIdx) throws Exception{
        return boardService.selectBoardDetail(boardIdx);
    }

    //글 수정
    @RequestMapping(value = "/api/board/{boardIdx}",method = RequestMethod.PUT)
    public String updateBoard(@RequestBody BoardDto board) throws Exception{
        boardService.updateBoard(board);
        return  "redirect:/board";

    }

    //글삭제
    @RequestMapping(value = "/api/board/{boardIdx}",method = RequestMethod.DELETE)
    @DeleteMapping(value = "/board/{boardIdx}")
    public String deleteBoard(@PathVariable("boardIdx") int boardIdx)throws Exception{
        boardService.deleteBoard(boardIdx);
        return "redirect:/board";
    }
}
