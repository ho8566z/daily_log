package com.office.calendaradmin.admin;

import com.office.calendaradmin.member.MemberDto;
import com.office.calendaradmin.member.jpa.MemberEntity;
import com.office.calendaradmin.member.jpa.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminService {

    final private MemberRepository memberRepository;


    public Map<String, Object> admins() {
        log.info("admins()");

        Map<String, Object> resultMap = new HashMap<>();

        List<MemberEntity> memberEntities = memberRepository.findAll();

        List<MemberDto> admins = memberEntities.stream()
                .map(MemberEntity::toDto)
                .collect(Collectors.toUnmodifiableList());

        resultMap.put("admins", admins);

        return resultMap;

    }
}
