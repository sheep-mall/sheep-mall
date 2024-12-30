package com.sheep.mall.member.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/member")
@Slf4j
public class MemberController {
    
    @GetMapping("/v1/find-by-pk/{pk}")
    public ResponseEntity<?> findByPk(@PathVariable long pk){
        log.info("[MemberController][findByPk][request pk = {}]",pk);
        return ResponseEntity.ok("hihihi");
    }
}
