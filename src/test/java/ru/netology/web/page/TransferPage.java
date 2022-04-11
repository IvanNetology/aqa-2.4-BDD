package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement amount = $("[data-test-id=amount] input");
    private SelenideElement from = $("[data-test-id=from] input");
    private SelenideElement transferButton = $("[data-test-id=action-transfer]");

    public DashboardPage shouldTransferBetweenCards(DataHelper.CardInfo cardInfo) {
        amount.setValue(cardInfo.getSumma());
        from.setValue(cardInfo.getNumber());
        transferButton.click();
        return new DashboardPage();
    }
}
