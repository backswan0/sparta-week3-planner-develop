package com.example.plan.member7.controller;

import com.example.plan.member7.dto.request.*;
import com.example.plan.member7.dto.response.*;
import com.example.plan.member7.service.MemberServiceImpl;
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
    private final MemberServiceImpl memberService;

    @PostMapping("/signup")
    public ResponseEntity<MemberResponseDto> signUp(
            @Valid @RequestBody SignUpMemberRequestDto requestDto
    ) {
        MemberResponseDto responseDto = memberService
                .signUp(
                        requestDto.username(),
                        requestDto.email(),
                        requestDto.password()
                );
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public String signIn(
            @Valid @RequestBody SignInMemberRequestDto requestDto
            , HttpServletRequest request
    ) {
        SignInMemberResponseDto dto = memberService
                .signIn(
                        requestDto.email(),
                        requestDto.password()
                );

        Long memberId = dto.id();

        HttpSession session = request.getSession();

        MemberResponseDto responseDto = memberService
                .readMemberById(memberId);

        session.setAttribute("member", responseDto);

        return "로그인 성공!";
    }

    @GetMapping
    public ResponseEntity<List<MemberResponseDto>> readAllMembers() {
        List<MemberResponseDto> memberList = new ArrayList<>();

        memberList = memberService.readAllMembers();

        return new ResponseEntity<>(memberList, HttpStatus.OK);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberResponseDto> readMemberById(
            @PathVariable Long memberId
    ) {
        MemberResponseDto responseDto = memberService
                .readMemberById(memberId);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/{memberId}")
    public ResponseEntity<MemberResponseDto> updateMember(
            @PathVariable Long memberId,
            @Valid @RequestBody UpdateMemberRequestDto requestDto
    ) {
        MemberResponseDto responseDto = memberService
                .updateMember(
                        memberId,
                        requestDto.username(),
                        requestDto.email()
                );
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> deleteMember(
            @PathVariable Long memberId
    ) {
        memberService.deleteMember(memberId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}