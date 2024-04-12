package com.algawords.algafoods;

import com.algawords.algafoods.domain.modelo.Cozinha;
import com.algawords.algafoods.domain.repository.CozinhaRepository;
import com.algawords.algafoods.util.DatabaseCleaner;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import static org.hamcrest.Matchers.equalTo;

@TestPropertySource("/applicationTest.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CadastroCozinhaIT {

    @LocalServerPort
    private int port;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @BeforeEach
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.basePath = "/cozinhas";
        RestAssured.port = port;

        databaseCleaner.clearTables();
        preparaDados();
    }

    @Test
    public void deveRetornarStatus200_QuandoConsultaCozinha() {

        RestAssured.given()
                .accept(ContentType.JSON)
        .when()
                .get()
        .then()
                .statusCode(200);
    }

    @Test
    public void deveConter2Cozinhas_QuandoConsultaCozinha() {
        RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .body("", Matchers.hasSize(2))
                .body("nome", Matchers.hasItems("Americana", "Tailandesa"));
    }

    @Test
    public void testRetornarStatus201_QuandoCadastrarCozinha() {
        RestAssured.given()
                .body("{ \"nome\" : \"Chinesa\" }")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void deveRetornarRespostaEStatusCorreto_QuandoConsultaCozinhaExistente() {

        RestAssured.given()
                .pathParam("cozinhaId", 2)
                .accept(ContentType.JSON)
                .when()
                .get("/{cozinhaId}")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("nome", equalTo("Americana"));
    }

    @Test
    public void deveRetornarStatus404_QuandoConsultaCozinhaEnexistente() {

        RestAssured.given()
                .pathParam("cozinhaId", 100)
                .accept(ContentType.JSON)
                .when()
                .get("/{cozinhaId}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());

    }


    private void preparaDados() {
        Cozinha cozinha = new Cozinha();
        cozinha.setNome("Tailandesa");
        cozinhaRepository.save(cozinha);

        Cozinha cozinha1 = new Cozinha();
        cozinha1.setNome("Americana");
        cozinhaRepository.save(cozinha1);
    }
}
