//package guru_springframework.mssc_brewery.web.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import guru_springframework.mssc_brewery.web.model.BeerDto;
//import guru_springframework.mssc_brewery.web.model.BeerStyleEnum;
//import guru_springframework.mssc_brewery.web.service.BeerService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.math.BigDecimal;
//import java.util.UUID;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
//
//@ExtendWith(MockitoExtension.class)
//@WebMvcTest(BeerController.class)
//class BeerControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private BeerService beerService;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    private BeerDto beerDto;
//    private UUID beerId;
//
//    @BeforeEach
//    void setUp() {
//        beerId = UUID.randomUUID();
//        beerDto = BeerDto.builder()
//                .id(beerId)
//                .beerName("Test Beer")
//                .beerStyle(BeerStyleEnum.BREEZER)
//                .upc(123456789L)
//                .price(new BigDecimal("100.00"))
//                .build();
//    }
//
//    @Test
//    void getBeer_ShouldReturnBeerDto() throws Exception {
//        when(beerService.getBeerById(beerId)).thenReturn(beerDto);
//
//        mockMvc.perform(get("/api/v1/beer/" + beerId))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(beerId.toString()))
//                .andExpect(jsonPath("$.beerName").value("Test Beer"));
////                .andExpect(jsonPath("$.beerStyle").value("IPA"));
//    }
//
//    @Test
//    void handlePost_ShouldCreateBeer() throws Exception {
//        when(beerService.saveBeer(any(BeerDto.class))).thenReturn(beerDto);
//
//        mockMvc.perform(post("/api/v1/beer")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(beerDto)))
//                .andExpect(status().isCreated())
//                .andExpect(header().exists("Location"));
//    }
//
//    @Test
//    void handleUpdate_ShouldUpdateBeer() throws Exception {
//        doNothing().when(beerService).updateBeer(any(UUID.class), any(BeerDto.class));
//
//        mockMvc.perform(put("/api/v1/beer/" + beerId)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(beerDto)))
//                .andExpect(status().isNoContent());
//    }
//}