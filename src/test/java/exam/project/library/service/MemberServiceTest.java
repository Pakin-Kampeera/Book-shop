package exam.project.library.service;

import exam.project.library.model.Member;
import exam.project.library.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class MemberServiceTest {

    @Mock
    JdbcTemplate jdbcTemplate;

    MemberRepository memberRepository;

    Member member1, member2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.memberRepository = new MemberRepository(jdbcTemplate);

        member1 = new Member()
                .setMemberId(1L)
                .setFirstName("Steve")
                .setLastName("Jobs")
                .setTelephone("9834757936");

        member2 = new Member()
                .setMemberId(2L)
                .setFirstName("Tim")
                .setLastName("Cook")
                .setTelephone("8983498739");
    }

    @Test
    void getAllMember() {
        List<Member> memberSet = new ArrayList<>();
        memberSet.add(member1);
        memberSet.add(member2);

        when(jdbcTemplate.query(anyString(), any(ResultSetExtractor.class))).thenReturn(memberSet);

        List<Member> members = memberRepository.getAllMember();
        assertEquals(2, members.size());
        verify(jdbcTemplate, times(1)).query(anyString(), any(ResultSetExtractor.class));
    }

    @Test
    void getMemberById() {
        List<Member> memberSet = new ArrayList<>();
        memberSet.add(member1);

        when(jdbcTemplate.query(anyString(), any(ResultSetExtractor.class), anyLong())).thenReturn(memberSet);

        List<Member> members = memberRepository.getMemberById(1L);
        assertEquals(1, members.size());
        verify(jdbcTemplate, times(1)).query(anyString(), any(ResultSetExtractor.class), anyLong());
    }

    @Test
    void saveNewMember() {
        when(jdbcTemplate.update(anyString(), anyString(), anyString(), anyString())).thenReturn(1);
        memberRepository.saveNewMember(member1);
        verify(jdbcTemplate, times(1)).update(anyString(), anyString(), anyString(), anyString());
    }

    @Test
    void updateMember() {
        when(jdbcTemplate.update(anyString(), anyString(), anyString(), anyString(), anyLong())).thenReturn(1);
        memberRepository.updateMember(1L, member1);
        verify(jdbcTemplate, times(1)).update(anyString(), anyString(), anyString(), anyString(), anyLong());
    }

    @Test
    void deleteMember() {
        when(jdbcTemplate.update(anyString(), anyLong())).thenReturn(1);
        memberRepository.deleteMember(1L);
        verify(jdbcTemplate, times(1)).update(anyString(), anyLong());
    }
}