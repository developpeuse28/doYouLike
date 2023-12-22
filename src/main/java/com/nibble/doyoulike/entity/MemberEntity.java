package com.nibble.doyoulike.entity;

import com.nibble.doyoulike.dto.MemberDTO;
import com.nibble.doyoulike.enums.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "member")
public class MemberEntity {

    @Id
    @Column(nullable = false, unique = true)
    private Long memberId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private int birthYear;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Major major;

    @Column(columnDefinition = "TEXT")
    private String status;

    @Enumerated(EnumType.STRING)
    @Column
    private DormDepartment dormDepartment;

    @Enumerated(EnumType.STRING)
    @Column
    private RoomType roomType;

    @Enumerated(EnumType.STRING)
    @Column
    private Lifestyle lifestyle1;

    @Enumerated(EnumType.STRING)
    @Column
    private Lifestyle lifestyle2;

    @Enumerated(EnumType.STRING)
    @Column
    private Lifestyle lifestyle3;

    @Enumerated(EnumType.STRING)
    @Column
    private SleepHabit sleepHabit1;

    @Enumerated(EnumType.STRING)
    @Column
    private SleepHabit sleepHabit2;

    @Enumerated(EnumType.STRING)
    @Column
    private SleepHabit sleepHabit3;

    @Column(columnDefinition = "TEXT")
    private String specialNote;

    public static MemberEntity toMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberId(memberDTO.getMemberId());
        memberEntity.setPassword(memberDTO.getPassword());
        memberEntity.setGender(Gender.valueOf(memberDTO.getGender()));
        memberEntity.setEmail(memberDTO.getEmail());
        memberEntity.setName(memberDTO.getName());
        memberEntity.setBirthYear(memberDTO.getBirthYear());
        memberEntity.setMajor(Major.valueOf(memberDTO.getMajor()));

        return memberEntity;
    }


    public static MemberEntity toUpdateMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberId(memberDTO.getMemberId());
        memberEntity.setPassword(memberDTO.getPassword());
        memberEntity.setGender(Gender.valueOf(memberDTO.getGender()));
        memberEntity.setEmail(memberDTO.getEmail());
        memberEntity.setName(memberDTO.getName());
        memberEntity.setBirthYear(memberDTO.getBirthYear());
        memberEntity.setMajor(Major.valueOf(memberDTO.getMajor()));
        memberEntity.setStatus(memberDTO.getStatus());
        memberEntity.setDormDepartment(DormDepartment.valueOf(memberDTO.getDormDepartment()));
        memberEntity.setRoomType(RoomType.valueOf(memberDTO.getRoomType()));
        memberEntity.setLifestyle1(Lifestyle.valueOf(memberDTO.getLifestyle1()));
        memberEntity.setLifestyle2(Lifestyle.valueOf(memberDTO.getLifestyle2()));
        memberEntity.setLifestyle3(Lifestyle.valueOf(memberDTO.getLifestyle3()));

        if (memberDTO.getSleepHabit1() != null) {
            memberEntity.setSleepHabit1(SleepHabit.valueOf(memberDTO.getSleepHabit1()));
        }
        if (memberDTO.getSleepHabit2() != null) {
            memberEntity.setSleepHabit2(SleepHabit.valueOf(memberDTO.getSleepHabit2()));
        }
        if (memberDTO.getSleepHabit3() != null) {
            memberEntity.setSleepHabit3(SleepHabit.valueOf(memberDTO.getSleepHabit3()));
        }

        memberEntity.setSpecialNote(memberDTO.getSpecialNote());

        return memberEntity;
    }

}