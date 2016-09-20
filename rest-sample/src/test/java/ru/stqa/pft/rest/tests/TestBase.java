package ru.stqa.pft.rest.tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;
import ru.stqa.pft.rest.model.Issue;

import java.io.IOException;
import java.util.Objects;
import java.util.Set;

public class TestBase {
  public boolean isIssueOpen(int issueId) throws IOException {
    String json = getExecutor().execute(Request.Get("http://demo.bugify.com/api/issues/" + issueId + ".json"))
        .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    Set<Issue> newIssue = new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
    }.getType());
    String status = newIssue.iterator().next().getState_name();
    if (Objects.equals(status, "close") | Objects.equals(status, "resolved")) {
      return false;
    }
    return true;
  }

  public void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

  private Executor getExecutor() {
    return Executor.newInstance().auth("LSGjeU4yP1X493ud1hNniA==", "");
  }
}
