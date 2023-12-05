package com.spring3.oauth.jwt.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring3.oauth.jwt.models.MemberLocation;
import com.spring3.oauth.jwt.services.MemberLocationService;

@RestController
@RequestMapping("/api/v1/member-locations")
public class MemberLocationController {
        private final MemberLocationService memberLocationService;

    public MemberLocationController(MemberLocationService memberLocationService) {
        this.memberLocationService = memberLocationService;
    }

    @GetMapping
    public ResponseEntity<List<MemberLocation>> getAllMemberLocations() {
        List<MemberLocation> memberLocations = memberLocationService.getAllMemberLocations();
        return new ResponseEntity<>(memberLocations, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberLocation> getMemberLocationById(@PathVariable Long id) {
        Optional<MemberLocation> memberLocation = memberLocationService.getMemberLocationById(id);
        return memberLocation.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<MemberLocation> saveMemberLocation(@RequestBody MemberLocation memberLocation) {
        MemberLocation savedLocation = memberLocationService.saveMemberLocation(memberLocation);
        return new ResponseEntity<>(savedLocation, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMemberLocation(@PathVariable Long id) {
        memberLocationService.deleteMemberLocation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
