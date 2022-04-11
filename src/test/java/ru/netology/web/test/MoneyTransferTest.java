package ru.netology.web.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.TransferPage;

import static com.codeborne.selenide.Selenide.open;

class MoneyTransferTest {

  @Test
  void shouldSuccessTransferMoneyBetweenOwnCards() {
    open("http://localhost:9999");
    var loginPage = new LoginPage();
    var authInfo = DataHelper.getAuthInfo();
    var verificationPage = loginPage.validLogin(authInfo);
    var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
    verificationPage.validVerify(verificationCode);
    var dashboardPage = new DashboardPage();
    int balanceCard1 = dashboardPage.getCardBalance("92df3f1c-a033-48e6-8390-206f6b1f56c0");
    int balanceCard2 = dashboardPage.getCardBalance("0f3f5c2a-249e-4c3d-8287-09f7a039391d");
    dashboardPage.shouldTransferCard1ToCard2();
    var cardInfo = DataHelper.getInfoForTransfer(authInfo);
    var transferPage = new TransferPage();
    transferPage.shouldTransferBetweenCards(cardInfo);
    Assertions.assertEquals(balanceCard1 - 500, dashboardPage.getCardBalance("92df3f1c-a033-48e6-8390-206f6b1f56c0"));
    Assertions.assertEquals(balanceCard2 + 500, dashboardPage.getCardBalance("0f3f5c2a-249e-4c3d-8287-09f7a039391d"));
  }

  @Test
  void shouldFailureTransferMoneyBetweenOwnCards() {
    open("http://localhost:9999");
    var loginPage = new LoginPage();
    var authInfo = DataHelper.getAuthInfo();
    var verificationPage = loginPage.validLogin(authInfo);
    var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
    verificationPage.validVerify(verificationCode);
    var dashboardPage = new DashboardPage();
    int balanceCard1 = dashboardPage.getCardBalance("92df3f1c-a033-48e6-8390-206f6b1f56c0");
    int balanceCard2 = dashboardPage.getCardBalance("0f3f5c2a-249e-4c3d-8287-09f7a039391d");
    dashboardPage.shouldTransferCard1ToCard2();
    var cardInfo = DataHelper.getOtherInfoForTransfer(authInfo);
    var transferPage = new TransferPage();
    transferPage.shouldTransferBetweenCards(cardInfo);
    Assertions.assertEquals(balanceCard1, dashboardPage.getCardBalance("92df3f1c-a033-48e6-8390-206f6b1f56c0"));
    Assertions.assertEquals(balanceCard2, dashboardPage.getCardBalance("0f3f5c2a-249e-4c3d-8287-09f7a039391d"));
  }
}

