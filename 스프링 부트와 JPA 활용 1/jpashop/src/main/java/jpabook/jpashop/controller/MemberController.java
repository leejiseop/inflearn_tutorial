package jpabook.jpashop.controller;

import jakarta.validation.Valid;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
        // members/createMemberForm.html 에 이동할 때
        // memberForm 이라는
        // 빈 껍데기 멤버 객체 new MemberForm() 를 가지고 간다
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result) {
        // 서버사이드에서 validation 하고,
        // 오류 발생 시 BindingResult 에 에러가 담긴다 (코드는 정상 실행)

        if (result.hasErrors()) {
            return "members/createMemberForm";
        }

        // 실무에서 엔티티로 데이터를 그대로 바인딩 받기에는
        // validation 추가하고 점점 그러면 엔티티가 점점 지저분해진다...
        // 그래서 실무에서는 form 으로 받아서 넘기는 것
        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());
        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);

        memberService.join(member);
        return "redirect:/"; // 저장 뒤에는 리다이렉트를 한다. 재로딩 하면 안됨
    }

    @GetMapping("/members")
    public String list(Model model) {
        // 사실 이거도 memberform (dto) 를 사용해서 뿌려줘야 한다
        // api 를 만들 때는 절대로 엔티티를 웹으로 반환하면 안된다
        // api 라는것은 스펙.
        // 엔티티에 뭐 추가하면 api 스펙 또 변경해야하고.. 비밀번호도 노출될 수 있고..
        // 경우에 따라 다르긴 한데 일단은 이렇게 알고있을 것
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "/members/memberList";
    }
}





