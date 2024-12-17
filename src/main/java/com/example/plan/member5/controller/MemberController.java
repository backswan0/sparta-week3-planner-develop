package com.example.plan.member5.controller;

import com.example.plan.member5.dto.request.*;
import com.example.plan.member5.dto.response.LoginMemberResponseDto;
import com.example.plan.member5.dto.response.MemberResponseDto;
import com.example.plan.member5.service.MemberServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    // 속성
    private final MemberServiceImpl memberService;

    /**
     * 기능
     * CREATE - 사용자 생성, 즉 회원가입
     *
     * @param requestDto : CreateMemberRequestDto
     * @return MemberResponseDto, HttpStatus 201 CREATED
     */
    @PostMapping("/signup")
    public ResponseEntity<MemberResponseDto> signUp(
            @Valid @RequestBody CreateMemberRequestDto requestDto
    ) {
        MemberResponseDto responseDto = memberService.signUp(
                requestDto.getUsername()
                , requestDto.getEmail()
                , requestDto.getPassword()
        );
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    /**
     * 기능
     * POST - 로그인 기능 구현
     *
     * @param requestDto : LoginMemberRequestDto
     * @param request    : HttpServletRequest
     * @return 로그인 성공!
     */
    @PostMapping("/signin")
    public String login(
            @RequestBody LoginMemberRequestDto requestDto
            , HttpServletRequest request
    ) {
        LoginMemberResponseDto responseDto = memberService.login(
                requestDto.getEmail()
                , requestDto.getPassword()
        );

        Long userId = responseDto.getId();

        HttpSession session = request.getSession();

        MemberResponseDto memberResponseDto = memberService.findById(userId);

        session.setAttribute("member", memberResponseDto);

        return "로그인 성공!";
    }

    /**
     * 기능
     * READ - 사용자 목록 조회
     *
     * @return List<MemberResponseDto>, HttpStatus 200 OK
     */
    @GetMapping
    public ResponseEntity<List<MemberResponseDto>> findAll() {
        List<MemberResponseDto> allMembers = new ArrayList<>();

        allMembers = memberService.findAll();

        return new ResponseEntity<>(allMembers, HttpStatus.OK);
    }

    /**
     * 기능
     * READ - 사용자 단건 조회
     *
     * @param id : 조회하려는 작성자의 식별자
     * @return MemberResponseDto, HttpStatus 200 OK
     */
    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> findById(@PathVariable Long id) {
        MemberResponseDto memberResponseDto = memberService.findById(id);

        return new ResponseEntity<>(memberResponseDto, HttpStatus.OK);
    }

    /**
     * 기능
     * UPDATE (PUT) - 사용자 수정
     *
     * @param id         : 수정하려는 사용자의 식별자
     * @param requestDto : UpdateMemberRequestDto
     * @return MemberResponseDto
     */
    @PutMapping("/{id}")
    public ResponseEntity<MemberResponseDto> updateMemberById(
            @PathVariable Long id,
            @RequestBody UpdateMemberRequestDto requestDto
    ) {
        MemberResponseDto responseDto = memberService.updateMember(
                id
                , requestDto.getUsername()
                , requestDto.getEmail()
        );
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     * 기능
     * DELETE - 사용자 단건 삭제
     *
     * @param id : 삭제하려는 사용자의 식별자
     * @return HttpStatus 200 OK
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        memberService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}