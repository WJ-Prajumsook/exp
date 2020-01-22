package org.wj.prajumsook.reservation.frontend.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockReset;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.wj.prajumsook.reservation.frontend.ReservationApplication;
import org.wj.prajumsook.reservation.persistence.Room;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ReservationApplication.class)
@WebAppConfiguration
public class RoomControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    private String mapToJson(Object object) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

    private <T> T mapFromJson(String json, Class<T> klass)throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, klass);
    }

    @Test
    public void getRooms() throws Exception {
        String uri = "/rooms";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        Room[] roomsArray = mapFromJson(content, Room[].class);
        assertTrue(roomsArray.length > 0);
    }

    @Test
    public void addRoom() throws Exception {
        String uri = "/rooms";
        Room room = new Room();
        room.setName("Test single room");
        room.setDescription("Test single room description");
        String json = mapToJson(room);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).content(json)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        System.out.println("HTTP STATUS: " + status);
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Room returnRoom = mapFromJson(content, Room.class);
        assertEquals("Test single room", returnRoom.getName());
        System.out.println(returnRoom);
    }

    @Test
    public void updateRoom() throws Exception {
        String uri = "/rooms";
        Room room = new Room();
        room.setId(Long.parseLong("10"));
        room.setName("Test update single room");
        room.setDescription("Test update single room description");
        String input = mapToJson(room);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON).content(input)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        Room r = mapFromJson(content, Room.class);
        assertEquals("Test update single room", r.getName());

        System.out.println(r);
    }

    @Test
    public void deleteRoom() throws Exception {
        String uri = "/rooms/100";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(404, status);

        String content = mvcResult.getResponse().getContentAsString();
        assertEquals("{\"errorId\":404,\"errorMessage\":\"Error deleting room with id=100\",\"errorDescription\":\"Error description here.\"}", content);
    }
}
