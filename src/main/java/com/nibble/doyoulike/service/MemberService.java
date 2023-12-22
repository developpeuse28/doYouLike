package com.nibble.doyoulike.service;

import com.nibble.doyoulike.dto.MemberDTO;
import com.nibble.doyoulike.entity.MemberEntity;
import com.nibble.doyoulike.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void signup(MemberDTO memberDTO) {
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        memberRepository.save(memberEntity);
    }

    public MemberDTO login(MemberDTO memberDTO) {
        Optional<MemberEntity> byMemberId = memberRepository.findByMemberId(memberDTO.getMemberId());
        if (byMemberId.isPresent()) {
            MemberEntity memberEntity = byMemberId.get();
            if (memberEntity.getPassword().equals(memberDTO.getPassword())) {
                MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
                return dto;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public String idCheck(Long memberId) {
        Optional<MemberEntity> byMemberId = memberRepository.findByMemberId(memberId);
        if (byMemberId.isPresent()) {
            return null;
        } else {
            return "ok";
        }
    }

    public List<MemberDTO> findAll() {
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        List<MemberDTO> memberDTOList = new ArrayList<>();
        for (MemberEntity memberEntity : memberEntityList) {
            memberDTOList.add(MemberDTO.toUpdateMemberDTO(memberEntity));
        }
        return memberDTOList;
    }

    public MemberDTO findById(Long id) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
        if (optionalMemberEntity.isPresent()) {
            return MemberDTO.toUpdateMemberDTO(optionalMemberEntity.get());
        } else {
            return null;
        }
    }

    public MemberDTO updateForm(Long loginId) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(loginId);
        if (optionalMemberEntity.isPresent()) {
            return MemberDTO.toMemberDTO(optionalMemberEntity.get());
        } else {
            return null;
        }
    }

    public void update(MemberDTO memberDTO) {
        memberRepository.save(MemberEntity.toUpdateMemberEntity(memberDTO));
    }

    public boolean updatePassword(Long loginId, String currentPassword, String newPassword) {
        MemberEntity member = memberRepository.findById(loginId)
                .orElseThrow(() -> new EntityNotFoundException("Member not found"));

        if (member.getPassword().equals(currentPassword)) {
            if (isPasswordValid(newPassword)) {
                member.setPassword(newPassword);
                memberRepository.save(member);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean isPasswordValid(String password) {
        if (password.length() < 8) {
            return false;
        }

        String letterRegex = "[a-zA-Z]";
        String numberRegex = "[0-9]";
        String symbolRegex = "[@$!%*#?&]";

        if (!password.matches(".*" + letterRegex + ".*") ||
                !password.matches(".*" + numberRegex + ".*") ||
                !password.matches(".*" + symbolRegex + ".*")) {
            return false;
        }

        return true;
    }

    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }
}