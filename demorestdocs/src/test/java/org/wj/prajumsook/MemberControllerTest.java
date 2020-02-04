package org.wj.prajumsook;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.wj.prajumsook.entity.Member;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
@AutoConfigureRestDocs
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberController memberController;

    private Member member;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        member = new Member(0, "first name", "last name");
    }

    @Test
    public void testGetAll() throws Exception {
        List<Member> members = Arrays.asList(
                new Member(0, "first name", "last name"),
                new Member(1, "first name", "last name"),
                new Member(2, "first name", "last name")
        );
        when(memberController.getAllMember()).thenReturn(members);

        mockMvc.perform(get("/member"))
                .andExpect(status().isOk())
                .andDo(document(
                        "get-all-member",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint())
                ));
    }

    @Test
    public void testGetMemberById() throws Exception {
        when(memberController.getMember(any())).thenReturn(member);

        mockMvc.perform(get("/member/"+member.getId()))
                .andExpect(status().isOk())
                .andDo(document(
                        "get-by-id",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())
                ));
    }

    @Test
    public void testSaveMember() throws Exception {
        when(memberController.saveMember(any())).thenReturn(member);

        mockMvc.perform(post("/member").content(objectMapper.writeValueAsString(member)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document(
                        "post",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())
                ));
    }

    @Test
    public void testUpdateMember() throws Exception {
        when(memberController.update(any())).thenReturn(member);

        mockMvc.perform(put("/member").content(objectMapper.writeValueAsString(member)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document(
                        "put",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())
                ));
    }

    @Test
    public void testDeleteMember() throws Exception {
        when(memberController.delete(any())).thenReturn(member);

        mockMvc.perform(delete("/member/"+member.getId()))
                .andExpect(status().isOk())
                .andDo(document(
                        "delete",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())
                ));
    }
}
