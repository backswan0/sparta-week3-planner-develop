package com.example.plan.member3.controller;

import com.example.plan.member3.dto.request.LoginMemberRequestDto;
import com.example.plan.member3.dto.request.CreateMemberRequestDto;
import com.example.plan.member3.dto.request.UpdateMemberRequestDto;
import com.example.plan.member3.dto.response.LoginMemberResponseDto;
import com.example.plan.member3.dto.response.MemberResponseDto;
import com.example.plan.member3.service.MemberServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.bcel.Const;
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

    // 회원 가입 - 나중에 따로 빼자
    /**
     * 기능
     * [1/5] CREATE - 사용자 생성
     *
     * @param requestDto : CreateMemberRequestDto
     * @return MemberResponseDto, HttpStatus 201 CREATED
     */
    @PostMapping("/signup")
    public ResponseEntity<MemberResponseDto> signUp(
            @RequestBody CreateMemberRequestDto requestDto
    ) {
        MemberResponseDto responseDto = memberService.signUp(
                requestDto.getUsername()
                , requestDto.getEmail()
                , requestDto.getPassword()
        );
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    // 인증 관련 - 나중에 따로 빼자
    /*
    쿠키와 세션 중에 세션을 선택한 이유: 보안 문제를 해결하고 싶어서
    쿠키: 중요한 정보를 클라이언트가 갖고 있음
    세션: 중요한 정보를 서버가 갖고 있음
     */
    @PostMapping("/signin")
    public String login(
            @ModelAttribute LoginMemberRequestDto requestDto
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

        return "redirect:/home";
    }

    /**
     * 기능
     * [2/5] READ - 사용자 목록 조회
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
     * [3/5] READ - 사용자 단건 조회
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
     * [4/5] UPDATE (PUT) - 사용자 수정
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
     * [5/5] DELETE - 사용자 단건 삭제
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