package com.nibble.doyoulike.controller;

import com.nibble.doyoulike.dto.MemberDTO;
import com.nibble.doyoulike.service.MemberService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class MemberController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private HttpSession session;


    @GetMapping("/member/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDTO memberDTO, Model model) {
        MemberDTO loginResult = memberService.login(memberDTO);
        if (loginResult != null) {
            session.setAttribute("loginId", loginResult.getMemberId());
            return "redirect:/main";
        } else {
            model.addAttribute("loginError", "Login failed. Your ID or password is incorrect.");
            return "login";
        }
    }

    @GetMapping("/member/signup")
    public String signupForm() {
        return "signup";
    }

    @PostMapping("/member/signup")
    public String signup(@ModelAttribute MemberDTO memberDTO) {
        System.out.println("MemberController.signup");
        System.out.println("memberDTO = " + memberDTO);
        memberService.signup(memberDTO);
        return "login";
    }

    @GetMapping ("/member/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "login";
    }

    @PostMapping("/member/id-check")
    public @ResponseBody String idCheck(@RequestParam Long memberId) {
        System.out.println("memberId = " + memberId);
        String checkResult = memberService.idCheck(memberId);
        if (checkResult != null) {
            return "ok";
        } else {
            return "no";
        }
    }

    @GetMapping("/main")
    public String showMainScreen() {
        if (session.getAttribute("loginId") == null) {
            return "login";
        }
        return "main";
    }

    @GetMapping("/profile")
    public String showProfileScreen(HttpSession session, Model model) {
        if (session.getAttribute("loginId") == null) {
            return "login";
        }
        Long loginId = (Long) session.getAttribute("loginId");

        MemberDTO memberDTO = memberService.findById(loginId);
        model.addAttribute("updateMember", memberDTO);

        int currentYear = Year.now().getValue();
        model.addAttribute("currentYear", currentYear);
        return "profile";
    }

    @PostMapping("/profile")
    public String update(@ModelAttribute MemberDTO memberDTO) {
        if (session.getAttribute("loginId") == null) {
            return "login";
        }
        Long loginId = (Long) session.getAttribute("loginId");

        MemberDTO asMemberDTO = memberService.findById(loginId);

        memberDTO.setMemberId(loginId);
        memberDTO.setPassword(asMemberDTO.getPassword());
        memberDTO.setGender(asMemberDTO.getGender().toString());
        memberDTO.setEmail(asMemberDTO.getEmail());
        memberDTO.setName(asMemberDTO.getName());
        memberDTO.setBirthYear(asMemberDTO.getBirthYear());
        memberDTO.setMajor(asMemberDTO.getMajor().toString());

        memberService.update(memberDTO);

        return "redirect:/profile";
    }

    @GetMapping("/search")
    public String findAll(Model model) {
        if (session.getAttribute("loginId") == null) {
            return "login";
        }
        Long loginId = (Long) session.getAttribute("loginId");
        List<MemberDTO> memberDTOList = memberService.findAll();

        List<MemberDTO> filteredMemberDTOList = memberDTOList.stream()
                .filter(memberDTO -> !memberDTO.getMemberId().equals(loginId))
                .collect(Collectors.toList());

        model.addAttribute("memberList", filteredMemberDTOList);
        return "search";
    }

    @GetMapping("/member/{id}")
    public String findById(@PathVariable Long id, Model model) {
        MemberDTO memberDTO = memberService.findById(id);
        model.addAttribute("member", memberDTO);
        return "detail";
    }

    @PostMapping("/update-password")
    public String updatePassword(
            HttpSession session,
            @RequestParam("currentPassword") String currentPassword,
            @RequestParam("newPassword") String newPassword,
            Model model,
            HttpServletResponse response
    ) {
        if (session.getAttribute("loginId") == null) {
            return "redirect:/login";
        }

        Long loginId = (Long) session.getAttribute("loginId");

        if (memberService.updatePassword(loginId, currentPassword, newPassword)) {
            model.addAttribute("errorMessage", "Password updated successfully");

        } else {
            model.addAttribute("errorMessage",  "Password update failed due to incorrect current password or invalid new password");
        }
        return "updatePassword";
    }



    @GetMapping("/member/delete/{id}")
    public String delete(@PathVariable Long id) {
        memberService.deleteById(id);
        return "redirect:/member/login";
    }

    @GetMapping("/message")
    public String showMessagePage(Model model) {
        if (session.getAttribute("loginId") == null) {
            return "login";
        }
        return "message";
    }
}