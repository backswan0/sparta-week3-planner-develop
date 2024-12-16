package com.example.plan.member3.controller;

import com.example.plan.member3.dto.LoginMemberRequestDto;
import com.example.plan.member3.dto.request.CreateMemberRequestDto;
import com.example.plan.member3.dto.request.UpdateMemberRequestDto;
import com.example.plan.member3.dto.response.MemberResponseDto;
import com.example.plan.member3.service.MemberServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
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
    @PostMapping("/signin")

    public String login(
            @ModelAttribute LoginMemberRequestDto requestDto
            , HttpServletRequest request
    ) {
        // 이메일 비교

        // 비밀번호 비교

        /*
        세선 생성: 그래야 filter를 통과할 수 있다 (
        session == null이 아니고(&&)
        session.getAttribute("sessionKey")==null이 아니고
        )
         */

        // 클래스를 처음 만들 때에도 어떤 문제를 해결하려고 클래스를 만들었는지 끝까지 잊지 말자...!

        return null;
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