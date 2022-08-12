import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.message.MessageType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static com.consol.citrus.http.actions.HttpActionBuilder.http;
import static com.consol.citrus.validation.json.JsonPathMessageValidationContext.Builder.jsonPath;


public class CitrusStepsREST {

    @CitrusResource
    private TestCaseRunner runner;

    @Given("запрошена страница списка пользователей {string}")
    public void getSecondUsersPage(String num) {
        runner.given(http()
                .client("reqresClient")
                .send()
                .get("/api/users?page=" + num)
                .message()
                .accept(MediaType.APPLICATION_JSON_VALUE));
    }

    @Then("полученный ответ соответствует файлу {string}")
    public void checkAnswerEqualsFile(String address) {
        runner.then(http()
                .client("reqresClient")
                .receive()
                .response(HttpStatus.OK)
                .message()
                .type(MessageType.JSON)
                .body(new ClassPathResource(address)));
    }

    @Given("запрошен пользователь с идентификатором {string}")
    public void getSecondUser(String id) {
        runner.given(http()
                .client("reqresClient")
                .send()
                .get("/api/users/" + id)
                .message()
                .accept(MediaType.APPLICATION_JSON_VALUE));
    }

    @Then("email-адрес пользователя {string}")
    public void checkEmailEquals(String address) {
        runner.then(http()
                .client("reqresClient")
                .receive()
                .response(HttpStatus.OK)
                .message()
                .type(MessageType.JSON)
                .type(MessageType.JSON)
                .validate(jsonPath().expression("$.data.email", address)));
    }

    @Then("имя пользователя {string}")
    public void checkFirstNameEquals(String address) {
        runner.then(http()
                .client("reqresClient")
                .receive()
                .response(HttpStatus.OK)
                .message()
                .type(MessageType.JSON)
                .type(MessageType.JSON)
                .validate(jsonPath().expression("$.data.first_name", address)));
    }

    @Then("фамилия пользователя {string}")
    public void checkLastNameEquals(String address) {
        runner.then(http()
                .client("reqresClient")
                .receive()
                .response(HttpStatus.OK)
                .message()
                .type(MessageType.JSON)
                .type(MessageType.JSON)
                .validate(jsonPath().expression("$.data.last_name", address)));
    }
}
