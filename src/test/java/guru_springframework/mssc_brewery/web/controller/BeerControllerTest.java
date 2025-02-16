package guru_springframework.mssc_brewery.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru_springframework.mssc_brewery.web.model.BeerDto;
import guru_springframework.mssc_brewery.web.model.BeerStyleEnum;
import guru_springframework.mssc_brewery.web.service.BeerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(BeerController.class)
@AutoConfigureRestDocs(uriScheme = "https", uriPort = 80, uriHost = "INLSW9E8C0.icepor.com")
@ExtendWith(RestDocumentationExtension.class)
class BeerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BeerService beerService;

    @Autowired
    private ObjectMapper objectMapper;

    private BeerDto beerDto;
    private UUID beerId;

    @BeforeEach
    void setUp() {
        beerId = UUID.randomUUID();
        beerDto = BeerDto.builder()
//                .id(beerId)
                .beerName("Test Beer")
                .beerStyle(BeerStyleEnum.BREEZER)
                .upc(123456789L)
                .price(new BigDecimal("100.00"))
                .build();
    }

    @Test
    void getBeer_ShouldReturnBeerDto() throws Exception {
        when(beerService.getBeerById(beerId)).thenReturn(beerDto);

        mockMvc.perform(get("/api/v1/beer/{beerId}", beerId).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(beerId.toString()))
//                .andExpect(jsonPath("$.beerName").value("Test Beer")
        .andDo(document("v1/beer-get", pathParameters(
                parameterWithName("beerId").description("UUID of desired beer to get")
        )
//                , requestParameters(parameterWithName("isCold").description("Is beer cold query param"))
        , responseFields(fieldWithPath("id").description("id to the beer created")
                , fieldWithPath("version").description("version of the beer")
                        , fieldWithPath("createdDate").description("when it is created in DB")
                        , fieldWithPath("lastModifiedDate").description("when it is updated in DB")
                        , fieldWithPath("beerName").description("name of the beer")
                        , fieldWithPath("beerStyle").description("style of the beer")
                        , fieldWithPath("upc").description("a long unique id for beer")
                        , fieldWithPath("price").description("price of the beer")
                        , fieldWithPath("quantiryOnHand").description("these many beers left in stock")
                )));
//                .andExpect(jsonPath("$.beerStyle").value("IPA"));
    }

    @Test
    void handlePost_ShouldCreateBeer() throws Exception {
        when(beerService.saveBeer(any(BeerDto.class))).thenReturn(beerDto);

        ConstrainedFields fields = new ConstrainedFields(BeerDto.class);
        mockMvc.perform(post("/api/v1/beer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(beerDto)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andDo(MockMvcRestDocumentation.document("v1/beer-new",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())
                , requestFields(fields.withPath("id").ignored()
                                , fields.withPath("version").ignored()
                                , fields.withPath("createdDate").ignored()
                                , fields.withPath("lastModifiedDate").ignored()
                                , fields.withPath("beerName").description("name of the beer")
                                , fields.withPath("beerStyle").description("style of the beer")
                                , fields.withPath("upc").description("a long unique id for beer")
                                , fields.withPath("price").description("price of the beer")
                                , fields.withPath("quantiryOnHand").description("these many beers left in stock"))));;
    }

    @Test
    void handleUpdate_ShouldUpdateBeer() throws Exception {
        doNothing().when(beerService).updateBeer(any(UUID.class), any(BeerDto.class));

        mockMvc.perform(put("/api/v1/beer/" + beerId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(beerDto)))
                .andExpect(status().isNoContent());
    }

    private static class ConstrainedFields {

        private final ConstraintDescriptions constraintDescriptions;

        ConstrainedFields(Class<?> input) {
            this.constraintDescriptions = new ConstraintDescriptions(input);
        }

        private FieldDescriptor withPath(String path) {
            return fieldWithPath(path).attributes(key("constraints").value(StringUtils
                    .collectionToDelimitedString(this.constraintDescriptions
                            .descriptionsForProperty(path), ". ")));
        }
    }
}