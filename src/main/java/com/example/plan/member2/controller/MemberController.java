package com.example.plan.member2.controller;


import com.example.plan.member2.dto.request.CreateMemberRequestDto;
import com.example.plan.member2.dto.response.MemberResponseDto;
import com.example.plan.member2.service.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberServiceImpl memberService;

    @PostMapping("/signup")
    public ResponseEntity<MemberResponseDto> signUp(
            @RequestBody CreateMemberRequestDto requestDto
    ) {
        MemberResponseDto responseDto = memberService.signUp(
                requestDto.getUsername(),
                requestDto.getEmail()
        );

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}
