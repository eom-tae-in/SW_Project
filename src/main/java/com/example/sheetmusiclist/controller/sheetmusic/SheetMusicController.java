package com.example.sheetmusiclist.controller.sheetmusic;


import com.amazonaws.services.s3.AmazonS3;
import com.example.sheetmusiclist.dto.sheetmusic.SheetMusicCreateRequestDto;
import com.example.sheetmusiclist.dto.sheetmusic.SheetMusicEditRequestDto;
import com.example.sheetmusiclist.dto.sheetmusic.SheetMusicSearchRequestDto;
import com.example.sheetmusiclist.entity.member.Member;
import com.example.sheetmusiclist.exception.MemberNotFoundException;
import com.example.sheetmusiclist.repository.member.MemberRepository;
import com.example.sheetmusiclist.response.Response;
import com.example.sheetmusiclist.service.S3Service;
import com.example.sheetmusiclist.service.sheetmusic.SheetMusicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@Api(value = "SheetMusic Controller",tags = "SheetMusic")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class SheetMusicController {

    private final SheetMusicService sheetMusicService;
    private final MemberRepository memberRepository;

    private final S3Service s3Service;

    // 악보 등록
    @ApiOperation(value = "악보 등록", notes = "악보를 등록한다.")
    @PostMapping("/sheetmusics")
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@Valid @ModelAttribute SheetMusicCreateRequestDto sheetMusicCreateRequestDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member member = memberRepository.findByUsername(authentication.getName()).orElseThrow(MemberNotFoundException::new);

        sheetMusicService.createSheetMusic(sheetMusicCreateRequestDto, member);

        return Response.success("악보 등록 완료");
    }

    // 악보 전체 조회
    @ApiOperation(value = "악보 전체 조회", notes = "전체 악보를 조회한다.")
    @GetMapping("/sheetmusics")
    @ResponseStatus(HttpStatus.OK)
    public Response findAll(@PageableDefault(size = 10,sort = "id",direction = Sort.Direction.DESC)Pageable pageable) {

        return Response.success(sheetMusicService.findAllSheetMusic(pageable));
    }

    // 악보 단건 조회
    @ApiOperation(value = "악보 단건 조회", notes = "악보를 등록한다.")
    @GetMapping("/sheetmusics/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response find(@ApiParam(value = "악보 id",required = true)
            @PathVariable("id") Long id) {

        return Response.success(sheetMusicService.findSheetMusic(id));
    }


    //이게찐이다 다운로드주소.
    @GetMapping("/sheetmusics/download/{fileName}")
    public ResponseEntity<byte[]> download(@PathVariable String fileName) throws IOException {
        return s3Service.getObject(fileName);
    }

    //악보 제목으로 검색
    @ApiOperation(value = "악보 제목으로 검색 하기", notes = "악보 제목으로 검색하기.")
    @GetMapping("/sheetmusics/title/{title}")
    @ResponseStatus(HttpStatus.OK)
    public Response searchTitle(@PageableDefault(size = 10,sort = "id",direction = Sort.Direction.DESC)Pageable pageable,
            @PathVariable ("title") String title){
        return Response.success(sheetMusicService.searchTitleSheetMusic(pageable,title));
    }

    //악보 작곡가로 검색 하기
    @ApiOperation(value = "악보 작곡가로 검색 하기", notes = "악보 작곡가로 검색하기.")
    @GetMapping("/sheetmusics/writer/{writer}")
    @ResponseStatus(HttpStatus.OK)
    public Response searchWriter(@PageableDefault(size = 10,sort = "id",direction = Sort.Direction.DESC) Pageable pageable,
            @PathVariable("writer") String writer){
        return Response.success(sheetMusicService.searchWriterSheetMusic(pageable,writer));
    }


    // 악보 수정
    @ApiOperation(value = "악보 수정", notes = "악보를 수정한다.")
    @PutMapping("/sheetmusics/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response edit(@ApiParam(value = "악보 id",required = true)
            @PathVariable("id") Long id, @Valid @ModelAttribute SheetMusicEditRequestDto sheetMusicEditRequestDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member member = memberRepository.findByUsername(authentication.getName()).orElseThrow(MemberNotFoundException::new);

        sheetMusicService.editSheetMusic(id, member, sheetMusicEditRequestDto);

        return Response.success("악보 수정 완료");
    }

    // 악보 삭제
    @ApiOperation(value = "악보 삭제", notes = "악보를 삭제한다.")
    @DeleteMapping("/sheetmusics/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response delete(@ApiParam(value = "악보 id",required = true)
            @PathVariable("id") Long id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member member = memberRepository.findByUsername(authentication.getName()).orElseThrow(MemberNotFoundException::new);

        sheetMusicService.deleteSheetMusic(id, member);

        return Response.success("악보 삭제 완료");
    }
}

