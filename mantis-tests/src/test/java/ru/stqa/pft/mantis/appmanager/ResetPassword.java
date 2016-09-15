package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.mantis.model.MailMessage;

import java.util.List;

public class ResetPassword extends HelperBase {

  public ResetPassword(ApplicationManager app) {
    super(app);
  }
  public void reset (String username) {
    click(By.xpath("//*[@id=\"manage-menu\"]/ul/li[1]/a"));
    click(By.xpath("//*[@id=\"manage-user-div\"]/table/tbody/tr[6]/td[1]/a"));
    click(By.cssSelector("input[value = 'Сбросить пароль']"));
  }

 public void finishResetPassword (String confirmationLink, String password){
   wd.get(confirmationLink);
   type(By.name("password"), password);
   type(By.name("password_confirm"), password);
   click(By.cssSelector("input[value = 'Изменить учетную запись']"));

 }

}
