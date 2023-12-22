package com.nibble.doyoulike.dto;

import com.nibble.doyoulike.entity.MemberEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MemberDTO {
    private Long memberId;
    private String password;
    private String confirmPassword;
    private String name;
    private String gender;
    private String email;
    private int birthYear;
    private String major;
    private String dormDepartment;
    private String roomType;
    private String lifestyle1;
    private String lifestyle2;
    private String lifestyle3;
    private String sleepHabit1;
    private String sleepHabit2;
    private String sleepHabit3;
    private String specialNote;
    private String status;

    public static MemberDTO toMemberDTO(MemberEntity memberEntity) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberId(memberEntity.getMemberId());
        memberDTO.setPassword(memberEntity.getPassword());
        memberDTO.setGender(memberEntity.getGender().toString());
        memberDTO.setEmail(memberEntity.getEmail());
        memberDTO.setName(memberEntity.getName());
        memberDTO.setBirthYear(memberEntity.getBirthYear());
        memberDTO.setMajor(memberEntity.getMajor().toString());
        return memberDTO;
    }

    public static MemberDTO toUpdateMemberDTO(MemberEntity memberEntity) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberId(memberEntity.getMemberId());
        memberDTO.setPassword(memberEntity.getPassword());
        memberDTO.setGender(memberEntity.getGender().toString());
        memberDTO.setEmail(memberEntity.getEmail());
        memberDTO.setName(memberEntity.getName());
        memberDTO.setBirthYear(memberEntity.getBirthYear());
        memberDTO.setMajor(memberEntity.getMajor().toString());

        memberDTO.setDormDepartment(memberEntity.getDormDepartment() != null ? memberEntity.getDormDepartment().toString() : null);
        memberDTO.setRoomType(memberEntity.getRoomType() != null ? memberEntity.getRoomType().toString() : null);
        memberDTO.setLifestyle1(memberEntity.getLifestyle1() != null ? memberEntity.getLifestyle1().toString() : null);
        memberDTO.setLifestyle2(memberEntity.getLifestyle2() != null ? memberEntity.getLifestyle2().toString() : null);
        memberDTO.setLifestyle3(memberEntity.getLifestyle3() != null ? memberEntity.getLifestyle3().toString() : null);

        memberDTO.setStatus(memberEntity.getStatus() != null ? memberEntity.getStatus() : null);
        memberDTO.setSpecialNote(memberEntity.getSpecialNote() != null ? memberEntity.getSpecialNote() : null);

        if (memberEntity.getSleepHabit1() != null) {
            memberDTO.setSleepHabit1(memberEntity.getSleepHabit1().toString());
        }
        if (memberEntity.getSleepHabit2() != null) {
            memberDTO.setSleepHabit2(memberEntity.getSleepHabit2().toString());
        }
        if (memberEntity.getSleepHabit3() != null) {
            memberDTO.setSleepHabit3(memberEntity.getSleepHabit3().toString());
        }

        return memberDTO;
    }
}