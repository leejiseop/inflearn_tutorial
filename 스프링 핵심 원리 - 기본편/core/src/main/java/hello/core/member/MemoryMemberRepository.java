package hello.core.member;

import java.util.HashMap;
import java.util.Map;

<<<<<<< Updated upstream
=======
@Component // -> memoryMemberRepository 빈 생성
>>>>>>> Stashed changes
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
